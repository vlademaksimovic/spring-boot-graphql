package com.seavus.kg.codetalks.graphql.service.fetchers;

import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import com.seavus.kg.codetalks.graphql.repository.AuthorRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewAuthorDataFetcher implements DataFetcher<AuthorEntity> {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorEntity get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        Long id = Long.valueOf(dataFetchingEnvironment.getArgument("id"));
        String firstName = dataFetchingEnvironment.getArgument("firstName");
        String lastName = dataFetchingEnvironment.getArgument("lastName");
        return authorRepository.save(new AuthorEntity(id, firstName, lastName));
    }

}
