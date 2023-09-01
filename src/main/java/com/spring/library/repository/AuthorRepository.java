package com.spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.library.model.Author;
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
