package org.example.customerService.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>BankAccount</b> - класс личного счета пользователя
 */
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long sum;

    @OneToMany(mappedBy = "bankAccount", targetEntity = BankOperation.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<BankOperation> operations = new ArrayList<>();

    public BankAccount(Long sum, List<BankOperation> operations) {
        this.sum = sum;
        this.operations = operations;
    }

    public BankAccount() {
    }

    public void refill(Long value) {
        sum = sum + value;
    }

    public void takeOff(Long value) {
        sum = sum - value;
    }

    public Long getSum() {
        return sum;
    }

    @JsonManagedReference
    public List<BankOperation> getOperations() {
        return operations;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public void setOperations(List<BankOperation> operations) {
        this.operations = operations;
    }

}
