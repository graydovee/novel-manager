package cn.graydove.ndovel.author.controller;

import cn.graydove.ndovel.author.model.dto.BookDTO;
import cn.graydove.ndovel.author.service.BookService;
import cn.graydove.ndovel.server.api.model.request.BookRequest;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author graydove
 */
@RestController
@AllArgsConstructor
public class AuthorController {

    private BookService bookService;

    @PostMapping("create_book")
    public BookVO createBook(@Valid BookDTO bookDTO){
        return bookService.createBook(bookDTO);
    }

}
