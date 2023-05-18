-- DATA SEED

-- Customers
insert into customers(id, first_name, last_name, ssn, created, last_updated)
values
    (1, 'Daniel', 'Fellden', '8505243241', current_timestamp(), current_timestamp()),
    (2, 'Alex', 'Brun', '5052412416', current_timestamp(), current_timestamp()),
    (3, 'Ceasar', 'Olle', '8505241241', current_timestamp(), current_timestamp());