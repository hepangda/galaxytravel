# noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE User (
        id BIGINT      NOT NULL PRIMARY KEY,
  username VARCHAR(20) NOT NULL,
       pwd VARCHAR(30) NOT NULL,
      type SMALLINT    NOT NULL,
  realname VARCHAR(30) NOT NULL,
     phone VARCHAR(15)
);

CREATE TABLE Feedback (
       id BIGINT NOT NULL PRIMARY KEY,
  orderId BIGINT NOT NULL,
  message TEXT   NOT NULL
);

CREATE TABLE Orders (
        id BIGINT   NOT NULL PRIMARY KEY,
    userId BIGINT   NOT NULL,
  flightId BIGINT   NOT NULL,
     clazz SMALLINT NOT NULL,
      cost BIGINT   NOT NULL,
      type SMALLINT NOT NULL,
       row SMALLINT NOT NULL,
       col SMALLINT NOT NULL
);

CREATE TABLE Airport (
        id BIGINT      NOT NULL PRIMARY KEY,
  cityName VARCHAR(20) NOT NULL,
  portName VARCHAR(20) NOT NULL,
      type SMALLINT    NOT NULL
);

CREATE TABLE Airplane (
              id BIGINT      NOT NULL PRIMARY KEY,
            name VARCHAR(30) NOT NULL,
            type SMALLINT    NOT NULL,
            rows SMALLINT    NOT NULL,
            cols SMALLINT    NOT NULL,
  firstClassRows SMALLINT    NOT NULL
);

CREATE TABLE Flight (
                id BIGINT      NOT NULL PRIMARY KEY,
         airlineId BIGINT      NOT NULL,
        airplaneId BIGINT      NOT NULL,
              name VARCHAR(20) NOT NULL,
          scheTime TIMESTAMP   NOT NULL,
   firstClassPrice BIGINT      NOT NULL,
  secondClassPrice BIGINT      NOT NULL
);

CREATE TABLE Airline (
            id BIGINT      NOT NULL PRIMARY KEY,
          name VARCHAR(20) NOT NULL,
  sourcePortId BIGINT      NOT NULL,
    destPortId BIGINT      NOT NULL,
          type SMALLINT    NOT NULL
);