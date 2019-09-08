package com.ndovel.ebook;

import com.ndovel.ebook.model.entity.Author;
import com.ndovel.ebook.repository.AuthorRepository;
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

}
