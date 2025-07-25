package com.automation.tests;

import org.testng.annotations.Test;

import java.sql.*;

public class DBRollBackTest {

        private Connection connection;
        private Statement stmt;

        @Test(enabled = false)
        public void rollBackTest() throws SQLException {
            connection= DriverManager.getConnection("jdbc:h2:mem:testDB","sa","");
            connection.setAutoCommit(false);

            stmt=connection.createStatement();
            try {
                stmt.execute("CREATE TABLE USERS(id INT PRIMARY KEY,name VARCHAR(255))");
                stmt.executeUpdate("Insert into users values(101,'remya')");
                stmt.executeUpdate("Insert into users values(102,'ram')");

                if (true) {
                    throw new RuntimeException("simulating a failure, go to catch");
                }

                connection.commit();
            }
            catch (Exception e)
            {
                if(connection!=null)
                {
                    connection.rollback();
                }
            }
            finally {
                if(connection!=null)
                {
                    ResultSet rs=stmt.executeQuery("Select * from users");
                    if(!rs.next())
                    {
                        System.out.println("No data found.DB rolled back successfully");
                    }
                    else
                    {
                        System.out.println(" Inserted data found.DB didnt roll back");
                    }
                }
                if(stmt!=null)stmt.close();
                if(connection!=null)connection.close();
            }


        }



}
