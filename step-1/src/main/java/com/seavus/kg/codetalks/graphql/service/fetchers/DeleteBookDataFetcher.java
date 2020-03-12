package com.seavus.kg.codetalks.graphql.service.fetchers;

import com.seavus.kg.codetalks.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBookDataFetcher implements DataFetcher<Boolean> {

    private final BookRepository bookRepository;

    @Override
    public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        Long id = Long.valueOf(dataFetchingEnvironment.getArgument("id"));
        bookRepository.deleteById(id);
        return Boolean.TRUE;
    }

}
