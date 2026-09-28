package com.example.springwebdb;

import com.example.springwebdb.DTO.UserDTO;
import com.example.springwebdb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }

  @PostMapping("/signup")
  public String signupPost(UserDTO userDTO) {
    userService.signup(userDTO);
    return "redirect:/users";
  }

  @GetMapping("/users")
  public String getUsers(Model model) {
    List<UserDTO> users = userService.getAllUsers();
    model.addAttribute("users", users);
    return "users";
  }

  @GetMapping("/index")
  public String index(Model model) {
    model.addAttribute("list", userService.getAllUsers());
    return "index";
  }

  @GetMapping("/edit")
  public String edit(@RequestParam int id, Model model) {
    UserDTO user = userService.getUserById(id);
    model.addAttribute("user", user);
    return "edit";
  }

  @PostMapping("/update")
  public String update(UserDTO userDTO) {
    userService.updateUser(userDTO);
    return "redirect:/users";
  }

  @PostMapping("/delete")
  public String deleteUser(@RequestParam int id) {
    userService.deleteUser(id);
    return "redirect:/users";
  }
}