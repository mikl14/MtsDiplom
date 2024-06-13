package ru.mtsbank.diplom.diplom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.mtsbank.diplom.diplom.aggregator.entity.AccountInfo;
import ru.mtsbank.diplom.diplom.aggregator.service.CustomerRestService;

@TestConfiguration

public class TestsConfiguration {
    @Value("${service.costumer-url}")
    private String costumerAddress;
    @Value("${service.deposit-url}")
    private String depositAddress;
    @Primary
    @Bean
    public CustomerRestService testCustomerRestService(ObjectMapper objectMapper) throws JsonProcessingException {
        AccountInfo accountInfo = new AccountInfo();

        accountInfo.setUsername("user3");
        accountInfo.setPassword("password3");

        return new CustomerRestService(costumerAddress,depositAddress,accountInfo,objectMapper);
    }

}