FROM aglover/java8-pier
VOLUME ["/tmp"]
ADD ./build/libs/todo-demo-main.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
EXPOSE 9002
