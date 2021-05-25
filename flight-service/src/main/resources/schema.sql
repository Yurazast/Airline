drop table if exists flight_crew;
drop table if exists flight;

create table flight (
    id integer not null auto_increment,
    departure_place varchar(20),
    arrival_place varchar(20),
    airplane varchar(35),
    departure_time datetime(6),
    arrival_time datetime(6),
    primary key (id)
) engine=InnoDB;

create table flight_crew (
    flight_id integer not null,
    crew_member_id integer not null,
    primary key (flight_id, crew_member_id)
) engine=InnoDB;
