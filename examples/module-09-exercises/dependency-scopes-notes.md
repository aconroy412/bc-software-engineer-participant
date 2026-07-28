| Dependency need | Scope |
| --------------- | ----- |
| JUnit Jupiter used only in `src/test/java` | `test` |
| Spring Context API called from production sources (Lab 9 learning placeholder) | `compile` |
| JDBC driver you never import in Java source but need at runtime later | `runtime` |
| API the application server will provide in production | `provided` |

## Bad pom.xml
```xml
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.11.4</version>
  <!-- no scope — defaults to compile -->
</dependency>
```
This is a jUnit test dependency yet its scope is compile not test

4. All dependencies shall have the scope tag for clarity and practicality


