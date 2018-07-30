CREATE TABLE Member(
	MemberId INT NOT NULL AUTO_INCREMENT,
	MemberName varchar(10) NOT NULL UNIQUE,
	Gender varchar(10) NOT NULL,
  PRIMARY KEY(MemberId));

INSERT into Member (MemberName,Gender) VALUES ('Alex','Male');
INSERT into Member (MemberName,Gender) VALUES ('Bern','Male');
INSERT into Member (MemberName,Gender) VALUES ('John','Male');
INSERT into Member (MemberName,Gender) VALUES ('Joe','Male');
INSERT into Member (MemberName,Gender) VALUES ('Evan','Male');
INSERT into Member (MemberName,Gender) VALUES ('Diana','Female');
INSERT into Member (MemberName,Gender) VALUES ('Nancy','Female');
INSERT into Member (MemberName,Gender) VALUES ('Niki','Female');
INSERT into Member (MemberName,Gender) VALUES ('Adam','Male');
INSERT into Member (MemberName,Gender) VALUES ('Nisha','Female');
INSERT into Member (MemberName,Gender) VALUES ('Jacob','Male');
INSERT into Member (MemberName,Gender) VALUES ('Rufi','Female');
INSERT into Member (MemberName,Gender) VALUES ('Shaun','Male');
INSERT into Member (MemberName,Gender) VALUES ('Piers','Male');
INSERT into Member (MemberName,Gender) VALUES ('Pippa','Male');
INSERT into Member (MemberName,Gender) VALUES ('Owen','Male');
INSERT into Member (MemberName,Gender) VALUES ('Sally','Female');
INSERT into Member (MemberName,Gender) VALUES ('Neil','Male');
INSERT into Member (MemberName,Gender) VALUES ('Ruth','Female');
INSERT into Member (MemberName,Gender) VALUES ('William','Male');
INSERT into Member (MemberName,Gender) VALUES ('Rose','Female');
INSERT into Member (MemberName,Gender) VALUES ('Paul','Male');
INSERT into Member (MemberName,Gender) VALUES ('Zoe','Female');
INSERT into Member (MemberName,Gender) VALUES ('Roger','Male');
INSERT into Member (MemberName,Gender) VALUES ('Steve','Male');

--INSERT into Member VALUES (5,'Julia','Female');

CREATE TABLE Relation(
	RelationId INT NOT NULL,
	RelationName varchar(20) NOT NULL,
  PRIMARY KEY(RelationId));


INSERT into Relation VALUES (101,'FATHER');
INSERT into Relation VALUES (102,'MOTHER');
INSERT into Relation VALUES (103,'BROTHERS');
INSERT into Relation VALUES (105,'SISTERS');
INSERT into Relation VALUES (106,'SONS');
INSERT into Relation VALUES (107,'DAUGHTERS');
INSERT into Relation VALUES (108,'COUSINS');
INSERT into Relation VALUES (109,'GRANDMOTHER');
INSERT into Relation VALUES (110,'GRANDFATHER');
INSERT into Relation VALUES (111,'GRADSONS');
INSERT into Relation VALUES (112,'GRANDDAUGHTERS');
INSERT into Relation VALUES (113,'AUNTS');
INSERT into Relation VALUES (114,'UNCLES');
INSERT into Relation VALUES (115,'HUSBAND');
INSERT into Relation VALUES (116,'WIFE');


CREATE TABLE MemberRelation(
  id INT NOT NULL AUTO_INCREMENT,
  MemberId INT,
	RelationId INT,
	UserId INT,
	FOREIGN  KEY (MemberId) REFERENCES Member(MemberId),
	FOREIGN  KEY (RelationId) REFERENCES Relation(RelationId),
  PRIMARY KEY(id));

INSERT into MemberRelation VALUES (111,1,103,3);
INSERT into MemberRelation VALUES (112,1,103,4);
--INSERT into MemberRelation VALUES (113,2,116,5);