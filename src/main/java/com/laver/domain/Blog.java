package com.laver.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;

/**
 * Created by L on 2018/9/12.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "blog",type = "blog")
public class Blog implements Serializable {

    @Id
    private String id;

    private String title;

    private String summary;

    private String content;

}
