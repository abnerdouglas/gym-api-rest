package br.com.apirest.gym.service;

import br.com.apirest.gym.entity.Product;
import br.com.apirest.gym.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
         return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product updatedProducted) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProducted.getName());
            product.setPrice(updatedProducted.getPrice());
            product.setQuantity(updatedProducted.getQuantity());

            return productRepository.save(product);
        } else {
            throw new RuntimeException("Produto não foi encontrado");
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
