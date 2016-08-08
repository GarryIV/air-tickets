CREATE SEQUENCE IF NOT EXISTS FLIGHT_SEQ START WITH 1000;
CREATE SEQUENCE IF NOT EXISTS USER_SEQ START WITH 1000;
CREATE SEQUENCE IF NOT EXISTS RESERVATION_SEQ START WITH 1000;
CREATE SEQUENCE IF NOT EXISTS NOTIFICATION_SEQ START WITH 1000;

CREATE CACHED TABLE IF NOT EXISTS FLIGHT_TBL (
  FLIGHT_ID BIGINT DEFAULT (NEXT VALUE FOR FLIGHT_SEQ) NOT NULL NULL_TO_DEFAULT SEQUENCE FLIGHT_SEQ PRIMARY KEY,
  ORIGIN VARCHAR(255),
  DESTINATION VARCHAR(255) NOT NULL,
  ARRIVAL DATETIME NOT NULL,
  DEPARTURE DATETIME NOT NULL,
  FLIGHT_NUMBER VARCHAR(255) NOT NULL,
  PRICE NUMBER(10, 2) NOT NULL,
  STATUS VARCHAR(10) NOT NULL DEFAULT 'SCHEDULED',
  VERSION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

CREATE CACHED TABLE IF NOT EXISTS USER_TBL (
  USER_ID BIGINT DEFAULT (NEXT VALUE FOR USER_SEQ) NOT NULL NULL_TO_DEFAULT SEQUENCE USER_SEQ PRIMARY KEY,
  EMAIL VARCHAR(255) NOT NULL,
  NAME VARCHAR(255),
);

CREATE CACHED TABLE IF NOT EXISTS RESERVATION_TBL (
  RESERVATION_ID BIGINT DEFAULT (NEXT VALUE FOR RESERVATION_SEQ) NOT NULL NULL_TO_DEFAULT SEQUENCE RESERVATION_SEQ PRIMARY KEY,
  FLIGHT_ID BIGINT NOT NULL,
  USER_ID BIGINT NOT NULL,
  ORIGIN VARCHAR(255),
  DESTINATION VARCHAR(255) NOT NULL,
  ARRIVAL DATETIME NOT NULL,
  DEPARTURE DATETIME NOT NULL,
  FLIGHT_NUMBER VARCHAR(255) NOT NULL,
  PRICE NUMBER(10, 2) NOT NULL,
  STATUS VARCHAR(10) NOT NULL
);

CREATE CACHED TABLE IF NOT EXISTS NOTIFICATION_TBL (
  NOTIFICATION_ID BIGINT DEFAULT (NEXT VALUE FOR NOTIFICATION_SEQ) NOT NULL NULL_TO_DEFAULT SEQUENCE NOTIFICATION_SEQ PRIMARY KEY,
  PAYLOAD VARBINARY NOT NULL,
  FAILURES INT NOT NULL,
  STATUS VARCHAR(10) NOT NULL
);