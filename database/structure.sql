create table if not exists product(
    id uuid DEFAULT uuid_generate_v1(),
    pname varchar(50) not null,
    price float not null,
    CONSTRAINT   product_pk   PRIMARY KEY (id)
);