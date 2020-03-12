package com.seavus.kg.codetalks.graphql.service.fetchers;

import com.seavus.kg.codetalks.graphql.model.BookEntity;
import com.seavus.kg.codetalks.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindBookByIdDataFetcher implements DataFetcher<BookEntity> {

    private final BookRepository bookRepository;

    @Override
    public BookEntity get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        Long id = Long.valueOf(dataFetchingEnvironment.getArgument("id"));
        return bookRepository.findById(id).get();
    }

}
