package com.seavus.kg.codetalks.graphql.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import com.seavus.kg.codetalks.graphql.model.BookEntity;
import com.seavus.kg.codetalks.graphql.repository.AuthorRepository;
import com.seavus.kg.codetalks.graphql.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutationService implements GraphQLMutationResolver {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorEntity newAuthor(Long id, String firstName, String lastName) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(id);
        authorEntity.setFirstName(firstName);
        authorEntity.setLastName(lastName);
        return authorRepository.save(authorEntity);
    }

    public BookEntity newBook(Long id, String title, Integer pageCount, Long authorId) {
        BookEntity book = new BookEntity();
        book.setId(id);

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(authorId);
        book.setAuthor(authorEntity);

        book.setTitle(title);
        book.setPageCount(pageCount != null ? pageCount : 0);

        return bookRepository.save(book);
    }

    public boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }
}
