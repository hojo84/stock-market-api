drop table if exists companies CASCADE;
drop table if exists exchanges CASCADE;
drop table if exists listings CASCADE;

create table companies
(
    id          varchar(255) not null,
    equity_type varchar(255),
    name        varchar(255),
    sector      varchar(255),
    primary key (id)
);

create table exchanges
(
    id       varchar(255) not null,
    currency varchar(255),
    location varchar(255),
    name     varchar(255),
    website  varchar(255),
    primary key (id)
);

create table listings
(
    exchange_id varchar(255) not null,
    company_id  varchar(255) not null,
    primary key (exchange_id, company_id)
);

alter table listings
    add constraint fk_listings_companies foreign key (company_id) references companies;
alter table listings
    add constraint fk_listings_exchanges foreign key (exchange_id) references exchanges;