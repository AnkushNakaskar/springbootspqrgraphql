package com.spqr.graphql.springbootspqrgraphql.config;

import com.spqr.graphql.springbootspqrgraphql.service.ArticleService;
import com.spqr.graphql.springbootspqrgraphql.service.ProfileService;
import graphql.GraphQL;
import graphql.analysis.MaxQueryComplexityInstrumentation;
import graphql.analysis.MaxQueryDepthInstrumentation;
import graphql.execution.batched.BatchedExecutionStrategy;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author ankushnakaskar
 */
@Configuration
public class GraphQLConfiguration {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ArticleService articleService;
    @Bean
    public GraphQL graphQL() {

        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(
                        new AnnotatedResolverBuilder(),
                        new PublicResolverBuilder("com.spqr.graphql"))
                .withOperationsFromSingleton(profileService)
                .withOperationsFromSingleton(articleService)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
        return GraphQL.newGraphQL(schema)
                .queryExecutionStrategy(new BatchedExecutionStrategy())
                .instrumentation(new ChainedInstrumentation(Arrays.asList(
                        new MaxQueryComplexityInstrumentation(200),
                        new MaxQueryDepthInstrumentation(20)
                )))
                .build();
    }
}
