package com.ndovel.ebook.controller.admin;


import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminCacheController {

    @Autowired
    private CacheService cacheService;

    @DeleteMapping("/cache")
    public Response delCache(String name, Integer key){
        cacheService.delCache(name, key);
        return Response.success();
    }
}
