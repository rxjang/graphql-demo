package io.graphqldemo.service;

import io.graphqldemo.model.entity.Author;
import io.graphqldemo.repository.AuthorRepository;
import io.graphqldemo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Transactional
    public Author saveAuthor() {
        Author author = Author.builder()
                .id(3L)
                .name("최은영")
                .build();
        authorRepository.saveAuthor();
        authorRepository.saveAuthor();
        return author;
    }
}
