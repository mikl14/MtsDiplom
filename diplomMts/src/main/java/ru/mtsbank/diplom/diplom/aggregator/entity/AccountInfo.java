package ru.mtsbank.diplom.diplom.aggregator.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>AccountInfo - хранит информацию о пользователе и счете полученую из других сервисов</b>
 */
@Component
public class AccountInfo {
    private String username;

    private String password;

    private Long sum;

    private Long neededSum;
    private List<BankOperation> operationList = new ArrayList<>();

    public AccountInfo() {
        sum = 0l;
    }

    public Long getNeededSum() {
        return neededSum;
    }

    public void setNeededSum(Long neededSum) {
        this.neededSum = neededSum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public List<BankOperation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<BankOperation> operationList) {
        this.operationList = operationList;
    }

    public void updateOperationList(List<BankOperation> operationList) {
        this.operationList.addAll(operationList);
    }

    public void addOperationList(BankOperation operation) {
        this.operationList.add(operation);
    }
}
