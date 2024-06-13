package ru.mtsbank.diplom.diplom.aggregator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <b>BankOperation</b> - являеться классом записи об операции со счетом собираеться из json отправленного внешним серисом
 */
public class BankOperation {

    @JsonProperty("id")
    Long id;
    @JsonProperty("sum")
    BigDecimal sum;
    @JsonProperty("type")
    String type;
    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    public BankOperation(BigDecimal sum, String type, LocalDate date) {
        this.sum = sum;
        this.type = type;
        this.date = date;
    }

    public BankOperation() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }


}
