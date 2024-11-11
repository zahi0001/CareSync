package org.example;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//        1. Enter a new team into the database (1/month).
//        2. Enter a new client into the database and associate him or her with one or more teams (1/week).
//        3. Enter a new volunteer into the database and associate him or her with one or more teams (2/month).
//        4. Enter the number of hours a volunteer worked this month for a particular team (30/month).
//        5. Enter a new employee into the database and associate him or her with one or more teams (1/year).
//        6. Enter an expense charged by an employee (1/day).
//        7. Enter a new donor and associate him or her with several donations (1/day).
//        8. Retrieve the name and phone number of the doctor of a particular client (1/week).
//        9. Retrieve the total amount of expenses charged by each employee for a particular period of time. The list should be sorted by the total amount of expenses (1/month).
//        10. Retrieve the list of volunteers that are members of teams that support a particular client (4/year).
//        11. Retrieve the names of all teams that were founded after a particular date (1/month).
//        12. Retrieve the names, social security numbers, contact information, and emergency contact information of all people in the database (1/week).
//        13. Retrieve the name and total amount donated by donors that are also employees. The list should be sorted by the total amount of the donations, and indicate if each donor wishes to remain anonymous (1/week)
//        14. Increase the salary by 10% of all employees to whom more than one team must report. (1/year)
//        15. Delete all clients who do not have health insurance and whose value of importance for transportation is less than 5 (4/year).


//DROP TABLE IF EXISTS DonorEmergencyContacts;
//DROP TABLE IF EXISTS VolunteerEmergencyContacts;
//DROP TABLE IF EXISTS EmployeeEmergencyContacts;
//DROP TABLE IF EXISTS ClientEmergencyContacts;
//DROP TABLE IF EXISTS Has;
//DROP TABLE IF EXISTS Gives;
//DROP TABLE IF EXISTS Charged;
//DROP TABLE IF EXISTS Leads;
//DROP TABLE IF EXISTS Reports;
//DROP TABLE IF EXISTS CaresFor;
//DROP TABLE IF EXISTS ServesOn;
//DROP TABLE IF EXISTS CreditCards;
//DROP TABLE IF EXISTS DonationChecks;
//DROP TABLE IF EXISTS Donations;
//DROP TABLE IF EXISTS Donors;
//DROP TABLE IF EXISTS Expenses;
//DROP TABLE IF EXISTS Employees;
//DROP TABLE IF EXISTS Teams;
//DROP TABLE IF EXISTS InsurancePolicies;
//DROP TABLE IF EXISTS Volunteers;
//DROP TABLE IF EXISTS Needs;
//DROP TABLE IF EXISTS Clients;
//
//-- Clients Table
//
//CREATE TABLE Clients (
//        ssn INT PRIMARY KEY,
//        person_name VARCHAR(256),
//gender VARCHAR(256),
//profession VARCHAR(256),
//on_mailing_list BIT,
//mailing_address VARCHAR(256),
//phone_number VARCHAR(256),
//email_address VARCHAR(256),
//assignment_date DATE,
//doctor_name VARCHAR(256),
//doctor_phone_number VARCHAR(256)
//);
//
//        -- Needs Table
//
//CREATE TABLE Needs (
//        ssn INT,
//        need_type VARCHAR(256),
//importance INT,
//FOREIGN KEY (ssn) REFERENCES Clients(ssn)
//        );
//
//        -- Insurance Policies Table
//
//CREATE TABLE InsurancePolicies (
//        ssn INT,
//        policy_id INT PRIMARY KEY,
//        provider_name VARCHAR(256),
//provider_address VARCHAR(256),
//insurance_type VARCHAR(256),
//FOREIGN KEY (ssn) REFERENCES Clients(ssn)
//        );
//
//        -- Volunteers Table
//
//CREATE TABLE Volunteers (
//        ssn INT PRIMARY KEY,
//        person_name VARCHAR(256),
//gender VARCHAR(256),
//profession VARCHAR(256),
//on_mailing_list BIT,
//mailing_address VARCHAR(256),
//phone_number VARCHAR(256),
//email_address VARCHAR(256),
//date_joined DATE,
//training_date DATE,
//training_location VARCHAR(256)
//);
//
//        -- Teams Table
//
//CREATE TABLE Teams (
//        team_name VARCHAR(256) PRIMARY KEY,
//team_type VARCHAR(256),
//date_formed DATE
//);
//
//        -- Employees Table
//
//CREATE TABLE Employees (
//        ssn INT PRIMARY KEY,
//        person_name VARCHAR(256),
//gender VARCHAR(256),
//profession VARCHAR(256),
//on_mailing_list BIT,
//mailing_address VARCHAR(256),
//phone_number VARCHAR(256),
//email_address VARCHAR(256),
//salary DECIMAL(10, 2),
//marital_status VARCHAR(256),
//hire_date DATE
//);
//
//        -- Expenses Table
//
//CREATE TABLE Expenses (
//        ssn INT,
//        expense_date DATE,
//        amount DECIMAL(10, 2),
//expense_description VARCHAR(256),
//FOREIGN KEY (ssn) REFERENCES Employees(ssn)
//        );
//
//        -- Donors Table
//
//CREATE TABLE Donors (
//        ssn INT PRIMARY KEY,
//        person_name VARCHAR(256),
//gender VARCHAR(256),
//profession VARCHAR(256),
//on_mailing_list BIT,
//mailing_address VARCHAR(256),
//phone_number VARCHAR(256),
//email_address VARCHAR(256),
//is_anon BIT
//);
//
//        -- -- Donations Table
//
//-- CREATE TABLE Donations (
//--     ssn INT,
//--     donation_date DATE,
//--     amount DECIMAL(10, 2),
//--     donation_type VARCHAR(256),
//--     campaign_name VARCHAR(256),
//--     FOREIGN KEY (ssn) REFERENCES Donors(ssn)
//        -- );
//
//        -- DonationChecks Table
//
//CREATE TABLE DonationChecks (
//        ssn INT,
//        check_number VARCHAR(256),
//donation_date DATE,
//amount DECIMAL(10, 2),
//donation_type VARCHAR(256),
//campaign_name VARCHAR(256),
//FOREIGN KEY (ssn) REFERENCES Donors(ssn)
//        );
//
//        -- CreditCards Table
//
//CREATE TABLE CreditCards (
//        ssn INT,
//        card_number VARCHAR(256),
//card_type VARCHAR(256),
//expiration_date VARCHAR(256),
//donation_date DATE,
//amount DECIMAL(10, 2),
//donation_type VARCHAR(256),
//campaign_name VARCHAR(256),
//FOREIGN KEY (ssn) REFERENCES Donors(ssn)
//        );
//
//        -- ServesOn Table
//
//CREATE TABLE ServesOn (
//        ssn INT,
//        team_name VARCHAR(256),
//serving_month VARCHAR(256),
//served_hours INT,
//active BIT,
//FOREIGN KEY (ssn) REFERENCES Volunteers(ssn),
//FOREIGN KEY (team_name) REFERENCES Teams(team_name)
//        );
//
//        -- CaresFor Table
//
//CREATE TABLE CaresFor (
//        ssn INT,
//        team_name VARCHAR(256),
//client_active BIT,
//FOREIGN KEY (ssn) REFERENCES Clients(ssn),
//FOREIGN KEY (team_name) REFERENCES Teams(team_name)
//        );
//
//        -- Reports Table
//
//CREATE TABLE Reports (
//        ssn INT,
//        team_name VARCHAR(256),
//report_date DATE,
//report_description VARCHAR(512),
//FOREIGN KEY (ssn) REFERENCES Employees(ssn),
//FOREIGN KEY (team_name) REFERENCES Teams(team_name)
//        );
//
//        -- Leads Table
//
//CREATE TABLE Leads (
//        ssn INT,
//        team_name VARCHAR(256),
//FOREIGN KEY (ssn) REFERENCES Volunteers(ssn),
//FOREIGN KEY (team_name) REFERENCES Teams(team_name)
//        );
//
//        -- Has Table
//
//CREATE TABLE Has (
//        ssn INT,
//        policy_id INT,
//        FOREIGN KEY (ssn) REFERENCES Clients(ssn),
//FOREIGN KEY (policy_id) REFERENCES InsurancePolicies(policy_id)
//        );
//
//        -- Separate Emergency Contacts Tables for each entity
//
//CREATE TABLE DonorEmergencyContacts (
//        donor_ssn INT,
//        contact_name VARCHAR(256),
//contact_phone_number VARCHAR(256),
//relationship VARCHAR(256),
//PRIMARY KEY (donor_ssn, contact_name),
//FOREIGN KEY (donor_ssn) REFERENCES Donors(ssn)
//        );
//
//CREATE TABLE VolunteerEmergencyContacts (
//        volunteer_ssn INT,
//        contact_name VARCHAR(256),
//contact_phone_number VARCHAR(256),
//relationship VARCHAR(256),
//PRIMARY KEY (volunteer_ssn, contact_name),
//FOREIGN KEY (volunteer_ssn) REFERENCES Volunteers(ssn)
//        );
//
//CREATE TABLE EmployeeEmergencyContacts (
//        employee_ssn INT,
//        contact_name VARCHAR(256),
//contact_phone_number VARCHAR(256),
//relationship VARCHAR(256),
//PRIMARY KEY (employee_ssn, contact_name),
//FOREIGN KEY (employee_ssn) REFERENCES Employees(ssn)
//        );
//
//CREATE TABLE ClientEmergencyContacts (
//        client_ssn INT,
//        contact_name VARCHAR(256),
//contact_phone_number VARCHAR(256),
//relationship VARCHAR(256),
//PRIMARY KEY (client_ssn, contact_name),
//FOREIGN KEY (client_ssn) REFERENCES Clients(ssn)
//        );
//

