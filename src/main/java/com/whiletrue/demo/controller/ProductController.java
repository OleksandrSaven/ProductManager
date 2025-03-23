package com.whiletrue.demo.controller;

import com.whiletrue.demo.dto.CreateProductRequestDto;
import com.whiletrue.demo.dto.ProductDto;
import com.whiletrue.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Product management", description = "Endpoints for managing products")
@RequiredArgsConstructor
@RequestMapping(value = "products")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Create new product", description = "Return saved product")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')")
    public ProductDto save(@Valid @RequestBody CreateProductRequestDto createProductRequestDto) {
        return productService.save(createProductRequestDto);
    }

    @Operation(summary = "Find product by id", description = "Return product")
    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @Operation(summary = "Find all products", description = "Return page products")
    @GetMapping
    public Page<ProductDto> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @Operation(summary = "Change information about product", description = "Return changed product")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')")
    public ProductDto update(@PathVariable Long id,
                             @Valid @RequestBody CreateProductRequestDto requestDto) {
        return productService.update(id, requestDto);
    }

    @Operation(summary = "Delete product")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
