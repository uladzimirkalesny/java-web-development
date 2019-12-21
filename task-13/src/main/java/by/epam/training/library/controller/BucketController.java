package by.epam.training.library.controller;

import by.epam.training.library.domain.Book;
import by.epam.training.library.exception.NoAvailableBookException;
import by.epam.training.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bucket")
public class BucketController {
    @Autowired
    private BookService bookService;

    @GetMapping("/show")
    public String showBucket(Model model, HttpSession session) {
        return "bucket";
    }

    @GetMapping("/add/{id}")
    public String addBookInBucket(@PathVariable("id") int bookId, HttpSession session) {
        List<Book> bucket = (List<Book>) session.getAttribute("bucket");
        if (bucket == null) {
            bucket = new ArrayList<>();
        }

        Book book = bookService.findById(bookId);
        if (book != null && book.getUnitsInStock() >= 1) {
            bucket.add(book);
            book.setUnitsInStock(book.getUnitsInStock() - 1);
            bookService.update(book);
            session.setAttribute("bucket", bucket);
        } else {
            throw new NoAvailableBookException("The Book is not available, cuz  someone reads it");
        }
        return "redirect:/bucket/show";
    }

    @GetMapping("/remove/{id}")
    public String removeBookInBucket(@PathVariable("id") int bookId, HttpSession session) {
        List<Book> bucket = (ArrayList<Book>) session.getAttribute("bucket");

        Optional<Book> anyBook = bucket.stream()
                                       .filter(b -> b.getId() == bookId)
                                       .findAny();

        anyBook.ifPresent(bucket::remove);

        Book updateBook = bookService.findById(bookId);
        updateBook.setUnitsInStock(updateBook.getUnitsInStock() + 1);
        bookService.update(updateBook);

        if (bucket.size() > 0) {
            session.setAttribute("bucket", bucket);
            return "redirect:/bucket/show";
        }
        session.setAttribute("bucket", bucket);
        return "redirect:/books";
    }

    @ExceptionHandler(NoAvailableBookException.class)
    public ModelAndView handleError(NoAvailableBookException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
