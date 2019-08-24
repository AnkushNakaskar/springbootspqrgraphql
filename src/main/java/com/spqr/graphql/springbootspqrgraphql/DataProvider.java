package com.spqr.graphql.springbootspqrgraphql;

import com.spqr.graphql.springbootspqrgraphql.bean.Article;
import com.spqr.graphql.springbootspqrgraphql.bean.Comment;
import com.spqr.graphql.springbootspqrgraphql.bean.Profile;
import com.spqr.graphql.springbootspqrgraphql.repository.ArticleRepository;
import com.spqr.graphql.springbootspqrgraphql.repository.CommentRepository;
import com.spqr.graphql.springbootspqrgraphql.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ankushnakaskar
 */
@Component
@AllArgsConstructor
public class DataProvider implements CommandLineRunner {
    private CommentRepository commentRepository;
    private ArticleRepository articleRepository;
    private ProfileRepository profileRepository;


    @Override
    @Transactional
    public void run(String... strings) {
        Profile author = profileRepository.save(new Profile(null, "g00glen00b", "The author of this blog"));
        Profile admin = profileRepository.save(new Profile(null, "admin", "The administrator of this blog"));
        Article article1 = articleRepository.save(new Article(null, "Hello world", "This is a hello world", author.getId()));
        Article article2 = articleRepository.save(new Article(null, "Foo", "Bar", admin.getId()));
        commentRepository.save(new Comment(null, "Do you like this article?", article1.getId(), author.getId()));
        commentRepository.save(new Comment(null, "This is a great article", article1.getId(), admin.getId()));
        commentRepository.save(new Comment(null, "This is a comment", article2.getId(), admin.getId()));
    }
}