//-- DROP TABLE IF EXISTS DonorEmergencyContacts;
//-- DROP TABLE IF EXISTS VolunteerEmergencyContacts;
//-- DROP TABLE IF EXISTS EmployeeEmergencyContacts;
//-- DROP TABLE IF EXISTS ClientEmergencyContacts;
//-- DROP TABLE IF EXISTS Has;
//-- DROP TABLE IF EXISTS Gives;
//-- DROP TABLE IF EXISTS Charged;
//-- DROP TABLE IF EXISTS Leads;
//-- DROP TABLE IF EXISTS Reports;
//-- DROP TABLE IF EXISTS CaresFor;
//-- DROP TABLE IF EXISTS ServesOn;
//-- DROP TABLE IF EXISTS CreditCards;
//-- DROP TABLE IF EXISTS DonationChecks;
//-- DROP TABLE IF EXISTS Donations;
//-- DROP TABLE IF EXISTS Donors;
//-- DROP TABLE IF EXISTS Expenses;
//-- DROP TABLE IF EXISTS Employees;
//-- DROP TABLE IF EXISTS Teams;
//-- DROP TABLE IF EXISTS InsurancePolicies;
//-- DROP TABLE IF EXISTS Volunteers;
//-- DROP TABLE IF EXISTS Needs;
//-- DROP TABLE IF EXISTS Clients;
//
//-- -- Clients Table
//
//-- CREATE TABLE Clients (
//--     ssn INT PRIMARY KEY,
//--     person_name VARCHAR(256),
//--     gender VARCHAR(256),
//--     profession VARCHAR(256),
//--     on_mailing_list BIT,
//--     mailing_address VARCHAR(256),
//--     phone_number VARCHAR(256),
//--     email_address VARCHAR(256),
//--     assignment_date DATE,
//--     doctor_name VARCHAR(256),
//--     doctor_phone_number VARCHAR(256)
//-- );
//
//        -- -- Needs Table
//
//-- CREATE TABLE Needs (
//--     ssn INT,
//--     need_type VARCHAR(256),
//--     importance INT,
//--     FOREIGN KEY (ssn) REFERENCES Clients(ssn)
//        -- );
//
//        -- -- Insurance Policies Table
//
//-- CREATE TABLE InsurancePolicies (
//--     ssn INT,
//--     policy_id INT PRIMARY KEY,
//--     provider_name VARCHAR(256),
//--     provider_address VARCHAR(256),
//--     insurance_type VARCHAR(256),
//--     FOREIGN KEY (ssn) REFERENCES Clients(ssn)
//        -- );
//
//        -- -- Volunteers Table
//
//-- CREATE TABLE Volunteers (
//--     ssn INT PRIMARY KEY,
//--     person_name VARCHAR(256),
//--     gender VARCHAR(256),
//--     profession VARCHAR(256),
//--     on_mailing_list BIT,
//--     mailing_address VARCHAR(256),
//--     phone_number VARCHAR(256),
//--     email_address VARCHAR(256),
//--     date_joined DATE,
//--     training_date DATE,
//--     training_location VARCHAR(256)
//-- );
//
//        -- -- Teams Table
//
//-- CREATE TABLE Teams (
//--     team_name VARCHAR(256) PRIMARY KEY,
//--     team_type VARCHAR(256),
//--     date_formed DATE
//-- );
//
//        -- -- Employees Table
//
//-- CREATE TABLE Employees (
//--     ssn INT PRIMARY KEY,
//--     person_name VARCHAR(256),
//--     gender VARCHAR(256),
//--     profession VARCHAR(256),
//--     on_mailing_list BIT,
//--     mailing_address VARCHAR(256),
//--     phone_number VARCHAR(256),
//--     email_address VARCHAR(256),
//--     salary DECIMAL(10, 2),
//--     marital_status VARCHAR(256),
//--     hire_date DATE
//-- );
//
//        -- -- Expenses Table
//
//-- CREATE TABLE Expenses (
//--     ssn INT,
//--     expense_date DATE,
//--     amount DECIMAL(10, 2),
//--     expense_description VARCHAR(256),
//--     FOREIGN KEY (ssn) REFERENCES Employees(ssn)
//        -- );
//
//        -- -- Donors Table
//
//-- CREATE TABLE Donors (
//--     ssn INT PRIMARY KEY,
//--     person_name VARCHAR(256),
//--     gender VARCHAR(256),
//--     profession VARCHAR(256),
//--     on_mailing_list BIT,
//--     mailing_address VARCHAR(256),
//--     phone_number VARCHAR(256),
//--     email_address VARCHAR(256),
//--     is_anon BIT
//-- );
//
//        -- -- -- Donations Table
//
//-- -- CREATE TABLE Donations (
//-- --     ssn INT,
//-- --     donation_date DATE,
//-- --     amount DECIMAL(10, 2),
//-- --     donation_type VARCHAR(256),
//-- --     campaign_name VARCHAR(256),
//-- --     FOREIGN KEY (ssn) REFERENCES Donors(ssn)
//        -- -- );
//
//        -- -- DonationChecks Table
//
//-- CREATE TABLE DonationChecks (
//--     ssn INT,
//--     check_number VARCHAR(256),
//--     donation_date DATE,
//--     amount DECIMAL(10, 2),
//--     donation_type VARCHAR(256),
//--     campaign_name VARCHAR(256),
//--     FOREIGN KEY (ssn) REFERENCES Donors(ssn)
//        -- );
//
//        -- -- CreditCards Table
//
//-- CREATE TABLE CreditCards (
//--     ssn INT,
//--     card_number VARCHAR(256),
//--     card_type VARCHAR(256),
//--     expiration_date VARCHAR(256),
//--     donation_date DATE,
//--     amount DECIMAL(10, 2),
//--     donation_type VARCHAR(256),
//--     campaign_name VARCHAR(256),
//--     FOREIGN KEY (ssn) REFERENCES Donors(ssn)
//        -- );
//
//        -- -- ServesOn Table
//
//-- CREATE TABLE ServesOn (
//--     ssn INT,
//--     team_name VARCHAR(256),
//--     serving_month VARCHAR(256),
//--     served_hours INT,
//--     active BIT,
//--     FOREIGN KEY (ssn) REFERENCES Volunteers(ssn),
//        --     FOREIGN KEY (team_name) REFERENCES Teams(team_name)
//        -- );
//
//        -- -- CaresFor Table
//
//-- CREATE TABLE CaresFor (
//--     ssn INT,
//--     team_name VARCHAR(256),
//--     client_active BIT,
//--     FOREIGN KEY (ssn) REFERENCES Clients(ssn),
//        --     FOREIGN KEY (team_name) REFERENCES Teams(team_name)
//        -- );
//
//        -- -- Reports Table
//
//-- CREATE TABLE Reports (
//--     ssn INT,
//--     team_name VARCHAR(256),
//--     report_date DATE,
//--     report_description VARCHAR(512),
//--     FOREIGN KEY (ssn) REFERENCES Employees(ssn),
//        --     FOREIGN KEY (team_name) REFERENCES Teams(team_name)
//        -- );
//
//        -- -- Leads Table
//
//-- CREATE TABLE Leads (
//--     ssn INT,
//--     team_name VARCHAR(256),
//--     FOREIGN KEY (ssn) REFERENCES Volunteers(ssn),
//        --     FOREIGN KEY (team_name) REFERENCES Teams(team_name)
//        -- );
//
//        -- -- Has Table
//
//-- CREATE TABLE Has (
//--     ssn INT,
//--     policy_id INT,
//--     FOREIGN KEY (ssn) REFERENCES Clients(ssn),
//        --     FOREIGN KEY (policy_id) REFERENCES InsurancePolicies(policy_id)
//        -- );
//
//        -- -- Separate Emergency Contacts Tables for each entity
//
//-- CREATE TABLE DonorEmergencyContacts (
//--     donor_ssn INT,
//--     contact_name VARCHAR(256),
//--     contact_phone_number VARCHAR(256),
//--     relationship VARCHAR(256),
//--     PRIMARY KEY (donor_ssn, contact_name),
//--     FOREIGN KEY (donor_ssn) REFERENCES Donors(ssn)
//        -- );
//
//        -- CREATE TABLE VolunteerEmergencyContacts (
//--     volunteer_ssn INT,
//--     contact_name VARCHAR(256),
//--     contact_phone_number VARCHAR(256),
//--     relationship VARCHAR(256),
//--     PRIMARY KEY (volunteer_ssn, contact_name),
//--     FOREIGN KEY (volunteer_ssn) REFERENCES Volunteers(ssn)
//        -- );
//
//        -- CREATE TABLE EmployeeEmergencyContacts (
//--     employee_ssn INT,
//--     contact_name VARCHAR(256),
//--     contact_phone_number VARCHAR(256),
//--     relationship VARCHAR(256),
//--     PRIMARY KEY (employee_ssn, contact_name),
//--     FOREIGN KEY (employee_ssn) REFERENCES Employees(ssn)
//        -- );
//
//        -- CREATE TABLE ClientEmergencyContacts (
//--     client_ssn INT,
//--     contact_name VARCHAR(256),
//--     contact_phone_number VARCHAR(256),
//--     relationship VARCHAR(256),
//--     PRIMARY KEY (client_ssn, contact_name),
//--     FOREIGN KEY (client_ssn) REFERENCES Clients(ssn)
//        -- );


