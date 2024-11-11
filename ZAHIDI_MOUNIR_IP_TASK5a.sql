-- Query 1: Insert a new team into the database (1/month)
DROP PROCEDURE IF EXISTS insertTeam;
GO

CREATE PROCEDURE insertTeam
(
    @name_team VARCHAR(256),
    @team_type VARCHAR(256),
    @date_formed DATE
)
AS
BEGIN
    INSERT INTO team (name_team, team_type, date_formed) VALUES (@name_team, @team_type, @date_formed);
END;
GO

-- Query 2: Insert a new client into the database and associate with one or more teams
DROP PROCEDURE IF EXISTS insertClient;
GO

CREATE PROCEDURE insertClient
(
    @SSN INT,
    @name VARCHAR(256),
    @gender VARCHAR(256),
    @profession VARCHAR(256),
    @on_mailing_list BIT,
    @email VARCHAR(256),
    @mailing_address VARCHAR(256),
    @phone_number VARCHAR(256),
    @date_assigned DATE,
    @name_doctor VARCHAR(256),
    @phone_number_doctor VARCHAR(256),
    -- @name_team VARCHAR(256),
    @name_contact VARCHAR(256),
    @phone_number_contact VARCHAR(256),
    @relationship_to_person VARCHAR(256),
    @need_type VARCHAR(256),
    @importance_value INT,
    @policy_id VARCHAR(256),
    @provider_name VARCHAR(256),
    @provider_address VARCHAR(256),
    @insurance_type VARCHAR(256)
)
AS
BEGIN

    -- Insert into client table
    INSERT INTO client (SSN, name, gender, profession, on_mailing_list, email, mailing_address, phone_number, date_assigned, name_doctor, phone_number_doctor)
    VALUES (@SSN, @name, @gender, @profession, @on_mailing_list, @email, @mailing_address, @phone_number, @date_assigned, @name_doctor, @phone_number_doctor);

    -- Insert into emergency_contact_client table
    INSERT INTO emergency_contact_client (SSN_client, name_contact, phone_number_contact, relationship_to_person)
    VALUES (@SSN, @name_contact, @phone_number_contact, @relationship_to_person);

    -- -- Insert into cares_for table
    -- INSERT INTO CaresFor (ssn, team_name, client_active)
    -- VALUES (@ssn, @team_name, 1);

    -- Insert into Needs table
    INSERT INTO need (SSN, need_type, importance_value)
    VALUES (@SSN, @need_type, @importance_value);

    -- Insert into insurance_policy table
    INSERT INTO insurance_policy (policy_id, provider_name, provider_address, insurance_type)
    VALUES (@policy_id, @provider_name, @provider_address, @insurance_type);

    -- -- Insert into Has table
    -- INSERT INTO Has (ssn, policy_id)
    -- VALUES (@ssn, @policy_id);
END;
GO

-- Query 3: Insert a new volunteer into the database and associate with one or more teams
DROP PROCEDURE IF EXISTS insertVolunteer;
GO

CREATE PROCEDURE insertVolunteer
(
    @SSN INT,
    @name VARCHAR(256),
    @gender VARCHAR(256),
    @profession VARCHAR(256),
    @on_mailing_list BIT,
    @email VARCHAR(256),
    @mailing_address VARCHAR(256),
    @phone_number VARCHAR(256),
    @date_joined DATE,
    @date_trained DATE,
    @training_location VARCHAR(256),
    -- @team_name VARCHAR(256),
    @name_contact VARCHAR(256),
    @phone_number_contact VARCHAR(256),
    @relationship_to_person VARCHAR(256)
    -- @serving_month VARCHAR(256),
    -- @served_hours INT,
    -- @active BIT
)
AS
BEGIN

    -- Insert into volunteer table
    INSERT INTO volunteer (SSN, name, gender, profession, on_mailing_list, email, mailing_address, phone_number, date_joined, date_trained, training_location)
    VALUES (@SSN, @name, @gender, @profession, @on_mailing_list, @email, @mailing_address, @phone_number, @date_joined, @date_trained, @training_location);

    -- Insert into emergency_contact_volunteer table
    INSERT INTO emergency_contact_volunteer (SSN_volunteer, name_contact, phone_number_contact, relationship_to_person)
    VALUES (@SSN, @name_contact, @phone_number_contact, @relationship_to_person);

    -- -- Insert into ServesOn table
    -- INSERT INTO ServesOn (ssn, team_name, serving_month, served_hours, active)
    -- VALUES (@ssn, @team_name, @serving_month, @served_hours, @active);

    -- -- Insert into Leads table
    -- INSERT INTO Leads (ssn, team_name)
    -- VALUES (@ssn, @team_name, @is_leader);
