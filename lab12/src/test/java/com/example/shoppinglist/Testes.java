package com.example.shoppinglist;

import com.example.shoppinglist.controllers.ShoppingApiController;
import com.example.shoppinglist.models.Product;
import com.example.shoppinglist.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Testes {
    @Autowired
    private ShoppingApiController shoppingApiController;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void rest() throws Exception{
        assertThat(shoppingApiController).isNotNull();
    }
    @Test
    public void listOfProducts() throws Exception {
        this.mockMvc.perform(get("/api/products/"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void getProductById() throws Exception {
        long id = 1;
        Product product = productRepository.findById(id);

        this.mockMvc.perform(get("/api/products/" + id)
                        .contentType(APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"apple\",\"isPurchased\":\"false\"}"))
                .andDo(print())
                .andExpect(content().string(containsString(product.getName())))
                .andExpect(status().isOk());
    }
}
