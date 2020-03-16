package com.ndovel.ebook;

import com.ndovel.ebook.config.SpiderProperties;
import com.ndovel.ebook.repository.*;
import com.ndovel.ebook.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	protected AuthorRepository authorRepository;

	@Autowired
	protected MatchRexRepository matchRexRepository;

	@Autowired
	protected BookRepository bookRepository;

	@Autowired
	protected ContentRepository contentRepository;

	@Autowired
	protected ChapterRepository chapterRepository;

	@Autowired
	protected SpiderInfoRepository spiderInfoRepository;

	@Autowired
	protected SpiderService spiderService;

	@Autowired
	protected BookService bookService;

	@Autowired
	protected VisitRepository visitRepository;

	@Autowired
	protected VisitService visitService;

	@Autowired
	protected AsyncService asyncService;

	@Autowired
	protected ScheduledSpiderService ScheduledSpiderService;

	@Autowired
	protected SpiderProperties spiderProperties;

	@Test
	public void text(){
	}

	@Test
	public void testSpider(){
//		MatchRexDTO matchRex;
//		matchRex = new MatchRexDTO();
//
//		matchRex.setContentRex("<div[^<]*id=\"content\"[^<]*>([\\s\\S]*?)</div>");
//		matchRex.setTitleRex("<h1>([^<]*)</h1>");
//		matchRex.setNextPageRex("<a[^<]*href=\"([^<]*.html)\"[^<]*>下一章</a>");
//		matchRex.setName("笔趣阁爬虫方案");
//		matchRex.setInfo("可以爬取笔趣阁的小说");
//		matchRexRepository.save(matchRex);
//
//		MatchRex m = matchRexRepository.findOneByName("笔趣阁爬虫方案");
//		System.out.println(m);
//		MatchRexDTO matchRexDTO = new MatchRexDTO();
//		matchRexDTO.init(m);
//		System.out.println(matchRexDTO);
//
//		BookDTO book = new BookDTO();
//		book.setName("牧神记");
//		AuthorDTO author = new AuthorDTO("宅猪");
//		book.setAuthor(author);

//		spiderService.spider(book, "http://www.biquge001.com/Book/16/16935/12799783.html", matchRex.getId());
//		SpiderInfoDTO spiderInfo = new SpiderInfoDTO();
//		spiderInfo.setMatchRex(matchRex);
//		//spiderInfo.setUrl("https://www.biqukan.com/51_51530/465314625.html");
//		spiderInfo.setUrl("https://www.biquge.tw/37_37206/2159914.html");
//		NovelSpider spider =  new CommonNovelSpider(spiderInfo);
//		spider.run();
//		System.out.println(spider.getContent());
//		if(spider.hasNext()){
//			spider.run();
//			System.out.println(spider.getContent());
//		}
	}
}
