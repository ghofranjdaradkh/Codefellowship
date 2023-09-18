package com.ghofranjdaradkh.codefellowship.config;

import com.ghofranjdaradkh.codefellowship.Repositroy.ApplicationUserRepository;
import com.ghofranjdaradkh.codefellowship.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SiteUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser=applicationUserRepository.findByUsername(username);

        if(applicationUser == null){
            System.out.println("User not found "+ username);
            throw new UsernameNotFoundException("user"+ username+ " was not found in the database");
        }
        System.out.println("Found User: "+applicationUser.getUsername());
        return applicationUser;
    }


    }

