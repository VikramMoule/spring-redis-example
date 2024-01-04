This is a sample project from someone who learnt JAVA only during college and never as a professional developer.
So take it with a sack of salt ;-)

Versions:

```
Spring framework: 4.3.25.RELEASE
Spring-data-redis: 1.8.23.RELEASE
Jedis: 2.9.0
JDK/JRE: Java8/openjdk version "1.8.0_392"
mvn: 3.0.5
```

What does the project do?

The project connects to a Redis Enterprise Database over TLS to set a key and then get the same key.

Commands used on **CentOS7** to run the compile and project:

1. Install Java8 so you have JDK1.8
```
sudo yum update -y
sudo yum install java-1.8.0-openjdk -y
java -version
```
2. Hit enter after the command below if you have only one Java version, or enter the number from the list to select the 1.8 version
```
sudo update-alternatives --config java
```
3. Set the JAVA_HOME variable in your bash profile so maven can use it and so can other processes.
```
sed -i -e '$aexport JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.392.b08-2.el7_9.x86_64/jre' ~/.bash_profile
```
4. Refresh the bash config by making it reread the back profile and use the changes made without logging out or restarting the terminal/application server. Verify JAVA_HOME is set correctly.
```
source .bash_profile
$JAVA_HOME/bin/java -version
```
5. Install Maven and version the version.
```
sudo yum install maven -y
mvn -version
```
6. Install git, clone this repository and change the directory to it.
```
sudo yum install git -y
git clone https://github.com/VikramMoule/spring-redis-example.git
cd spring-redis-example
```
7. Clean, install dependencies and compile the project using Maven
```
mvn clean
mvn install
mvn compile
```

8. By default, the project connects to localhost on the default port, 6379. This is typically not the scenario for Redis Enterprise. So set the bash variables as below with the values relevant to your environment.
```
export REDIS_HOST="yourRedisEnterpriseDBEndpointWithoutPort"
export REDIS_PORT=yourRedisEnterpriseDBPort
export REDIS_PASSWORD="yourRedisEnterpriseDBpassword"
export REDIS_USESSL="True"
```
9. Import the server certificate into the JRE truststore.
  #### If you are using the default or any self-signed or internal CA signed cert for the proxy, grab the proxy_cert.pem from the UI or the Redis Enterprise node $configdir and import it into the JRE truststore as below
  #### When importing multiple certificates, ensure that the alias name is different for each of them. Also, the default password for the JRE trust store is ‘changeit’. If you have changed this password, ensure that you use the correct password.
  #### Answer yes to the question when keytool asks if you trust the certificate and when successful, you should see `Certificate was added to keystore`.

```
sudo keytool -alias proxy_cert -import -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -file /path/to/locally/stored/proxy_cert.pem
```
10. Finally, run/execute the project

```
mvn exec:java -Dexec.mainClass="com.example.MainApp" -Dexec.cleanupDaemonThreads=false
```

