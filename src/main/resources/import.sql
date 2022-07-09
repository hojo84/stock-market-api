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

insert into listings (id, equity_type, ipo, company_id, exchange_id) values ('UGI', 'COMMON_STOCK', '2002-04-12', 'UGI', 'NYSE');
insert into listings (id, equity_type, ipo, company_id, exchange_id) values ('AAPL', 'COMMON_STOCK', '1999-12-14', 'AAPL', 'XNAS');
insert into listings (id, equity_type, ipo, company_id, exchange_id) values ('NVDA', 'COMMON_STOCK', '2003-10-11', 'NVDA', 'XNAS');
insert into listings (id, equity_type, ipo, company_id, exchange_id) values ('WDAY', 'COMMON_STOCK', '2003-10-11', 'WDAY', 'XNAS');
insert into listings (id, equity_type, ipo, company_id, exchange_id) values ('OTP', 'COMMON_STOCK', '1995-08-10', 'OTP', 'BUD');
insert into listings (id, equity_type, ipo, company_id, exchange_id) values ('MOL', 'COMMON_STOCK', '1998-10-11', 'MOL', 'BUD');
insert into listings (id, equity_type, ipo, company_id, exchange_id) values ('OTPb', 'COMMON_STOCK', '2003-03-05', 'OTP', 'LSE');

insert into trading (id, price_close, price_high, price_low, price_open, trading_day, volume, listing_id) values (1, 141.1, 145, 139.4, 142.4, '2022-07-04', 28788586, 'AAPL');
insert into trading (id, price_close, price_high, price_low, price_open, trading_day, volume, listing_id) values (2, 141.35, 143.75, 141.28, 141.68, '2022-07-05', 73353800, 'AAPL');
insert into trading (id, price_close, price_high, price_low, price_open, trading_day, volume, listing_id) values (3, 141.56, 141.61, 136.93, 137.77, '2022-07-06', 71007500, 'AAPL');