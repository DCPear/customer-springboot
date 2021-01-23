
1. install docker
2. build package (jar)
   mvn package -DskipTests=true
   ``` 
    $ ls -ltr target
     total 51860
    drwxr-xr-x 1 dcpea 197609        0 Jan 23 10:10 generated-sources/
    drwxr-xr-x 1 dcpea 197609        0 Jan 23 10:10 maven-status/
    drwxr-xr-x 1 dcpea 197609        0 Jan 23 10:10 generated-test-sources/
    drwxr-xr-x 1 dcpea 197609        0 Jan 23 10:10 surefire-reports/
    drwxr-xr-x 1 dcpea 197609        0 Jan 23 10:10 maven-archiver/
    drwxr-xr-x 1 dcpea 197609        0 Jan 23 13:08 classes/
    drwxr-xr-x 1 dcpea 197609        0 Jan 23 13:08 test-classes/
    -rw-r--r-- 1 dcpea 197609    33351 Jan 23 14:09 customer-0.0.1-SNAPSHOT.jar.original
    -rw-r--r-- 1 dcpea 197609 53061260 Jan 23 14:09 customer-0.0.1-SNAPSHOT.jar
   ```
3. Add a dockerfile
4. build image
   ```
    docker build -t customer .
   ```   
   ```
   $ docker images
   REPOSITORY   TAG       IMAGE ID       CREATED              SIZE
   customer     latest    00f46f4d88fd   About a minute ago   696MB
   mysql        latest    c8562eaf9d81   4 days ago           546MB
   ```
5. Run docker container
   docker run --name customer-app -p8080:8080 -d customer
   ```
   $  docker run --name customer-app -p8080:8080 -d customer
      d4bfd4e2bf8d6f784fb85be8c16f70941a2cd9a515770a44fbfe610a596d5776
   ```
6. docker ps -a
   ```
    $ docker ps -a
    CONTAINER ID   IMAGE      COMMAND                  CREATED          STATUS          PORTS                    NAMES
    bf78cc1e17bb   customer   "java -jar /customer…"   22 seconds ago   Up 20 seconds   0.0.0.0:8080->8080/tcp   customer-app
   ```
7 check running logs
   ```
  docker logs customer-app
  ```
8. if the docker run failed for some reason, as there will not be logs, which gives details
    ```
    $ docker inspect customer-app
    ```
Reading: https://www.baeldung.com/spring-boot-docker-images