//        1. Enter a new team into the database (1/month).
//        2. Enter a new client into the database and associate him or her with one or more teams (1/week).
//        3. Enter a new volunteer into the database and associate him or her with one or more teams (2/month).
//        4. Enter the number of hours a volunteer worked this month for a particular team (30/month).
//        5. Enter a new employee into the database and associate him or her with one or more teams (1/year).
//        6. Enter an expense charged by an employee (1/day).
//        7. Enter a new donor and associate him or her with several donations (1/day).
//        8. Retrieve the name and phone number of the doctor of a particular client (1/week).
//        9. Retrieve the total amount of expenses charged by each employee for a particular period of time. The list should be sorted by the total amount of expenses (1/month).
//        10. Retrieve the list of volunteers that are members of teams that support a particular client (4/year).
//        11. Retrieve the names of all teams that were founded after a particular date (1/month).
//        12. Retrieve the names, social security numbers, contact information, and emergency contact information of all people in the database (1/week).
//        13. Retrieve the name and total amount donated by donors that are also employees. The list should be sorted by the total amount of the donations, and indicate if each donor wishes to remain anonymous (1/week)
//        14. Increase the salary by 10% of all employees to whom more than one team must report. (1/year)
//        15. Delete all clients who do not have health insurance and whose value of importance for transportation is less than 5 (4/year).


