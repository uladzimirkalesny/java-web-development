package by.epam.training.library.controller;

import by.epam.training.library.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{uid}")
    public String createAuthor(@PathVariable String uid, Authentication authentication, Model model) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String login = userDetails.getUsername();

        // mock user data
        User user = User.createReaderBuilder()
                .id(new Random().nextInt(100))
                .login(userDetails.getUsername())
                .name(login + "Name")
                .surname(login + "Surname")
                .email(login + "@email.com")
                .roles(userDetails.getAuthorities().toString())
                .birthday(getRandomBirthday())
                .build();

        model.addAttribute("user", user);
        return "users";
    }

    private LocalDate getRandomBirthday() {
        LocalDate start = LocalDate.of(1970, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        return start.plusDays(new Random().nextInt((int) (days + 1)));
    }

}
