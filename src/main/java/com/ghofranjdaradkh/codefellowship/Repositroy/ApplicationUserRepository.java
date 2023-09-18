package com.ghofranjdaradkh.codefellowship.Repositroy;

import com.ghofranjdaradkh.codefellowship.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);

}
