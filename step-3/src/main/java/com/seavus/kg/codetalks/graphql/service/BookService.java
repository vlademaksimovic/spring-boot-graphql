package com.seavus.kg.codetalks.graphql.service;

import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import com.seavus.kg.codetalks.graphql.model.BookEntity;
import com.seavus.kg.codetalks.graphql.repository.BookRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@GraphQLApi
public class BookService {

    private final BookRepository bookRepository;

    @GraphQLQuery
    public Page<BookEntity> getBooksPage(@GraphQLNonNull Integer pageNumber, @GraphQLNonNull Integer pageSize, @GraphQLNonNull String sortDirection) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,
                Sort.by(new Sort.Order(Sort.Direction.fromString(sortDirection.toUpperCase()), "title", Sort.NullHandling.NULLS_LAST)));
        return bookRepository.findAll(pageRequest);
    }

    @GraphQLQuery
    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    @GraphQLQuery
    public Long countBooks() {
        return bookRepository.count();
    }

    @GraphQLQuery
    public BookEntity findBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @GraphQLMutation
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

    @GraphQLMutation
    public boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    @GraphQLQuery(name = "url")
    public String getUrl(@GraphQLContext BookEntity bookEntity) {
        return "URL";
    }
}
