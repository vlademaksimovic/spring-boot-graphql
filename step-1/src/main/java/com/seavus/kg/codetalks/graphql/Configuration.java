package com.seavus.kg.codetalks.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.seavus.kg.codetalks.graphql.service.fetchers.CountAuthorsDataFetcher;
import com.seavus.kg.codetalks.graphql.service.fetchers.CountBooksDataFetcher;
import com.seavus.kg.codetalks.graphql.service.fetchers.DeleteBookDataFetcher;
import com.seavus.kg.codetalks.graphql.service.fetchers.FindAllAuthorsDataFetcher;
import com.seavus.kg.codetalks.graphql.service.fetchers.FindAllBooksDataFetcher;
import com.seavus.kg.codetalks.graphql.service.fetchers.FindBookAuthorDataFetcher;
import com.seavus.kg.codetalks.graphql.service.fetchers.FindBookByIdDataFetcher;
import com.seavus.kg.codetalks.graphql.service.fetchers.NewAuthorDataFetcher;
import com.seavus.kg.codetalks.graphql.service.fetchers.NewBookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URL;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    private FindAllAuthorsDataFetcher findAllAuthorsDataFetcher;

    @Autowired
    private CountAuthorsDataFetcher countAuthorsDataFetcher;

    @Autowired
    private FindAllBooksDataFetcher findAllBooksDataFetcher;

    @Autowired
    private CountBooksDataFetcher countBooksDataFetcher;

    @Autowired
    private FindBookByIdDataFetcher findBookByIdDataFetcher;

    @Autowired
    private FindBookAuthorDataFetcher findBookAuthorDataFetcher;

    @Autowired
    private NewAuthorDataFetcher newAuthorDataFetcher;

    @Autowired
    private NewBookDataFetcher newBookDataFetcher;

    @Autowired
    private DeleteBookDataFetcher deleteBookDataFetcher;

    @Bean
    public GraphQL graphQL() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("findAllAuthors", findAllAuthorsDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("countAuthors", countAuthorsDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("findAllBooks", findAllBooksDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("countBooks", countBooksDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("findBookById", findBookByIdDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Book").dataFetcher("author", findBookAuthorDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("newAuthor", newAuthorDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("newBook", newBookDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("deleteBook", deleteBookDataFetcher))
                .build();
    }

}