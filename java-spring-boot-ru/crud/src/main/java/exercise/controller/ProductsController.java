package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;
import exercise.repository.CategoryRepository;
import exercise.model.Product;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getOne(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return productMapper.toDto(product);
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductCreateDto dto) {
        try {
            Product product = productMapper.toEntity(dto);
            Product saved = productRepository.save(product);
            return new ResponseEntity<>(productMapper.toDto(saved), HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category ID");
        }
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id, @RequestBody ProductCreateDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        try {
            productMapper.update(dto, product);
            return productMapper.toDto(productRepository.save(product));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category ID");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        productRepository.delete(product);
    }
    // END
}
