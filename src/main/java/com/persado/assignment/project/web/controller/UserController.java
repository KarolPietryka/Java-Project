package com.persado.assignment.project.web.controller;

import com.persado.assignment.project.dao.user.entity.UserEntity;
import com.persado.assignment.project.dao.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

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
}
