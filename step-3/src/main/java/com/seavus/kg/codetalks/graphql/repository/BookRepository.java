package com.seavus.kg.codetalks.graphql.repository;

import com.seavus.kg.codetalks.graphql.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long>, PagingAndSortingRepository<BookEntity, Long> {
}
