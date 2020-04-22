# MyCharge
MyCharge is an interface built using Spring-boot that manages User Sign Up / Sign In, Wallet Balance and List of All Transactions for each user and you can opt for Recharge of any Plan using Wallet Balance. 

#Start project from Begin
Go to https://start.spring.io/ and Generate.
Extract the zip file and import it into Eclipse.

#Setup
Your installed jdk should be atleast Java8 Version.
Set database connection on eclipse.

#Dependency
Go to pom.xml and add all dependencies required.

#Modify these according to your database credential
Go to application.properties file

#MYSQL settings
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

