package com.filipe.my_links.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filipe.my_links.domain.model.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
