--> Dropping Tables
DROP TABLE IF EXISTS emergency_contact_donor;
DROP TABLE IF EXISTS emergency_contact_volunteer;
DROP TABLE IF EXISTS emergency_contact_employee;
DROP TABLE IF EXISTS emergency_contact_client;
DROP TABLE IF EXISTS has;
DROP TABLE IF EXISTS gives;
DROP TABLE IF EXISTS leads;
DROP TABLE IF EXISTS reports;
DROP TABLE IF EXISTS cares_for;
DROP TABLE IF EXISTS serves_on;
DROP TABLE IF EXISTS donation_credit_cards;
DROP TABLE IF EXISTS donation_checks;
DROP TABLE IF EXISTS donor;
DROP TABLE IF EXISTS expense;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS insurance_policy;
DROP TABLE IF EXISTS volunteer;
DROP TABLE IF EXISTS need;
DROP TABLE IF EXISTS client;

-- client Table

CREATE TABLE client (
    SSN INT PRIMARY KEY,
    name VARCHAR(256),
    gender VARCHAR(256),
    profession VARCHAR(256),
    on_mailing_list BIT,
    email VARCHAR(256),
    mailing_address VARCHAR(256),
    phone_number VARCHAR(256),
    date_assigned DATE,
    name_doctor VARCHAR(256),
    phone_number_doctor VARCHAR(256)
);

-- insurance_policy Table

CREATE TABLE insurance_policy (
    policy_id VARCHAR(256) PRIMARY KEY,
    provider_name VARCHAR(256),
    provider_address VARCHAR(256),
    insurance_type VARCHAR(256),
);

-- volunteer Table

CREATE TABLE volunteer (
    SSN INT PRIMARY KEY,
    name VARCHAR(256),
    gender VARCHAR(256),
    profession VARCHAR(256),
    on_mailing_list BIT,
    email VARCHAR(256),
    mailing_address VARCHAR(256),
    phone_number VARCHAR(256),
    date_joined DATE,
    date_trained DATE,
    training_location VARCHAR(256)
);

-- team Table

CREATE TABLE team (
    name_team VARCHAR(256) PRIMARY KEY,
    team_type VARCHAR(256),
    date_formed DATE
);

-- employee Table

CREATE TABLE employee (
    SSN INT PRIMARY KEY,
    name VARCHAR(256),
    gender VARCHAR(256),
    profession VARCHAR(256),
    on_mailing_list BIT,
    email VARCHAR(256),
    mailing_address VARCHAR(256),
    phone_number VARCHAR(256),
    salary DECIMAL(10, 2),
    marital_status VARCHAR(256),
    date_hired DATE
);

-- expense Table

CREATE TABLE expense (
    SSN INT,
    expense_date DATE,
    expense_amount DECIMAL(10, 2),
    expense_description VARCHAR(256),
    PRIMARY KEY (SSN, expense_date, expense_description),
    FOREIGN KEY (SSN) REFERENCES employee(SSN)
);

-- donor Table

CREATE TABLE donor (
    SSN INT PRIMARY KEY,
    name VARCHAR(256),
    gender VARCHAR(256),
    profession VARCHAR(256),
    on_mailing_list BIT,
    email VARCHAR(256),
    mailing_address VARCHAR(256),
    phone_number VARCHAR(256),
    is_anonymous BIT
);

-- donation_checks Table

CREATE TABLE donation_checks (
    SSN INT,
    check_number VARCHAR(256),
    donation_date DATE,
    donation_amount DECIMAL(10, 2),
    donation_type VARCHAR(256),
    campaign_name VARCHAR(256),
    PRIMARY KEY (SSN, donation_date, donation_amount, donation_type),
    FOREIGN KEY (SSN) REFERENCES donor(SSN)
);

-- donation_credit_cards Table

CREATE TABLE donation_credit_cards (
    SSN INT,
    card_number VARCHAR(256),
    card_type VARCHAR(256),
    expiration_date VARCHAR(256),
    donation_date DATE,
    donation_amount DECIMAL(10, 2),
    donation_type VARCHAR(256),
    campaign_name VARCHAR(256),
    PRIMARY KEY (SSN, donation_date, donation_amount, donation_type),
    FOREIGN KEY (SSN) REFERENCES donor(SSN)
);

-- serves_on Table

CREATE TABLE serves_on (
    SSN INT,
    name_team VARCHAR(256),
    month_serving VARCHAR(256),
    hours_served INT,
    is_active BIT,
    PRIMARY KEY (SSN, name_team, month_serving),
    FOREIGN KEY (SSN) REFERENCES volunteer(SSN),
    FOREIGN KEY (name_team) REFERENCES team(name_team)
);

-- cares_for Table

CREATE TABLE cares_for (
    SSN INT,
    name_team VARCHAR(256),
    is_active BIT,
    PRIMARY KEY (SSN, name_team),
    FOREIGN KEY (SSN) REFERENCES client(SSN) ON DELETE CASCADE,
    FOREIGN KEY (name_team) REFERENCES team(name_team)
);

