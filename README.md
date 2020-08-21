#codeTest
  
You will require Java 8, GIT Client, Maven & PostMan/CURL installed on your local for building and running the project.  
Database used : H2 (auto update tables for ### Order)
  
### First Clone the project as below:  
  
`1. open a command prompt or power shell (windows) or CTRL-SHIFT-T ubuntu to open a terminal.(you will need to be a SUDOER) 
2. git clone https://github.com/ISingh2015/codeTest.git`
  
### Second check if JAVA, MAVEN  is on the path and build the web app as below:
  
1. cd into project directory and run MAVEN commands to run the web application.
2.`mvn clean install` 
    
If everything goes well the JAR file is generated in the TARGET folder of the web app root.
### Third RUN the application
CD into the target folder and run as below
`java -jar codeTest.0.0.1.jar`
  
If run succeeds web app is up and running. You can browse ### Product Service end points on the below URL's:
    
### End Points Product Controller :-	
    
1. http://localhost:8080/products/allProducts 
	List all products 
	 
2. http://localhost:8080/products/byCompany&comp=Apple&price=7000 (list products from apple excluding price =7000)
	Parameters :- comp & price (optional)
	List Products by company
	 
3. http://localhost:8080/products/byCategory&cat=Mobile&price=50000(list products from Mobile category excluding price =50000)
	Parameters :- cat & price (optional)
	List Products by Category
	
### End Points Order Controller :-   
  
#### 1. http://localhost:8080/order/addOrder (Post End point. you will require POSTMAN installed on your local)
	To add an order to H2 database

#### 2. http://localhost:8080/order/deleteAll
	To remove all saved Orders 

#### 3. http://localhost:8080/order/delete/{id}
	To remove saved Order by Order ID

	