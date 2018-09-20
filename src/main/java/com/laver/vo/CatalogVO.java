package com.laver.vo;


import java.io.Serializable;

import com.laver.domain.Catalog;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CatalogVO implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Catalog catalog;
	
	public CatalogVO() {
	}


}