import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

public class Main {
    private static final Logger log; // Logger for the application

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n"); // Set log format
        log = Logger.getLogger(Main.class.getName()); // Initialize the logger
    }

    public static void main(String[] args) throws Exception {
        log.info("Starting the application"); // Log application start
        log.info("Loading application properties"); // Log loading properties

        Properties properties = new Properties(); // Load database properties
        properties.load(Main.class.getClassLoader().getResourceAsStream("application.properties")); // Load properties file

        log.info("Connecting to the database"); // Log database connection attempt
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties); // Establish connection
        log.info("Database connection test: " + connection.getCatalog()); // Test the connection

        Scanner keyboard = new Scanner(System.in); // Initialize scanner for user input
        int query = 0; // Initialize query input variable
        System.out.print("WELCOME TO THE PATIENT ASSISTANT NETWORK DATABASE SYSTEM\n");

        while (query != 18) { // Loop until user selects option 18 (quit)
            System.out.println("""
                    Choose an option:\s
                    1: Insert Team\s
                    2: Insert Client\s
                    3: Insert Volunteer\s
                    4: Insert number of hours volunteer worked this month\s
                    5: Insert Employee\s
                    6: Insert Expense\s
                    7: Insert Donor\s
                    8: Retrieve Doctor information\s
                    9: Retrieve Employee charges for particular time period\s
                    10: Retrieve Volunteers that are members of teams that support particular client\s
                    11: Retrieve names of teams founded after particular dates\s
                    12: Retrieve information of all people in database\s
                    13: Retrieve name and amount donated by donors that are also employees\s
                    14: Increase salary of employees with more than one team\s
                    15: Delete Clients who don't have health insurance and value of importance for transportation is less than 5\s
                    16: Import new team from data file\s
                    17: Export names and mailing address of people on mailing list\s
                    18: Quit\s"""); // Prompt user
            query = keyboard.nextInt(); // Get user input

            switch (query) { // Handle different query options
                case 1:
                    executeInsertTeamProcedure(connection, keyboard);
                    break;
                case 2:
                    executeInsertClientProcedure(connection, keyboard);
                    break;
                case 3:
                    executeInsertVolunteerProcedure(connection, keyboard);
                    break;
                case 4:
                    executeInsertVolunteerHoursProcedure(connection, keyboard);
                    break;
                case 5:
                    executeInsertEmployeeProcedure(connection, keyboard);
                    break;
                case 6:
                    executeInsertExpenseProcedure(connection, keyboard);
                    break;
                case 7:
                    executeInsertDonorProcedure(connection, keyboard);
                    break;
                case 8:
                    executeRetrieveDoctorInfoProcedure(connection, keyboard);
                    break;
                case 9:
                    executeRetrieveTotalExpensesProcedure(connection, keyboard);
                    break;
                case 10:
                    executeGetVolunteersForClientProcedure(connection, keyboard);
                    break;
                case 11:
                    executeGetTeamsFoundedAfterDateProcedure(connection, keyboard);
                    break;
                case 12:
                    executeGetPeopleInfoProcedure(connection, keyboard);
                    break;
                case 13:
                    executeGetDonorsThatAreEmployeesProcedure(connection, keyboard);
                    break;
                case 14:
                    executeIncreaseSalaryForEmployeesProcedure(connection, keyboard);
                    break;
                case 15:
                    executeDeleteClientsProcedure(connection);
                    break;
                case 16:
                    executeImportTeamProcedure(connection, keyboard);
                    break;
                case 17:
                    executeExportMailingListProcedure(connection, keyboard);
                    break;
                case 18:
                    System.out.println("Terminating program."); // Exit program
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option (1-18)."); // Handle invalid input
            }
        }

        connection.close(); // Close database connection
    }

    // Execute Query 1 - Insert Team
    private static void executeInsertTeamProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter Team Name: "); // Prompt for team name
        String teamName = keyboard.next(); // Get team name

        System.out.println("Enter Team Type: "); // Prompt for team type
        String teamType = keyboard.next(); // Get team type

        System.out.println("Enter date formed (yyyy-MM-dd): "); // Prompt for team formed date
        String dateFormed = keyboard.next();

        // handle date format
        try {
            // Define the date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Parse the input into a java.util.Date
            java.util.Date utilDate = dateFormat.parse(dateFormed);
            // Convert to java.sql.Date for database storage
            Date sqlDate = new Date(utilDate.getTime());

        } catch (ParseException e) {
            System.err.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        }
        String sql = "{call insertTeam(?, ?, ?)}"; // Prepare the SQL call for the procedure
        CallableStatement stmt = connection.prepareCall(sql); // Create CallableStatement

        stmt.setString(1, teamName); // Set team name parameter
        stmt.setString(2, teamType); // Set team type parameter
        stmt.setString(3, dateFormed); // Set date formed parameter

        stmt.execute(); // Execute the stored procedure
        System.out.println("Team inserted successfully."); // Confirm success
        stmt.close(); // Close the statement
    }

    // Execute Query 2 - Insert Client
    private static void executeInsertClientProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter client ssn:");
        int clientSSN = keyboard.nextInt();
        keyboard.nextLine(); // need nextLine when reading Int

        System.out.println("Enter client name: ");
        String clientName = keyboard.nextLine();

        System.out.println("Enter client gender:");
        String clientGender = keyboard.nextLine();

        System.out.println("Enter client profession:");
        String clientProfession = keyboard.nextLine();

        System.out.println("Enter client mailing list status (1 for yes, 0 for no):");
        int clientMailingListStatus = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Enter client mailing address:");
        String clientMailingAddress = keyboard.nextLine();

        System.out.println("Enter client phone number: ");
        String clientPhoneNumber = keyboard.nextLine();

        System.out.println("Enter client email: ");
        String clientEmail = keyboard.nextLine();

        System.out.println("Enter client assignment date (yyyy-MM-dd): ");
        String clientAssignmentDate = keyboard.nextLine();
        if (!isValidDate(clientAssignmentDate)) {
            System.err.println("Invalid date format for assignment date. Please enter the date in yyyy-MM-dd format.");
            return;
        }

        System.out.println("Enter doctor name of client:");
        String doctorName = keyboard.nextLine();

        System.out.println("Enter client doctor's phone number:");
        String doctorPhoneNumber = keyboard.nextLine();

        // Collect multiple team names
        List<String> teamNames = new ArrayList<>();
        System.out.println("How many teams care for the client? ");
        int teams = keyboard.nextInt();
        keyboard.nextLine();
        for (int i = 0; i < teams; i++) {
            System.out.println("Enter team " + (i+1) + " name:");
            teamNames.add(keyboard.nextLine());
        }

        System.out.println("Enter client emergency contact person: ");
        String clientContactPerson = keyboard.nextLine();

        System.out.println("Enter client emergency contact person's phone number: ");
        String clientContactPersonPhoneNumber = keyboard.nextLine();

        System.out.println("Enter client emergency contact person's relationship: ");
        String clientContactPersonRelationship = keyboard.nextLine();

        System.out.println("Enter client's need type: ");
        String clientNeedType = keyboard.nextLine();

        System.out.println("Enter client's need importance (1-10): ");
        int clientNeedImportance = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Enter client's insurance policy id: ");
        String clientInsurancePolicyId = keyboard.nextLine();

        System.out.println("Enter client's insurance provider: ");
        String clientInsuranceProvider = keyboard.nextLine();

        System.out.println("Enter client's insurance provider's address: ");
        String clientInsuranceProviderAddress = keyboard.nextLine();

        System.out.println("Enter client's insurance type: ");
        String clientInsuranceType = keyboard.nextLine();

        // Execute main client insertion procedure
        String sql = "{call insertClient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, clientSSN);
        callableStatement.setString(2, clientName);
        callableStatement.setString(3, clientGender);
        callableStatement.setString(4, clientProfession);
        callableStatement.setInt(5, clientMailingListStatus);
        callableStatement.setString(6, clientMailingAddress);
        callableStatement.setString(7, clientEmail);
        callableStatement.setString(8, clientPhoneNumber);
        callableStatement.setString(9, clientAssignmentDate);
        callableStatement.setString(10, doctorName);
        callableStatement.setString(11, doctorPhoneNumber);
