insert into aircrafts(id, manufacturer, model, seats) values (1, 'Airbus', 'A380', 520), (2, 'Boeing', '777', 350);

insert into flights(id, airline, route, aircraft, dep_time) values (1, 'FarAway airways', 1, 1, '2024-01-01 12:00:00+03:00');

insert into tickets(id, flight, class, price, luggage_kg, hand_luggage_kg, refundable) values(1, 1, 'economy', 100, 20, 10, true);
