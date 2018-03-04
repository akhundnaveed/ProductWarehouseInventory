# ProductWarehouseInventory
The Product and Warehouse and Inventory Assignment by Vantibolli

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
`File` > `Import` Select `Existing Maven Projects` > `Next` and browse to project location and `Finish`

## Database Setup
The database used by PWI project is MySql 5.7.x

### Database Design
![alt PWI Database Design](https://github.com/akhundnaveed/ProductWarehouseInventory/img/pwi_db.png)
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
grant all on pwi.* to 'pwiusr'@'localhost;
```
### Restore database
Exit from MySql query shell
Go to `<Eclipse_Workspace>/ProductWarehouseInventory/db` path and execute following command
```
mysql pwi < pwi.sql 
```

## Running and Testing Services
### Execute Maven Tests
`Run` > `Run Configurations...` Select `m2 Maven Build` and click new button
Name: `PWI Tests`
Base Directory: `Browse Workspace...` > select `ProductWarehouseInventory` project
Goals: `clean compile test`
Click `Run` button

### Deploy to Apache tomcat
`Run` > `Run Configurations...` Select `m2 Maven Build` and click new button
Name: `PWI Tests`
Base Directory: `Browse Workspace...` > select `ProductWarehouseInventory` project
Goals: `clean compile tomcat:redeploy`
![maven deployment goals](https://github.com/akhundnaveed/ProductWarehouseInventory/img/maven_deploy_goals.png)
Click `Environment` tab add following variables and values
![maven tomcat parameters](https://github.com/akhundnaveed/ProductWarehouseInventory/img/maven_tomcat_param.png)
Note 1: Considering the user exists with manager rights in the Apache tomcat `conf/tomcat_users.xml` file
Note 2: Before clicking the `Run` button make sure Apache tomcat is up and running
Click `Run` button

### View Swagger-UI
Open browser and go to following URL
http://localhost:8080/pwi/
This should display the Swagger-UI page with API endpoints available for ProductWarehouseInventory project
![PWI Swagger UI](https://github.com/akhundnaveed/ProductWarehouseInventory/img/pwi_swagger_ui.png)

### View Javadoc
Open browser and go to following URL
http://localhost:8080/pwi/doc/index.html
This should display all available packages and classes for ProductWarehouseInventory project in Javadoc form
![PWI Java Documentation](https://github.com/akhundnaveed/ProductWarehouseInventory/img/pwi_javadoc.png)



