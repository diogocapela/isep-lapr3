delimiter /

--------------------------------------------------------
-- Create Tables
--------------------------------------------------------
CREATE TABLE Bike (
  id_bike           number(10)      GENERATED ALWAYS AS IDENTITY,
  id_park           number(10),
  description       varchar2(255)   DEFAULT 'Default Description',
  drag_coefficient  number(10, 6)   DEFAULT 0.0,
  bike_type         varchar2(255)   DEFAULT 'MOUNTAIN',
  current_battery   number(10, 6)   DEFAULT 0.0,
  max_battery       number(10, 6)   DEFAULT 0.0,
  weight            number(10, 6)   DEFAULT 0.0,
  battery_type      varchar2(255)   DEFAULT 'Default Battery Type',
  is_active         number(1)       DEFAULT 1,
  PRIMARY KEY (id_bike)
)
/
CREATE TABLE Park (
  id_park               number(10)      GENERATED ALWAYS AS IDENTITY,
  descricao             varchar2(255)   DEFAULT 'Default Description',
  geo_latitude          number(10, 6)   DEFAULT 0.0,
  geo_longitude         number(10, 6)   DEFAULT 0.0,
  geo_altitude          number(10, 6)   DEFAULT 0.0,
  max_capacity_electric number(10)      DEFAULT 10,
  max_capacity_standard number(10)      DEFAULT 25,
  intensity             number(10, 6)   DEFAULT 0.0,
  voltage               number(10, 6)   DEFAULT 0.0,
  is_active             number(1)       DEFAULT 1,
  PRIMARY KEY (id_park)
)
/
CREATE TABLE Ride (
  id_ride          number(10)       GENERATED ALWAYS AS IDENTITY,
  id_bike          number(10),
  id_user          number(10),
  id_park_start    number(10),
  id_park_finish   number(10),
  timestamp_start  timestamp(0),
  timestamp_finish timestamp(0),
  ride_cost        number(10, 6)    DEFAULT 0.0,
  id_invoice       number(10),
  PRIMARY KEY (id_ride)
)
/
CREATE TABLE "User" (
  id_user            number(10)         GENERATED ALWAYS AS IDENTITY,
  username           varchar2(255)      UNIQUE,
  email              varchar2(255)      UNIQUE,
  user_password      varchar2(255)      DEFAULT 'password123',
  credit_card_number varchar2(255)      DEFAULT '0000 0000 0000 0000 0000 0',
  height             number(10, 6)      DEFAULT 150.0,
  weight             number(10, 6)      DEFAULT 50.0,
  credit_points      number(10)         DEFAULT 0,
  avg_speed          number(10, 6)      DEFAULT 10.0,
  is_admin           number(1)          DEFAULT 0,
  PRIMARY KEY (id_user)
)
/
CREATE TABLE Touristic_point (
  id_touristic_point    number(10)      GENERATED ALWAYS AS IDENTITY,
  descricao             varchar2(255)   DEFAULT 'Default Description',
  geo_latitude          number(10, 6)   DEFAULT 0.0,
  geo_longitude         number(10, 6)   DEFAULT 0.0,
  geo_altitude          number(10, 6)   DEFAULT 0.0,
  PRIMARY KEY (id_touristic_point)
)
/
CREATE TABLE Receipt (
  id_receipt            number(10)      GENERATED ALWAYS AS IDENTITY,
  id_invoice            number(10),
  id_user               number(10),
  cost                  number(10, 6)   DEFAULT 0.0,
  PRIMARY KEY (id_receipt)
)
/
CREATE TABLE Invoice (
  id_invoice            number(10)      GENERATED ALWAYS AS IDENTITY,
  id_user               number(10),
  total_cost            number(10, 6)   DEFAULT 0.0,
  money_paid            number(10, 6)   DEFAULT 0.0,
  points_used           number(10)      DEFAULT 0,
  status                varchar2(255)   DEFAULT 'PENDING',
  PRIMARY KEY (id_invoice)
)
/
CREATE TABLE Route (
  id_route                  number(10)      GENERATED ALWAYS AS IDENTITY,
  latitude_a                number(10, 6)   DEFAULT 0.0,
  longitude_a               number(10, 6)   DEFAULT 0.0,
  latitude_b                number(10, 6)   DEFAULT 0.0,
  longitude_b               number(10, 6)   DEFAULT 0.0,
  route_direction           varchar2(255)   DEFAULT 'uni',
  aerodynamic_coefficient   number(10, 6)   DEFAULT 0.0,
  wind_direction            number(10, 6)   DEFAULT 0.0,
  wind_speed                number(10, 6)   DEFAULT 0.0
)
/
--------------------------------------------------------
-- Constraints
--------------------------------------------------------
ALTER TABLE Bike ADD CONSTRAINT FKBike39538 FOREIGN KEY (id_park) REFERENCES Park (id_park)
/
ALTER TABLE Ride ADD CONSTRAINT FKRide791926 FOREIGN KEY (id_bike) REFERENCES Bike (id_bike)
/
ALTER TABLE Ride ADD CONSTRAINT FKRide65527 FOREIGN KEY (id_user) REFERENCES "User" (id_user)
/
ALTER TABLE Ride ADD CONSTRAINT FKRide159319 FOREIGN KEY (id_park_start) REFERENCES Park (id_park)
/
ALTER TABLE Receipt ADD CONSTRAINT FKReceipt45897 FOREIGN KEY (id_user) REFERENCES "User" (id_user)
/
ALTER TABLE Receipt ADD CONSTRAINT FKInvoice45897 FOREIGN KEY (id_invoice) REFERENCES Invoice (id_invoice)
/
ALTER TABLE Invoice ADD CONSTRAINT FKInvoice45899 FOREIGN KEY (id_user) REFERENCES "User" (id_user)
