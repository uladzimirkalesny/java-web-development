package by.epam.training.library.controller;

import by.epam.training.library.domain.Author;
import by.epam.training.library.domain.Book;
import by.epam.training.library.exception.AuthorNotFoundException;
import by.epam.training.library.exception.BookNotFoundException;
import by.epam.training.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public String createAuthor(@ModelAttribute("author") Author author, int bookId) {
        author.setBooks(Collections.singletonList(Book.createBookBuilder().id(bookId).build()));
        authorService.create(author);
        return "redirect:/books/" + bookId;
    }

    @GetMapping("/{id}/edit")
    public String editAuthor(@PathVariable int id, @RequestParam(value = "bookId", required = false) Integer bookId, Model model) {
        model.addAttribute("author", authorService.findById(id));
        if (bookId != null) {
            model.addAttribute("bookId", bookId);
        }
        return "authorEdit";
    }

    @PostMapping("/{id}/edit")
    public String saveChanges(@ModelAttribute Author author) {
        authorService.update(author);
        return "redirect:/authors/" + author.getId() + "/edit";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id,  @RequestParam(value = "bookId", required = false) Integer bookId) {
        Author author = authorService.findById(id);
        if (author != null) {
            authorService.delete(author);
        }
        if (bookId == null) {
            return "redirect:/books";
        }
        return "redirect:/books/" + bookId + "/edit";
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, AuthorNotFoundException ex) {
        ModelAndView mav = new ModelAndView();
        final String NOT_FOUND_AUTHOR = "The Book is not available with author id ";
        mav.addObject("message", NOT_FOUND_AUTHOR + ex.getId());
        mav.setViewName("error");
        return mav;
    }
}
