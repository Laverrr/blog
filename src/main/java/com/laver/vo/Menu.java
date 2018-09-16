package com.laver.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by L on 2018/9/16.
 */
@Getter
@Setter
@AllArgsConstructor
public class Menu implements Serializable{

    private String name;
    private String url;
}
