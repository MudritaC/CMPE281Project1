create table USER(
USER_ID VARCHAR(50) NOT NULL,
FIRST_NAME VARCHAR (100),
LAST_NAME VARCHAR (100),
CREATED_DATE DATETIME ,
MODIFIED_DATE DATETIME,
PASSWORD VARCHAR(100),
PRIMARY KEY (USER_ID)
);

CREATE INDEX USERID_INDEX
ON USER (USER_ID);

create table FILE_DETAILS(
FILE_ID INT NOT NULL AUTO_INCREMENT,
USER_ID VARCHAR (50) NOT NULL,
FILE_NAME VARCHAR (1024)NOT NULL UNIQUE,
FILE_DSC VARCHAR(2048),
FILE_URL VARCHAR(2048),
VERSION VARCHAR(50),
CREATED_DATE DATETIME ,
MODIFIED_DATE DATETIME,
PRIMARY KEY (FILE_ID)
);

CREATE INDEX FILE_INDEX
ON FILE_DETAILS (FILE_ID);








