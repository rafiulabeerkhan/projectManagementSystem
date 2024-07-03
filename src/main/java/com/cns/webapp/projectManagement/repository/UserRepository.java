package com.cns.webapp.projectManagement.repository;

import com.cns.webapp.projectManagement.model.UserDetails;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
    public boolean existsByEmail(String email);

    UserDetails findByUsernameAndPassword(String username, String password);
    UserDetails findByUsername(String username);

}
