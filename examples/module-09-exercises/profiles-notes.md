``` xml
<profiles>
  <profile>
    <id>dev</id>
    <activation>
      <activeByDefault>true</activeByDefault>
    </activation>
    <properties>
      <app.env>dev</app.env>
    </properties>
  </profile>
  <profile>
    <id>prod</id>
    <properties>
      <app.env>prod</app.env>
    </properties>
  </profile>
</profiles>
```

| Question | Your answer |
| -------- | ----------- |
| Which profile is active when you run plain `mvn package`? | `dev` |
| How do you activate `prod` on the command line? | `mvn -Pprod ...` |
| What is the `app.env` value under `dev`? | `dev` |
| What is the `app.env` value under `prod`? | `prod` |

1. putting production database passwords inside the dev profile;
- if an opensource project, anyone could access sensitive information
2. making prod activeByDefault on every engineer laptop;
- A developer might accidentally delete data in the production database
3.  assuming profiles change Java package names (they do not — they change build/config properties);
- This is just not how build profiles work
4. documenting secrets in screenshots of profile properties. 
- anyone with access to the repo could get knowledge of business secrets.

- Keep `dev` as the laptop default.
- Activate `prod` intentionally with `-Pprod`.
- Never store real production secrets in `pom.xml` profiles.