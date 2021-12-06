DROP TABLE  IF EXISTS DELETE_ACCOUNT_BOOK;
DROP TABLE  IF EXISTS ACCOUNT_BOOK;
DROP TABLE  IF EXISTS USER;

CREATE TABLE USER (
	id int not null auto_increment,
    email varchar(30) not null,
    password varchar(30) not null,
    primary key(id)
);

CREATE TABLE ACCOUNT_BOOK (
	id int not null auto_increment,
    price bigint not null,
    memo varchar(200) not null,
    category varchar(20) not null,
    create_at datetime not null,
    user_id int not null,
    primary key (id),
    foreign key (user_id) references user (id)
);

CREATE TABLE DELETE_ACCOUNT_BOOK (
	id int not null auto_increment,
    account_book_id int not null,
    price bigint not null,
    memo varchar(200) not null,
    category varchar(20) not null,
    create_at datetime not null,
    user_id int not null,
    primary key (id),
    foreign key (user_id) references user (id)
);