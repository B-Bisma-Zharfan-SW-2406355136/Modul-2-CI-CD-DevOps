package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        this.product1 = new Product();
        this.product1.setProductId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        this.product1.setProductName("Sampo Cap Bambang");
        this.product1.setProductQuantity(100);

        this.product2 = new Product();
        this.product2.setProductId(UUID.fromString("a0f9de46-90b1-437d-a0bf-d0821dde9096"));
        this.product2.setProductName("Sampo Cap User");
        this.product2.setProductQuantity(50);
    }

    @Test
    void testCreateProduct(){
        productService.create(this.product1);
        verify(productRepository, times(1)).create(product1);
    }

    @Test
    void testFindAllProduct(){
        // Arrange
        List<Product> productList = Arrays.asList(product1, product2);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        // Act
        List<Product> result = productService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Sampo Cap Bambang", result.get(0).getProductName());
        assertEquals("Sampo Cap User", result.get(1).getProductName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindAllIfEmpty() {
        List<Product> emptyList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(emptyList.iterator());

        List<Product> result = productService.findAll();

        assertTrue(result.isEmpty());

        verify(productRepository, times(1)).findAll();
    }
}