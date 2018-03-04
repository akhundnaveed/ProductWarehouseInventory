# ProductWarehouseInventory
The Product and Warehouse and Inventory Assignment by Vantibolli

## Requirements
- Java 8 or higher version
- Apache tomcat 8.0.29 or higher version
- MySql 5.7.20 or higher version
- Git 2.14.3 or higher version
- Eclipse IDE or any other

## Project Setup
### Check out project
```
git clone https://github.com/akhundnaveed/ProductWarehouseInventory
```
or using ssh
```
git clone git@github.com:akhundnaveed/ProductWarehouseInventory.git
```
### Import in Eclipse
`File` > `Import` > `Existing Maven Projects` > `Next` > browse to project location `ProductWarehouseInventory` > `Finish`

## Database Setup

### Database Design
![PWI Database Design](https://github.com/akhundnaveed/ProductWarehouseInventory/blob/master/img/pwi_db.png)

### Create database
Connect to mysql as root user and execute following command
```
create database pwi;
```
### Create user
```
create user 'pwiusr'@'localhost' identified by 'pwi123';
```
### Grant permission to user on pwi database
```
grant all on pwi.* to 'pwiusr'@'localhost';
```
### Restore database
Exit from MySql query shell

Go to `<Eclipse_Workspace>/ProductWarehouseInventory/db` path and execute following command
```
mysql pwi < pwi.sql -u pwiusr -p
Enter password: pwi123
```

## Running and Testing Services
### Execute Maven Tests
`Run` > `Run Configurations...` Select `m2 Maven Build` and click new button

Name: `PWI Tests`

Base Directory: `Browse Workspace...` > select `ProductWarehouseInventory` project

Goals: `clean compile test`

Click `Apply` and then `Run`

### Deploy to Apache tomcat

Note: Before deploying make sure Apache tomcat is up and running and there is at least one user defined in `tomcat_users.xml` file with manager rights.

`Run` > `Run Configurations...` > `m2 Maven Build` and click New

Name: `PWI Deployment`

Base Directory: `Browse Workspace...` > `ProductWarehouseInventory` project

Goals: `clean compile tomcat:redeploy`

![maven deployment goals](https://github.com/akhundnaveed/ProductWarehouseInventory/blob/master/img/maven_deploy_goals.png)

Click `Environment` tab add following variables and values (enter values as your tomcat manager user):
 
 Name           | Value 
--------------- | ------------------------------ 
tomcat.url      | http://localhost:8080/manager/text 
tomcat.username | admin 
tomcat.password | 6yqpbx93

![maven tomcat parameters](https://github.com/akhundnaveed/ProductWarehouseInventory/blob/master/img/maven_tomcat_param.png)

Click `Run` button

### View Swagger-UI
Open browser and go to following URL: http://localhost:8080/pwi

This should display the Swagger-UI page with API endpoints available for ProductWarehouseInventory project

![PWI Swagger UI](https://github.com/akhundnaveed/ProductWarehouseInventory/blob/master/img/pwi_swagger_ui.png)

### View Javadoc
Open browser and go to following URL: http://localhost:8080/pwi/doc/index.html

This should display all available packages and classes for ProductWarehouseInventory project in Javadoc form

![PWI Java Documentation](https://github.com/akhundnaveed/ProductWarehouseInventory/blob/master/img/pwi_javadoc.png)