//        callableStatement.setString(12, teamNames.isEmpty() ? null : teamNames.getFirst()); // Pass first team name if exists
        callableStatement.setString(12, clientContactPerson);
        callableStatement.setString(13, clientContactPersonPhoneNumber);
        callableStatement.setString(14, clientContactPersonRelationship);
        callableStatement.setString(15, clientNeedType);
        callableStatement.setInt(16, clientNeedImportance);
        callableStatement.setString(17, clientInsurancePolicyId);
        callableStatement.setString(18, clientInsuranceProvider);
        callableStatement.setString(19, clientInsuranceProviderAddress);
        callableStatement.setString(20, clientInsuranceType);

        callableStatement.execute();
        System.out.println("Client inserted successfully.");
        callableStatement.close();

        // Insert additional team names into CaresFor table
        String caresForSql = "INSERT INTO CaresFor (ssn, team_name, client_active) VALUES (?, ?, ?)";
        for (int i = 0; i < teamNames.size(); i++) { // Start from 1 since the first team was already added
            try (CallableStatement caresForStmt = connection.prepareCall(caresForSql)) {
                caresForStmt.setInt(1, clientSSN);
                caresForStmt.setString(2, teamNames.get(i));
                // Get client active status
                System.out.println("Is client active for team " + teamNames.get(i) + "? (1 for yes, 0 for no):");
                int active = keyboard.nextInt();
                keyboard.nextLine();
                caresForStmt.setInt(3, active);
                caresForStmt.execute();
            }
        }

        String hasSql = "INSERT INTO Has (ssn, policy_id) VALUES (?, ?)";
        try (CallableStatement hasStmt = connection.prepareCall(hasSql)) {
            hasStmt.setInt(1, clientSSN);
            hasStmt.setString(2, clientInsurancePolicyId);
            hasStmt.execute();
        }


    }

    // query 3
    private static void executeInsertVolunteerProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter volunteer ssn:");
        int volunteerSSN = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline left-over

        System.out.println("Enter volunteer name: ");
        String volunteerName = keyboard.nextLine();

        System.out.println("Enter volunteer gender:");
        String volunteerGender = keyboard.nextLine();

        System.out.println("Enter volunteer profession:");
        String volunteerProfession = keyboard.nextLine();

        System.out.println("Enter volunteer mailing list status (1 for yes, 0 for no):");
        int volunteerMailingListStatus = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline left-over

        System.out.println("Enter volunteer mailing address:");
        String volunteerMailingAddress = keyboard.nextLine();

        System.out.println("Enter volunteer phone number:");
        String volunteerPhoneNumber = keyboard.nextLine();

        System.out.println("Enter volunteer email:");
        String volunteerEmail = keyboard.nextLine();

        System.out.println("Enter volunteer date joined (yyyy-MM-dd):");
        String volunteerDateJoined = keyboard.nextLine();
        if (!isValidDate(volunteerDateJoined)) {
            System.err.println("Invalid date format for date joined. Please enter the date in yyyy-MM-dd format.");
            return;
        }

        System.out.println("Enter volunteer training date (yyyy-MM-dd):");
        String volunteerTrainingDate = keyboard.nextLine();
        if (!isValidDate(volunteerTrainingDate)) {
            System.err.println("Invalid date format for training date. Please enter the date in yyyy-MM-dd format.");
            return;
        }

        System.out.println("Enter volunteer training location:");
        String volunteerTrainingLocation = keyboard.nextLine();

        // Collect team names and determine leadership status
        List<String> teamNames = new ArrayList<>();
//        List<Boolean> isLeader = new ArrayList<>();

        System.out.println("How many teams does the volunteer serve on?");
        int teams = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline left-over
        for (int i = 0; i < teams; i++) {
            System.out.println("Enter team name:");
            teamNames.add(keyboard.nextLine());

//            System.out.println("Is this volunteer the team leader for " + teamNames.get(i) + "? (1 for yes, 0 for no):");
//            isLeader.add(keyboard.nextInt() == 1);
//            keyboard.nextLine(); // Consume newline left-over
        }

        System.out.println("Enter volunteer emergency contact name:");
        String volunteerContactName = keyboard.nextLine();

        System.out.println("Enter volunteer emergency contact phone number:");
        String volunteerContactPhoneNumber = keyboard.nextLine();

        System.out.println("Enter volunteer emergency contact relationship:");
        String volunteerContactRelationship = keyboard.nextLine();

        // Prepare the main volunteer insertion procedure with 15 parameters
        String sql = "{call insertVolunteer(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; // 15 placeholders
        CallableStatement callableStatement = connection.prepareCall(sql);

        // Set parameters for `insertVolunteer`
        callableStatement.setInt(1, volunteerSSN); // ssn (INT)
        callableStatement.setString(2, volunteerName); // person_name (VARCHAR)
        callableStatement.setString(3, volunteerGender); // gender (VARCHAR)
        callableStatement.setString(4, volunteerProfession); // profession (VARCHAR)
        callableStatement.setInt(5, volunteerMailingListStatus); // on_mailing_list (BIT/INT)
        callableStatement.setString(6, volunteerMailingAddress); // mailing_address (VARCHAR)
        callableStatement.setString(7, volunteerEmail); // email_address (VARCHAR)
        callableStatement.setString(8, volunteerPhoneNumber); // phone_number (VARCHAR)
        callableStatement.setString(9, volunteerDateJoined); // date_joined (DATE as String)
        callableStatement.setString(10, volunteerTrainingDate); // training_date (DATE as String)
        callableStatement.setString(11, volunteerTrainingLocation); // training_location (VARCHAR)
