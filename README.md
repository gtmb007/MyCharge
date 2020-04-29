# MyCharge
MyCharge is an interface built using Spring-boot that manages User Sign Up / Sign In, Wallet Balance and List of All Transactions for each user and you can opt for Recharge of any Plan using Wallet Balance. 

# Start the project from Scratch
Go to https://start.spring.io/  generate, extract the downloaded zip file and import it into Eclipse. <br/>

Your installed JDK should be at least Java8 Version and establish database connection on eclipse. <br/>

Go to pom.xml and add all required dependencies.

# Modify these according to your database credential
Go to application.properties file
###### MYSQL settings
spring.datasource.url=jdbc:mysql://localhost:3306/spring_project <br/>
spring.datasource.username=root <br/>
spring.datasource.password=abcdef
###### Oracle settings
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe <br/>
spring.datasource.username=system <br/>
spring.datasource.password=oracle
###### JPA settings
spring.jpa.show-sql=true <br/>
spring.jpa.properties.hibernate.format_sql=true <br/>
spring.jpa.hibernate.ddl-auto=create <br/>

# Main Logic
Sign Up / Sign In <br/>
After sign in you have the following options:- <br/>
1.) Show User details with all previous transactions <br/>
2.) Edit user name <br/>
3.) Change password <br/>
4.) Add wallet balance <br/>
5.) Recharge a plan <br/>
6.) Log Out

# Logging using Log4j
I have provided logging logic in LoggingExpect.java class i.e followed the Spring AOP concept for all the methods of DAO and Service class after throwing an exception on error level.

# Unit Testing using JUnit5
I have tested each and every method of Service class without the help of DAO class real object i.e mocked the DAO class object and injected it into Service class object and tested the logic of Service class methods.


