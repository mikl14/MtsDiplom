create schema IF NOT EXISTS mtsbank;

CREATE TABLE IF NOT EXISTS mtsbank.bank_account (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    sum bigint
);

CREATE TABLE IF NOT EXISTS mtsbank.bank_user (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255),
    bank_account_id bigint
);

CREATE TABLE IF NOT EXISTS mtsbank.bank_operation (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    date date,
    sum numeric(19,2),
    type character varying(255),
    bank_account bigint
);

CREATE TABLE IF NOT EXISTS mtsbank.role (
     id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     role_name character varying(255)
);

CREATE TABLE IF NOT EXISTS mtsbank.user_roles (
     user_id BIGINT NOT NULL,
     role_id BIGINT NOT NULL
);
