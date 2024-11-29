package hu.lenartl.petstore.it;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql("/init.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GetOrderByIdIT {

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
    void givenCorrectId_shouldReturnOrder() throws Exception {
        long id = 1L;

        mvc.perform(get("/store/order/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(apiKey, apiKeyValue))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void givenIncorrectId_expectNotFound() throws Exception {

        long id = Long.MAX_VALUE;

        mvc.perform(get("/store/order/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(apiKey, apiKeyValue))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenInvalidIdFormat_expectBadRequest() throws Exception {

        String id = "invalid";

        mvc.perform(get("/store/order/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(apiKey, apiKeyValue))
                .andExpect(status().isBadRequest());
    }

}
