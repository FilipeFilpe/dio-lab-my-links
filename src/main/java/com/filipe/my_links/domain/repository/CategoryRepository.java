package com.filipe.my_links.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filipe.my_links.domain.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
