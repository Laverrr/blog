package com.laver.domain;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by L on 2018/9/10.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String name;


}
