insert into Company (id, companyName, companyDescription, companyCode) values (1, 'IBM', 'IT Services', 'IBM');

insert into Currency  (id, currencyCode, currencyDescription, isoCurrencySymbol) values (1, 'EUR', 'Euro currency', 'EUR');
insert into Currency  (id, currencyCode, currencyDescription, isoCurrencySymbol) values (2, 'CAD', 'Canadian currency', 'CAD');

insert into Country  (id, countryCode, description, isoCountryCode) values (1, 'USA', 'USA', 'US');

insert into Panel (id, company_id, currency_id, country_id) values (1, 1, 1, 1);
insert into Panel (id, company_id, currency_id, country_id) values (2, 1, 2, 1);

insert into Account(id,accountType,openingDate,amount) values (1, 'CHECKING', '2000-01-01', 12998.45);
insert into Account(id,accountType,openingDate,amount) values (2, 'SAVING', '2000-01-01', 22998.45);
insert into Account(id,accountType,openingDate,amount) values (3, 'CHECKING', '2000-01-01', 100.45);

insert into Customer(id,name,familyName,age)values(1,'Bahman','Kalali',28);
insert into Customer(id,name,familyName,age)values(2,'Layen','Wiesendanger',16);

insert into Customer_Account(CUST_ID,ACC_ID)values(1,1);
insert into Customer_Account(CUST_ID,ACC_ID)values(1,2);
insert into Customer_Account(CUST_ID,ACC_ID)values(2,3);



--select account0_.id as id0_, 
--account0_.CREATION_TIME as CREATION2_0_, 
--account0_.UPDATE_TIME as UPDATE3_0_, 
--account0_.accountType as accountT4_0_, 
--account0_.amount as amount0_, 
--account0_.openingDate as openingD6_0_ 

--from Account account0_ 
--    inner join Customer_Account customers1_ on account0_.id=customers1_.ACC_ID 
--    inner join Customer customer2_ on customers1_.CUST_ID=customer2_.id 
--    where customer2_.id=?

