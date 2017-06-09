#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Continent
#------------------------------------------------------------

CREATE TABLE Continent(
        continentid     int (11) Auto_increment  NOT NULL ,
        englishname     Text  ,
        frenchname      Text  ,
        longitude       FLOAT NOT NULL ,
        latitude        FLOAT NOT NULL ,
        population      FLOAT ,
        sizekm          FLOAT ,
        wikidescription Text ,
        wikipicture     Text ,
        PRIMARY KEY (continentid )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Country
#------------------------------------------------------------

CREATE TABLE Country(
        countryid          int (11) Auto_increment  NOT NULL ,
        englishname        Text   NOT NULL ,
        frenchname         Text  ,
        longitude          FLOAT NOT NULL ,
        latitude           FLOAT NOT NULL ,
        population         FLOAT NOT NULL ,
        currency           Text   ,
        wikidescription    Text  ,
        wikipicture        Text  ,
        temperature        TinyINT ,
        temperaturelevel   Text   ,
        precipitation      FLOAT ,
        precipitationlevel Text   ,
        criminality        FLOAT ,
        criminalitylevel   Text   ,
        continentid        Int ,
        PRIMARY KEY (countryid )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Destination
#------------------------------------------------------------

CREATE TABLE Destination(
        destinationid   int (11) Auto_increment  NOT NULL ,
        englishname     Text   NOT NULL ,
        frenchname      Text   NOT NULL ,
        longitude       FLOAT NOT NULL ,
        latitude        FLOAT ,
        wikidescription Text   ,
        wikipicture     Text   ,
        type            Text   NOT NULL ,
        countryid       Int ,
        PRIMARY KEY (destinationid )
)ENGINE=InnoDB;