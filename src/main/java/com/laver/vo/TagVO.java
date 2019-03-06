package com.laver.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Tag 值对象.
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class TagVO implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Long count;
	
}
