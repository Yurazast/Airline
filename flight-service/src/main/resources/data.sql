insert into flight (departure_place, arrival_place, airplane, departure_time, arrival_time) values
                                    ("Adelaide", "Sydney", "Boeing 787-9 Dreamliner", "2020-09-04 09:25:00", "2020-09-04 12:25:00"),
                                    ("London", "Johannesburg", "Airbus A321-271NX", "2020-08-12 20:05:00", "2020-08-13 08:05:00"),
                                    ("Delhi", "New York", "McDonnell Douglas MD-11F", "2020-06-22 01:45:00", "2020-06-22 07:35:00"),
                                    ("Ankara", "Istanbul", "Boeing 777-FDZ", "2020-02-21 10:00:00", "2020-02-21 11:05:00"),
                                    ("Dubai", "Kochi", "Airbus A300B4-622R", "2020-11-07 13:30:00", "2020-11-07 19:05:00"),
                                    ("Chicago", "Delhi", "Embraer E170STD", "2020-08-31 12:00:00", "2020-08-31 13:05:00");

insert into flight_crew (flight_id, crew_member_id) values
                                    (1, 13), (1, 9), (1, 3), (1, 17), (1, 20), (1, 7), (1, 4),
                                    (2, 6), (2, 1), (2, 14), (2, 5), (2, 12), (2, 2),
                                    (3, 19), (3, 16), (3, 11), (3, 8), (3, 10), (3, 15),
                                    (4, 13), (4, 6), (4, 11), (4, 5), (4, 15), (4, 2), (4, 18),
                                    (5, 19), (5, 9), (5, 14), (5, 17), (5, 12), (5, 10), (5, 4),
                                    (6, 1), (6, 16), (6, 3), (6, 8), (6, 7), (6, 2);