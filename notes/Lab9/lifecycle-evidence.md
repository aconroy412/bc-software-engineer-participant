| command | output |
|---------|--------|
|mvn validate| `BUILD SUCCESS` |
|mvn compile| `BUILD SUCCESS` |
|mvn test| `BUILD SUCCESS` |
|mvn package| `BUILD SUCCESS` |
|mvn verify| `BUILD SUCCESS` |
|mvn install| `BUILD SUCCESS` |

```powershell
mvn validate
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.northstar:customer-service >-------------------
[INFO] Building Northstar Customer Service 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.223 s
[INFO] Finished at: 2026-07-28T13:29:31-04:00
[INFO] ------------------------------------------------------------------------
```

```powershell
mvn compile 
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.northstar:customer-service >-------------------
[INFO] Building Northstar Customer Service 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ customer-service ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.544 s
[INFO] Finished at: 2026-07-28T13:45:12-04:00
[INFO] ------------------------------------------------------------------------
```

```powershell
mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.northstar:customer-service >-------------------
[INFO] Building Northstar Customer Service 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ customer-service ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ customer-service ---
[INFO] skip non existing resourceDirectory C:\Users\acer6\java-bootcamp\examples\lab9-crm\src\test\resources
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- surefire:3.5.2:test (default-test) @ customer-service ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.northstar.crm.PlaceholderTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.095 s -- in com.northstar.crm.PlaceholderTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.985 s
[INFO] Finished at: 2026-07-28T13:45:56-04:00
[INFO] ------------------------------------------------------------------------
```

```powershell 
mvn package
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.northstar:customer-service >-------------------
[INFO] Building Northstar Customer Service 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ customer-service ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ customer-service ---
[INFO] skip non existing resourceDirectory C:\Users\acer6\java-bootcamp\examples\lab9-crm\src\test\resources
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- surefire:3.5.2:test (default-test) @ customer-service ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.northstar.crm.PlaceholderTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.099 s -- in com.northstar.crm.PlaceholderTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.4.2:jar (default-jar) @ customer-service ---
[INFO] Building jar: C:\Users\acer6\java-bootcamp\examples\lab9-crm\target\customer-service.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.960 s
[INFO] Finished at: 2026-07-28T13:49:48-04:00
[INFO] ------------------------------------------------------------------------
```

```powershell
mvn verify
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.northstar:customer-service >-------------------
[INFO] Building Northstar Customer Service 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ customer-service ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ customer-service ---
[INFO] skip non existing resourceDirectory C:\Users\acer6\java-bootcamp\examples\lab9-crm\src\test\resources
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- surefire:3.5.2:test (default-test) @ customer-service ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.northstar.crm.PlaceholderTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.114 s -- in com.northstar.crm.PlaceholderTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.4.2:jar (default-jar) @ customer-service ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.660 s
[INFO] Finished at: 2026-07-28T13:50:28-04:00
[INFO] ------------------------------------------------------------------------
```

```powershell
mvn install
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.northstar:customer-service >-------------------
[INFO] Building Northstar Customer Service 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ customer-service ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ customer-service ---
[INFO] skip non existing resourceDirectory C:\Users\acer6\java-bootcamp\examples\lab9-crm\src\test\resources
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- surefire:3.5.2:test (default-test) @ customer-service ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.northstar.crm.PlaceholderTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.096 s -- in com.northstar.crm.PlaceholderTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.4.2:jar (default-jar) @ customer-service ---
[INFO] 
[INFO] --- install:3.1.2:install (default-install) @ customer-service ---
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.9.18/maven-resolver-util-1.9.18.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.9.18/maven-resolver-util-1.9.18.pom (2.9 kB at 4.9 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver/1.9.18/maven-resolver-1.9.18.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver/1.9.18/maven-resolver-1.9.18.pom (22 kB at 421 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.9.18/maven-resolver-api-1.9.18.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.9.18/maven-resolver-api-1.9.18.pom (2.7 kB at 60 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.9.18/maven-resolver-util-1.9.18.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.9.18/maven-resolver-util-1.9.18.jar (196 kB at 2.0 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.9.18/maven-resolver-api-1.9.18.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.9.18/maven-resolver-api-1.9.18.jar (157 kB at 2.4 MB/s)
[INFO] Installing C:\Users\acer6\java-bootcamp\examples\lab9-crm\pom.xml to C:\Users\acer6\.m2\repository\com\northstar\customer-service\0.1.0-SNAPSHOT\customer-service-0.1.0-SNAPSHOT.pom
[INFO] Installing C:\Users\acer6\java-bootcamp\examples\lab9-crm\target\customer-service.jar to C:\Users\acer6\.m2\repository\com\northstar\customer-service\0.1.0-SNAPSHOT\customer-service-0.1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.360 s
[INFO] Finished at: 2026-07-28T14:03:53-04:00
[INFO] ------------------------------------------------------------------------
```
