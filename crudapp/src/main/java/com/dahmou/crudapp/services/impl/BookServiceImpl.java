package com.dahmou.crudapp.services.impl;

import com.dahmou.crudapp.domain.Book;
import com.dahmou.crudapp.repositories.BookRepository;
import com.dahmou.crudapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(final Book book) {
        return null;
    }
}
