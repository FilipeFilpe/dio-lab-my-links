package com.filipe.my_links.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.filipe.my_links.domain.model.Category;
import com.filipe.my_links.domain.repository.CategoryRepository;
import com.filipe.my_links.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        var categories = this.categoryRepository.findAll();
        return categories;
    }

    @Transactional
    public Category create(Category category) {
        // TODO Validations
        return this.categoryRepository.save(category);
    }

    @Transactional
    public Category update(Long id, Category categoryToUpdate) {
        // TODO Validations
        // TODO DTO

        Category category = this.categoryRepository.findById(id).orElseThrow();

        category.setName(categoryToUpdate.getName());
        category.setDescription(categoryToUpdate.getDescription());
        category.setLinks(categoryToUpdate.getLinks());
        category.setParent(categoryToUpdate.getParent());

        return this.categoryRepository.save(category);
    }

    @Transactional
    public void delete(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow();
        this.categoryRepository.delete(category);
    }
}
