## Clean-me-up

### Preconditions
* The application exposes a public REST end-point for sending emails
* The application acts like a facade towards a legacy email library located in module **clean-me-up-support**
* The current implementation is rather ugly
* The API contract has not yet been published so you don't need to be backwards compatible
* Application is located in module **clean-me-up-rest**
* Don't change the legacy module **clean-me-up-support**

### Assignment
* Refactor the code in module **clean-me-up-rest** as you wish
* When you are done you should be rather happy with the code when it comes to: maintenance, quality, structure, architecture, testing, validation etc
* If you left things behind - please note that down in a read-me file

Estimated time ~4 hours, but feel free to impress us. 

### Stretch goals
When implementing a service - there are lots of things to consider. Some of those are:
* DevOps
* Security
* Documentation
* Versioning
* Monitoring
* Logging
* CI / CD
* Configuration
* Data integrity
* Fault handling

## Solution: After clean up but not all:)

You can reach me anytime, if you have additional questions regarding the solution.
We can dive into technical details. 

Yes, I know what are clean code rules or YAGNI, DRY, KISS abbreviations mean. 

Yes, I know there is a lot to do and improve. 

Yes, I have proved experience with developing, deploying and maintaining Cloud based containerized Microservices.

Yes, I can bring given project up to real startup from scratch.

### Code base changes 

##### * Spring boot version upgrade
##### * Project structure changed, created additional packages
##### * Some exceptions handled
##### * Some validation of email done
##### * Spring security used to make demo how we can move forward. 
##### * Spring Security cases tested
##### * Added Swagger to provide API documentation. 
##### * Spring boot app dockerized. 


### What we can improve in the project

##### * Note: Improvements come with the requirements by the time.
##### * CI/CD tools can be integrated like Jenkins, I am already on the first step if you are checking my code in GIT.
##### * Unit tests might be improved.
##### * We can also put into Cloud like Azure, AWS.
