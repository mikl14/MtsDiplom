package ru.mtsbank.diplom.diplom.aggregator.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import ru.mtsbank.diplom.diplom.aggregator.entity.AccountInfo;
import ru.mtsbank.diplom.diplom.aggregator.service.CustomerRestService;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

    @Value("${service.costumer-url}")
    private String costumerAddress;
    @Value("${service.deposit-url}")
    private String depositAddress;

    @Bean
    public CustomerRestService customerRestService() throws JsonProcessingException {
        return new CustomerRestService(costumerAddress,depositAddress,new AccountInfo(),objectMapper());
    }
    @Bean
    @Scope("singleton")
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }
}
