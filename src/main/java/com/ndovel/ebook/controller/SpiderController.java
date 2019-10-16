package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.SpiderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spider")
public class SpiderController {

    private SpiderService spiderService;

    public SpiderController(SpiderService spiderService) {
        this.spiderService = spiderService;
    }

    @PostMapping("/content")
    public Response spiderOne(String url, Integer matchRexId){
        if (matchRexId != null && matchRexId > 0){
            return Response.success(spiderService.spiderOne(url, matchRexId));
        } else {
            return  Response.success(spiderService.spiderOne(url, 0));
        }
    }

    @PostMapping("/search")
    public Response spiderIndex(String name){
        return Response.success(spiderService.spiderByName(name));
    }

    @PostMapping("/index")
    public Response spiderMain(String url){
        return Response.success(spiderService.spiderByIndex(url));
    }
}
