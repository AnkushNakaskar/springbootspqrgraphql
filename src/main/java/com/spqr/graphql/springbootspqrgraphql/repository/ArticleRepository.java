package com.spqr.graphql.springbootspqrgraphql.repository;

import com.spqr.graphql.springbootspqrgraphql.bean.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author ankushnakaskar
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findById(Long id);
    void deleteById(Long id);
}

