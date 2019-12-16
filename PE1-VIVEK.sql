 /*Name:- Vivek Panchal
   ISTE.722.01
   PE-1
 */
 
/*--- QUERY 1.---- */	
	SELECT p.FName fname, p.LName lname, p.Street street, z.City city, z.State state, p.Zip zip
    FROM passenger AS p 
	JOIN zips as z 
    USING(Zip)
    ORDER BY p.LName ASC;
	
/*--- QUERY 2.---- */	
	SELECT TripNum tripnum,DepartureTime departuretime,DepartureLocCode departureloccode from trip_directory 
	JOIN tripcodes ON
	tripcodes.TripType = trip_directory.TripType
	WHERE tripcodes.TypeName = "Bus";

/*--- QUERY 3.---- */		
	Select CONCAT(P.FName," ",P.LName) as Passenger
    FROM passenger as P
    JOIN trip_people ON
    P.PassengerID = trip_people.PassengerID;

/*--- QUERY 4.---- */		
	SELECT L.location Location ,COUNT(DepartureLocCode) 'Number of Departures' from trip_directory
    JOIN locations as L
    ON trip_directory.DepartureLocCode = L.LocationCode
    GROUP BY L.location;

/*--- QUERY 5.---- */		
   SELECT Name as name from staff
   JOIN trip ON
   trip.TripNum = staff.TripNum
   AND trip.Date = staff.Date
   AND trip.DepartureLocCode IN(
   SELECT LocationCode FROM locations WHERE Location = "Boston")
   AND trip.ArrivalLocCode IN(
   SELECT LocationCode FROM locations WHERE Location = "Nassau");

/*--- QUERY 6.---- */	 
    SELECT TripNum tripnum ,CONCAT(Fname," ",LName)AS  'People from Frankfort'
	FROM trip_people tp JOIN passenger p
	USING (PassengerID)
	JOIN staff s
	USING (TripNum)
	JOIN zips z
	USING (Zip)
	WHERE s.Name = "Brian Page"
	AND z.City = "Frankfort";	

/*--- QUERY 7.---- */	 
   SELECT FName fname, LName lname from passenger
   JOIN zips ON
   zips.Zip = passenger.Zip
   JOIN trip_people ON
   trip_people.PassengerID = passenger.PassengerID
   JOIN trip_directory ON
   trip_directory.TripNum = trip_people.TripNum 
   WHERE zips.City = "Rochester"
   AND trip_directory.TripType IN(
   SELECT TripType FROM tripcodes WHERE TypeName = "Bus");
 	
 /*--- QUERY 8.---- */	
    SELECT equipmentdescription from equipment
	JOIN trip ON
	equipment.EquipId = trip.EquipId
	JOIN trip_people ON
	trip.TripNum = trip_people.TripNum
	JOIN passenger ON
	passenger.PassengerID = trip_people.PassengerID
	WHERE passenger.FName ="Curtis"
	AND passenger.LName = "Brown";
	
/*--- QUERY 9.---- */	
   UPDATE  equipment
   SET EquipmentDescription = "Mid Range "
   WHERE EquipmentName = "Boeing 767";
	
/*--- QUERY 10.---- */	
   SELECT equipment.EquipId equipid,equipment.equipmentname equipname,COUNT(TripNum) NumTrips 
   FROM equipment
   LEFT JOIN trip ON
   trip.EquipId = equipment.EquipId
   GROUP BY equipment.equipId, equipment.equipmentname;




   
   
	