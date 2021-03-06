-- Use this query in Microsoft SQL Database Manager.

USE master;

DROP DATABASE IF EXISTS Netflix;
CREATE DATABASE Netflix;

GO

USE Netflix

CREATE TABLE Subscription (
	Email NVARCHAR(30) NOT NULL,
	[Name] NVARCHAR(30),
	Postalcode NVARCHAR(30),
	City NVARCHAR(30),
	Street NVARCHAR(30),
	Housenumber INT,
	PRIMARY KEY (Email));

GO

CREATE TABLE [Profile] (
	[Name] NVARCHAR(30),
	BirthDate DATE,
	Email NVARCHAR(30),
	PRIMARY KEY (Email, [Name]),
	FOREIGN KEY (Email)
	REFERENCES Subscription (Email)
	ON UPDATE CASCADE
	ON DELETE CASCADE);

GO

CREATE TABLE Series (
	Title NVARCHAR(50) NOT NULL,
	Genre NVARCHAR(30),
	MinimumAge INT,
	[Language] NVARCHAR (30),
	RelatedSeries NVARCHAR(50),
	PRIMARY KEY (Title),
	FOREIGN KEY (RelatedSeries)
	REFERENCES Series(Title)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION);

GO

CREATE TABLE Content (
	ContentNr INT NOT NULL,
	Title NVARCHAR(50),
	Duration INT,
	ContentType NVARCHAR(30),
	Series NVARCHAR(50),
	Season INT,
	Genre NVARCHAR(30),
	[Language] NVARCHAR(30),
	MinimumAge INT,
	PRIMARY KEY (ContentNr),
	FOREIGN KEY (Series)
	REFERENCES Series(Title)
	ON UPDATE CASCADE
	ON DELETE CASCADE);

GO

CREATE TABLE Stream (
	SubscriptionEmail NVARCHAR(30) NOT NULL,
	ProfileName NVARCHAR(30) NOT NULL,
	ContentType NVARCHAR(30),
	ContentNr INT NOT NULL,
	PercentageWatched INT,
	PRIMARY KEY (SubscriptionEmail, ProfileName, ContentNr),
	FOREIGN KEY (SubscriptionEmail, ProfileName)
	REFERENCES [Profile](Email, [Name])
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (ContentNr)
	REFERENCES Content(ContentNr)
	ON UPDATE CASCADE
	ON DELETE NO ACTION);

INSERT INTO Subscription VALUES
('janbeltermann@gmail.com', 'Belterman', '5126ED', 'Gilze', 'Den Dries', 63),
('florisbotermans@gmail.com', 'Botermans', '5126GS', 'Gilze', 'Oranjestraat', 29);
INSERT INTO [Profile] VALUES
('Jan', '1998-05-02', 'janbeltermann@gmail.com'),
('Anton', '2001-03-01', 'janbeltermann@gmail.com'),
('Floris', '2000-01-01', 'florisbotermans@gmail.com');
INSERT INTO Series VALUES
('Arrow', 'Thriller', 16, 'English', null),
('Flash', 'Action', 16, 'English', 'Arrow'),
('The Punisher', 'Action', 18, 'English', 'Arrow');
UPDATE Series
SET RelatedSeries = 'Flash'
WHERE Title = 'Arrow'
INSERT INTO Content VALUES
(0001, 'Jack Reacher', 134, 'Movie', null, null, 'Action', 'English', 16),
(0002, 'Arrival', 113, 'Movie', null, null, 'Science Fiction', 'English', 13),
(0003, 'Interstellar', 169, 'Movie', null, null, 'Science Fiction', 'English', 13),
(0004, 'The Matrix', 136, 'Movie', null, null, 'Action', 'English', 17),
(0005, 'The Martian', 141, 'Movie', null, null, 'Science Fiction', 'English', 13),
(0006, 'Fight Club', 139, 'Movie', null, null, 'Drama', 'English', 17),
(0007, 'John Wick', 101, 'Movie', null, null, 'Action', 'English', 17),
(0008, 'The Wolf of Wall Street', 180, 'Movie', null, null, 'Comedy', 'English', 17),
(0009, 'Django Unchained', 165, 'Movie', null, null, 'Drama', 'English', 17),
(0010, 'Mad Max: Fury Road', 120, 'Movie', null, null, 'Action', 'English', 17),
(0011, 'Wall-E', 98, 'Movie', null, null, 'Animation', 'English', 3),
(0101, 'Pilot', 45, 'Episode', 'Arrow', 1, null, null, null),
(0102, 'New Friends', 45, 'Episode', 'Arrow', 1, null, null, null);
INSERT INTO Stream VALUES
('janbeltermann@gmail.com', 'Jan', 'Movie', 0001, 100),
('janbeltermann@gmail.com', 'Jan', 'Movie', 0005, 80),
('janbeltermann@gmail.com', 'Jan', 'Movie', 0007, 100),
('janbeltermann@gmail.com', 'Jan', 'Movie', 0003, 100),
('florisbotermans@gmail.com', 'Floris', 'Movie', 0001, 100),
('florisbotermans@gmail.com', 'Floris', 'Movie', 0005, 100),
('florisbotermans@gmail.com', 'Floris', 'Movie', 0007, 100),
('janbeltermann@gmail.com', 'Jan', 'Episode', 0101, 100),
('janbeltermann@gmail.com', 'Jan', 'Episode', 0102, 64);
