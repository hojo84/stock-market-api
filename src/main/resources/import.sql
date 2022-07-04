insert into countries (id, name) values(1, 'United States');
insert into countries (id, name) values(2, 'Hungary');
insert into countries (id, name) values(3, 'China');
insert into countries (id, name) values(4, 'Great Britain');
insert into countries (id, name) values(5, 'France');

insert into exchanges (id, currency, location, name, website, country_id) values('NYSE', 'USD', 'New York', 'New York Stock Exchange', 'www.nyse.com', 1);
insert into exchanges (id, currency, location, name, website, country_id) values('XNAS', 'USD', 'New York', 'Nasdaq Stock Market', 'www.nasdaq.com', 1);
insert into exchanges (id, currency, location, name, website, country_id) values('SSE', 'CNY', 'Shanghai', 'Shanghai Stock Exchange', 'english.sse.com.cn', 3);
insert into exchanges (id, currency, location, name, website, country_id) values('BUD', 'HUF', 'Budapest', 'Budapest Stock Exchange Index', 'bet.hu', 2);
insert into exchanges (id, currency, location, name, website, country_id) values('LSE', 'GBP', 'London', 'London Stock Exchange', 'www.londonstockexchange.com', 4);
insert into exchanges (id, currency, location, name, website, country_id) values('XPAR', 'EUR', 'Paris', 'Euronext Paris', 'https://www.euronext.com/en/markets/paris', null);

insert into companies (id, equity_type, name, sector) values ('AAPL', 'COMMON_STOCK', 'Apple', 'Information Technology');
insert into companies (id, equity_type, name, sector) values ('OTP', 'COMMON_STOCK', 'OTP Nyrt', 'Financials');
insert into companies (id, equity_type, name, sector) values ('MOL', 'COMMON_STOCK', 'MOL Nyrt', 'Energy');
insert into companies (id, equity_type, name, sector) values ('NVDA', 'COMMON_STOCK', 'Nvidia', 'Information Technology');
insert into companies (id, equity_type, name, sector) values ('WDAY', 'COMMON_STOCK', 'Workday', 'Information Technology');
insert into companies (id, equity_type, name, sector) values ('UGI', 'COMMON_STOCK', 'UGI Corp', 'Utilities');

insert into listings (exchange_id, company_id) values ('NYSE', 'UGI');
insert into listings (exchange_id, company_id) values ('XNAS', 'AAPL');
insert into listings (exchange_id, company_id) values ('XNAS', 'NVDA');
insert into listings (exchange_id, company_id) values ('XNAS', 'WDAY');
insert into listings (exchange_id, company_id) values ('BUD', 'OTP');
insert into listings (exchange_id, company_id) values ('BUD', 'MOL');
insert into listings (exchange_id, company_id) values ('LSE', 'OTP');