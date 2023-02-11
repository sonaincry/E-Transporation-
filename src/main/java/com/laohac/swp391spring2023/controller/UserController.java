package com.laohac.swp391spring2023.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.dto.UserDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String home(Model model, User user){
        model.addAttribute("customer", user);
        return "home/Register";
    }

    @PostMapping("/save")
    public String register (@ModelAttribute("customer") User user){

        UserDTOResponse userDTOResponse = userService.registerUser(user);
        System.out.println(userDTOResponse.getFullName());
        return "home/index";
    }

    @GetMapping("/login-user")
    public String showLogin(Model model, UserDTORequest userDTORequest){
        model.addAttribute("customerInfo", userDTORequest);
        return "user/login";
    }

    @PostMapping("/sign-in")
    public String login(Model model, @ModelAttribute("customerInfo") UserDTORequest userDTORequest){

        UserDTOResponse userDTOResponse = userService.login(userDTORequest);
        System.out.println(userDTOResponse.getFullName());
        return "home/index";
    }

    @GetMapping("/user-info")
    public String getUserInfo(Model model, String username){
        UserDTOResponse userDTOResponse = userService.getUserInfo(username);
        model.addAttribute("userInfo", userDTOResponse);
        return "user/userDetail";
    }

    
    /*@GetMapping("/login-google")
    public String login(Model model, @AuthenticationPrincipal OAuth2User user){

        UserDTOResponse userLogin = userService.login(user);
        model.addAttribute("user", userLogin);

        return "index";
    }*/

    
}
