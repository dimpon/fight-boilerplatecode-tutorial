@Echo off

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005  -jar ./target/fight-spring-boot.jar 
