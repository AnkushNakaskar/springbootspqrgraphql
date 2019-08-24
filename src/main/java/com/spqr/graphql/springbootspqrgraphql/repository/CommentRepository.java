package com.spqr.graphql.springbootspqrgraphql.repository;

import com.spqr.graphql.springbootspqrgraphql.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ankushnakaskar
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);
    void deleteById(Long id);
}
