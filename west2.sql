DROP DATABASE west2;
CREATE DATABASE  IF NOT EXISTS west2;

CREATE TABLE countryList(
	iso INT PRIMARY KEY,
	countryName VARCHAR(20) 
);

CREATE TABLE countryDetails( 
	iso_country INT, 
	continent VARCHAR(20), 
	capital_city VARCHAR(20), 
	life_expectancy DOUBLE, 
	abbreviation VARCHAR(10),
	confirmed INT, 
	population INT,
	sq_km_area INT, 
	recovered INT, 
	elevation_in_meters VARCHAR(10), 
	location VARCHAR(20), 
	deaths INT, 
	CONSTRAINT countryDetails FOREIGN KEY (iso_country) REFERENCES countryList(iso) ON UPDATE CASCADE
);


CREATE TABLE ProvinceDetails (
	iso_pro INT, 
	provinceName VARCHAR(30),
	recovered INT, 
	confirmed INT, 
	updated VARCHAR(60), 
	latitude DOUBLE, 
	longitude DOUBLE, 
	deaths INT,
	CONSTRAINT dis_iso_fk FOREIGN KEY (iso_pro) REFERENCES countryList(iso) ON UPDATE CASCADE 
);
