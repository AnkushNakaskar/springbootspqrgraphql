package com.spqr.graphql.springbootspqrgraphql.bean;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ankushnakaskar
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GraphQLQuery(name = "title", description = "A Articles's title")
    private String title;
    private String text;
    private Long authorId;
}

