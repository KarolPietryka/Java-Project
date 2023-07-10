package com.persado.assignment.project.web.controller;

import com.persado.assignment.project.dao.user.entity.UserEntity;
import com.persado.assignment.project.dao.user.repo.UserRepository;
import com.persado.assignment.project.service.exception.user.UserHasBooksException;
import com.persado.assignment.project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/newUser")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "newUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserEntity user) {
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/manageUsers")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "manageUsers";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        try {
            userService.deleteUser(id);
        } catch (UserHasBooksException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("books", e.getBooks());
            return "error";
        }
        return "redirect:/manageUsers";
    }
}
