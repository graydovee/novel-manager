package com.ndovel.novel.controller.admin;

import com.ndovel.novel.exception.RequestException;
import com.ndovel.novel.model.dto.BookDTO;
import com.ndovel.novel.model.dto.MatchRexDTO;
import com.ndovel.novel.model.dto.SpiderIndex;
import com.ndovel.novel.model.dto.SpiderInfoDTO;
import com.ndovel.novel.model.vo.Response;
import com.ndovel.novel.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


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
    public Response spider(SpiderIndex spiderIndex){

        if (spiderIndex == null ||
                spiderIndex.getBookName() == null ||
                spiderIndex.getAuthorName() == null ||
                spiderIndex.getFirstChapterUrl() == null ||
                spiderIndex.getCoverUrl() == null ||
                spiderIndex.getIntroduce() == null ||
                spiderIndex.getMatchRexId() == null)
            return Response.error("信息不完整");
        BookDTO b = bookService.findExact(spiderIndex.getBookName(), spiderIndex.getAuthorName()).orElse(null);
        if (b != null) {
            return Response.error("该书已存在");
        }
        return Response.success(spiderService.spider(spiderIndex));
    }

    @PutMapping("/cover")
    public Response updateCover(Integer bookId, String url){
        try {
            spiderService.saveImg(url, String.valueOf(bookId));
        } catch (RequestException | IOException e) {
            return Response.error();
        }
        return Response.success();
    }


    @GetMapping("/rex")
    public Response getAllRex(Integer index, Integer size){
        if(index == null || size == null)
            return Response.success(matchRexService.getAllRex());
        else
            return Response.success(matchRexService.find(index, size));
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
    public Response getSpiderInfo(String name, Integer index, Integer size, Integer mod){
        if (mod == null || mod == 0)
            return Response.success(spiderInfoService.find(name, null, index, size));
        else if (mod == 1)
            return Response.success(spiderInfoService.find(name, false, index, size));
        else if (mod == 2)
            return Response.success(spiderInfoService.find(name, true, index, size));
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
