package com.seavus.kg.codetalks.graphql.service.fetchers;

import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import com.seavus.kg.codetalks.graphql.model.BookEntity;
import com.seavus.kg.codetalks.graphql.repository.AuthorRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindBookAuthorDataFetcher implements DataFetcher<AuthorEntity> {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorEntity get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        Long id = ((BookEntity) dataFetchingEnvironment.getSource()).getAuthor().getId();
        return authorRepository.findById(id).get();
    }

}
