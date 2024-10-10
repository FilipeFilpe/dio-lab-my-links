package com.filipe.my_links.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.filipe.my_links.domain.model.Link;
import com.filipe.my_links.domain.repository.LinkRepository;
import com.filipe.my_links.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Transactional(readOnly = true)
    public List<Link> findAll() {
        return this.linkRepository.findAll();
    }

    @Transactional
    public Link create(Link link) {
        // TODO validations
        return this.linkRepository.save(link);
    }

    @Transactional
    public Link update(Long id, Link linkToUpdate) {
        // TODO validations
        // TODO DTO
        
        var link = this.linkRepository.findById(id).orElseThrow();
        link.setLink(linkToUpdate.getLink());
        link.setName(linkToUpdate.getName());
        link.setDescription(linkToUpdate.getDescription());
        link.setIcon(linkToUpdate.getIcon());
        link.setLink(linkToUpdate.getLink());

        return this.linkRepository.save(link);
    }

    @Transactional
    public void delete(Long id) {
        var link = this.linkRepository.findById(id).orElseThrow();
        this.linkRepository.delete(link);
    }
    
}
