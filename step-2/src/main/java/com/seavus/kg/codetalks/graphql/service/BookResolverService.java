package com.seavus.kg.codetalks.graphql.service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import com.seavus.kg.codetalks.graphql.model.BookEntity;
import com.seavus.kg.codetalks.graphql.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  BookResolverService implements GraphQLResolver<BookEntity> {

    private final AuthorRepository authorRepository;

    public AuthorEntity getAuthor(BookEntity bookEntity) {
        return authorRepository.findById(bookEntity.getAuthor().getId()).get();
    }
}
