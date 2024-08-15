package com.library_management.borrowingRecord.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.library_management.applicationData.entities.BaseEntity;
import com.library_management.book.entity.Book;
import com.library_management.patron.entity.Patron;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "Borrowing_Record")
public class BorrowingRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "base_entity_id")
    private BaseEntity baseEntity;
}
