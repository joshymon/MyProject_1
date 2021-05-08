# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/#build-image)

#How to Run the project in Local

#Prerequisite
- Java 8 or higher
- Install kafka in local - https://www.javatpoint.com/installing-apache-kafka-on-macos
- Run zookeeper
- Run kafka server
- Login to slack 
- Open channel -> imnotification


#Command line 

mvn clean install
mvn spring-boot:run

#Testing
#API Endpoints
POST -> http://localhost:8081/Incident
Payload
{
	"TicketId":1234,
	"Reporter":"Sam",
	"Processor":"John",
	"Issue_description":"Issue with slack notification not available"
}

PUT -> http://localhost:8081/Incident
Payload
{
	"TicketId":1234,
	"Reporter":"Sam",
	"Processor":"John",
	"Issue_description":"Issue fixed. Please retry"
}
