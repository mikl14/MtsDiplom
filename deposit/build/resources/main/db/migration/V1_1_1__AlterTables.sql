
ALTER TABLE ONLY mtsbank.contribution_contribution_type
    ADD CONSTRAINT contribution_contribution_type_pkey PRIMARY KEY (id_contribution);


ALTER TABLE ONLY mtsbank.contribution_contribution_type
    ADD CONSTRAINT fkspykq1mcd4algaf4jtals39aq FOREIGN KEY (id_contribution) REFERENCES mtsbank.bank_contribution(id);

ALTER TABLE ONLY mtsbank.contribution_contribution_type
    ADD CONSTRAINT fkt1mogtwm956lj3xhx8yxk9mvw FOREIGN KEY (id_contribution_type) REFERENCES mtsbank.contribution_type(id);

