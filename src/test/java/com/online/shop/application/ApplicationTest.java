package com.online.shop.application;

import com.online.shop.application.controllers.CartController;
import com.online.shop.application.entities.Order;
import com.online.shop.application.entities.Product;
import com.online.shop.application.entities.ProductReview;
import com.online.shop.application.repositories.CategoryRepo;
import com.online.shop.application.repositories.OrderRepo;
import com.online.shop.application.repositories.ProductRepo;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.online.shop.application.TestBaseUtils.COMPUTERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@PropertySource("classpath:application.yml")
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartController cartController;
    private MockHttpSession mockHttpSession = new MockHttpSession();

    @Test
    @Transactional
    public void testBuying() throws Exception {
        getRequest("/");
        getRequest("/category/" + COMPUTERS.getId());
        List<Product> products = categoryRepo.getById(COMPUTERS.getId()).getProducts();
        mockMvc.perform(post("/cart/add/" + getIdOf(products, 0)).session(mockHttpSession))
                .andExpect(status().isOk());
        assertThat(getRequest("/cart/size")).isEqualTo("1");
        mockMvc.perform(post("/cart/add/" + getIdOf(products, 1)).session(mockHttpSession))
                .andExpect(status().isOk());
        assertThat(getRequest("/cart/size")).isEqualTo("2");
        getRequest("/cart/clear");
        mockMvc.perform(post("/cart/add/" + getIdOf(products, 1)).session(mockHttpSession))
                .andExpect(status().isOk());
        mockMvc.perform(post("/cart/add/" + getIdOf(products, 2)).session(mockHttpSession))
                .andExpect(status().isOk());
        getRequest("/cart/content");
        getRequest("/cart/checkout");
        mockMvc.perform(post("/cart/submit").session(mockHttpSession)
                .param("customerName", "Andris")
                .param("customerSurname", "Zacs")
                .param("address", "Visku 1")
                .param("bankAccount", "IBAN12321412"))
                .andExpect(status().isOk());
        List<Order> orders = orderRepo.findAll();
        assertThat(orders).size().isEqualTo(1);
        Order expected = expectedOrder(products);
        Order actual = orders.get(0);
        assertThat(actual.getPurchases()).size().isEqualTo(2);
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id", "purchases")
                .isEqualTo(expected);
        assertThat(getRequest("/cart/size")).isEqualTo("0");
    }

    @Test
    public void testAdminRights() throws Exception {
        getRequest("/");
        mockMvc.perform(post("/login").session(mockHttpSession)
                .param("username", "chadAdmin")
                .param("password", "abc123"))
                .andExpect(status().is3xxRedirection());
        String input = Stream.of(getRequest("/category/" + COMPUTERS.getId())
                .split("\\r?\\n"))
                .filter(line -> line.contains("Apple MacBook Air 13"))
                .findAny().orElseThrow(RuntimeException::new);
        Matcher m = Pattern.compile("/product/\\d+").matcher(input);
        String productUrl = "url";
        if (m.find()) productUrl = m.group();
        String product = mockMvc.perform(get(productUrl))
                .andReturn().getResponse()
                .getContentAsString();
        assertTrue(product.contains("Apple MacBook Air 13"));
        mockMvc.perform(post("/product/" + getIdOfProduct("Apple MacBook Air 13")).session(mockHttpSession)
                .param("name", "Apple MacBook Air 13_modified_name")
                .param("description", "Available in silver, space gray, and gold, the new thinner and lighter MacBook Air features a brilliant Retina display with True Tone technology, Touch ID, the latest-generation keyboard, and a Force Touch trackpad. The iconic wedge is created from 100 percent recycled aluminum, making it the greenest Mac ever.1 And with all-day battery life, MacBook Air is your perfectly portable, do-it-all notebook")
                .param("price", "1299.99")
                .param("category.id", String.valueOf(COMPUTERS.getId()))
                .param("properties", "Display: Retina display with True Tone")
                .param("properties", "Processor: 1.6GHz dual-core 8th-generation Intel Core i5 processor with Turbo Boost up to 3.6GHz")
                .param("properties", "Storage: 128GB SSD storage")
                .param("properties", "Graphics: Intel UHD Graphics 617")
                .session(mockHttpSession))
                .andExpect(status().is3xxRedirection());
        product = mockMvc.perform(get(productUrl))
                .andReturn().getResponse()
                .getContentAsString();
        assertTrue(product.contains("Apple MacBook Air 13_modified_name"));
        getRequest(String.format("/product/%s/%s/delete", COMPUTERS.getId(), productUrl.split("/")[2]));
        mockMvc.perform(get(productUrl))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testProductReview() throws Exception {
        mockMvc.perform(post("/login").session(mockHttpSession)
                .param("username", "typicalUser")
                .param("password", "strongPassword"))
                .andExpect(status().is3xxRedirection());
        long appleBookId = getIdOfProduct("Apple MacBook Air 13");
        mockMvc.perform(post("/product/review/" + appleBookId).session(mockHttpSession)
                .param("rating", "0")
                .param("content", "good stuff")
                .session(mockHttpSession))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(post("/product/review/" + appleBookId).session(mockHttpSession)
                .param("rating", "10")
                .param("content", "good stuff")
                .session(mockHttpSession))
                .andExpect(status().is3xxRedirection());
        final ProductReview[] review = new ProductReview[1];
        transactionTemplate.execute(s -> {
            review[0] = productRepo.getById(appleBookId).getReviews().get(0);
            return null;
        });
        assertThat(review[0].getRating()).isEqualTo(10);
        assertThat(review[0].getContent()).isEqualTo("good stuff");
    }

    private long getIdOfProduct(String productName) {
        return productRepo.findAll().stream()
                .filter(p -> p.getName().contains(productName))
                .map(Product::getId)
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    private String getRequest(String url) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(url).session(mockHttpSession))
                .andReturn();
        int st = mvcResult.getResponse().getStatus();
        assertTrue(st == HttpStatus.OK.value() || st == 302);
        return mvcResult.getResponse().getContentAsString();
    }

    private long getIdOf(List<Product> products, int i) {
        return products.get(i).getId();
    }

    private Order expectedOrder(List<Product> products) {
        Order order = new Order();
        order.setAddress("Visku 1");
        order.setBankAccount("IBAN12321412");
        order.setCustomerName("Andris");
        order.setCustomerSurname("Zacs");
        return order;
    }


}