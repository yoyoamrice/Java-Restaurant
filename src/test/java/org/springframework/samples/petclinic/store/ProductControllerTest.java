package org.springframework.samples.petclinic.store;


import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;


import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ProductController.class)
class ProductControllerTest {
	private static final Long TEST_PRODUCT_ID = 1L;
	@Autowired
	private MockMvc mockMvc; // Corrected field name
	@MockitoBean // Corrected annotation
	private ProductRepository productRepository; // Corrected field name
	@MockitoBean // Corrected annotation
	private ProductCategoryRepository categoryRepository; // Corrected field name
	private Product product; // Corrected field name
	@BeforeEach
	void setup() {
		product = new Product();
		product.setId(TEST_PRODUCT_ID);
		product.setName("Chair From China");
		product.setDomain("china.com");
		product.setQuantity(1);
		product.setPrice(BigDecimal.valueOf(1.0));
	}
@Test
@DisplayName("Should display product list with pagination") // Added DisplayName for clarity
void testShowProductList() throws Exception {
	// Corrected variable name from Pageable to pageable (lowercase)
	Pageable pageable = PageRequest.of(0, 5);
	// Corrected variable name from product to this.product
	Page<Product> productPage = new PageImpl<>(List.of(this.product), pageable, 1);
	given(this.productRepository.findAll(any(Pageable.class)))
		.willReturn(productPage);
	mockMvc.perform(get("/products").param("page", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listProducts"))
		.andExpect(view().name("products/productList"));
	// Optionally, verify that the service method was called
	verify(productRepository).findAll(any(Pageable.class));
}
}
