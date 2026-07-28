```com.northstar:build-demo:jar:0.1.0-SNAPSHOT
+- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
|  \- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
\- (no production compile dependencies in the mini project)```

| Artifact | Direct or transitive? | Scope shown |
| -------- | --------------------- | ----------- |
| `junit-jupiter` | direct | test |
| `junit-jupiter-params` | transitive | test |

| Question | Answer |
| -------- | ------ |
| What does `-B` mean? | Batch mode — less interactive prompts, friendlier for CI logs |
| Why `verify` instead of casual `install` on every push? | Proves package + checks without writing into every agent’s `~/.m2` unless the pipeline intentionally installs |
| Preferred CI-style command for this bootcamp | `mvn -B verify` |
