package com.library_management.book.entity;

import java.io.Serializable;

//import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.library_management.applicationData.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "BOOK")
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -286231151746677958L;
	
	@Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "PUBLICATION_YEAR")
    private Integer publicatinYear;

    @Column(name = "ISBN")
    private String isbn;
    
    @Column(name = "NUMBER_OF_PAGES")
    private Integer numberOfPages;

    @Column(name = "STATUS_DESCRIPTION")
    private String statusDescription;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "READER_AGE")
    private Integer readerAge;

    @Column(name = "LANGUAGE")
    private String language;
    
    @Column(name = "IS_BORROWED", nullable = false)
    private boolean isBorrowed;
    
    @ManyToOne
    @JoinColumn(name = "BASE_ENTITY_ID")
    private BaseEntity baseEntity;

}
