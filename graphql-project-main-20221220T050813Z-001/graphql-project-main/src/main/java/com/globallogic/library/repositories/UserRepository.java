package com.globallogic.library.repositories;

import com.globallogic.library.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmailIdAndPassword(String userName, String password);
}
