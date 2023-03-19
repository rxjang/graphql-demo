package io.graphqldemo.repository;

import io.graphqldemo.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Modifying
    @Query(value = "INSERT INTO author (id, name) VALUES (3, '최은영')", nativeQuery = true)
    void saveAuthor();
}
