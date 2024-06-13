package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc

public class RestControllerTest {
    @Value("${test.username}")
    String username;

    @Autowired
    MockMvc mvc;

    /**
     * <b>getTokenTest</b> - проверяет доступность метода получения токена пользователя
     *
     * @throws JsonProcessingException
     */
    @Test
    public void getTokenTest() throws Exception {

        mvc.perform(post("/customer/api/getToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(username))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    /**
     * <b>getPasswordTest</b> - проверяет доступность метода получения пароля пользователя
     *
     * @throws JsonProcessingException
     */
    @Test
    public void getPasswordTest() throws Exception {

        mvc.perform(post("/customer/api/getPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(username))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    /**
     * <b>getSumTest</b> - проверяет доступность метода получения суммы на счету пользователя
     *
     * @throws JsonProcessingException
     */
    @Test
    @WithMockUser(username = "user3", roles = "USER")
    public void getSumTest() throws Exception {

        mvc.perform(post("/customer/api/getSum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(username))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    /**
     * <b>getHistoryTest</b> - проверяет доступность метода получения истории операций пользователя
     *
     * @throws JsonProcessingException
     */
    @Test
    @WithMockUser(username = "user3", roles = "USER")
    public void getHistoryTest() throws Exception {

        mvc.perform(post("/customer/api/getHistory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(username))
                .andExpect(status().isForbidden())
                .andReturn();
    }
}
