insert into exchanges (id, currency, location, name, website) values('NYSE', 'USD', 'New York', 'New York Stock Exchange', 'www.nyse.com');
insert into exchanges (id, currency, location, name, website) values('XNAS', 'USD', 'New York', 'Nasdaq Stock Market', 'www.nasdaq.com');
insert into exchanges (id, currency, location, name, website) values('SSE', 'CNY', 'Shanghai', 'Shanghai Stock Exchange', 'english.sse.com.cn');
insert into exchanges (id, currency, location, name, website) values('BUD', 'HUF', 'Budapest', 'Budapest Stock Exchange Index', 'bet.hu');
insert into exchanges (id, currency, location, name, website) values('LSE', 'GBP', 'London', 'London Stock Exchange', 'www.londonstockexchange.com');

insert into stocks (id, company_name, equity_type, sector) values ('AAPL', 'Apple', 'COMMON_STOCK', 'Information Technology');
insert into stocks (id, company_name, equity_type, sector) values ('OTP', 'OTP Nyrt', 'COMMON_STOCK', 'Financials');
insert into stocks (id, company_name, equity_type, sector) values ('MOL', 'MOL Nyrt', 'COMMON_STOCK', 'Energy');
insert into stocks (id, company_name, equity_type, sector) values ('NVDA', 'Nvidia', 'COMMON_STOCK', 'Information Technology');
insert into stocks (id, company_name, equity_type, sector) values ('WDAY', 'Workday', 'COMMON_STOCK', 'Information Technology');
insert into stocks (id, company_name, equity_type, sector) values ('UGI', 'UGI Corp', 'COMMON_STOCK', 'Utilities');

insert into listings (exchange_id, stock_id) values ('NYSE', 'UGI');
insert into listings (exchange_id, stock_id) values ('XNAS', 'AAPL');
insert into listings (exchange_id, stock_id) values ('XNAS', 'NVDA');
insert into listings (exchange_id, stock_id) values ('XNAS', 'WDAY');
insert into listings (exchange_id, stock_id) values ('BUD', 'OTP');
insert into listings (exchange_id, stock_id) values ('BUD', 'MOL');
insert into listings (exchange_id, stock_id) values ('LSE', 'OTP');