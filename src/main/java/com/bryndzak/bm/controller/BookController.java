package com.bryndzak.bm.controller;

import com.bryndzak.bm.domain.Book;
import com.bryndzak.bm.repository.BookRepository;
import com.bryndzak.bm.validation.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;
    private BookValidator bookValidator;

    @Autowired
    public BookController(BookRepository bookRepository, BookValidator bookValidator ) {

        this.bookRepository = bookRepository;
        this.bookValidator = bookValidator;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getBooks(Model model) {
        List<Book> books = this.bookRepository.listAll();

        model.addAttribute("books", books);

        return "index";//cкорочений запис доступний за допомогою InternalResourceViewResolver
    }

    @RequestMapping(value = "addBook", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String addBook(Model model) {
        model.addAttribute("book",new Book());

        return "addBook";//ретурнить сторінку addBook
    }

    @RequestMapping(value = "addBook", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public String addBook(@ModelAttribute("book") Book book ,BindingResult bindingResult){//додоаємо нову книгу в БД///bindingResult-зберігає помилки валідації
        this.bookValidator.validate(book, bindingResult);

        if(bindingResult.hasErrors()){
            return "addBook";
        }

        this.bookRepository.addBook(book);
        return "redirect:/";
    }

    @RequestMapping(value = "deleteBook/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String deleteBook(@PathVariable Integer id){//PathVariable - вказує що ід є частиною url
        this.bookRepository.removeBook(id);

        return "redirect:/";
    }


}