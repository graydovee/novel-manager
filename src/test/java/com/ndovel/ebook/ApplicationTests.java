package com.ndovel.ebook;

import com.ndovel.ebook.model.dto.*;
import com.ndovel.ebook.model.entity.*;
import com.ndovel.ebook.repository.*;
import com.ndovel.ebook.service.AsyncSpiderService;
import com.ndovel.ebook.spider.core.AbstractSpider;
import com.ndovel.ebook.spider.core.impl.CommonSpider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	MatchRexRepository matchRexRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ContentRepository contentRepository;

	@Autowired
	ChapterRepository chapterRepository;

	@Autowired
	AsyncSpiderService asyncSpiderService;


	@Test
	public void contextLoads() {

	}

	@Test
	public void textRepository(){
		Author author = new Author();
		author.setName("aasd");
		authorRepository.save(author);
		author = new Author();
		author.setName("aasdddaa");
		authorRepository.save(author);
		authorRepository.delete(author);
		List<Author> list = authorRepository.findAllIsExist();
		System.out.println("-------------");
		System.out.println(list);
		System.out.println("-------------");
		System.out.println("2:"+authorRepository.findOneIsExist(1));
		System.out.println("1:"+authorRepository.findOneIsExist(2));
	}

	@Test
	public void testSpider(){
		MatchRex matchRex;
		matchRex = new MatchRex();

		matchRex.setContentRex("<div[^<]*id=\"content\"[^<]*>([\\s\\S]*?)</div>");
		matchRex.setTitleRex("<h1>([^<]*)</h1>");
		matchRex.setNextPageRex("<a[^<]*href=\"([^<]*.html)\"[^<]*>下一章</a>");
		matchRex.setName("笔趣阁爬虫方案");
		matchRex.setInfo("可以爬取笔趣阁的小说");
		matchRexRepository.save(matchRex);

		MatchRex m = matchRexRepository.findOneByName("笔趣阁爬虫方案");
		System.out.println(m);
		MatchRexDTO matchRexDTO = new MatchRexDTO();
		matchRexDTO.init(m);
		System.out.println(matchRexDTO);

		BookDTO book = new BookDTO();
		book.setName("牧神记");
		AuthorDTO author = new AuthorDTO("宅猪");
		book.setAuthor(author);

		asyncSpiderService.spider(book, "http://www.biquge001.com/Book/16/16935/12799783.html", "gbk", matchRex.getId());
	}
}
