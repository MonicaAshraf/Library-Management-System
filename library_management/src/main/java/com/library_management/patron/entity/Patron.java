package com.library_management.patron.entity;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import com.library_management.applicationData.entities.BaseEntity;
import com.library_management.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "PATRON")
public class Patron implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3081014871858329699L;
	@Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "BASE_ENTITY_ID")
    private BaseEntity baseEntity;
}

