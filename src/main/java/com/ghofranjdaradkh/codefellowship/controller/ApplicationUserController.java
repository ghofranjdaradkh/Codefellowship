package com.ghofranjdaradkh.codefellowship.controller;

import com.ghofranjdaradkh.codefellowship.Repositroy.ApplicationUserRepository;
import com.ghofranjdaradkh.codefellowship.config.SiteUserDetailsServiceImpl;
import com.ghofranjdaradkh.codefellowship.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository ApplicationUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SiteUserDetailsServiceImpl SiteUserDetailsServiceImpl;


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
        return "signup.html";
    }

    @GetMapping("/logout")
    public String getLogoutPage(){
        return "index.html";
    }



    @PostMapping("/signup")
    public RedirectView createUser(String username, String password,String dateOfBirth,String  lastname,String bio, String firstname){
 ApplicationUser appUser=new ApplicationUser();
        appUser.setUsername(username);
        appUser.setDateOfBirth(dateOfBirth);
        appUser.setBio(bio);
        appUser.setLastName(lastname);
        appUser.setFirstName(firstname);
        String encryptedPassword = passwordEncoder.encode(password);
        appUser.setPassword(encryptedPassword);

      ApplicationUserRepository.save(appUser);
        authWithHttpServletRequest(username, password);

        return new RedirectView("/");

    }

    public void authWithHttpServletRequest(String username, String password){

        try {
            request.login(username, password);
        }catch (ServletException e){
            e.printStackTrace();
        }
    }

}


