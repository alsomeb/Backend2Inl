insert into orders(id, customer_id, created, last_updated)
values
    (1, 1, current_timestamp(),  current_timestamp()),
    (2, 2, current_timestamp(),  current_timestamp()),
    (3, 2, current_timestamp(),  current_timestamp()),
    (4, 3, current_timestamp(),  current_timestamp());
