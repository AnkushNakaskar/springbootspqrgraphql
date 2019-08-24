package com.spqr.graphql.springbootspqrgraphql.service;

import com.spqr.graphql.springbootspqrgraphql.bean.Article;
import com.spqr.graphql.springbootspqrgraphql.bean.Comment;
import com.spqr.graphql.springbootspqrgraphql.bean.Profile;
import com.spqr.graphql.springbootspqrgraphql.repository.ArticleRepository;
import com.spqr.graphql.springbootspqrgraphql.repository.CommentRepository;
import com.spqr.graphql.springbootspqrgraphql.repository.ProfileRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ankushnakaskar
 */
@Service
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Article> articles() {
        log.info("Fetching all the articles ....!!!!");
        return articleRepository.findAll().stream().collect(Collectors.toList());
    }

    @GraphQLQuery
    public Profile author(@GraphQLContext Article article) {
        log.info("Fetching the profile for article..." + article);
        return profileRepository.findById(article.getAuthorId()).get();
    }

    @GraphQLQuery
    public List<Comment> getComments(@GraphQLContext Article article) {
        log.info("Fetching the comments for article..." + article);
        return commentRepository.findByArticleId(article.getId());
    }

    @GraphQLQuery(name = "article")
    public Optional<Article> getArticle(@GraphQLArgument(name = "articleId") Long articleId) {
        return articleRepository.findById(articleId);
    }

}
