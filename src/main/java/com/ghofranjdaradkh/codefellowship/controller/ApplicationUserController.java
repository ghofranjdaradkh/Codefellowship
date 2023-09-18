package com.ghofranjdaradkh.codefellowship.controller;

import com.ghofranjdaradkh.codefellowship.Repositroy.ApplicationUserRepository;
import com.ghofranjdaradkh.codefellowship.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import static com.ghofranjdaradkh.codefellowship.Enum.userPage.*;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository ApplicationUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public RedirectView createUser(String username, String password){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(username);
    applicationUser.setPassword(password);
    applicationUser.setDateOfBirth(dateOfBirth);
    applicationUser.setFirstName(firstName);
    applicationUser.setLastName(lastName);

        String encryptedPassword = passwordEncoder.encode(password);
        applicationUser.setPassword(encryptedPassword);

        ApplicationUserRepository.save(applicationUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");}

    @GetMapping("/")
    public String indexPage(){
        return "index.html";
    }

    @GetMapping("/login")
    public String getLoginPage(){

        return "/login.html";

    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "/singup.html";
    }

    @GetMapping("/logout")
    public String getLogoutPage(){
        return "index.html";
    }

}
