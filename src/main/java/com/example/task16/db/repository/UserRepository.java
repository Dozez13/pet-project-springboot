package com.example.task16.db.repository;


import com.example.task16.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
     User findByLogin(String login);
}
