package com.library_management.applicationData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library_management.applicationData.entities.BaseEntity;
@Repository
public interface BaseEntityRepository  extends JpaRepository<BaseEntity, Integer>{

}
