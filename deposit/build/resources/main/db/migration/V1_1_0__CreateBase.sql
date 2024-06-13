create schema IF NOT EXISTS mtsbank;

CREATE TABLE IF NOT EXISTS mtsbank.bank_contribution (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    closed_date date,
    contribution_account character varying(255),
    open_date date,
    pay_period date,
    period character varying(255),
    persent integer NOT NULL,
    product_code character varying(255),
    return_code character varying(255),
    sum bigint,
    bank_user_id bigint,
    type_id bigint
);


CREATE TABLE IF NOT EXISTS mtsbank.contribution_contribution_type (
   id_contribution bigint NOT NULL,
   id_contribution_type bigint
);


CREATE TABLE IF NOT EXISTS mtsbank.contribution_type (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    type character varying(255)
);

