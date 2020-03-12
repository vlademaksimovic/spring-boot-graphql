package com.seavus.kg.codetalks.graphql.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import com.seavus.kg.codetalks.graphql.model.BookEntity;
import com.seavus.kg.codetalks.graphql.repository.AuthorRepository;
import com.seavus.kg.codetalks.graphql.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryService implements GraphQLQueryResolver {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    public Long countBooks() {
        return bookRepository.count();
    }

    public List<AuthorEntity> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Long countAuthors() {
        return authorRepository.count();
    }

    public BookEntity findBookById(Long id) {
        return bookRepository.findById(id).get();
    }
}
