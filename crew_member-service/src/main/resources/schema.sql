drop table if exists flight_crew;
drop table if exists crew_member;

create table crew_member (
    id integer not null auto_increment,
    surname varchar(15),
    name varchar(15),
    gender varchar(6),
    role varchar(11),
    age integer,
    primary key (id)
) engine=InnoDB;
