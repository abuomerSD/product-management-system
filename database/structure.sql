create table if not exists product(
    id int not null default  uuid_generate_v4(),
    pname varchar(50) not null,
    price float not null,
    image BYTEA not null,
    CONSTRAINT   product_pk   PRIMARY KEY (id)
);