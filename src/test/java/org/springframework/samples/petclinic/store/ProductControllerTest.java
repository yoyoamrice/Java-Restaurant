package org.springframework.samples.petclinic.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.store.Product;
import org.springframework.samples.petclinic.store.ProductCategoryRepository;
import org.springframework.samples.petclinic.store.ProductController;
import org.springframework.samples.petclinic.store.ProductRepository;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the {@link ProductController}
 */
@Nested
@WebMvcTest(ProductController.class)
class ProductControllerTest {

	private static final Long TEST_PRODUCT_ID = 1L;

	@Autowired
	private MockMvc mockMvc;


	@MockitoBean
	private ProductRepository productRepository;

	private Product Product;


	@BeforeEach
	void setup() {
		// Create a dummy school to be returned by the mocked repository
		Product = new Product();
		Product.setId(TEST_PRODUCT_ID);
		Product.setName("Chair From China");
		Product.setDomain("china.com");
		Product.setQuantity(1);
		Product.setPrice(BigDecimal.valueOf(1.0));
	}

	@Test
	void testShowProductList() throws Exception {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Product> productPage = new PageImpl<>(List.of(Product), pageable, 1);

		// FIX: Stub the REPOSITORY, not the entity instance
		given(this.productRepository.findAll(any(Pageable.class))).willReturn(productPage);

		mockMvc.perform(get("/products").param("page", "1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("listProducts"))
			.andExpect(view().name("products/productList"));
	}
}

//@Test
//void testShowProductList() throws Exception {
//	// 1. Arrange: Create a "Page" of product to mock the database response
//	// matches the 5 items per page logic in your controller
//	Pageable pageable = PageRequest.of(0, 5);
//	Page<Product> productPage = new PageImpl<>(List.of(Product), pageable, 1);
//
//	// Tell the mock: "When the controller asks for all products, give them this list"
//	given(this.Product.findAll(any(Pageable.class))).willReturn(productPage);
//
//	// 2. Act & Assert: Perform the GET request and verify the results
//	mockMvc.perform(get("/products").param("page", "1"))
//		.andExpect(status().isOk())
//		.andExpect(model().attributeExists("listProducts"))
//		.andExpect(model().attributeExists("totalPages"))
//		.andExpect(model().attributeExists("currentPage"))
//		.andExpect(view().name("products/productList"));
//}
