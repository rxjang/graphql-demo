package io.graphqldemo;

import graphql.kickstart.execution.error.GraphQLErrorHandler;
import io.graphqldemo.error.GraphQLExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlDemoApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler graphQLErrorHandler() {
        return new GraphQLExceptionHandler();
    }

}
