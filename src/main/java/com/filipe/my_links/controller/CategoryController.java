package com.filipe.my_links.controller;

import java.util.List;
import java.net.URI;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.filipe.my_links.domain.model.Category;
import com.filipe.my_links.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/categories")
@Tag(name = "Cotegories", description = "Retrieve a list of all registered categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieve a list of all registered categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<Category>> findAll() {
        var categories = this.categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Create a new catetory and return the created category's data")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Category created successfully"),
        @ApiResponse(responseCode = "422", description = "Invalid category data provided")
    })
    public ResponseEntity<Category> create(@RequestBody Category categoryToCreate) {
        var category = this.categoryService.create(categoryToCreate);
        // URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        //     .path("/{id}")
        //     .buildAndExpand(category.getId())
        //     .toUri();
        return ResponseEntity.created(null).body(category);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "Update the data of an existing catetory based on its ID")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Catetory updated successfully"),
        @ApiResponse(responseCode = "404", description = "Catetory not found"),
        @ApiResponse(responseCode = "422", description = "Invalid catetory data provided")
    })
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category categoryToUpdate) {
        var category = this.categoryService.update(id, categoryToUpdate);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Delete an existing category based on its ID")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
