package com.ghofranjdaradkh.codefellowship.controller;

import com.ghofranjdaradkh.codefellowship.Repositroy.ApplicationUserRepository;
import com.ghofranjdaradkh.codefellowship.Repositroy.postRepository;
import com.ghofranjdaradkh.codefellowship.models.ApplicationUser;
import com.ghofranjdaradkh.codefellowship.models.post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class postController {
    @Autowired
    postRepository  postRepository;
    @Autowired
            ApplicationUserRepository   ApplicationUserRepository;


    @GetMapping("/posts")
    public String viewPosts(Model model) {

        Iterable<post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        return "post";
    }




    @GetMapping("/profile")
    public String viewUserProfile(Model model, Principal principal) {
        String username = principal.getName();
        ApplicationUser user = ApplicationUserRepository.findByUsername(username);
        Iterable<post> userPosts = postRepository.findByUser(user);
        model.addAttribute("userPosts", userPosts);
        return "profile";}

    @PostMapping("/addPost")
    public RedirectView addPost(Principal principal, String text) {
        String username = principal.getName();
        ApplicationUser user = ApplicationUserRepository.findByUsername(username);

        post newPost = new post();
        newPost.setBody(text);
        newPost.setUser(user);




        postRepository.save(newPost);

        return new RedirectView("/profile");
    }








    @GetMapping("/feed")
    public String getFeed(Principal p, Model m) {
        ApplicationUser applicationUser = ApplicationUserRepository.findByUsername(p.getName());
        m.addAttribute("applicationUser", applicationUser);
        return "feed";
    }




}
