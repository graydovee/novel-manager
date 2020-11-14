package cn.graydove.ndovel.spider.api.openfeign;

import cn.graydove.ndovel.api.facade.NovelFacade;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ndovel-server")
public interface NovelFacadeFeign extends NovelFacade {
}
