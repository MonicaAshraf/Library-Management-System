package com.library_management.applicationData.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "Base_Entity")
public class BaseEntity implements Serializable {



    /**
	 * 
	 */
	private static final long serialVersionUID = 1672457247152611514L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	 
	@Column(name = "CREATER_ID")
    private Long createrId;

    @Column(name = "CHANGER_ID")
    private Long changerId;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "CHANGE_DATE")
    private LocalDateTime changeDate;
}