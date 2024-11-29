package hu.lenartl.petstore.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.lenartl.petstore.order.OrderStatus;
import hu.lenartl.petstore.order.dto.OrderCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql("/init.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PostOrderIT {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Value("${api.key}")
    private String apiKeyValue;
    private final String apiKey = "x-api-key";

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    void givenCorrectOrder_expectSavedOrder() throws Exception {
        var content = new OrderCommand(1L, 1, LocalDateTime.now(), OrderStatus.PLACED.getDisplayValue(), false);

        mvc.perform(post("/store/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(content))
                        .header(apiKey, apiKeyValue))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void givenIncorrectOrder_expectBadRequest() throws Exception {
        var content = new OrderCommand(null, 1, LocalDateTime.now(), OrderStatus.PLACED.getDisplayValue(), false);

        mvc.perform(post("/store/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(content))
                        .header(apiKey, apiKeyValue))
                .andExpect(status().isBadRequest());
    }

}
