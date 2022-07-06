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

insert into companies (id, industry, name, sector) values ('AAPL', 'Consumer Electronics', 'Apple', 'Information Technology');
insert into companies (id, industry, name, sector) values ('OTP', 'Banks', 'OTP Nyrt', 'Financials');
insert into companies (id, industry, name, sector) values ('MOL', 'Oil & Gas Integrated', 'MOL Nyrt', 'Energy');
insert into companies (id, industry, name, sector) values ('NVDA', 'Semiconductors', 'Nvidia', 'Information Technology');
insert into companies (id, industry, name, sector) values ('WDAY', 'Software', 'Workday', 'Information Technology');
insert into companies (id, industry, name, sector) values ('UGI', 'Regulated Gas', 'UGI Corp', 'Utilities');

insert into listings (exchange_id, company_id, equity_type) values ('NYSE', 'UGI', 'COMMON_STOCK');
insert into listings (exchange_id, company_id, equity_type) values ('XNAS', 'AAPL', 'COMMON_STOCK');
insert into listings (exchange_id, company_id, equity_type) values ('XNAS', 'NVDA', 'COMMON_STOCK');
insert into listings (exchange_id, company_id, equity_type) values ('XNAS', 'WDAY', 'COMMON_STOCK');
insert into listings (exchange_id, company_id, equity_type) values ('BUD', 'OTP', 'COMMON_STOCK');
insert into listings (exchange_id, company_id, equity_type) values ('BUD', 'MOL', 'COMMON_STOCK');
insert into listings (exchange_id, company_id, equity_type) values ('LSE', 'OTP', 'COMMON_STOCK');