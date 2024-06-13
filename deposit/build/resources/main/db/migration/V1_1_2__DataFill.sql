INSERT INTO mtsbank.bank_contribution (
closed_date,
contribution_account,
open_date,
pay_period,
period,
persent,
product_code,
return_code,
sum,
bank_user_id,
type_id)
VALUES
    ('2022-12-31','user1','2022-11-30','2022-12-31',null,12,'1234',null,10000,1,1),
    ('2022-12-31','user2','2022-11-30','2022-12-31',null,12,'1234',null,10000,1,2),
    (null,'user3','2022-11-30','2022-12-31',null,12,'1234',null,10000,1,1);

INSERT INTO mtsbank.contribution_type (type)
VALUES
    ('family'),
    ('home');

INSERT INTO mtsbank.contribution_contribution_type (id_contribution,id_contribution_type)
VALUES
    (1,1),
    (2,1),
    (3,2);