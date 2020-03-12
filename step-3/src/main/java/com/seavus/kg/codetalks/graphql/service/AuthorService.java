package com.seavus.kg.codetalks.graphql.service;

import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import com.seavus.kg.codetalks.graphql.model.BookEntity;
import com.seavus.kg.codetalks.graphql.repository.AuthorRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@GraphQLApi
public class AuthorService {

    private final AuthorRepository authorRepository;

    @GraphQLQuery
    public List<AuthorEntity> findAllAuthors() {
        return authorRepository.findAll();
    }

    @GraphQLQuery
    public Long countAuthors() {
        return authorRepository.count();
    }

    @GraphQLQuery(name = "author")
    public AuthorEntity getAuthor(@GraphQLContext BookEntity bookEntity) {
        return authorRepository.findById(bookEntity.getAuthor().getId()).get();
    }

    /**
     * Create new Author
     *
     * @param author
     * @return
     */
    @GraphQLMutation
    public AuthorEntity newAuthor(@GraphQLArgument(name = "author") AuthorEntity author) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(author.getId());
        authorEntity.setFirstName(author.getFirstName());
        authorEntity.setLastName(author.getLastName());
        return authorRepository.save(authorEntity);
    }

}
