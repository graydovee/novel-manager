package cn.graydove.ndovel.spider.controller;

import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.spider.config.TenantManager;
import cn.graydove.ndovel.spider.model.CryptoData;
import cn.graydove.ndovel.spider.model.dto.BroadCastDTO;
import cn.graydove.ndovel.spider.model.dto.NovelPutDTO;
import cn.graydove.ndovel.spider.model.dto.RegisterDTO;
import cn.graydove.ndovel.spider.model.vo.TenantVO;
import cn.graydove.ndovel.spider.service.SpiderService;
import cn.graydove.ndovel.spider.util.CryptoUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author graydove
 */
@Slf4j
@RestController
@AllArgsConstructor
public class BroadcastController {

    private TenantManager tenantManager;

    private SpiderService spiderService;

    @PostMapping("/register")
    public TenantVO register(@RequestBody RegisterDTO registerDTO) {
        log.info("new register: {}", registerDTO);
        tenantManager.register(registerDTO);
        return tenantManager.getSelfInfo();
    }

    
    @PostMapping("/publish")
    public Boolean publish(@RequestBody BroadCastDTO broadCastDTO) {
        log.info("new publish: {}", broadCastDTO);
        CryptoData cryptoData = CryptoUtil.decode(broadCastDTO, tenantManager);
        if (null == cryptoData) {
            log.warn("invalid request");
            return false;
        }
        log.info("receive publish: {}", cryptoData);
        String uid = tenantManager.getTenant().getUid();
        NovelPutDTO novelPutDTO = (NovelPutDTO) cryptoData.getData();
        if (spiderService.publishNovel(novelPutDTO)) {
            spiderService.broadCaster(CryptoUtil.encode(novelPutDTO, uid, cryptoData.getSourceUid(), tenantManager.getRsa()));
            return true;
        }
        return false;
    }

}
