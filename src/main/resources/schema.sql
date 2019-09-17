CREATE TABLE logins
(
    userId varchar(11) NOT NULL,
    userName varchar(100) NOT NULL,
    lastLogin TIMESTAMP,
    PRIMARY KEY (userId)
);
