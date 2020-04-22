# MyCharge
MyCharge is an interface built using Spring-boot that manages User Sign Up / Sign In, Wallet Balance and List of All Transactions for each user and you can opt for Recharge of any Plan using Wallet Balance. 

# Start project from Begin
Go to https://start.spring.io/ and Generate.
Extract the zip file and import it into Eclipse.

# Setup
Your installed jdk should be atleast Java8 Version.
Set database connection on eclipse.

# Dependency
Go to pom.xml and add all required dependencies.

# Modify these according to your database credential
Go to application.properties file

# MYSQL settings
spring.datasource.url=jdbc:mysql://localhost:3306/spring_project
spring.datasource.username=root
spring.datasource.password=abcdef

# Oracle settings
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=system
spring.datasource.password=oracle

# JPA settings
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=create

# Main Logic

Sign Up / Sign In

After SignIn you have the following options:-
1.) Show User details with all previous transactions
2.) Edit user name
3.) Change password
4.) Add wallet balance
5.) Rechage a plan
6.) Log Out

# Logging using Log4j with Spring AOP concept
I have provided logging logic in LoggingExpect.java class i.e followed the Spring AOP concept for all the methods of DAO and Service class after throwing exception on error level.

# Unit Testing using JUnit5
I have tested each and every methods of Service class without the help of DAO class real object i.e mocked the DAO clas object and injected it into Service class object and tested the logic of Service class methods.


