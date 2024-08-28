# Spring Hotel Application
A project to improve my Spring knowledge.

# How to Run
Clone the repository if you haven't already:
```bash
git clone https://github.com/Chain60062/spring-hotel.git
```
After that, just run ./gradlew bootRun, or ./gradlew.bat if you're on Windows, and the application backend will be up and running.

It's important to note that this web app has two main environments: one called dev and the other staging. The dev environment is the default and uses H2 as the database, while staging uses PostgreSQL. If you want to use PostgreSQL, you will need to change the profile to staging.

But before you do, set the PostgreSQL properties in the env.example.properties file under src/main/resources and rename it to env.properties:

```yaml
POSTGRESQL_USER=mypsqluser
POSTGRESQL_PASSWORD=mypsqlpassword
```
Then, run:

```bash
./gradlew bootRun -Pprofile=staging
```
This will change the profile from dev to staging and run the application with PostgreSQL.