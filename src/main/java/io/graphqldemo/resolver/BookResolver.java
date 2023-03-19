package io.graphqldemo.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.graphqldemo.model.entity.Author;
import io.graphqldemo.repository.AuthorRepository;
import io.graphqldemo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookResolver implements GraphQLQueryResolver {

    private final AuthorRepository authorRepository;

    private final BookService bookService;

    public List<Author> authors() {
        return authorRepository.findAll();
    }

    public Optional<Author> authorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author save() {

        return bookService.saveAuthor();
    }
}
