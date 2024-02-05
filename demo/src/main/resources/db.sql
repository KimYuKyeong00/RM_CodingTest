create sequence pso_serial;
create sequence pfl_serial;

create table private_member(
pm_id varchar2(20 char) primary key,
pm_pw varchar2(20 char) not null,
pm_email varchar2(20 char)not null,
pm_date date not null,
pm_subscribe number(1) not null
);

create table business_member(
bm_id varchar2(20 char) primary key,
bm_pw varchar2(20 char) not null,
bm_email varchar2(20 char)not null,
bm_date date not null,
bm_noe number(5) not null,
bm_subscribe number(1) not null
);


create table business_emp(
be_id varchar2(20 char) primary key,
bm_id varchar2(20 char) not null,
bm_name varchar2(50 char)not null,
be_pw varchar2(20 char) not null,
be_email varchar2(20 char) not null,
be_date date not null,
constraint bm foreign key (bm_id) references business_member(bm_id) on delete cascade
);


create table private_commodity_list(
commodity_id varchar2(20 char)primary key,
subscriber number(5)not null,
service_price number(7)not null,
storage_price number(7)not null
);

create table business_commodity_list(
commodity_id varchar2(20 char)primary key,
subscriber number(5)not null,
service_price number(7)not null,
storage_price number(7)not null
);


insert into private_commodity_list values('private_basic',0,12000,24000);
insert into private_commodity_list values('private_standard',0,18000,21000);
insert into private_commodity_list values('private_premium',0,24000,18000);

insert into business_commodity_list values('business_basic',0,9000,24000);
insert into business_commodity_list values('business_standard',0,15000,21000);
insert into business_commodity_list values('business_premium',0,21000,18000);


create table private_storage(
pm_id varchar2(20 char) primary key,
commodity_id varchar2(20 char) not null,
expire_date date not null,
max_storage number(20) not null,
storage_status number(20)not null,
constraint fk_id foreign key (pm_id) references private_member(pm_id) on delete cascade,
constraint c_id foreign key (commodity_id) references private_commodity_list(commodity_id)
);

create table business_storage(
bm_id varchar2(20 char) primary key,
commodity_id varchar2(20 char) not null,
expire_date date not null,
max_user number(5) not null,
max_storage number(20) not null,
storage_status number(20)not null,
constraint bfk_id foreign key (bm_id) references business_member(bm_id) on delete cascade,
constraint bc_id foreign key (commodity_id) references business_commodity_list(commodity_id)
);


create table private_subscribe_order(
order_serial number(5) primary key,
pm_id varchar2(20 char)not null,
order_kind varchar2(20 char)not null,
order_date date not null,
total_pay number(10) not null,
commodity varchar2(20 char) not null,
capacity varchar2(20 char) not null,
period varchar2(20 char) not null,
constraint p_id foreign key (pm_id) references private_member(pm_id)
);




create table private_file_list(
private_file_serial number(5) primary key,
pm_id varchar2(20 char) not null,
file_name varchar2(100 char)not null,
file_extension varchar2(40 char) not null,
file_size number(15) not null,
upload_date date not null,
constraint pi foreign key (pm_id) references private_member(pm_id) on delete cascade
);

create table business_file_list(
business_file_serial number(5) primary key,
bm_id varchar2(20 char) not null,
be_id varchar2(20 char) not null,
file_name varchar2(100 char)not null,
file_extension varchar2(40 char) not null,
file_size number(15) not null,
upload_date date not null,
constraint be foreign key (be_id) references business_emp(be_id),
constraint bi foreign key (bm_id) references business_member(bm_id) on delete cascade
);


