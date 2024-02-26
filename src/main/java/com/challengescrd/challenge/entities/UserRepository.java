package com.challengescrd.challenge.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    static User findByUsername(String login) {
        return null;
    }

    UserDetails findByLogin(String login);
}
