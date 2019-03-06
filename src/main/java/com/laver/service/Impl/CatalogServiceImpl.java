package com.laver.service.Impl;

import java.util.List;

import com.laver.domain.Catalog;
import com.laver.domain.User;
import com.laver.repository.CatalogRepository;
import com.laver.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Catalog 服务.
 *
 */
@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private CatalogRepository catalogRepository;

	@Override
	public Catalog saveCatalog(Catalog catalog) {
		// 判断重复
		List<Catalog> list = catalogRepository.findByUserAndName(catalog.getUser(), catalog.getName());
		if (list != null && list.size() > 0) {
			throw new IllegalArgumentException("该分类已经存在了");
		}
		return catalogRepository.save(catalog);
	}

	@Override
	public void removeCatalog(Long id) {
		catalogRepository.deleteById(id);
	}

	@Override
	public Catalog getCatalogById(Long id) {
		return catalogRepository.getOne(id);
	}

	@Override
	public List<Catalog> listCatalogs(User user) {
		return catalogRepository.findByUser(user);
	}
}