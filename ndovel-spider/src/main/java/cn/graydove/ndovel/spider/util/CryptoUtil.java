package cn.graydove.ndovel.spider.util;

import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.spider.config.Tenant;
import cn.graydove.ndovel.spider.config.TenantManager;
import cn.graydove.ndovel.spider.model.CryptoData;
import cn.graydove.ndovel.spider.model.dto.BroadCastDTO;
import cn.graydove.ndovel.spider.model.dto.NovelPutDTO;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author graydove
 */
@Slf4j
public class CryptoUtil {
    public static BroadCastDTO encode(NovelPutDTO message, String senderUid, String sourceUid, RSA rsa) {
        CryptoData<NovelPutDTO> cryptoData = new CryptoData<>();
        cryptoData.setSenderUid(senderUid);
        cryptoData.setSourceUid(sourceUid);
        cryptoData.setData(message);
        BroadCastDTO broadCastDTO = new BroadCastDTO();
        broadCastDTO.setSenderUid(senderUid);
        broadCastDTO.setCryptoData(rsa.encryptBase64(JSONUtil.toJsonStr(message), KeyType.PrivateKey));

        return broadCastDTO;
    }

    public static CryptoData decode(BroadCastDTO broadCastDTO, TenantManager tenantManager) {
        String uid = broadCastDTO.getSenderUid();
        Tenant tenant = tenantManager.findTenant(uid);
        if (tenant == null) {
            return null;
        }
        CryptoData cryptoData;
        try {
            RSA rsa = SecureUtil.rsa(null, tenant.getPublicKey());
            String s = rsa.decryptStr(broadCastDTO.getCryptoData(), KeyType.PublicKey);
            cryptoData = JSONUtil.toBean(s, CryptoData.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        if (StrUtil.equals(cryptoData.getSenderUid(), uid)) {
            return cryptoData;
        }
        return null;
    }
}
