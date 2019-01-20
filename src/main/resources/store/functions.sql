delimiter /
--------------------------------------------------------
-- Functions
--------------------------------------------------------



--------------------------------------------------------
-- Add Bike
--------------------------------------------------------
create or replace function addBike(
    p_id_park number,
    p_description varchar2,
    p_drag_coefficient number,
    p_type varchar2,
    p_current_battery number,
    p_max_battery number,
    p_weight number,
    p_battery_type varchar2,
    p_is_active number
) return number as r_id_bike number;
begin
    insert into Bike(
        id_park,
        description      ,
        drag_coefficient ,
        bike_type,
        current_battery,
        max_battery,
        weight,
        battery_type,
        is_active
    ) values (
        p_id_park,
        p_description     ,
        p_drag_coefficient,
        p_type,
        p_current_battery,
        p_max_battery,
        p_weight,
        p_battery_type,
        p_is_active
    )
    returning id_bike into r_id_bike;
    commit;
    return r_id_bike;
end;
/
--------------------------------------------------------
-- Add Park
--------------------------------------------------------
create or replace function addPark(
    p_descricao varchar2,
    p_geo_latitude number,
    p_geo_longitude number,
    p_geo_altitude number,
    p_max_capacity_electric number,
    p_max_capacity_standard number,
    p_intensity number,
    p_voltage number,
    p_is_active number
) return number as r_id_park number;
begin
    insert into Park(
        descricao,
        geo_latitude,
        geo_longitude,
        geo_altitude,
        max_capacity_electric,
        max_capacity_standard,
        intensity,
        voltage,
        is_active
    ) values (
        p_descricao,
        p_geo_latitude,
        p_geo_longitude,
        p_geo_altitude,
        p_max_capacity_electric,
        p_max_capacity_standard,
        p_intensity,
        p_voltage,
        p_is_active
    )
    returning id_park into r_id_park;
    commit;
    return r_id_park;
end;
/
--------------------------------------------------------
-- Add Ride
--------------------------------------------------------
create or replace function addRide(
    p_id_bike           number,
    p_id_user           number,
    p_id_park_start     number,
    p_id_park_finish    number,
    p_timestamp_start   timestamp,
    p_timestamp_finish  timestamp,
    p_cost              number default 0,
    p_id_invoice        number
)
return number
as
r_id_ride number;
begin
    insert into Ride(
        id_bike,
        id_user,
        id_park_start,
        id_park_finish,
        timestamp_start,
        timestamp_finish,
        ride_cost,
        id_invoice
    ) values (
        p_id_bike,
        p_id_user,
        p_id_park_start,
        p_id_park_finish,
        p_timestamp_start,
        p_timestamp_finish,
        p_cost,
        p_id_invoice
    )
    returning id_ride into r_id_ride;
    commit;
    return r_id_ride;
end;
/
--------------------------------------------------------
-- Add Touristic Point
--------------------------------------------------------
create or replace function addTouristic_point(
    p_descricao varchar2,
    p_geo_latitude number,
    p_geo_longitude number,
    p_geo_altitude number
) return number as r_id_touristic_point number;
begin
 insert into Touristic_point(
        descricao,
        geo_latitude,
        geo_longitude,
        geo_altitude
    ) values (
        p_descricao,
        p_geo_latitude,
        p_geo_longitude,
        p_geo_altitude
    )
    returning id_touristic_point into r_id_touristic_point;
    commit;
    return r_id_touristic_point;
end;
/
--------------------------------------------------------
-- Add User
--------------------------------------------------------
create or replace function addUser(
    p_username varchar2,
    p_email varchar2,
    p_password varchar2,
    p_credit_card_number varchar2,
    p_height number,
    p_weight number,
    p_credit_points number,
    p_avg_speed number,
    p_is_admin number
) return number as r_id_user number;
begin
    insert into "User"(
        username,
        email,
        user_password,
        credit_card_number,
        height,
        weight,
        credit_points,
        avg_speed,
        is_admin
    ) values (
        p_username,
        p_email,
        p_password,
        p_credit_card_number,
        p_height,
        p_weight,
        p_credit_points,
        p_avg_speed,
        p_is_admin
    )
    returning id_user into r_id_user;
    commit;
    return r_id_user;
