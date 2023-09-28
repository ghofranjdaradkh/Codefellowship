package com.ghofranjdaradkh.codefellowship.controller;

import com.ghofranjdaradkh.codefellowship.Repositroy.ApplicationUserRepository;
import com.ghofranjdaradkh.codefellowship.config.SiteUserDetailsServiceImpl;
import com.ghofranjdaradkh.codefellowship.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationUserController {

    //s used to interact with the database to manage user data
    @Autowired
    ApplicationUserRepository ApplicationUserRepository;

    // is used to encode and decode user passwords securely
    @Autowired
    private PasswordEncoder passwordEncoder;

    //used to work with HTTP requests
    @Autowired
    private HttpServletRequest request;

    // used to authenticate users
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SiteUserDetailsServiceImpl SiteUserDetailsServiceImpl;


    @GetMapping("/home")
    public String indexPage(Principal p, Model model){
        if(p !=null) {
            String username = p.getName();
            ApplicationUser user = ApplicationUserRepository.findByUsername(username);
            model.addAttribute("username", username);
        }

        return "home.html";
    }


    @GetMapping("/")
    public String getHomePage(Principal p, Model m){

        if(p != null){
            String username = p.getName();
            ApplicationUser User= ApplicationUserRepository.findByUsername(username);

            m.addAttribute("username", username);
            m.addAttribute("createdDate", User.getBio());

        }

        return "index.html";
    }

    @GetMapping("/main")
        public String main () {
        return"index.html" ;
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
    public RedirectView createUser(String username, String password, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth, String  lastName, String bio, String firstName){
        String encryptedPassword = passwordEncoder.encode(password);
 ApplicationUser appUser=new ApplicationUser(username,encryptedPassword,firstName,lastName,dateOfBirth,bio);


      ApplicationUserRepository.save(appUser);
        System.out.println(appUser.getUsername());
        authWithHttpServletRequest(username, password);

        return new RedirectView("/login");

    }



@GetMapping("/myprofile")
public String showUserProfile (Principal p, Model model){

            String username = p.getName();
            ApplicationUser user = ApplicationUserRepository.findByUsername(username);
            model.addAttribute("username", username);
            model.addAttribute("firstName",user.getFirstName());
            model.addAttribute("lastName",user.getLastName());
    model.addAttribute("dateOfBirth",user.getDateOfBirth());
    model.addAttribute("bio",user.getBio());


return "profile";

}





    @GetMapping("/users")
    public String AllUsers(Principal p, Model m) {
        ApplicationUser applicationUser = ApplicationUserRepository.findByUsername(p.getName());
        List<ApplicationUser> allUsers = ApplicationUserRepository.findAll();
        m.addAttribute("applicationUser", applicationUser);
        m.addAttribute("allUsers", allUsers);
        return "users";
    }





    @PostMapping("/users/follow")
    public RedirectView addFollower(long followedUser, Principal p) {
        ApplicationUser user = ApplicationUserRepository.findByUsername(p.getName());
        user.addFollower(ApplicationUserRepository.findById(followedUser).get());
        ApplicationUserRepository.save(user);
        return new RedirectView("/users");
    }

    @PostMapping("/users/unfollow")
    public RedirectView removeFollower(long unfollowedUser, Principal p) {
        ApplicationUser user = ApplicationUserRepository.findByUsername(p.getName());
        user.removeFollower(ApplicationUserRepository.findById(unfollowedUser).get());
        ApplicationUserRepository.save(user);
        return new RedirectView("/users");
    }








    @GetMapping("/user/{id}")
    public String showUserInfo(Principal p, Model model, @PathVariable Long id) {
        if (p != null) {
            String userName = p.getName();
            ApplicationUser userApp = ApplicationUserRepository.findByUsername(userName);


            ApplicationUser user = ApplicationUserRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));


            model.addAttribute("user", user.getUsername());
            model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("dateOfBirth", user.getDateOfBirth());
            model.addAttribute("bio", user.getBio());


            return "userInformation";
        } else {
            return "signup";
        }}


//    @GetMapping("/user/{username}")
//    public String userProfile(@PathVariable String username, Principal p, Model model) {
//
//        ApplicationUser currentUser = null;
//        if (p != null) {
//            currentUser = ApplicationUserRepository.findByUsername(p.getName());
//        }
//
//        ApplicationUser profileUser = ApplicationUserRepository.findByUsername(username);
//
//
//        boolean isFollowing = false;
//        if (currentUser != null && profileUser != null) {
//            isFollowing = currentUser.isFollowing(profileUser);
//        }
//
//        model.addAttribute("username", username);
//        model.addAttribute("firstName", profileUser.getFirstName());
//        model.addAttribute("lastName", profileUser.getLastName());
//        model.addAttribute("dateOfBirth", profileUser.getDateOfBirth());
//        model.addAttribute("bio", profileUser.getBio());
//        model.addAttribute("isFollowing", isFollowing);
//
//
//
//        return "profile";
//    }











    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException
    {
        ResourceNotFoundException(String message)
        {
            super(message);
        }
    }
    public RedirectView authWithHttpServletRequest(String username, String password){

        try {
            request.login(username, password);
        }catch (ServletException e){
            e.printStackTrace();
        }
        return new RedirectView("/profile");

    }

}


