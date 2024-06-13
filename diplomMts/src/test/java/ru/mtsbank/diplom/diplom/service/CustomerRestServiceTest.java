package ru.mtsbank.diplom.diplom.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mtsbank.diplom.diplom.TestsConfiguration;
import ru.mtsbank.diplom.diplom.aggregator.service.CustomerRestService;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestsConfiguration.class)
public class CustomerRestServiceTest {
    @Autowired
    private CustomerRestService customerRestService;

    /**
     * <b>getPasswordTest</b> - Проверяет метод получения пароля для текущего пользователя.
     */
    @Test
    void getPasswordTest() {
        try {
            Assertions.assertEquals(customerRestService.getUserPassword(), customerRestService.getAccountInfo().getPassword());
        } catch (JsonProcessingException e) {
            Assertions.fail("Exception " + e);
        }
    }

    /**
     * <b>getUserTokenTest</b> - Проверяет метод получения токена для текущего пользователя.
     */
    @Test
    void getUserTokenTest() {
        Assertions.assertFalse(customerRestService.getUserToken().isEmpty());
    }

    /**
     * <b>getHistoryTest</b> - Проверяет метод получения истории счета для текущего пользователя.
     */
    @Test
    void getHistoryTest() throws JsonProcessingException {
        Assertions.assertTrue(customerRestService.getHistory().size() > 1);
    }

    /**
     * <b>getDepositsTest</b> - Проверяет метод получения списка вкладов для текущего пользователя.
     */
    @Test
    void getDepositsTest() throws JsonProcessingException {
        Assertions.assertTrue(customerRestService.getDeposits().size() > 1);
    }
}