END;
GO

-- Query 4: Insert number of hours a volunteer worked this month for a particular team
DROP PROCEDURE IF EXISTS insertVolunteerHours;
GO

CREATE PROCEDURE insertVolunteerHours
(
    @SSN INT,
    @name_team VARCHAR(256),
    @month_serving VARCHAR(256),
    @hours_served INT,
    @is_active BIT
)
AS
BEGIN
    INSERT INTO serves_on (SSN, name_team, month_serving, hours_served, is_active)
    VALUES (@SSN, @name_team, @month_serving, @hours_served, @is_active);
END;
GO

-- Query 5: Insert a new employee into the database and associate with one or more teams
DROP PROCEDURE IF EXISTS insertEmployee;
GO

CREATE PROCEDURE insertEmployee
(
    @SSN INT,
    @name VARCHAR(256),
    @gender VARCHAR(256),
    @profession VARCHAR(256),
    @on_mailing_list BIT,
    @email VARCHAR(256),
    @mailing_address VARCHAR(256),
    @phone_number VARCHAR(256),
    @salary DECIMAL(10,2),
    @marital_status VARCHAR(256),
    @date_hired DATE,
    @name_contact VARCHAR(256),
    @phone_number_contact VARCHAR(256),
    @relationship_to_person VARCHAR(256),
    @name_team VARCHAR(256) 
)
AS
BEGIN
    -- Insert into employee table
    INSERT INTO employee (SSN, name, gender, profession, on_mailing_list, email, mailing_address, phone_number, salary, marital_status, date_hired)
    VALUES (@SSN, @name, @gender, @profession, @on_mailing_list, @email, @mailing_address, @phone_number, @salary, @marital_status, @date_hired);

    -- Insert into EmployeeEmergencyContacts table
    INSERT INTO emergency_contact_employee (SSN_employee, name_contact, phone_number_contact, relationship_to_person)
    VALUES (@SSN, @name_contact, @phone_number_contact, @relationship_to_person);

    -- -- Insert into Reports table
    -- INSERT INTO Reports (ssn, team_name, report_date, report_description)
    -- VALUES (@ssn, @team_name, @report_date, @report_description);
END;
GO


-- Query 6: Insert an expense charged by an employee
DROP PROCEDURE IF EXISTS insertExpense;
GO

CREATE PROCEDURE insertExpense
(
    @SSN INT,
    @expense_date DATE,
    @expense_amount DECIMAL(10, 2),
    @expense_description VARCHAR(256)
)
AS
BEGIN
    INSERT INTO expense (SSN, expense_date, expense_amount, expense_description)
    VALUES (@SSN, @expense_date, @expense_amount, @expense_description);
END;
GO

-- Query 7: Insert a new donor and associate with multiple donations
DROP PROCEDURE IF EXISTS insertDonor;
GO

