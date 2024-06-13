package ru.mtsbank.diplom.diplom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@Import(TestsConfiguration.class)
class DiplomApplicationTests {
    @Test
    void contextLoads() {
    }
}
