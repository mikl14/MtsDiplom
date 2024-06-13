package ru.mtsbank.diplom.diplom.aggregator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ContributionType implements Serializable {
    @JsonProperty("id")
    Long id;
    @JsonProperty("type")
    String type;

    public ContributionType() {
    }

    public ContributionType(String type) {
        this.type = type;
    }
}
