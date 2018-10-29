CREATE TABLE Admin(
    id varchar(50),
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(64),
    organization_id varchar(50) NOT NULL,
    PRIMARY KEY (id)
    -- FOREIGN KEY (organization_id) REFERENCES Organization(id)
);

CREATE TABLE AdminRole(
    id varchar(50),
    admin_id varchar(50) NOT NULL,
    role_id varchar(50) NOT NULL,
    PRIMARY KEY (id)
--    FOREIGN KEY (admin_id) REFERENCES Admin(id),
--    FOREIGN KEY (role_id) REFERENCES Role(id)
);

CREATE TABLE Role(
    id varchar(50),
    name varchar(255) NOT NULL,
    organization_id varchar(50) NOT NULL,
    PRIMARY KEY (id)
--    FOREIGN KEY (organization_id) REFERENCES Organization(id)
);

CREATE TABLE Permission(
    id varchar(50),
    entity varchar(30) NOT NULL,
    operation varchar(10) NOT NULL,
    role_id varchar(50) NOT NULL,
    PRIMARY KEY (id),
--    FOREIGN KEY (role_id) REFERENCES Role(id),
--    CONSTRAINT CHK_Entity CHECK (entity in ('ADMIN', 'ROLE', 'ORGANIZATION', 'PRODUCT', 'SHARING_STATEMENT')),
--    CONSTRAINT CHK_Operation CHECK (operation in ('CREATE', 'READ', 'UPDATE', 'DELETE'))
);

CREATE TABLE Organization(
    id varchar(50),
    name varchar(255) NOT NULL,
    master tinyint,
    PRIMARY KEY (id),
--    CONSTRAINT CHK_Master CHECK (master in (0, 1)),
    CONSTRAINT UQ_Name UNIQUE (name)
);

CREATE TABLE Product(
    id varchar(50),
    name varchar(255) NOT NULL,
    description varchar(500),
    price float,
    organization_id varchar(50) NOT NULL,
    PRIMARY KEY (id)
--    FOREIGN KEY (organization_id) REFERENCES Organization (id)
);

CREATE TABLE ProductSharingStatement(
    id varchar(50),
    sharingOrgId varchar(50) NOT NULL,
    accessingOrgId varchar(50) NOT NULL,
    quantity bigint,
    relation varchar(5),
    approved tinyint,
    price bigint,
    role_id varchar(50),
    PRIMARY KEY (id)
--    FOREIGN KEY (sharingOrgId) REFERENCES Organization (id),
--    FOREIGN KEY (accessingOrgId) REFERENCES Organization (id),
--    CONSTRAINT CHK_Relation CHECK (relation in ('EQ', 'GT', 'GTE','LT', 'LTE')),
--    CONSTRAINT CHK_APPROVED CHECK (approved in (0,1))
);

CREATE TABLE USERS(
    username varchar(50) not null,
    password varchar(64) not null,
    enabled tinyint not null,
    Primary key (username)
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
);

