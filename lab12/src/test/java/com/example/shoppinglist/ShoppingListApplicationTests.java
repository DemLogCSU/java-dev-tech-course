package com.example.shoppinglist;

import com.example.shoppinglist.controllers.ShoppingApiController;
import com.example.shoppinglist.controllers.ShoppingController;
import com.example.shoppinglist.models.Product;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ShoppingListApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ShoppingController shoppingController;
	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;
	@Test
	void contextLoads() {
	}
	@Test
	public void test() throws Exception {
		this.mockMvc.perform(get("/products/list"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Shopping List")));

	}
	@Test
	public void test2() throws Exception {
		this.mockMvc.perform(get("/products/list"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(xpath("//*[@id=\"btn-re-add\"]").string("Add new product"));

	}
	@Test
	public void test3() throws Exception {
		this.mockMvc.perform(get("/products/add"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(xpath("//*[@id=\"bt-add-product\"]").string("Add Product"));

	}
	@Test
	public void test4() throws Exception {
		productService.save("one");
		productService.save("two");
		productService.save("three");
		this.mockMvc.perform(get("/products/list"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(xpath("//*[@id=\"shopping-list\"]/div").nodeCount(3));

	}


	@Test
	public void addProduct(){
		String productTitle = "молоко";
		Product savedProduct = productService.save(productTitle);
		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct.getName().equals(productTitle));

		Mockito.verify(productRepository, Mockito.times(1)).save(savedProduct);
	}

	@Test
	public void deleteProduct(){
		Product product = new Product(3L, "ветчина");
		Long id = 3L;
		Mockito.doReturn(product).when(productRepository).findById(id);
		Long deleteResult = productService.delete(id);
		assertThat(deleteResult).isEqualTo(0);
	}

}
