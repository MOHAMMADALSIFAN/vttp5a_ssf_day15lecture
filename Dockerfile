FROM maven:3.9.9-eclipse-temurin-23

LABEL MAINTAINER = "ALSIFAN"
LABEL description = "This is VTTP SSF day_15L"
LABEL name = "vttp5a_ssf_day_15L"

ARG APP_DIR=/APP_DIR

# directory where your source code will reside
# directory where you copy your project to (in the next step)
WORKDIR ${APP_DIR}

#copy the required files and /or directories  into the image
COPY  pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

# package the application using the Run directive
# this will download the dependencies defined in pom xml
# compile and package to jar
#RUN chmod a+x ./mvnw
RUN mvn package -Dmaven.test.skip=true

ENV SERVER_PORT=3000

ENV INSTANCE_NAME  "Spring-Boot"
ENV INSTANCE_HASH  "Good Morning Sir"


EXPOSE ${SERVER_PORT}

# ENTRYPOINT SERVER_PORT = ${SERVER_PORT} java -jar target/lecture15day-0.0.1-SNAPSHOT.jar


# # Use exec form for ENTRYPOINT
ENTRYPOINT ["java", "-jar", "target/lecture15day-0.0.1-SNAPSHOT.jar"]

# # Pass the SERVER_PORT environment variable to the application via command-line arguments
CMD ["--server.port=${SERVER_PORT}"]

# docker cmd command
#1.) docker system prune (to clean )
#2.) docker build -t cihansifan/vttp5-ssf-day_13:v0.0.1 .
#3.) docker image ls (to check is it upload in local)
#4.) docker container ls 
#5.) docker run -p 8085:3000 cihansifan/vttp5-ssf-day_13:v0.0.1

# docker command
# docker --version
# Clear the docker compilation/build cache
# 2. docker system prune

# Building the docker image
# 3. docker build -t /:
# docker build -t darryl1975/vttp5a-ssf-day13l:0.0.1 .

# Check docker image create in local docker repo
# 4. docker image ls

# Run the image inside the container
# 5. docker run -d -t -p : 
# docker run -d -t -p 8085:3000 vttp5a-ssf-day13l:0.0.1

# Check docker container running
# 6. docker container ls

# Stop a running container
# 7. docker stop

# Start a stopped container
# 8. docker start

# To remove a stopped container
# 9. docker rm

# To delete image
# 10. docker rmi 
# docker rmi 06431f5c8648f5d19fa5d5642b24a12b524b2a322b940ee61426c1e9d90046eb

# Docker conple and push to railway
# In railway.app, create a service (linked the project)
# Create the environment variable, SERVER_PORT=3000
# In your project root folder (command prompt), execute "railway login --browserless"
# Link the project, execute "railway link"
# Deploy the project to railway, execute "railway up"