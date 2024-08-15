package com.library_management.user.entity;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import com.library_management.applicationData.entities.BaseEntity;
import com.library_management.applicationData.entities.Position;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "USER")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6492466413276325489L;
	
	@Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "THIRD_NAME")
    private String thirdName;

    @Column(name = "NATIONALITY_ID")
    private Long nationalityId;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;
    
    @Column(name = "STREET")
    private String street;


    @ManyToOne
    @JoinColumn(name = "POSITIONS_ID", referencedColumnName = "ID")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "BASE_ENTITY_ID")
    private BaseEntity baseEntity;
}
