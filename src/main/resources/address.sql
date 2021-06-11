CREATE TABLE address (
	id INTEGER GENERATED ALWAYS AS IDENTITY,
	first_name VARCHAR(32) NOT NULL,
	last_name VARCHAR(32) NOT NULL,
	street VARCHAR(32) NOT NULL,
	city VARCHAR(32) NOT NULL,
	state VARCHAR(32) NOT NULL,
	zip INTEGER NOT NULL,
	PRIMARY KEY(id)
);

INSERT INTO address (first_name, last_name, street, city, state, zip)
VALUES
('Mike', 'Smith', 'Main', 'West Palm', 'Florida', 33401),
('Tina', 'Johnson', 'Hathorne', 'Greensborough', 'North Carolina', 27214),
('Luke', 'Reed', 'Cole', 'West Plains', 'Missouri', 65775),
('James', 'Willson', 'Mission', 'Rancho Cucamonga', 'California', 91701),
('Sara', 'McCarthy', '300th Street', 'Phoenix', 'Arizona', 85001);