end;
/
--------------------------------------------------------
-- Add Invoice
--------------------------------------------------------
create or replace function addInvoice(
    p_id_user number,
    p_total_cost number,
    p_money_paid number,
    p_points_used number,
    p_status varchar2
) return number as r_id_invoice number;
begin
    insert into Invoice(
        id_user,
        total_cost,
        money_paid,
        points_used,
        status
    ) values (
        p_id_user,
        p_total_cost,
        p_money_paid,
        p_points_used,
        p_status
    )
    returning id_invoice into r_id_invoice;
    commit;
    return r_id_invoice;
end;
/
--------------------------------------------------------
-- Add Receipt
--------------------------------------------------------
create or replace function addReceipt(
    p_id_user number,
    p_cost number
) return number as r_id_receipt number;
begin
    insert into Receipt(
        id_user,
        cost
    ) values (
        p_id_user,
        p_cost
    )
    returning id_receipt into r_id_receipt;
    commit;
    return r_id_receipt;
end;
/
--------------------------------------------------------
-- Add Route
--------------------------------------------------------
create or replace function addRoute(
    p_latitude_a                number,
    p_longitude_a               number,
    p_latitude_b                number,
    p_longitude_b               number,
    p_route_direction           varchar2,
    p_aerodynamic_coefficient   number,
    p_wind_direction            number,
    p_wind_speed                number
)
return number
as
r_id_route number;
begin
    insert into Route(
        latitude_a,
        longitude_a,
        latitude_b,
        longitude_b,
        route_direction,
        aerodynamic_coefficient,
        wind_direction,
        wind_speed
    ) values (
        p_latitude_a,
        p_longitude_a,
        p_latitude_b,
        p_longitude_b,
        p_route_direction,
        p_aerodynamic_coefficient,
        p_wind_direction,
        p_wind_speed
    )
    returning id_route into r_id_route;
    commit;
    return r_id_route;
end;
/
--------------------------------------------------------
-- Update User
--------------------------------------------------------
create or replace function updateUserById(
   p_id_user                 number,
   p_username                varchar2,
   p_email                   varchar2,
   p_password                varchar2,
   p_credit_card_number      varchar2,
   p_height                  number,
   p_weight                  number,
   p_credit_points           number,
   p_avg_speed               number,
   p_is_admin                number
   )
   return number
   as
   r_id_user number;
begin
 update "User"
    set
           username              = p_username,
           email                 = p_email,
           user_password         = p_password,
           credit_card_number    = p_credit_card_number,
           height                = p_height,
           weight                = p_weight,
           credit_points         = p_credit_points,
           avg_speed             = p_avg_speed,
           is_admin              = p_is_admin
  where id_user = p_id_user
  returning id_user into r_id_user;
  commit;
  return r_id_user;
end;
/
--------------------------------------------------------
-- Update Park
--------------------------------------------------------
create or replace function updatePark(
   p_id_park               number,
   p_descricao             number,
   p_geo_latitude          number,
   p_geo_longitude         number,
   p_geo_altitude          number,
   p_max_capacity_electric number,
   p_max_capacity_standard number,
   p_intensity             number,
   p_voltage               number,
   p_is_active             number
   )
   return number
as
r_id_park number;
begin
 update Park
    set descricao             = p_descricao,
        geo_latitude          = p_geo_latitude,
        geo_longitude         = p_geo_longitude,
        geo_altitude          = p_geo_altitude,
        max_capacity_electric = p_max_capacity_electric,
        max_capacity_standard = p_max_capacity_standard,
        intensity             = p_intensity,
        voltage               = p_voltage,
        is_active             = p_is_active
  where id_park = p_id_park
  returning id_park into r_id_park;
  commit;
  return r_id_park;
end;
/
--------------------------------------------------------
-- Update Ride
--------------------------------------------------------
create or replace function updateRide(
   p_id_ride               number,
   p_id_bike               number,
   p_id_user               number,
   p_id_park_start         number,
   p_id_park_finish        number,
   p_timestamp_start       timestamp,
   p_timestamp_finish      timestamp,
   p_cost                  number,
   p_id_invoice            number
   )
   return number
as
r_id_ride number;
begin
 update Ride
    set id_bike               = p_id_bike,
        id_user               = p_id_user,
        id_park_start         = p_id_park_start,
        id_park_finish        = p_id_park_finish,
        timestamp_start       = p_timestamp_start,
        timestamp_finish      = p_timestamp_finish,
        ride_cost             = p_cost,
        id_invoice            = p_id_invoice
  where id_ride = p_id_ride
  returning id_ride into r_id_ride;
  commit;
  return r_id_ride;