//        callableStatement.setString(12, teamNames.isEmpty() ? null : teamNames.getFirst()); // Initial team name (VARCHAR)

        // Emergency Contact Information
        callableStatement.setString(12, volunteerContactName); // emergency_contact_name (VARCHAR)
        callableStatement.setString(13, volunteerContactPhoneNumber); // contact_phone_number (VARCHAR)
        callableStatement.setString(14, volunteerContactRelationship); // relationship (VARCHAR)


//        // ServesOn Information
//        callableStatement.setString(16, servingMonth); // serving_month (VARCHAR)
//        callableStatement.setInt(17, servedHours); // served_hours (INT)
//        callableStatement.setInt(18, active); // active (BIT/INT)

        callableStatement.execute();
        System.out.println("Volunteer inserted successfully.");
        callableStatement.close();


        // Insert additional team names into ServesOn table
        String servesOnSql = "INSERT INTO ServesOn (ssn, team_name, serving_month, served_hours, active) VALUES (?, ?, ?, ?, ?)";
        for (int i = 0; i < teamNames.size(); i++) {
            try (CallableStatement servesOnStmt = connection.prepareCall(servesOnSql)) {
                servesOnStmt.setInt(1, volunteerSSN);
                servesOnStmt.setString(2, teamNames.get(i));

                System.out.println("Enter the serving month for volunteer on team " + teamNames.get(i) + ":");
                String servingMonth = keyboard.nextLine();
                servesOnStmt.setString(3, servingMonth);

                System.out.println("Enter the number of hours served by volunteer on team " + teamNames.get(i) + ":");
                int servedHours = keyboard.nextInt();
                keyboard.nextLine(); // Consume newline left-over
                servesOnStmt.setInt(4, servedHours); // Initialize served hours to 0

                System.out.println("Is the volunteer active for team " + teamNames.get(i) + "? (1 for yes, 0 for no):");
                int active = keyboard.nextInt();
                keyboard.nextLine(); // Consume newline left-over
                servesOnStmt.setInt(5, active); // Initialize active status to 1
                servesOnStmt.execute();
            }
        }


        // Insert additional team leadership information if applicable
        String leadsSql = "INSERT INTO Leads (ssn, team_name, is_leader) VALUES (?, ?, ?)";
        for (int i = 0; i < teamNames.size(); i++) {
            try (CallableStatement leadsStmt = connection.prepareCall(leadsSql)) {
                leadsStmt.setInt(1, volunteerSSN);
                leadsStmt.setString(2, teamNames.get(i));

                System.out.println("Is the volunteer the team leader for: '" + teamNames.get(i) + "'? (1 for yes, 0 " +
                        "for no):");
                int isLeader = keyboard.nextInt();
                keyboard.nextLine(); // Consume newline left-over
                leadsStmt.setInt(3, isLeader);
                leadsStmt.execute();
                System.out.println("Assigned volunteer as team leader for: '" + teamNames.get(i) + "'.");
            }
        }
    }

    // query 4
    private static void executeInsertVolunteerHoursProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter volunteer ssn:");
        int volunteerSSN = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline left-over

        System.out.println("Enter team name:");
        String teamName = keyboard.nextLine();

        System.out.println("Enter serving month (e.g., January):");
        String servingMonth = keyboard.nextLine();

        System.out.println("Enter hours worked:");
        int hoursWorked = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline left-over

        System.out.println("Is the volunteer currently active? (1 for yes, 0 for no):");
        int isActive = keyboard.nextInt();
        keyboard.nextLine();

        // Execute procedure to insert or update hours worked for the volunteer
        String sql = "{call insertVolunteerHours(?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, volunteerSSN);
        callableStatement.setString(2, teamName);
        callableStatement.setString(3, servingMonth);
        callableStatement.setInt(4, hoursWorked);
        callableStatement.setInt(5, isActive);

        callableStatement.execute();
        System.out.println("Volunteer hours updated successfully.");
        callableStatement.close();
    }

    // query 5
    private static void executeInsertEmployeeProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter Employee ssn: "); // Prompt for person ssn
        int ssn = keyboard.nextInt(); // Get ssn
        keyboard.nextLine(); // Consume newline left-over

        System.out.println("Enter Employee name: "); // Prompt for person name
        String personName = keyboard.nextLine(); // Get person name

        System.out.println("Enter the employee gender: ");
        String gender = keyboard.nextLine();

        System.out.println("Enter the employee profession: ");
        String profession = keyboard.nextLine();

        System.out.println("Enter the employee mailing list status (1 for yes, 0 for no): ");
        int onMailingList = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Enter the employee mailing address: ");
        String mailingAddress = keyboard.nextLine();

        System.out.println("Enter the employee phone number: ");
        String phoneNumber = keyboard.nextLine();

        System.out.println("Enter the employee email address: ");
        String emailAddress = keyboard.nextLine();

        System.out.println("Enter the employee salary: ");
        BigDecimal salary = keyboard.nextBigDecimal();
        keyboard.nextLine();

        System.out.println("Enter the employee marital status: ");
        String maritalStatus = keyboard.nextLine();

        System.out.println("Enter the employee hire date (yyyy-MM-dd): ");
        String hireDate = keyboard.nextLine();

        // Collect multiple team names
        List<String> teamNames = new ArrayList<>();
        System.out.println("How many teams does the employee belong to?");
        int teams = keyboard.nextInt();
        keyboard.nextLine();
        for (int i = 0; i < teams; i++) {
            System.out.println("Enter team " + (i + 1) + " name:");
            teamNames.add(keyboard.nextLine());
        }

        System.out.println("Enter employee emergency contact name: ");
        String emergencyContactName = keyboard.nextLine();

        System.out.println("Enter employee emergency contact phone number: ");
        String emergencyContactPhoneNumber = keyboard.nextLine();

        System.out.println("Enter employee emergency contact relationship: ");
        String emergencyContactRelationship = keyboard.nextLine();

        // Prepare the SQL call for the procedure
        String sql = "{call insertEmployee(?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?)}";
        CallableStatement stmt = connection.prepareCall(sql);

        // Set parameters for the stored procedure
        stmt.setInt(1, ssn); // Set the ssn parameter
        stmt.setString(2, personName); // Set the name parameter
        stmt.setString(3, gender); // Set the
        stmt.setString(4, profession); // Set the profession parameter
        stmt.setInt(5, onMailingList); // Set the mailing list status parameter
        stmt.setString(6, mailingAddress); // Set the mailing address parameter
        stmt.setString(7, phoneNumber); // Set the phone number parameter
        stmt.setString(8, emailAddress); // Set the email address parameter
        stmt.setBigDecimal(9, salary); // Set the salary parameter
        stmt.setString(10, maritalStatus); // Set the marital status parameter
        stmt.setString(11, hireDate); // Set the hire date parameter
        stmt.setString(12, emergencyContactName); // Set the emergency contact name parameter
        stmt.setString(13, emergencyContactPhoneNumber); // Set the emergency contact phone number parameter
        stmt.setString(14, emergencyContactRelationship); // Set the emergency contact relationship parameter
        stmt.setString(15, teamNames.isEmpty() ? null : teamNames.get(0)); // Set the team name parameter


        stmt.execute(); // Execute the stored procedure
        System.out.println("Employee inserted successfully."); // Confirm success

        // Insert additional team names into Reports table
        String reportsSql = "INSERT INTO Reports (ssn, team_name, report_date, report_description) VALUES (?, ?, ?, ?)";
        for (String teamName : teamNames) {
            try (CallableStatement reportsStmt = connection.prepareCall(reportsSql)) {
                reportsStmt.setInt(1, ssn);
                reportsStmt.setString(2, teamName);

                System.out.println("Enter the report date (yyyy-MM-dd): " + teamName);
                String reportDate = keyboard.nextLine();
                reportsStmt.setString(3, reportDate);

                System.out.println("Enter the report description: " + teamName);
                String reportDescription = keyboard.nextLine();
                reportsStmt.setString(4, reportDescription);
                reportsStmt.execute();
            }
        }
    }

    // Execute Query 6
    private static void executeInsertExpenseProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter Employee ssn: "); // Prompt for person ssn
        int ssn = keyboard.nextInt(); // Get ssn

        System.out.println("Enter Expense Date (yyyy-MM-dd): ");
        String expenseDate = keyboard.next();

        Date sqlDate = null;
        BigDecimal expense = null;

        // Handle date parsing
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(expenseDate);
            sqlDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            System.err.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            return; // Exit the method if date parsing fails
        }

        System.out.println("Enter Expense Amount: ");
        String decimalExpenseInput = keyboard.next();

        // Handle decimal parsing
        try {
            expense = new BigDecimal(decimalExpenseInput);
        } catch (NumberFormatException e) {
            System.err.println("Invalid decimal format. Please enter a valid decimal number.");
            e.printStackTrace();
            return; // Exit the method if decimal parsing fails
        }

        System.out.println("Enter Expense Description: ");
        String expenseDescription = keyboard.next();

        // Prepare the SQL call for the procedure
        String sql = "{call insertExpense(?, ?, ?, ?)}";
        CallableStatement stmt = connection.prepareCall(sql);


        // Set parameters for the stored procedure
        stmt.setInt(1, ssn); // Set the date parameter
        stmt.setString(2, expenseDate);
        stmt.setBigDecimal(3, expense); // Set the decimal parameter
        stmt.setString(4, expenseDescription); // Set the description parameter

        // Execute the stored procedure
        stmt.execute();
        System.out.println("Expense inserted successfully.");

        stmt.close();
    }

    // Execute Query 7
    private static void executeInsertDonorProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter Donor ssn:");
        int donorSSN = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline left-over

        System.out.println("Enter Donor name: ");
        String donorName = keyboard.nextLine();

        System.out.println("Enter the gender of the donor:");
        String donorGender = keyboard.nextLine();

        System.out.println("Enter the profession of the donor:");
        String donorProfession = keyboard.nextLine();

        System.out.println("Is the donor on the mailing list? (1 for yes, 0 for no):");
        int donorMailingListStatus = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline left-over

        System.out.println("Enter the donor mailing address:");
        String donorMailingAddress = keyboard.nextLine();

        System.out.println("Enter the donor phone number:");
        String donorPhoneNumber = keyboard.nextLine();

        System.out.println("Enter the donor email:");
        String donorEmail = keyboard.nextLine();

        System.out.println("Is the donor anonymous? (1 for yes, 0 for no):");
        int isAnonymous = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline left-over

        System.out.println("Enter the donor emergency contact person: ");
        String donorContactPerson = keyboard.nextLine();

        System.out.println("Enter the donor emergency contact person's phone number: ");
        String donorContactPersonPhoneNumber = keyboard.nextLine();

        System.out.println("Enter the donor emergency contact person's relationship: ");
        String donorContactPersonRelationship = keyboard.nextLine();

        // Ask for number of donations
        System.out.println("How many donations does the donor wish to make?");
        int donations = keyboard.nextInt();
        keyboard.nextLine();

        // Prepare the SQL call for the procedure
        String sql = "{call insertDonor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = connection.prepareCall(sql);

        // Set parameters for the stored procedure
        stmt.setInt(1, donorSSN); // Set the ssn parameter
        stmt.setString(2, donorName); // Set the name parameter
        stmt.setString(3, donorGender); // Set the
        stmt.setString(4, donorProfession); // Set the profession parameter
        stmt.setInt(5, donorMailingListStatus); // Set the mailing list status parameter
        stmt.setString(6, donorMailingAddress); // Set the mailing address parameter
        stmt.setString(7, donorEmail); // Set the email address parameter
        stmt.setString(8, donorPhoneNumber); // Set the phone number parameter
        stmt.setInt(9, isAnonymous); // Set the anonymous status parameter
        stmt.setString(10, donorContactPerson); // Set the emergency contact name parameter
        stmt.setString(11, donorContactPersonPhoneNumber); // Set the emergency contact phone number parameter
        stmt.setString(12, donorContactPersonRelationship); // Set the emergency contact relationship parameter

        stmt.execute(); // Execute the
        System.out.println("Donor inserted successfully."); // Confirm success

        // Insert additional donation information based on credit card or check
        for (int i = 0; i < donations; i++) {
            System.out.println("Enter donation date (yyyy-MM-dd): ");
            String donationDate = keyboard.nextLine();

            System.out.println("Enter donation amount: ");
            BigDecimal donationAmount = keyboard.nextBigDecimal();
            keyboard.nextLine();

            System.out.println("Enter donation type (credit card or check): ");
            String donationType = keyboard.nextLine();

            System.out.println("Enter campaign name: ");
            String campaignName = keyboard.nextLine();

            if (donationType.equalsIgnoreCase("credit card")) {
                System.out.println("Enter card number: ");
                String cardNumber = keyboard.nextLine();

                System.out.println("Enter card type: ");
                String cardType = keyboard.nextLine();

                System.out.println("Enter expiration date (yyyy-MM-dd): ");
                String expirationDate = keyboard.nextLine();

                // Prepare the SQL call for the procedure
                String creditCardSql = "INSERT INTO CreditCards (ssn, card_number, card_type, expiration_date, donation_date, amount, donation_type, campaign_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (CallableStatement creditCardStmt = connection.prepareCall(creditCardSql)) {
                    creditCardStmt.setInt(1, donorSSN);
                    creditCardStmt.setString(2, cardNumber);
                    creditCardStmt.setString(3, cardType);
                    creditCardStmt.setString(4, expirationDate);
                    creditCardStmt.setString(5, donationDate);
                    creditCardStmt.setBigDecimal(6, donationAmount);
                    creditCardStmt.setString(7, donationType);
                    creditCardStmt.setString(8, campaignName);
                    creditCardStmt.execute();
                }

            } else if (donationType.equalsIgnoreCase("check")) {
                System.out.println("Enter check number: ");
                String checkNumber = keyboard.nextLine();

                // Prepare the SQL call for the procedure
                String checkSql = "INSERT INTO DonationChecks (ssn, check_number, donation_date, amount, donation_type, campaign_name) VALUES (?, ?, ?, ?, ?, ?)";
                try (CallableStatement checkStmt = connection.prepareCall(checkSql)) {
                    checkStmt.setInt(1, donorSSN);
                    checkStmt.setString(2, checkNumber);
                    checkStmt.setString(3, donationDate);
                    checkStmt.setBigDecimal(4, donationAmount);
                    checkStmt.setString(5, donationType);
                    checkStmt.setString(6, campaignName);
                    checkStmt.execute();
                }

            } else {
                System.out.println("Invalid donation type. Please enter either 'credit card' or 'check'.");
            }
        }

    }

    // Query 8
    private static void executeRetrieveDoctorInfoProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter the SSN of the client:");
        int ssn = keyboard.nextInt();
        String sql = "{call retrieveDoctorInfo(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, ssn);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Doctor name: " + rs.getString("doctor_name"));
                    System.out.println("Doctor phone number: " + rs.getString("doctor_phone_number"));
                }
            }
        }
    }

    // Query 9 call:
    private static void executeRetrieveTotalExpensesProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter the start date (YYYY-MM-DD):");
        String startDate = keyboard.next();
        System.out.println("Enter the end date (YYYY-MM-DD):");
        String endDate = keyboard.next();
        String sql = "{call retrieveTotalExpenses(?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("SSN: " + rs.getInt("ssn"));
                    System.out.println("Total expenses: " + rs.getDouble("total_expenses"));
                }
            }
        }
    }

    // query 10
    private static void executeGetVolunteersForClientProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter the SSN of the client:");
        int clientSSN = keyboard.nextInt();
        String sql = "{call GetVolunteersForClient(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, clientSSN);

            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasResults = false;
                System.out.println("Volunteers supporting client with SSN " + clientSSN + ":");

                while (rs.next()) {
                    hasResults = true;
                    String volunteerName = rs.getString("person_name");
                    System.out.println("- " + volunteerName);
                }

                if (!hasResults) {
                    System.out.println("No volunteers found for this client.");
                }
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving volunteer information: " + e.getMessage());
            throw e;  // Rethrow exception if further handling is needed
        }
    }

    // Helper method to validate date format
    private static boolean isValidDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Strict date parsing
        try {
            dateFormat.parse(dateString); // Attempt to parse the date
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // query 11
    private static void executeGetTeamsFoundedAfterDateProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter the date to retrieve teams founded after (YYYY-MM-DD):");
        String dateFormed = keyboard.next();
        String sql = "{call getTeamsAfterDate(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dateFormed);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Team name: " + rs.getString("team_name"));
                }
            }
        }
    }

    // query 12
    private static void executeGetPeopleInfoProcedure(Connection connection, Scanner keyboard) throws SQLException {
        String sql = "{call getAllPeopleInfo()}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Name: " + rs.getString("person_name"));
                    System.out.println("SSN: " + rs.getInt("ssn"));
                    System.out.println("Mailing Address: " + rs.getString("mailing_address"));
                    System.out.println("Phone Number: " + rs.getString("phone_number"));
                    System.out.println("Email Address: " + rs.getString("email_address"));
                    System.out.println("Emergency Contact Name: " + rs.getString("emergency_contact_name"));
                    System.out.println("Emergency Contact Phone: " + rs.getString("emergency_contact_phone"));
                    System.out.println("Emergency Contact Relationship: " + rs.getString("emergency_contact_relationship"));
                }
            }
        }
    }

    // query 13
    private static void executeGetDonorsThatAreEmployeesProcedure(Connection connection, Scanner keyboard) throws SQLException {
        String sql = "{call getEmployeeDonors()}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Name: " + rs.getString("person_name"));
                    System.out.println("Total Donations: " + rs.getDouble("total_donations"));
                }
            }
        }
    }

    // query 14
    private static void executeIncreaseSalaryForEmployeesProcedure(Connection connection, Scanner keyboard) throws SQLException {
        String sql = "{call increaseSalaryForMultiTeamEmployees()}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.execute();
        }
    }

    // query 15
    private static void executeDeleteClientsProcedure(Connection connection) throws SQLException {
        String sql = "{call deleteClientsWithoutInsurance()}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.execute();
            System.out.println("Successfully deleted uninsured clients with low transportation importance.");
        } catch (SQLException e) {
            System.err.println("Error executing delete procedure: " + e.getMessage());
            throw e;
        }
    }
    // query 16
    private static void executeImportTeamProcedure(Connection connection, Scanner keyboard) throws SQLException {
        System.out.println("Enter the file path to import the team data from:");
        String filePath = keyboard.next();
        String sql = "{call importTeam(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, filePath);
            stmt.execute();
        }
    }

    // query 17
    private static void executeExportMailingListProcedure(Connection connection, Scanner keyboard) throws SQLException {
        String sql = "{call exportMailingList()}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Name: " + rs.getString("person_name"));
                    System.out.println("Mailing Address: " + rs.getString("mailing_address"));
                }
            }
        }
    }

}