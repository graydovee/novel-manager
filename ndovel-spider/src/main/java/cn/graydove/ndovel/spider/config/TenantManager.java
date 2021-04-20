package cn.graydove.ndovel.spider.config;

import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.spider.model.dto.RegisterDTO;
import cn.graydove.ndovel.spider.model.vo.TenantVO;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author graydove
 */
@Slf4j
@Component
public class TenantManager {

    private final static String PARENT_REFRESH = "ndovel:publish:parent:";

    private SpiderProperties spiderProperties;

    private RestTemplate restTemplate;

    private StringRedisTemplate stringRedisTemplate;

    private Tenant tenant;

    private RSA rsa;

    public TenantManager(SpiderProperties spiderProperties, RestTemplate restTemplate, StringRedisTemplate stringRedisTemplate) {
        this.spiderProperties = spiderProperties;
        this.stringRedisTemplate = stringRedisTemplate;
        this.restTemplate = restTemplate;
        this.tenant = new Tenant();
        this.tenant.setUid(spiderProperties.getUid());
        this.tenant.setPublicKey(spiderProperties.getPublicKey());
        this.tenant.setPublishAddress(spiderProperties.getPublishAddress());
        this.rsa = SecureUtil.rsa(spiderProperties.getPrivateKey(), spiderProperties.getPublicKey());
    }

    public void registerParent(TenantVO tenantVO) {
        Tenant tenant = new Tenant();
        tenant.setUid(tenantVO.getUid());
        tenant.setPublicKey(tenantVO.getPublicKey());
        tenant.setPublishAddress(tenantVO.getPublishAddress());
        this.tenant.getParents().put(tenant.getUid(), tenant);
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void register(RegisterDTO registerDTO) {
        if (StrUtil.isBlank(spiderProperties.getToken()) || !StrUtil.equals(registerDTO.getToken(), spiderProperties.getToken())) {
            throw new TaskException("无效token");
        }
        Tenant tenant = new Tenant();
        tenant.setUid(registerDTO.getUid());
        tenant.setPublicKey(registerDTO.getPublicKey());
        tenant.setPublishAddress(registerDTO.getPublishAddress());
        tenant.getParents().put(this.tenant.getUid(), this.tenant);

        Map<String, Tenant> children = tenant.getChildren();
        children.put(tenant.getUid(), tenant);
    }

    public TenantVO getSelfInfo() {
        TenantVO tenantVO = new TenantVO();
        tenantVO.setUid(tenantVO.getUid());
        tenantVO.setPublicKey(tenantVO.getPublicKey());
        tenantVO.setPublishAddress(tenantVO.getPublishAddress());
        return tenantVO;
    }


    public Tenant findTenant(String uid) {
        if (StrUtil.equals(this.tenant.getUid(), uid)) {
            return this.tenant;
        }
        Tenant tenant = this.tenant.getChildren().get(uid);
        if (null != tenant) {
            return tenant;
        }
        return this.tenant.getParents().get(uid);
    }

    public RSA getRsa() {
        return rsa;
    }

    public void refreshParent() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUid(spiderProperties.getUid());
        registerDTO.setPublicKey(spiderProperties.getPublicKey());
        registerDTO.setPublishAddress(spiderProperties.getPublishAddress());

        for (SpiderProperties.Invitor invitor : spiderProperties.getInvitor()) {
            Boolean bool = stringRedisTemplate.opsForValue().setIfAbsent(PARENT_REFRESH + invitor.getRegisterAddress(), "true", 5, TimeUnit.MINUTES);
            if (!Boolean.TRUE.equals(bool)) {
                continue;
            }
            String inviteRegisterAddress = invitor.getRegisterAddress();
            registerDTO.setToken(invitor.getToken());
            if (StrUtil.isNotBlank(inviteRegisterAddress)) {
                TenantVO tenantVO = null;
                try {
                    tenantVO = restTemplate.postForObject(inviteRegisterAddress, registerDTO, TenantVO.class);
                } catch (Throwable e) {
                    log.error("register parent error: " + e.getMessage(), e);
                }
                if (null != tenantVO) {
                    registerParent(tenantVO);
                }
            }
        }
    }
}
