From openjdk:8
copy target/InventoryStockService-0.0.1-SNAPSHOT.jar InventoryStockService-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","InventoryStockService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8093
