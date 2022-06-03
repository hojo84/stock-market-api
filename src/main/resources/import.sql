--Hibernate: create table countries (id bigint generated by default as identity, name varchar(255), primary key (id))
insert into countries (id, name) values(1, 'United States');
insert into countries (id, name) values(2, 'China');
insert into countries (id, name) values(3, 'United Kingdom');
insert into countries (id, name) values(4, 'Germany');
insert into countries (id, name) values(5, 'Hungary');

--Hibernate: create table exchanges (id bigint generated by default as identity, currency varchar(255), location varchar(255), iso_mic varchar(255), name varchar(255), website varchar(255), country_id bigint, primary key (id))
insert into exchanges (id, currency, location, iso_mic, name, website, country_id) values(1, 'USD', 'New York', 'NYSE', 'New York Stock Exchange', 'www.nyse.com', 1);
insert into exchanges (id, currency, location, iso_mic, name, website, country_id) values(2, 'USD', 'New York', 'XNAS', 'Nasdaq Stock Market', 'www.nasdaq.com', 1);
insert into exchanges (id, currency, location, iso_mic, name, website, country_id) values(3, 'CNY', 'Shanghai', 'SSE', 'Shanghai Stock Exchange', 'english.sse.com.cn', 2);
insert into exchanges (id, currency, location, iso_mic, name, website, country_id) values(4, 'HUF', 'Budapest', 'BUD', 'Budapest Stock Exchange Index', 'bet.hu', 5);

--Hibernate: create table indices (id bigint generated by default as identity, constituents integer not null, name varchar(255), symbol varchar(255), exchange_id bigint, primary key (id))
insert into indices (id, constituents, name, symbol, exchange_id) values (1, 600, 'SSE Composite Index', 'SSE', 3);
insert into indices (id, constituents, name, symbol, exchange_id) values (2, 500, 'S&P 500', 'SPX', 1);
insert into indices (id, constituents, name, symbol, exchange_id) values (3, 100, 'Financial Times Stock Exchange 100 Index', 'FTSE', 3);
insert into indices (id, constituents, name, symbol, exchange_id) values (4, 20, 'Budapest Stock Exchange Index', 'BUX', 4);

--Hibernate: create table stocks (id bigint generated by default as identity, company_name varchar(255), equity_type varchar(255), industry varchar(255), sector varchar(255), ticker_symbol varchar(255), primary key (id))
insert into stocks (id, company_name, equity_type, industry, sector, ticker_symbol) values (1, 'Apple', 'COMMON_STOCK', 'Computer Hardware', 'Information Technology','AAPL');
insert into stocks (id, company_name, equity_type, industry, sector, ticker_symbol) values (2, 'OTP Nyrt', 'COMMON_STOCK', 'Banks', 'Financials','OTP');
insert into stocks (id, company_name, equity_type, industry, sector, ticker_symbol) values (3, 'MOL Nyrt', 'COMMON_STOCK', 'Oil', 'Refinery','MOL');
insert into stocks (id, company_name, equity_type, industry, sector, ticker_symbol) values (4, 'Nvidia', 'COMMON_STOCK', 'Computer Hardware', 'Information Technology','NVDA');
insert into stocks (id, company_name, equity_type, industry, sector, ticker_symbol) values (5, 'Workday', 'COMMON_STOCK', 'Software', 'Information Technology','WDAY');
insert into stocks (id, company_name, equity_type, industry, sector, ticker_symbol) values (6, 'UGI', 'COMMON_STOCK', 'Gas', 'Utilities','UGI');

--Hibernate: create table index_components (id bigint generated by default as identity, component_weight float not null, index_id bigint, stock_id bigint, primary key (id))
insert into index_components (id, component_weight, index_id, stock_id) values (1, 1.2, 2, 1);
insert into index_components (id, component_weight, index_id, stock_id) values (2, 10.2, 4, 2);
insert into index_components (id, component_weight, index_id, stock_id) values (3, 8.4, 4, 3);
insert into index_components (id, component_weight, index_id, stock_id) values (4, 3.6, 2, 4);
insert into index_components (id, component_weight, index_id, stock_id) values (5, 4.4, 2, 5);

--Hibernate: create table listings (exchange_id bigint not null, stock_id bigint not null)
insert into listings (exchange_id, stock_id) values (2, 1);
insert into listings (exchange_id, stock_id) values (4, 2);
insert into listings (exchange_id, stock_id) values (4, 3);
insert into listings (exchange_id, stock_id) values (2, 4);
insert into listings (exchange_id, stock_id) values (2, 5);
insert into listings (exchange_id, stock_id) values (1, 6);

--Hibernate: create table trading_data (id bigint generated by default as identity, price_close float not null, price_high float not null, price_low float not null, price_open float not null, trading_day date, volume integer not null, exchange_id bigint, stock_id bigint, primary key (id))
insert into trading_data (id, price_close, price_high, price_low, price_open, trading_day, volume, exchange_id, stock_id) values (1, 164.2, 165, 160,161.3, '2022-05-22', 2435473, 2, 1);
insert into trading_data (id, price_close, price_high, price_low, price_open, trading_day, volume, exchange_id, stock_id) values (2, 164.2, 165, 160,161.3, '2022-05-23', 2423473, 1, 2);
insert into trading_data (id, price_close, price_high, price_low, price_open, trading_day, volume, exchange_id, stock_id) values (3, 164.2, 165, 160,161.3, '2022-05-25', 3333432, 3, 3);
insert into trading_data (id, price_close, price_high, price_low, price_open, trading_day, volume, exchange_id, stock_id) values (4, 164.2, 165, 160,161.3, '2022-04-22', 9435473, 4, 5);
insert into trading_data (id, price_close, price_high, price_low, price_open, trading_day, volume, exchange_id, stock_id) values (5, 164.2, 165, 160,161.3, '2022-03-22', 2111193, 1, 4);