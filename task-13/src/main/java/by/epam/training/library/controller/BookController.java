package by.epam.training.library.controller;

import by.epam.training.library.domain.Author;
import by.epam.training.library.domain.Book;
import by.epam.training.library.exception.BookNotFoundException;
import by.epam.training.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/add")
    public String getAddNewBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "bookAdd";
    }

    @PostMapping("/add")
    public String createBook(@ModelAttribute("book") Book book) {
        bookService.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "bookDetails";
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/{id}/authors/add")
    public String addAuthor(@PathVariable int id, Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("bookId", id);
        return "authorAdd";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Book book = bookService.findById(id);
        if (book != null) {
            model.addAttribute("book", book);
        }
        return "bookEdit";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        Book book = bookService.findById(id);
        if (book != null) {
            bookService.delete(book);
        }
        return "redirect:/books";
    }

    @PostMapping("/{id}/edit")
    public String saveChanges(@ModelAttribute Book book) {
        bookService.update(book);
        return "redirect:/books/" + book.getId() + "/edit";
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, BookNotFoundException ex) {
        ModelAndView mav = new ModelAndView();
        final String NOT_FOUND_BOOK = "The Book is not available with book id ";
        mav.addObject("message", NOT_FOUND_BOOK + ex.getId());
        mav.setViewName("error");
        return mav;
    }
}