CREATE PROCEDURE insertDonor
(
    @SSN INT,
    @name VARCHAR(256),
    @gender VARCHAR(256),
    @profession VARCHAR(256),
    @on_mailing_list BIT,
    @email VARCHAR(256),
    @mailing_address VARCHAR(256),
    @phone_number VARCHAR(256),
    @is_anonymous BIT,
    @name_contact VARCHAR(256),
    @phone_number_contact VARCHAR(256),
    @relationship_to_person VARCHAR(256)
    -- @donation_date DATE,
    -- @donation_amount DECIMAL(10,2),
    -- @donation_type VARCHAR(256),
    -- @campaign_name VARCHAR(256),
    -- @card_number VARCHAR(256) = NULL,
    -- @card_type VARCHAR(256) = NULL,
    -- @expiration_date VARCHAR(256) = NULL,
    -- @check_number VARCHAR(256) = NULL
)
AS
BEGIN
    -- Insert into Donors table
    INSERT INTO donor (SSN, name, gender, profession, on_mailing_list, email, mailing_address, phone_number, is_anonymous)
    VALUES (@SSN, @name, @gender, @profession, @on_mailing_list, @email, @mailing_address, @phone_number, @is_anonymous);

    -- Insert into DonorEmergencyContacts table
    INSERT INTO emergency_contact_donor (SSN_donor, name_contact, phone_number_contact, relationship_to_person)
    VALUES (@SSN, @name_contact, @phone_number_contact, @relationship_to_person);

    -- -- Insert donation details based on the type of donation
    -- IF @donation_type = 'credit card' AND @card_number IS NOT NULL
    -- BEGIN
    --     INSERT INTO CreditCards (ssn, card_number, card_type, expiration_date, donation_date, amount, donation_type, campaign_name)
    --     VALUES (@ssn, @card_number, @card_type, @expiration_date, @donation_date, @donation_amount, @donation_type, @campaign_name);
    -- END
    -- ELSE IF @donation_type = 'check' AND @check_number IS NOT NULL
    -- BEGIN
    --     INSERT INTO DonationChecks (ssn, check_number, donation_date, amount, donation_type, campaign_name)
    --     VALUES (@ssn, @check_number, @donation_date, @donation_amount, @donation_type, @campaign_name);
    -- END
END;
GO


-- Query 8: Retrieve the name and phone number of the doctor of a particular client (1/week)
DROP PROCEDURE IF EXISTS retrieveDoctorInfo;
GO

CREATE PROCEDURE retrieveDoctorInfo
(
    @SSN INT
)
AS
BEGIN
    -- Retrieve the doctor’s name and phone number for a specific client
    SELECT name_doctor, phone_number_doctor 
    FROM client 
    WHERE SSN = @SSN;
END;
GO

-- Query 9: Retrieve total expenses charged by each employee for a particular period, sorted by amount (1/month)
DROP PROCEDURE IF EXISTS retrieveTotalExpenses;
GO

CREATE PROCEDURE retrieveTotalExpenses
(
    @start_date DATE,
    @end_date DATE
)
AS
BEGIN
    -- Calculate total expenses for each employee within a date range
    SELECT SSN, SUM(expense_amount) AS total_expenses
    FROM expense
    WHERE expense_date BETWEEN @start_date AND @end_date
    GROUP BY SSN
    ORDER BY total_expenses DESC;
END;
GO

-- Query 10: Retrieve the list of volunteers that are members of teams that support a particular client (4/year).
DROP PROCEDURE IF EXISTS GetVolunteersForClient;
GO

CREATE PROCEDURE GetVolunteersForClient
(
    @SSN INT
)
AS
BEGIN
    SELECT v.name
    FROM volunteer v
    JOIN serves_on s ON v.SSN = s.SSN
    JOIN cares_for c ON s.name_team = c.name_team
    WHERE c.SSN = @SSN;
END
GO

-- Query 11: Retrieve the names of all teams founded after a specified date (1/month)
DROP PROCEDURE IF EXISTS getTeamsAfterDate;
GO

CREATE PROCEDURE getTeamsAfterDate
(
    @date_formed DATE
)
AS
BEGIN
    -- Retrieve team names founded after a given date
    SELECT name_team 
    FROM team
    WHERE date_formed > @date_formed;
END;
GO

-- Query 12: Retrieve names, SSNs, contact information, and emergency contacts for all people (1/week)
DROP PROCEDURE IF EXISTS getAllPeopleInfo;
GO

CREATE PROCEDURE getAllPeopleInfo
AS
BEGIN
    -- Retrieve details for all people (employees, volunteers, clients, donors)
    SELECT e.name, e.SSN, e.mailing_address, e.phone_number, e.email, ec.name_contact AS name_contact, ec.phone_number_contact AS phone_number_contact, ec.relationship_to_person AS relationship_to_person
    FROM employee e
    LEFT JOIN emergency_contact_employee ec ON e.SSN = ec.SSN_employee
    UNION ALL
    SELECT v.name, v.SSN, v.mailing_address, v.phone_number, v.email, vc.name_contact AS name_contact, vc.phone_number_contact AS phone_number_contact, vc.relationship_to_person AS relationship_to_person
    FROM volunteer v
    LEFT JOIN emergency_contact_volunteer vc ON v.SSN = vc.SSN_volunteer
    UNION ALL
    SELECT c.name, c.SSN, c.mailing_address, c.phone_number, c.email, cc.name_contact AS name_contact, cc.phone_number_contact AS phone_number_contact, cc.relationship_to_person AS relationship_to_person
    FROM client c
    LEFT JOIN emergency_contact_client cc ON c.SSN = cc.SSN_client
    UNION ALL
    SELECT d.name, d.SSN, d.mailing_address, d.phone_number, d.email, dc.name_contact AS name_contact, dc.phone_number_contact AS phone_number_contact, dc.relationship_to_person AS relationship_to_person
    FROM donor d
    LEFT JOIN emergency_contact_donor dc ON d.SSN = dc.SSN_donor;
