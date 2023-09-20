package com.ghofranjdaradkh.codefellowship.Repositroy;

import com.ghofranjdaradkh.codefellowship.models.ApplicationUser;
import com.ghofranjdaradkh.codefellowship.models.post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepository extends JpaRepository<post,Long> {

    Iterable<post> findByUser(ApplicationUser user);

}

