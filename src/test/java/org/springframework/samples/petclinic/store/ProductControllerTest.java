package org.springframework.samples.petclinic.store;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(ProductController.class)
class ProductControllerTest {

//	private static final Long TEST_PRODUCT_ID = 1L;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockitoBean
//	private ProductRepository productRepository;
//	@MockitoBean
//	private ProductCategoryRepository categoryRepository;
//
//	private Product product;
//
//	@BeforeEach
//	void setup() {
//		product = new Product();
//		product.setId(TEST_PRODUCT_ID);
//		product.setName("Chair From China");
//		product.setDomain("china.com");
//		product.setQuantity(1);
//		product.setPrice(BigDecimal.valueOf(1.0));
//	}
//
//	@Test
//	void testShowProductList() throws Exception {
//		Pageable pageable = PageRequest.of(0, 5);
//
//		Page<Product> productPage =
//			new PageImpl<>(List.of(product), pageable, 1);
//
//		given(this.productRepository.findAll(any(Pageable.class)))
//			.willReturn(productPage);
//
//		mockMvc.perform(get("/products").param("page", "1"))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeExists("listProducts"))
//			.andExpect(view().name("products/productList"));
//	}
}
