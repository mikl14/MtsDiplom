package ru.mtsbank.diplom.diplom.aggregator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class Contribution implements Serializable {
    @JsonProperty("id")
    Long id;
    @JsonProperty("bankUserId")
    Long bankUserId;
    @JsonProperty("productCode")
    String productCode;
    @JsonProperty("contributionAccount")
    String contributionAccount;
    @JsonProperty("returnCode")
    String returnCode;
    @JsonProperty("period")
    String period;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("payPeriod")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate payPeriod;
    @JsonProperty("openDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate openDate;
    @JsonProperty("closedDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate closedDate;
    @JsonProperty("sum")
    Long sum;
    @JsonProperty("persent")
    int persent;

    @JsonProperty("type")
    ContributionType type;

    public Contribution(String productCode, String contributionAccount, String returnCode, String period, LocalDate payPeriod, LocalDate openDate, LocalDate closedDate, ContributionType type, Long sum, int persent) {
        this.productCode = productCode;
        this.contributionAccount = contributionAccount;
        this.returnCode = returnCode;
        this.period = period;
        this.payPeriod = payPeriod;
        this.openDate = openDate;
        this.closedDate = closedDate;
        this.sum = sum;
        this.persent = persent;
    }

    public Contribution() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankUserId() {
        return bankUserId;
    }

    public void setBankUserId(Long bankUserId) {
        this.bankUserId = bankUserId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getContributionAccount() {
        return contributionAccount;
    }

    public void setContributionAccount(String contributionAccount) {
        this.contributionAccount = contributionAccount;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public LocalDate getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(LocalDate payPeriod) {
        this.payPeriod = payPeriod;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public int getPersent() {
        return persent;
    }

    public void setPersent(int persent) {
        this.persent = persent;
    }
}
