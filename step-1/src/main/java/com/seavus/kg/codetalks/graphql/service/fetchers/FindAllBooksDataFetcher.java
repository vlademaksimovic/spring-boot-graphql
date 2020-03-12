package com.seavus.kg.codetalks.graphql.service.fetchers;

import com.seavus.kg.codetalks.graphql.model.BookEntity;
import com.seavus.kg.codetalks.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllBooksDataFetcher implements DataFetcher<List<BookEntity>> {

    private final BookRepository bookRepository;

    @Override
    public List<BookEntity> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return bookRepository.findAll();
    }

}
