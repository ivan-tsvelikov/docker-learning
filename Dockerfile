FROM maven:3.8.6-amazoncorretto-17 as builder
WORKDIR /usr/app
COPY pom.xml .
COPY src src
RUN mvn install
COPY target/dock-1.0-SNAPSHOT.jar ./dock-2.0.0.jar

FROM amazoncorretto:17-alpine-jdk
EXPOSE 80
WORKDIR /usr/app
COPY  --from=builder /usr/app/dock-2.0.0.jar /usr/app/dock.jar
#CMD ["java","-jar","./dock.jar"]