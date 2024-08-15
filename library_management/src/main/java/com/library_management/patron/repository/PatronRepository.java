package com.library_management.patron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library_management.patron.entity.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer>{


}
