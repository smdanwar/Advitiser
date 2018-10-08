create table advertiser
(
   name varchar(255) not null,
   contact_number varchar(15) not null,
   cradit_limit integer
);

commit;
ALTER TABLE advertiser ADD PRIMARY KEY (name);

commit;

show columns from advertiser;
