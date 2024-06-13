package ru.mtsbank.diplom.diplom.aggregator.entity;

import org.springframework.stereotype.Component;

/**
 * <b>InputData</b> - содержит данные полученые с Ui форм
 */
@Component
public class InputData {
    private String text;
    private String codeSMS;

    public InputData() {
    }

    public InputData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCodeSMS() {
        return codeSMS;
    }

    public void setCodeSMS(String codeSMS) {
        this.codeSMS = codeSMS;
    }
}
