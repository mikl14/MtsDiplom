ALTER TABLE ONLY mtsbank.bank_user
    ADD CONSTRAINT bank_user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY mtsbank.bank_operation
    ADD CONSTRAINT fk5qfduok45x8vdw78cro32kfx6 FOREIGN KEY (bank_account) REFERENCES mtsbank.bank_account(id);

ALTER TABLE ONLY mtsbank.bank_user
    ADD CONSTRAINT fkk8bqfnu39o2oi8wiiocbmbo2o FOREIGN KEY (bank_account_id) REFERENCES mtsbank.bank_account(id);