END;
GO


-- Query 13: Retrieve the name and total donations by donors who are also employees, sorted by amount (1/week)
DROP PROCEDURE IF EXISTS getEmployeeDonors;
GO

CREATE PROCEDURE getEmployeeDonors
AS
BEGIN
    -- Retrieve donation information for donors who are also employees
    SELECT d.name, SUM(donation_amount) AS total_donations
    FROM donor d
    JOIN donation_checks dc ON d.SSN = dc.SSN
    JOIN employee e ON d.SSN = e.SSN
    GROUP BY d.name
    ORDER BY total_donations DESC;
END;
GO
    

-- Query 14: Increase salary by 10% for employees with reports from more than one team (1/year)
DROP PROCEDURE IF EXISTS increaseSalaryForMultiTeamEmployees;
GO

CREATE PROCEDURE increaseSalaryForMultiTeamEmployees
AS
BEGIN
    -- Increase salary by 10% for employees reporting on multiple teams
    UPDATE employee
    SET salary = salary * 1.1
    WHERE SSN IN (
        SELECT SSN
        FROM reports
        GROUP BY SSN
        HAVING COUNT(DISTINCT name_team) > 1
    );
END;
GO

-- Query 15: Delete all clients who do not have health insurance and whose value of importance for transportation is less than 5 (4/year).

DROP PROCEDURE IF EXISTS deleteClients;
GO

CREATE PROCEDURE deleteClients
AS
BEGIN
    DECLARE @clientsToDelete TABLE (SSN INT);
    
    -- Insert the SSNs of clients who meet the criteria for deletion
    -- Insert the SSNs of clients who meet the criteria for deletion
    INSERT INTO @clientsToDelete (SSN)
    SELECT c.SSN
    FROM client c 
    JOIN has h ON c.SSN = h.SSN
    JOIN insurance_policy ip ON h.policy_id = ip.policy_id
    WHERE ip.insurance_type != 'Health' 
    AND c.SSN IN (
        SELECT SSN 
        FROM need 
        WHERE need_type = 'Transportation' 
        AND importance_value < 5
    );

    -- Delete from the 'Needs' table
    DELETE FROM need 
    WHERE SSN IN (SELECT SSN FROM @clientsToDelete);

    -- Delete from the 'Has' table
    DELETE FROM has 
    WHERE SSN IN (SELECT SSN FROM @clientsToDelete);

    -- Delete from the 'InsurancePolicies' table if not referenced in the 'Has' table
    DELETE FROM insurance_policy
    WHERE policy_id NOT IN (SELECT policy_id FROM has);

    -- Delete from 'ClientEmergencyContacts' table
    DELETE FROM emergency_contact_client
    WHERE SSN_client IN (SELECT SSN FROM @clientsToDelete);

    -- Finally, delete from 'Clients' table
    DELETE FROM client 
    WHERE SSN IN (SELECT SSN FROM @clientsToDelete);
END
GO

-- Query 17: Retrieve names and mailing addresses of all people on the mailing list
-- DROP PROCEDURE IF EXISTS exportMailingList;
-- GO

-- CREATE PROCEDURE exportMailingList
-- AS
-- BEGIN
--     SELECT person_name, mailing_address
--       FROM Clients
--      WHERE on_mailing_list = 1
--      UNION
--     SELECT person_name, mailing_address
--       FROM Volunteers
--      WHERE on_mailing_list = 1
--      UNION
--     SELECT person_name, mailing_address
--       FROM Employees
--      WHERE on_mailing_list = 1
--      UNION
--     SELECT person_name, mailing_address
--       FROM Donors
--      WHERE on_mailing_list = 1;
-- END
-- GO