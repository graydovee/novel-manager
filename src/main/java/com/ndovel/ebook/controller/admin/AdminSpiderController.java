package com.ndovel.ebook.controller.admin;

import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.dto.SpiderInfoDTO;
import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/admin")
@RestController
public class AdminSpiderController {

    private SpiderService spiderService;
    private MatchRexService matchRexService;
    private BookService bookService;
    private ChapterService chapterService;
    private SpiderInfoService spiderInfoService;

    public AdminSpiderController(SpiderService spiderService,
                                 MatchRexService matchRexService,
                                 BookService bookService,
                                 ChapterService chapterService,
                                 SpiderInfoService spiderInfoService) {
        this.spiderService = spiderService;
        this.matchRexService = matchRexService;
        this.bookService = bookService;
        this.chapterService = chapterService;
        this.spiderInfoService = spiderInfoService;
    }

    @PostMapping("/book")
    public Response spider(String bookName, String authorName, String url, Integer matchRexId){

        if(bookName == null || authorName == null || url == null || matchRexId == null)
            return null;

        return Response.success(spiderService.spider(bookName, authorName, url ,matchRexId));
    }

    @GetMapping("/rex")
    public Response getAllRex(){
        return Response.success(matchRexService.getAllRex());
    }

    @PostMapping("/rex")
    public Response updMatchRex(MatchRexDTO matchRexDTO){
        return Response.success(matchRexService.save(matchRexDTO.writeToDomain()));
    }

    @DeleteMapping("/rex")
    public Response delMatchRex(Integer id){
        if(id != null){
            matchRexService.delById(id);
            return Response.success("success");
        }
        return Response.error("无效参数");
    }

    @DeleteMapping("/book")
    public Response delBook(Integer id){
        if(id != null){
            log.info("delete:" + id.toString());
            bookService.deleteBookById(id);
            chapterService.delChapterByBookId(id);
            return Response.success("success");
        }
        return Response.error("无效参数");
    }

    @GetMapping("/spider_info")
    public Response getSpiderInfo(Integer index, Integer size, Integer mod){
        if (mod==null)
            return Response.success(spiderInfoService.find(index, size));
        else if (mod == 0)
            return Response.success(spiderInfoService.find(false, index, size));
        else if (mod == 1)
            return Response.success(spiderInfoService.find(true, index, size));
        else
            return Response.error();
    }


    @DeleteMapping("/spider_info")
    public Response delSpiderInfo(Integer id, Integer refresh){
        if(refresh!=null && refresh > 0){
            Integer c = spiderInfoService.continueSpider(id);
            if(c > 0)
                return Response.success();
            return Response.error("无信息");
        }else{
            Integer c = spiderInfoService.finishSpider(id);
            if (c > 0)
                return Response.success();
            return Response.error("无信息");
        }
    }

    @PutMapping("/spider_info")
    public Response refreshSpiderInfo(Integer id, String url, Integer matchRexId){
        SpiderInfoDTO s = spiderInfoService.save(id, url, matchRexId);
        if(s!=null){
            return Response.success(s);
        }
        return Response.error();
    }

    /**
     *
     * @param id SpiderInfo_Id
     * @return VO
     */
    @PostMapping("/update")
    public Response update(Integer id){
        SpiderInfoDTO update = spiderService.update(id);
        return Response.success(update);
    }
}
