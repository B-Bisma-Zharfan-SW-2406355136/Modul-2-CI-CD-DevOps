package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
        this.productRepository.create(this.product);
    }

    @Test
    void testCreateAndFind() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(this.product.getProductId(), savedProduct.getProductId());
        assertEquals(this.product.getProductName(), savedProduct.getProductName());
        assertEquals(this.product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        ProductRepository productRepositoryEmpty = new ProductRepository();
        Iterator<Product> productIterator = productRepositoryEmpty.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product2 = new Product();
        product2.setProductId(UUID.fromString("a0f9de46-90b1-437d-a0bf-d0821dde9096"));
        product2.setProductName("Sampo Cap User");
        product2.setProductQuantity(50);
        this.productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(this.product.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }
}