end;
/
--------------------------------------------------------
-- Update Bike
--------------------------------------------------------
create or replace function updateBike(
    p_id_bike         number,
    p_description varchar2,
    p_drag_coefficient number default 0,
    p_id_park         number,
    p_type            varchar2,
    p_current_battery number,
    p_max_battery     number,
    p_weight            number,
    p_battery_type varchar2,
    p_is_active       number
)
return number
as
r_id_bike number;
begin
 update Bike
    set id_park          = p_id_park,
        description      = p_description ,
        drag_coefficient = p_drag_coefficient ,
        bike_type        = p_type,
        current_battery  = p_current_battery,
        max_battery      = p_max_battery,
        weight           = p_weight,
        battery_type     = p_battery_type,
        is_active        = p_is_active
  where id_bike          = p_id_bike
  returning id_bike into r_id_bike;
  commit;
  return r_id_bike;
end;
/
--------------------------------------------------------
-- Remove User By Email
--------------------------------------------------------
create or replace function removeUserByEmail(p_email varchar)
return number
as
r_id_user number;
begin
    delete from "User" where "User".email = p_email
    returning id_user into r_id_user;
    commit;
    return r_id_user;
end;
/
--------------------------------------------------------
-- Remove User by ID
--------------------------------------------------------
create or replace function removeUserById(p_id_user number)
return number
as
r_id_user number;
begin
  delete from "User"
  where id_user = p_id_user
  returning id_user into r_id_user;
  commit;
  return r_id_user;
end;
/
--------------------------------------------------------
-- Remove Ride by ID
--------------------------------------------------------
create or replace function removeRideById(p_id_ride number)
return number
as
r_id_ride number;
begin
  delete from Ride
  where id_ride = p_id_ride
  returning id_ride into r_id_ride;
  commit;
  return r_id_ride;
end;
/
--------------------------------------------------------
-- Remove Park by ID
--------------------------------------------------------
create or replace function removeParkById(p_id_park number)
return number
as
r_id_park number;
begin
  delete from Park
  where id_park = p_id_park
  returning id_park into r_id_park;
  commit;
  return r_id_park;
end;
/
--------------------------------------------------------
-- Remove Touristic Point by ID
--------------------------------------------------------
create or replace function removeTouristic_PointById(p_id_touristic_point number)
return number
as
r_id_touristic_point number;
begin
   delete from Touristic_point
   where id_touristic_point = p_id_touristic_point
   returning id_touristic_point into r_id_touristic_point;
   commit;
   return r_id_touristic_point;
end;
/
--------------------------------------------------------
-- Remove Route by ID
--------------------------------------------------------
create or replace function removeRouteById(p_id_route number)
return number
as
r_id_route number;
begin
  delete from Route
  where id_route = p_id_route
  returning id_route into r_id_route;
  commit;
  return r_id_route;
end;
/
--------------------------------------------------------
-- Remove Invoice by ID
--------------------------------------------------------
create or replace function removeInvoiceById(p_id_invoice number)
return number
as
r_id_invoice number;
begin
  delete from Invoice
  where id_invoice = p_id_invoice
  returning id_invoice into r_id_invoice;
  commit;
  return r_id_invoice;
end;
/
--------------------------------------------------------
-- Remove Receipt by ID
--------------------------------------------------------
create or replace function removeReceiptById(p_id_receipt number)
return number
as
r_id_receipt number;
begin
  delete from Receipt
  where id_receipt = p_id_receipt
  returning id_receipt into r_id_receipt;
  commit;
  return r_id_receipt;
end;
/
--------------------------------------------------------
-- Deactivate Bike by ID
--------------------------------------------------------
create or replace function deactivateBikeById(p_id_bike number)
return number
as
r_id_bike number;
begin
   update Bike set
       is_active = 0
   where id_bike = p_id_bike
   returning id_bike into r_id_bike;
   commit;
   return r_id_bike;
end;
/
--------------------------------------------------------
-- Deactivate Park by ID
--------------------------------------------------------
create or replace function deactivateParkById(p_id_park number)
return number
as
r_id_park number;
begin
   update Park set
       is_active = 0
   where id_park = p_id_park
   returning id_park into r_id_park;
   commit;
   return r_id_park;
end;
