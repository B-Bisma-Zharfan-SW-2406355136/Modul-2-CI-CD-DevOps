package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    private Product sampleProduct;

    @BeforeEach
    void setUp(){
        this.sampleProduct = new Product();
        this.sampleProduct.setProductName("Sampo Cap Bambang");
        this.sampleProduct.setProductQuantity(100);
    }

    @Test
    void testCreateProductPage(){
        String viewName = productController.createProductPage(model);

        assertEquals("createProduct", viewName);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost(){
        String viewName = productController.createProductPost(sampleProduct, model);

        assertEquals(viewName, "redirect:list");
        verify(productService, times(1)).create(sampleProduct);
    }

    @Test
    void testproductListPage(){
        List<Product> productList = new ArrayList<>();
        productList.add(sampleProduct);
        when(productService.findAll()).thenReturn(productList);
        String viewName = productController.productListPage(model);

        assertEquals(viewName, "productList");
        verify(model, times(1)).addAttribute("products", productList);
        verify(productService, times(1)).findAll();
    }
}
