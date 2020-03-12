package com.seavus.kg.codetalks.graphql.repository;

import com.seavus.kg.codetalks.graphql.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
