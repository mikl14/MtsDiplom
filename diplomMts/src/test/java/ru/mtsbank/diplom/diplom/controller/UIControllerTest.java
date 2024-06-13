package ru.mtsbank.diplom.diplom.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.mtsbank.diplom.diplom.TestsConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestsConfiguration.class)
public class UIControllerTest {
    @Autowired
    MockMvc mvc;

    /**
     * <b>testIndexGetNoneUser</b> - проверяет что если пользователь не имеет роль USER его перенаправит на страницу входа
     *
     * @throws Exception
     */
    @Test
    public void testIndexGetNoneUser() throws Exception {
        MvcResult result = mvc.perform(get("/index"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        // Проверяем, что перенаправление верное
        assertEquals("http://localhost/login", result.getResponse().getRedirectedUrl());
    }

    /**
     * <b>testAccountGetNoneUser</b> - проверяет что если пользователь не имеет роль USER его перенаправит на страницу входа
     *
     * @throws Exception
     */
    @Test
    public void testAccountGetNoneUser() throws Exception {
        MvcResult result = mvc.perform(get("/account"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        // Проверяем, что перенаправление верное
        assertEquals("http://localhost/login", result.getResponse().getRedirectedUrl());
    }

    /**
     * <b>testAccountPostNoneUser</b> - проверяет что если пользователь не имеет роль USER его перенаправит на страницу входа
     *
     * @throws Exception
     */
    @Test
    public void testAccountPostNoneUser() throws Exception {
        MvcResult result = mvc.perform(post("/account"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        // Проверяем, что перенаправление верное
        assertEquals("http://localhost/login", result.getResponse().getRedirectedUrl());
    }

    /**
     * <b>testContributionPostNoneUser</b> - проверяет что если пользователь не имеет роль USER его перенаправит на страницу входа
     *
     * @throws Exception
     */
    @Test
    public void testContributionPostNoneUser() throws Exception {
        MvcResult result = mvc.perform(post("/contribution"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        // Проверяем, что перенаправление верное
        assertEquals("http://localhost/login", result.getResponse().getRedirectedUrl());
    }

    /**
     * <b>testContributionGetNoneUser</b> - проверяет что если пользователь не имеет роль USER его перенаправит на страницу входа
     *
     * @throws Exception
     */
    @Test
    public void testContributionGetNoneUser() throws Exception {
        MvcResult result = mvc.perform(get("/contribution"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        // Проверяем, что перенаправление верное
        assertEquals("http://localhost/login", result.getResponse().getRedirectedUrl());
    }

    /**
     * <b>testSubmitSMSFalse</b> - проверяет что если пользователь ввел неправильный код смс его перенаправит на страницу вкладов
     *
     * @throws Exception
     */
    @Test
    @WithMockUser(roles = "USER")
    public void testSubmitSMSFalse() throws Exception {
        // Отправляем запрос
        MvcResult result = mvc.perform(post("/submitSMS")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("codeSMS", "4234"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        // Проверяем, что страница создания вкладки была успешно отображена
        assertEquals("/contribution", result.getResponse().getRedirectedUrl());
    }

    /**
     * <b>testCreateContribution</b> - проверяет что если пользователь начнет создавать вклад то его направит на страницу создания вкладов
     *
     * @throws Exception
     */
    @Test
    @WithMockUser(roles = "USER")
    public void testCreateContribution() throws Exception {
        // Отправляем POST-запрос
        MvcResult result = mvc.perform(post("/create_contribution"))
                .andExpect(status().isOk())
                .andReturn();

        // Проверяем, что страница создания вкладки была успешно отображена
        assertEquals("create_contribution", result.getModelAndView().getViewName());
    }

    /**
     * <b>testAccountPost</b> - проверяет что если пользователь вошел во вкладку со счетом через кнопку то отобразиться страница account
     *
     * @throws Exception
     */
    @Test
    @WithMockUser(roles = "USER")
    public void testAccountPost() throws Exception {
        // Отправляем GET-запрос
        MvcResult result = mvc.perform(post("/account"))
                .andExpect(status().isOk())
                .andReturn();

        // Проверяем, что страница подтверждения была успешно отображена
        assertEquals("account", result.getModelAndView().getViewName());
    }

    /**
     * <b>testAccountGet</b> - проверяет что если пользователь вошел во вкладку со счетом через ссылку то отобразиться страница account
     *
     * @throws Exception
     */
    @Test
    @WithMockUser(roles = "USER")
    public void testAccountGet() throws Exception {
        // Отправляем GET-запрос
        MvcResult result = mvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andReturn();

        // Проверяем, что страница подтверждения была успешно отображена
        assertEquals("account", result.getModelAndView().getViewName());
    }

    /**
     * <b>testIndexGet</b> - проверяет что если пользователь вошел на главную страницу через ссылку то отобразиться страница index
     *
     * @throws Exception
     */
    @Test
    @WithMockUser(roles = "USER")
    public void testIndexGet() throws Exception {
        // Отправляем GET-запрос
        MvcResult result = mvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andReturn();

        // Проверяем, что страница подтверждения была успешно отображена
        assertEquals("index", result.getModelAndView().getViewName());
    }
}
