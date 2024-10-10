package com.filipe.my_links.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.filipe.my_links.domain.model.Link;
import com.filipe.my_links.service.LinkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/links")
@Tag(name = "Links", description = "Retrieve a list of all registered links")
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    @Operation(summary = "Get all links", description = "Retrieve a list of all registered links")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<Link>> findAll() {
        return ResponseEntity.ok(this.linkService.findAll());
    }

    @PostMapping
    @Operation(summary = "Create a new link", description = "Create a new link and return the created link's data")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Link created successfully"),
        @ApiResponse(responseCode = "422", description = "Invalid link data provided")
    })
    public ResponseEntity<Link> create(@RequestBody Link linkToCreate) {
        var link = this.linkService.create(linkToCreate);
        // URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        //     .path("/{id}")
        //     .buildAndExpand(category.link())
        //     .toUri();
        return ResponseEntity.created(null).body(link);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Link> update(@PathVariable Long id, @RequestBody Link linkToUpdate) {
        var link = this.linkService.update(id, linkToUpdate);
        return ResponseEntity.ok(link);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a link", description = "Delete an existing link based on its ID")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "Link deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Link not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.linkService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
