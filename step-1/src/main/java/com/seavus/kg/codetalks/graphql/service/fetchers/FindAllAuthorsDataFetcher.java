package com.seavus.kg.codetalks.graphql.service.fetchers;

import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import com.seavus.kg.codetalks.graphql.repository.AuthorRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllAuthorsDataFetcher implements DataFetcher<List<AuthorEntity>> {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorEntity> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return authorRepository.findAll();
    }

}
