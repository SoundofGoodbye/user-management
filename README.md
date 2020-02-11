Running the user-management API

	Prerequisites 
	git version 2.16.0
	maven
	Java 8
	MySQL 5.7
	
1) Download the project by executing the following git command into a empty directory: 
	You can also use the download button from github.
2) Navigate to the project root directory (where the pom file is) and execute the following maven commands:
	mvn package
	mvn org.springframework.boot:spring-boot-maven-plugin:run
	
	NOTE: You might need to change @CrossOrigin(origins = "http://localhost:4200") in RestController 
		if your client project is hosted on different port.
		
		
Running the user-management client

	Prerequisites
	git version 2.16.0
	Angular CLI: 8.0.3
	
1) Download the project by executing the following git command into a empty directory:
	You can also use the download button from github.
2) Navigate to the project root directory and execute the following commands:
	ng update
	ng serve -o
