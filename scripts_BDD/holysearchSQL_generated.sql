#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Continent
#------------------------------------------------------------

CREATE TABLE Continent(
        continentid     int (11) Auto_increment  NOT NULL ,
        englishname     Varchar (25) ,
        frenchname      Varchar (25) ,
        longitude       Int NOT NULL ,
        latitude        Int NOT NULL ,
        population      Int ,
        sizekm          Int ,
        wikidescription Varchar (25) ,
        wikipicture     Varchar (25) ,
        PRIMARY KEY (continentid )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Country
#------------------------------------------------------------

CREATE TABLE Country(
        countryid          int (11) Auto_increment  NOT NULL ,
        englishname        Varchar (25) NOT NULL ,
        frenchname         Varchar (25) ,
        longitude          Int NOT NULL ,
        latitude           Int NOT NULL ,
        population         Int NOT NULL ,
        currency           Varchar (25) ,
        isoa2              Varchar (25) ,
        isoa3              Varchar (25) ,
        wikidescription    Varchar (25) NOT NULL ,
        wikipicture        Varchar (25) NOT NULL ,
        temperature        TinyINT ,
        temperaturelevel   Varchar (25) ,
        precipitation      Int ,
        precipitationlevel Varchar (25) ,
        criminality        Int ,
        criminalitylevel   Varchar (25) ,
        continentid        Int ,
        PRIMARY KEY (countryid )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: City
#------------------------------------------------------------

CREATE TABLE City(
        cityid          int (11) Auto_increment  NOT NULL ,
        englishname     Varchar (25) NOT NULL ,
        frenchname      Varchar (25) NOT NULL ,
        longitude       Int ,
        latitude        Int ,
        population      Int NOT NULL ,
        wikidescription Varchar (25) NOT NULL ,
        wikipicture     Varchar (25) NOT NULL ,
        iscapital       Bool ,
        countryid       Int ,
        PRIMARY KEY (cityid )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Destination
#------------------------------------------------------------

CREATE TABLE Destination(
        destinationid   int (11) Auto_increment  NOT NULL ,
        englishname     Varchar (25) NOT NULL ,
        frenchname      Varchar (25) NOT NULL ,
        longitude       Int NOT NULL ,
        latitude        Int ,
        wikidescription Varchar (25) NOT NULL ,
        wikipicture     Varchar (25) NOT NULL ,
        type            Varchar (25) NOT NULL ,
        countryid       Int ,
        cityid          Int ,
        PRIMARY KEY (destinationid )
)ENGINE=InnoDB;

ALTER TABLE Country ADD CONSTRAINT FK_Country_continentid FOREIGN KEY (continentid) REFERENCES Continent(continentid);
ALTER TABLE City ADD CONSTRAINT FK_City_countryid FOREIGN KEY (countryid) REFERENCES Country(countryid);
ALTER TABLE Destination ADD CONSTRAINT FK_Destination_countryid FOREIGN KEY (countryid) REFERENCES Country(countryid);
ALTER TABLE Destination ADD CONSTRAINT FK_Destination_cityid FOREIGN KEY (cityid) REFERENCES City(cityid);