-- reports Table

CREATE TABLE reports (
    SSN INT,
    name_team VARCHAR(256),
    report_date DATE,
    report_description VARCHAR(512),
    PRIMARY KEY (SSN, name_team),
    FOREIGN KEY (SSN) REFERENCES employee(SSN),
    FOREIGN KEY (name_team) REFERENCES team(name_team)
);

-- leads Table

CREATE TABLE leads (
    SSN INT,
    name_team VARCHAR(256),
    is_leader BIT,
    PRIMARY KEY (SSN, name_team),
    FOREIGN KEY (SSN) REFERENCES volunteer(SSN),
    FOREIGN KEY (name_team) REFERENCES team(name_team)
);

-- has Table

CREATE TABLE has (
    SSN INT,
    policy_id VARCHAR(256),
    PRIMARY KEY (SSN, policy_id),
    FOREIGN KEY (SSN) REFERENCES client(SSN) ON DELETE CASCADE,
    FOREIGN KEY (policy_id) REFERENCES insurance_policy(policy_id)
);

-- need Table

CREATE TABLE need (
    SSN INT,
    need_type VARCHAR(256),
    importance_value INT, -- add constraint (between 1 and 10 )
    PRIMARY KEY (SSN, need_type),
    FOREIGN KEY (SSN) REFERENCES client(SSN)
);
-- Separate Emergency Contacts Tables for each entity

CREATE TABLE emergency_contact_donor (
    SSN_donor INT,
    name_contact VARCHAR(256),
    phone_number_contact VARCHAR(256),
    relationship_to_person VARCHAR(256),
    PRIMARY KEY (SSN_donor, name_contact , phone_number_contact),
    FOREIGN KEY (SSN_donor) REFERENCES donor(SSN)
);

CREATE TABLE emergency_contact_volunteer (
    SSN_volunteer INT,
    name_contact VARCHAR(256),
    phone_number_contact VARCHAR(256),
    relationship_to_person VARCHAR(256),
    PRIMARY KEY (SSN_volunteer, name_contact, phone_number_contact),
    FOREIGN KEY (SSN_volunteer) REFERENCES volunteer(SSN)
);

CREATE TABLE emergency_contact_employee (
    SSN_employee INT,
    name_contact VARCHAR(256),
    phone_number_contact VARCHAR(256),
    relationship_to_person VARCHAR(256),
    PRIMARY KEY (SSN_employee, name_contact, phone_number_contact),
    FOREIGN KEY (SSN_employee) REFERENCES employee(SSN)
);

CREATE TABLE emergency_contact_client (
    SSN_client INT,
    name_contact VARCHAR(256),
    phone_number_contact VARCHAR(256),
    relationship_to_person VARCHAR(256),
    PRIMARY KEY (SSN_client, name_contact, phone_number_contact),
    FOREIGN KEY (SSN_client) REFERENCES client(SSN) ON DELETE CASCADE
);

-- --Creating indexes
--client index
DROP INDEX IF EXISTS client.C_SSN;
CREATE INDEX C_SSN ON client(SSN);
--volunteer index
DROP INDEX IF EXISTS volunteer.V_SSN;
CREATE INDEX V_SSN ON volunteer(SSN);
--employee index
DROP INDEX IF EXISTS employee.E_SSN;
CREATE INDEX E_SSN ON employee(SSN);
--Donor index
DROP INDEX IF EXISTS donor.D_SSN;
CREATE INDEX D_SSN ON donor(SSN);
--emergency_contact_client index
DROP INDEX IF EXISTS emergency_contact_client.CEM_SSN;
CREATE INDEX CEM_SSN ON emergency_contact_client(SSN_client);
--VolunteerEmergencyContacts index
DROP INDEX IF EXISTS emergency_contact_volunteer.VEM_SSN;
CREATE INDEX VEM_SSN ON emergency_contact_volunteer(SSN_volunteer);
--emergency_contact_employee index
DROP INDEX IF EXISTS emergency_contact_employee.EEM_SSN;
CREATE INDEX EEM_SSN ON emergency_contact_employee(SSN_employee);
--emergency_contact_donor index
DROP INDEX IF EXISTS emergency_contact_donor.CEM_SSN;
CREATE INDEX CEM_SSN ON emergency_contact_donor(SSN_donor);
--team index
DROP INDEX IF EXISTS team.T_name;
CREATE INDEX T_name ON team(name_team);
--expense index
DROP INDEX IF EXISTS expense.E_amount;
CREATE INDEX E_amount ON expense(expense_amount); 
--donation_checks index
DROP INDEX IF EXISTS donation_checks.Check_amount; 
CREATE INDEX Check_amount ON donation_checks(donation_amount);
--donation_credit_cards index 
DROP INDEX IF EXISTS donation_credit_cards.CC_Donation_amount;
CREATE INDEX CC_Donation_amount ON donation_credit_cards(donation_amount);