delimiter /



alter SESSION set NLS_DATE_FORMAT = 'DD-MM-YYYY HH24:MI:SS'
/
--------------------------------------------------------
-- Park Inserts
--------------------------------------------------------
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard, intensity, voltage, is_active) values ('Park 1', 41.152712, -8.609297, 71.43, 33, 18, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Park 2', 17.312406, 41.887231, 17.59, 24, 30, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Park 3', 83.199101, 88.788584, 20.59, 36, 47, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Park 4', 76.994929, 30.457319, 59.39, 16, 42, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Park 5', 43.840578, 55.34291, 79.05, 22, 27, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Park 6', 69.887512, 72.96357, 40.13, 17, 13, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Park 7', 45.359339, 73.849242, 58.67, 38, 25, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Park 8', 46.496049, 61.032356, 47.32, 36, 36, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Park 9', 27.807557, 80.360704, 22.82, 50, 38, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Park 10', 39.630354, 48.266987, 81.88, 15, 17, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Farol Foz', 41.150004, -8.676257, 105.0, 15, 17, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Rotunda Boavista', 41.157905, -8.629128, 105.0, 15, 17, 16.0, 220.0, 1)
/
insert into Park (descricao, geo_latitude, geo_longitude, geo_altitude, max_capacity_electric, max_capacity_standard,intensity, voltage, is_active) values ('Castelo Queijo', 41.167331, -8.688336, 105.0, 15, 17, 16.0, 220.0, 1)
/
--------------------------------------------------------
-- User Inserts
--------------------------------------------------------
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('1', 'rpele0@japanpost.jp', 'Vq1Vf6ov', '3538203239798171', 92.74, 115.19, 10.0, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('2', 'elaflin1@pen.io', 'QhUCaXB10', '3584424481215291', 146.4, 132.9, 20.0, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('3', 'ejonin2@hibu.com', 'uF0vod0Ym', '3569302036892237', 167.11, 110.5, 15.0, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('4', 'rbradburne3@upenn.edu', 'rOQ2ObRqox', '5038905519576032', 221.81, 107.52, 12.4, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('5', 'aalderman4@4shared.com', 'ggsqTjU', '6709431263859250337', 160.07, 144.71, 17.9, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('6', 'awingrove5@msu.edu', 'akLMNmNW', '5602230586551685', 149.67, 102.06, 24.8, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('7', 'gtofpik6@squarespace.com', 't53QXVk5', '5602235473045436', 199.71, 45.75, 17.3, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('8', 'afarraway7@gnu.org', 'JaOViO6bK', '3531702517070283', 123.9, 142.08, 9.3, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('9', 'sdudhill8@buzzfeed.com', 'D4wIeW', '3543135873818118', 201.57, 149.77, 15.4, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin) values ('10', 'mcuckoo9@reference.com', 'Cn9wSI4MQ', '337941236489117', 181.04, 120.9, 18.6, 1)
/
insert into "User" (username, email, user_password, credit_card_number, height, weight,avg_speed, is_admin, credit_points) values ('11', 'rafiki@asdas.com', 'Cn9wSI4MQ', '337941236489117', 181.04, 120.9, 18.6, 1,20)
/
--------------------------------------------------------
-- Bike Inserts
--------------------------------------------------------
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (2, 'Bike A', 0.1,'MOUNTAIN', null, null, 6, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (9, 'Bike B', 0.1,'MOUNTAIN', null, null, 6, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (3, 'Bike C', 0.1,'MOUNTAIN', null, null, 6, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (null, 'Bike D', 0.1,'ROAD', null, null, 4, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (5, 'Bike E', 0.1,'ROAD', null, null, 4, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (2, 'Bike F', 0.1,'ROAD', null, null, 4, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (2, 'Bike G', 0.1,'ELECTRICAL', 89.38, 15.2, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (3, 'Bike H', 0.1,'ELECTRICAL', 98.47, 12.99, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (9, 'Bike I', 0.31,'ELECTRICAL', 45.26, 19.27,  8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (6, 'Bike J', 0.2,'ELECTRICAL', 30.57, 52.89, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (6, 'Bike K', 0.2,'ELECTRICAL', 30.57, 52.89, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (6, 'Bike L', 0.2,'ELECTRICAL', 39.57, 52.89, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (6, 'Bike M', 0.2,'ELECTRICAL', 25.57, 52.89, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike A', 0.1,'MOUNTAIN', null, null, 6, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike B', 0.1,'MOUNTAIN', null, null, 6, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike C', 0.1,'MOUNTAIN', null, null, 6, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike D', 0.1,'ROAD', null, null, 4, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike E', 0.1,'ROAD', null, null, 4, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike F', 0.1,'ROAD', null, null, 4, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike G', 0.1,'ELECTRICAL', 89.38, 15.2, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike H', 0.1,'ELECTRICAL', 98.47, 12.99, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike I', 0.31,'ELECTRICAL', 45.26, 19.27,  8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike J', 0.2,'ELECTRICAL', 30.57, 52.89, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike K', 0.2,'ELECTRICAL', 30.57, 52.89, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike L', 0.2,'ELECTRICAL', 39.57, 52.89, 8, null, 1)
/
insert into Bike (id_park, description, drag_coefficient,bike_type, current_battery, max_battery, weight, battery_type, is_active) values (11, 'Bike M', 0.2,'ELECTRICAL', 25.57, 52.89, 8, null, 1)
/
--------------------------------------------------------
-- Ride Inserts
--------------------------------------------------------
insert into Ride (id_bike, id_user, id_park_start, id_park_finish, timestamp_start, timestamp_finish, ride_cost, id_invoice) values (1, 1, 1, 2, to_date('10-10-2018 16-16-16'), to_date('10-10-2018 18-16-16'), 41.0,null)
/
insert into Ride (id_bike, id_user, id_park_start, id_park_finish, timestamp_start, timestamp_finish, ride_cost, id_invoice) values (2, 2, 2, 3, to_date('11-10-2018 16-16-16'), to_date('11-10-2018 18-16-16'), 32.5, null)
/
insert into Ride (id_bike, id_user, id_park_start, id_park_finish, timestamp_start, timestamp_finish, ride_cost, id_invoice) values (3, 3, 3, 4, to_date('12-10-2018 16-16-16'), to_date('12-10-2018 18-16-16'), 23.5, null)
/
insert into Ride (id_bike, id_user, id_park_start, id_park_finish, timestamp_start, timestamp_finish, ride_cost, id_invoice) values (4, 4, 4, null, to_date('13-10-2018 16-16-16'), null, 0.0, null)
/
insert into Ride (id_bike, id_user, id_park_start, id_park_finish, timestamp_start, timestamp_finish, ride_cost, id_invoice) values (5, 5, 5, null, to_date('14-10-2018 16-16-16'), null, 0.0, null)
/
insert into Ride (id_bike, id_user, id_park_start, id_park_finish, timestamp_start, timestamp_finish, ride_cost, id_invoice) values (5, 11, 5, 6, to_date('14-8-2018 16-16-16'), to_date('14-8-2018 16-36-16'), 0.0, null)
/
insert into Ride (id_bike, id_user, id_park_start, id_park_finish, timestamp_start, timestamp_finish, ride_cost, id_invoice) values (5, 11, 2, 1, to_date('15-8-2018 16-16-16'), to_date('15-8-2018 19-36-16'), 10.0, null)
/
--------------------------------------------------------
-- Touristic Point Inserts
--------------------------------------------------------
insert into Touristic_point (descricao, geo_latitude, geo_longitude, geo_altitude) values ('1', 50.630354, 20.266987, 20.88)
/
insert into Touristic_point (descricao, geo_latitude, geo_longitude, geo_altitude) values ('2', 39.630354, 48.266987, 99.50)
/
insert into Touristic_point (descricao, geo_latitude, geo_longitude, geo_altitude) values ('3', 67.123354, 89.466987, 15.88)
/
insert into Touristic_point (descricao, geo_latitude, geo_longitude, geo_altitude) values ('4', 58.340354, 43.236987, 50.00)
/
insert into Touristic_point (descricao, geo_latitude, geo_longitude, geo_altitude) values ('5', 32.676254, 14.432987, 7.22)
/
--------------------------------------------------------
-- Route Inserts
--------------------------------------------------------
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(41.152712, -8.609297, 42.152712, -54.609297, 'bi', 32.32, 1.23, 65.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(23.152712, -23.609297, 32.152712, 32.609297, 'uni', 12.32, 3.23, 10.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(87.152712, 8.609297, -42.152712, -1.609297, 'bi', 32.32, 6.23, 54.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(11.152712, 12.609297, 42.152712, -9.609297, 'uni', 3.32, 54.23, 43.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(-41.152712, 38.609297, -42.152712, 32.609297, 'bi', 5.32, 12.23, 10.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(41.152712, 10.609297, 53.152712, 42.609297, 'uni', 76.32, 43.23, 1.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(32.152712, 32.609297, 63.152712, 32.609297, 'bi', 8.32, 9.23, 3.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(54.152712, -23.609297, 63.152712, 10.609297, 'uni', 9.32, 0.23, 10.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(41.152712, -8.609297, 17.312406, 41.887231, 'bi', 2.32, 12.23, 76.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(41.167331, -8.688336, 41.150004, -8.676257, 'uni', 2.32, 12.23, 76.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(41.167331, -8.688336, 41.157905, -8.629128, 'bi', 2.32, 12.23, 76.22)
/
insert into Route (latitude_a, longitude_a, latitude_b, longitude_b, route_direction, aerodynamic_coefficient, wind_direction, wind_speed) values(41.157905, -8.629128, 41.150004, -8.676257, 'bi', 2.32, 12.23, 76.22)
