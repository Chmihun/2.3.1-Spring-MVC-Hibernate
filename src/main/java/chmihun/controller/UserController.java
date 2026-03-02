package chmihun.controller;

import chmihun.model.User;
import chmihun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping(value = "/")
    public String hello(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "user/firstPage";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("user") User user) {
        userService.delete(user.getId());
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user/edit";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

}

