package com.seavus.kg.codetalks.graphql.repository;

import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
