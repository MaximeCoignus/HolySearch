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
        capitalid          Int ,
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
        countryid       Int ,
        capitalid       Int ,
        PRIMARY KEY (cityid )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Beach
#------------------------------------------------------------

CREATE TABLE Beach(
        beachid         int (11) Auto_increment  NOT NULL ,
        englishname     Varchar (25) NOT NULL ,
        frenchname      Varchar (25) NOT NULL ,
        longitude       Int NOT NULL ,
        latitude        Int ,
        wikidescription Varchar (25) NOT NULL ,
        wikipicture     Varchar (25) NOT NULL ,
        countryid       Int ,
        cityid          Int ,
        PRIMARY KEY (beachid )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Skistation
#------------------------------------------------------------

CREATE TABLE Skistation(
        skistationid    int (11) Auto_increment  NOT NULL ,
        englishname     Varchar (25) NOT NULL ,
        frenchname      Varchar (25) NOT NULL ,
        longitude       Int NOT NULL ,
        latitude        Int NOT NULL ,
        wikidescription Varchar (25) NOT NULL ,
        wikipicture     Varchar (25) NOT NULL ,
        countryid       Int ,
        PRIMARY KEY (skistationid )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Capital
#------------------------------------------------------------

CREATE TABLE Capital(
        capitalid int (11) Auto_increment  NOT NULL ,
        countryid Int ,
        cityid    Int ,
        PRIMARY KEY (capitalid )
)ENGINE=InnoDB;

ALTER TABLE Country ADD CONSTRAINT FK_Country_continentid FOREIGN KEY (continentid) REFERENCES Continent(continentid);
ALTER TABLE Country ADD CONSTRAINT FK_Country_capitalid FOREIGN KEY (capitalid) REFERENCES Capital(capitalid);
ALTER TABLE City ADD CONSTRAINT FK_City_countryid FOREIGN KEY (countryid) REFERENCES Country(countryid);
ALTER TABLE City ADD CONSTRAINT FK_City_capitalid FOREIGN KEY (capitalid) REFERENCES Capital(capitalid);
ALTER TABLE Beach ADD CONSTRAINT FK_Beach_countryid FOREIGN KEY (countryid) REFERENCES Country(countryid);
ALTER TABLE Beach ADD CONSTRAINT FK_Beach_cityid FOREIGN KEY (cityid) REFERENCES City(cityid);
ALTER TABLE Skistation ADD CONSTRAINT FK_Skistation_countryid FOREIGN KEY (countryid) REFERENCES Country(countryid);
ALTER TABLE Capital ADD CONSTRAINT FK_Capital_countryid FOREIGN KEY (countryid) REFERENCES Country(countryid);
ALTER TABLE Capital ADD CONSTRAINT FK_Capital_cityid FOREIGN KEY (cityid) REFERENCES City(cityid);
