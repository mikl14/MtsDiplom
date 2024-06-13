package org.example.customerService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <b>BankOperation</b> - класс записи операции со счетом
 */
@Entity
public class BankOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    Long id;
    @JsonProperty("sum")
    BigDecimal sum;
    @JsonProperty("type")
    String type;
    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    @ManyToOne(targetEntity = BankAccount.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bank_account")
    @JsonIgnoreProperties("bankAccount")
    BankAccount bankAccount;

    public BankOperation(BigDecimal sum, String type, LocalDate date) {
        this.sum = sum;
        this.type = type;
        this.date = date;
    }

    public BankOperation() {
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonBackReference
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
