package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    private final
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @GetMapping("/admin")
    public String UsersList(Model model){
        model.addAttribute("users", userService.usersList());
        return "index";
    }

    @GetMapping( "/user/{id}")
    public String getUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserFromAdmin(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "userfromadmin";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user){

        return "new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/user/{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/admin/user/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable Long id){
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/user/{id}")
    public String delete(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/admin";
    }
}
