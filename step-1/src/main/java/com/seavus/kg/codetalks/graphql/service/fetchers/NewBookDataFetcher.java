package com.seavus.kg.codetalks.graphql.service.fetchers;

import com.seavus.kg.codetalks.graphql.model.AuthorEntity;
import com.seavus.kg.codetalks.graphql.model.BookEntity;
import com.seavus.kg.codetalks.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewBookDataFetcher implements DataFetcher<BookEntity> {

    private final BookRepository bookRepository;

    @Override
    public BookEntity get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        Long id = Long.valueOf(dataFetchingEnvironment.getArgument("id"));
        String title = dataFetchingEnvironment.getArgument("title");
        Integer pageCount = dataFetchingEnvironment.getArgument("pageCount");
        Long authorId = Long.valueOf(dataFetchingEnvironment.getArgument("author"));
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(authorId);
        return bookRepository.save(new BookEntity(id, title, pageCount, authorEntity));
    }

}
