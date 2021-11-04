drop table if exists author;
drop table if exists author;
drop table if exists hibernate_sequence;

create table author (
                      id int not null,
                      firstName varchar(255),
                      lastName varchar(255),
                      primary key (id)
) engine=InnoDB;

create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values ( 1 );