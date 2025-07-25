package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class DBConnectionTest {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @BeforeClass
    public void connectionJDBCSetup() throws SQLException {
        // Connect to H2 in-memory DB
        connection = DriverManager.getConnection("jdbc:h2:mem:testDB", "sa", "");
        statement = connection.createStatement();

        // Create table
        statement.execute("CREATE TABLE employees(id INT PRIMARY KEY,name VARCHAR(255),department VARCHAR(100))");

        // Insert sample data into DB
        statement.execute("Insert into employees values(1,'Alice','Dev')");
        statement.execute("Insert into employees values(2,'Ram','QA')");
        statement.execute("insert into employees values(3,'rishi','Admin')");

    }

    @Test(enabled = false)
    public void validateDBData() throws SQLException {
        // Simulated UI value for employee with id = 2, assume we got from UI using getText();
        String uiName="Ram";
        int id=2;

        // Query DB
        String query="Select name from employees where id="+id;
        resultSet=statement.executeQuery(query);
        String dbName="";
        if(resultSet.next())
        {
            dbName=resultSet.getString("name");
        }
        Assert.assertEquals(dbName, uiName,"Name in the ui "+uiName+" does not match the dbname "+dbName);

    }

    @AfterClass
    public void teardown() throws SQLException {
        if(resultSet!=null)
        {
            resultSet.close();
        }
        if(statement!=null)
        {
            statement.close();
        }
        if(connection!=null)
        {
            connection.close();
        }
    }
}
