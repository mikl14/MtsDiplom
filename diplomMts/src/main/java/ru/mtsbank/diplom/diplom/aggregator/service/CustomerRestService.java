package ru.mtsbank.diplom.diplom.aggregator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.mtsbank.diplom.diplom.aggregator.entity.AccountInfo;
import ru.mtsbank.diplom.diplom.aggregator.entity.BankOperation;
import ru.mtsbank.diplom.diplom.aggregator.entity.Contribution;

import java.util.List;

public class CustomerRestService {

    private final String costumerAddress;

    private final String depositAddress;

    private AccountInfo accountInfo;

    private final ObjectMapper mapper;

    public CustomerRestService(String costumerAddress, String depositAddress, AccountInfo accountInfo, ObjectMapper mapper) throws JsonProcessingException {
        this.accountInfo = accountInfo;
        this.mapper = mapper;
        this.costumerAddress = costumerAddress;
        this.depositAddress = depositAddress;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    /**
     * <b>sendRequest</b> - метод отправки запросов к сервисам
     *
     * @param url
     * @param body
     * @return
     */
    public String sendRequest(String url, String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    /**
     * <b>getUserToken</b> - запрашивает и возвращает токен пользователя из сервиса.
     *
     * @return
     */
    public String getUserToken() {

        String url = costumerAddress + "getUserToken"; // URL микросервиса
        String body = accountInfo.getUsername(); // Тело запроса в формате JSON

        return sendRequest(url, body);
    }

    /**
     * <b>getUserPassword</b> - запрашивает и возвращает пароль для подтверждения аунтификации пользователя из сервиса.
     *
     * @return
     */
    public String getUserPassword() throws JsonProcessingException {

        String url = costumerAddress + "getPassword"; // URL микросервиса
        String body = accountInfo.getUsername(); // Тело запроса в формате JSON

        return sendRequest(url, body);
    }

    /**
     * <b>getUserSum</b> - запрашивает и возвращает сумму доступную на счету пользователя.
     *
     * @return
     */
    public String getUserSum() throws JsonProcessingException {

        String url = costumerAddress + "getSum"; // URL микросервиса
        String body = accountInfo.getUsername(); // Тело запроса в формате JSON

        return sendRequest(url, body);
    }

    /**
     * <b>setUserSum</b> - изменяет сумму доступную на счету пользователя.
     *
     * @param value
     * @return
     */
    public String setUserSum(Long value) throws JsonProcessingException {

        String url = costumerAddress + "setSum"; // URL микросервиса
        String body = accountInfo.getUsername() + ":" + value; // Тело запроса в формате JSON

        String response = sendRequest(url, body);

        accountInfo.setSum(Long.parseLong(response));
        return response;
    }

    /**
     * <b>getHistory</b> - запрашивает и возвращает историю транзакций пользователя из сервиса.
     *
     * @return
     */
    public  List<BankOperation> getHistory() throws JsonProcessingException {

        String url = costumerAddress + "getHistory"; // URL микросервиса
        String body = accountInfo.getUsername(); // Тело запроса в формате JSON

        String response = sendRequest(url, body);
        List<BankOperation> bankOperationList = mapper.readValue(response, new TypeReference<List<BankOperation>>() {
        });
        accountInfo.setOperationList(bankOperationList);

        return bankOperationList;
    }

    /**
     * <b>setHistory</b> - изменяет историю транзакций пользователя из сервиса.
     *
     * @return
     */
    public String setHistory() throws JsonProcessingException {
        String url = costumerAddress + "setHistory"; // URL микросервиса
        List<BankOperation> operationList = accountInfo.getOperationList();
        String body = accountInfo.getUsername() + "`" + mapper.writeValueAsString(List.of(operationList.get(operationList.size() - 1))); // Тело запроса в формате JSON

        return sendRequest(url, body);
    }

    /**
     * <b>getDeposits</b> - запращивает и возвращает список всех вкладов текущего пользователя
     *
     * @return List<Contribution>
     * @throws JsonProcessingException
     */
    public List<Contribution> getDeposits() throws JsonProcessingException {
        String url = depositAddress + "getContributionList"; // URL микросервиса
        String body = accountInfo.getUsername(); // Тело запроса в формате JSON

        String response = sendRequest(url, body);
        List<Contribution> contributionList = mapper.readValue(response, new TypeReference<List<Contribution>>() {
        });

        return contributionList;
    }

    /**
     * <b>setContribution</b> - дополняет список всех вкладов текущего пользователя в базе запросом к сервису
     *
     * @return List<Contribution>
     * @throws JsonProcessingException
     */
    public void setContribution(Contribution contribution) throws JsonProcessingException {
        String url = depositAddress + "setContribution"; // URL микросервиса

        String body = mapper.writeValueAsString(contribution); // Тело запроса в формате JSON

        sendRequest(url, body);
    }
}
