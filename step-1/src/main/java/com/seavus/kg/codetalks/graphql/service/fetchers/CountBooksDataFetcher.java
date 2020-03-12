package com.seavus.kg.codetalks.graphql.service.fetchers;

import com.seavus.kg.codetalks.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountBooksDataFetcher implements DataFetcher<Long> {

    private final BookRepository bookRepository;

    @Override
    public Long get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return bookRepository.count();
    }
}
