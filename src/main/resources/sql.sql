create sequence role_id_seq;

alter sequence role_id_seq owner to postgres;

create sequence category_id_seq;

alter sequence category_id_seq owner to postgres;

create sequence product_category_id_seq;

alter sequence product_category_id_seq owner to postgres;

create sequence products_id_seq;

alter sequence products_id_seq owner to postgres;

create sequence users_id_seq;

alter sequence users_id_seq owner to postgres;

create sequence product_category_id_seq1;

alter sequence product_category_id_seq1 owner to postgres;

create table role
(
    id          integer not null
        constraint role_pk
            primary key,
    name        varchar,
    description varchar
);

alter table role
    owner to postgres;

create table users
(
    id         bigint  not null
        constraint users_pk
            primary key,
    name       varchar,
    role_id    integer not null
        constraint users_role_id_fk
            references role,
    address    varchar,
    created_at timestamp,
    updated_at timestamp
);

alter table users
    owner to postgres;

create table products
(
    id          integer   not null
        constraint products_pk
            primary key,
    name        varchar,
    brand_id    integer   not null,
    description varchar,
    price       integer,
    created_at  timestamp not null,
    updated_at  timestamp not null
);

alter table products
    owner to postgres;

create table product_category
(
    product_id  integer,
    category_id integer,
    id          integer generated always as identity
        constraint product_category_pk
            primary key
);

alter table product_category
    owner to postgres;

alter sequence product_category_id_seq1 owned by product_category.id;

create table category
(
    id   integer not null,
    name varchar
);

alter table category
    owner to postgres;

