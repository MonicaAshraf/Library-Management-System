package com.library_management.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library_management.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{


}
