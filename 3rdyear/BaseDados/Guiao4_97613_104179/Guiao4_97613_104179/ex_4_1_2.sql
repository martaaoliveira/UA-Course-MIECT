use p2g6
GO
CREATE SCHEMA RESERVA_DE_VOOS;
GO

CREATE TABLE RESERVA_DE_VOOS.AIRPORT(
CODIGO				INT NOT NULL CHECK(CODIGO>0),
CIDADE				NVARCHAR(20) NOT NULL,
ESTADO				NVARCHAR(20) NOT NULL,
NOME				NVARCHAR(20) NOT NULL,

CONSTRAINT PK_AIRPORT
	PRIMARY KEY (CODIGO)
);

CREATE TABLE RESERVA_DE_VOOS.AIRPLANE_TYPE(
WRITE_NAME			NVARCHAR(10) NOT NULL,
CONPANY				NVARCHAR(10) NOT NULL,
MAX_SEATS			INT NOT NULL CHECK(MAX_SEATS>0),

CONSTRAINT PK_AIRPLANE_TYPE
	PRIMARY KEY(WRITE_NAME),
);

CREATE TABLE RESERVA_DE_VOOS.AIRPLANE(
AIRPLANE_ID				INT NOT NULL CHECK(AIRPLANE_ID>0),
TOTAL_NO_SEATS			INT NOT NULL CHECK(TOTAL_NO_SEATS>0),
WRITE_NAME				NVARCHAR(10) NOT NULL,

CONSTRAINT PK_AIRPLANE
	PRIMARY KEY(AIRPLANE_ID),
CONSTRAINT FK_AIRPLANE
	FOREIGN KEY(WRITE_NAME) REFERENCES RESERVA_DE_VOOS.AIRPLANE_TYPE(WRITE_NAME),
);

CREATE TABLE RESERVA_DE_VOOS.CAN_LAND(
WRITE_NAME			NVARCHAR(10) NOT NULL,
AIRPORT_CODE		INT NOT NULL CHECK(AIRPORT_CODE>0),

CONSTRAINT PK_CAN_LAND
	PRIMARY KEY(WRITE_NAME, AIRPORT_CODE),
CONSTRAINT FK_CAN_LAND
	FOREIGN KEY(WRITE_NAME) REFERENCES RESERVA_DE_VOOS.AIRPLANE_TYPE(WRITE_NAME),
	FOREIGN KEY(AIRPORT_CODE) REFERENCES RESERVA_DE_VOOS.AIRPORT(CODIGO),
);




CREATE TABLE RESERVA_DE_VOOS.FLIGHT(
NUMBER			INT NOT NULL CHECK(NUMBER>0),
AIRLINE			NVARCHAR(20) NOT NULL,
WEEKDAYS		NVARCHAR(20) NOT NULL,

CONSTRAINT PK_FLIGHT
	PRIMARY KEY (NUMBER),
);

CREATE TABLE RESERVA_DE_VOOS.FLIGHT_LEG(
AIRP_CODIGO1		INT NOT NULL CHECK(AIRP_CODIGO1>0),
AIRP_CODIGO2		INT NOT NULL CHECK(AIRP_CODIGO2>0),
LEG_NO				INT NOT NULL CHECK(LEG_NO>0),
FLIGHT_NUMBER		INT NOT NULL CHECK(FLIGHT_NUMBER>0),
DEP_TIME			TIME NOT NULL,
ARR_TIME			TIME NOT NULL,

CHECK (DEP_TIME < ARR_TIME),

CONSTRAINT PK_FLIGHT_LEG
	PRIMARY KEY (LEG_NO, FLIGHT_NUMBER),
CONSTRAINT FK_FLIGHT_LEG
	FOREIGN KEY (AIRP_CODIGO1) REFERENCES RESERVA_DE_VOOS.AIRPORT(CODIGO),
	FOREIGN KEY (AIRP_CODIGO2) REFERENCES RESERVA_DE_VOOS.AIRPORT(CODIGO),
	FOREIGN KEY (FLIGHT_NUMBER) REFERENCES RESERVA_DE_VOOS.FLIGHT(NUMBER),
);



CREATE TABLE RESERVA_DE_VOOS.FARE(
CODE				INT NOT NULL,
AMOUNT				INT NOT NULL,
RESTRICTIONS		NVARCHAR(20),
FLIGHT_NUMBER		INT,

CONSTRAINT PK_FARE
	PRIMARY KEY (CODE, FLIGHT_NUMBER),
CONSTRAINT FK_FARE
	FOREIGN KEY (FLIGHT_NUMBER) REFERENCES RESERVA_DE_VOOS.FLIGHT(NUMBER)
);


CREATE TABLE RESERVA_DE_VOOS.LEG_INSTANCE(
AIRPLANE_ID			INT NOT NULL CHECK(AIRPLANE_ID>0),
INFO_DATE			DATE NOT NULL,
NO_AVAI_SEATS		INT,
AIRP_CODE1			INT NOT NULL CHECK(AIRP_CODE1>0),
AIRP_CODE2			INT NOT NULL CHECK(AIRP_CODE2>0),
LEG_NO				INT NOT NULL CHECK(LEG_NO>0),
FLIGHT_NUMBER		INT NOT NULL CHECK(FLIGHT_NUMBER>0),
DEP_TIME			TIME NOT NULL,
ARR_TIME			TIME NOT NULL,

CONSTRAINT PK_LEG_INSTANCE
	PRIMARY KEY (INFO_DATE, LEG_NO, FLIGHT_NUMBER),
CONSTRAINT FK_LEG_INSTANCE
	FOREIGN KEY (AIRPLANE_ID) REFERENCES RESERVA_DE_VOOS.AIRPLANE(AIRPLANE_ID),
	FOREIGN KEY (AIRP_CODE1) REFERENCES RESERVA_DE_VOOS.AIRPORT(CODIGO),
	FOREIGN KEY (AIRP_CODE2) REFERENCES RESERVA_DE_VOOS.AIRPORT(CODIGO),
	FOREIGN KEY (LEG_NO, FLIGHT_NUMBER) REFERENCES RESERVA_DE_VOOS.FLIGHT_LEG(LEG_NO, FLIGHT_NUMBER),
	FOREIGN KEY (FLIGHT_NUMBER) REFERENCES RESERVA_DE_VOOS.FLIGHT(NUMBER),
);

CREATE TABLE RESERVA_DE_VOOS.SEAT(
SEAT_NO			INT NOT NULL CHECK(SEAT_NO>0),
CPHONE			INT NOT NULL CHECK (CPHONE>0),
LEG_NO			INT NOT NULL CHECK(LEG_NO>0),
INFO_DATE		DATE NOT NULL,
FLIGHT_NUMBER	INT NOT NULL,
COSTUMER_NAME	NVARCHAR(10) NOT NULL,

CONSTRAINT PK_SEAT
	PRIMARY KEY (SEAT_NO, LEG_NO, INFO_DATE),
CONSTRAINT FK_SEAT
	FOREIGN KEY (LEG_NO, FLIGHT_NUMBER) REFERENCES RESERVA_DE_VOOS.FLIGHT_LEG(LEG_NO, FLIGHT_NUMBER),
	FOREIGN KEY (INFO_DATE, LEG_NO, FLIGHT_NUMBER) REFERENCES RESERVA_DE_VOOS.LEG_INSTANCE(INFO_DATE, LEG_NO, FLIGHT_NUMBER),
	FOREIGN KEY(FLIGHT_NUMBER) REFERENCES RESERVA_DE_VOOS.FLIGHT(NUMBER),
);









