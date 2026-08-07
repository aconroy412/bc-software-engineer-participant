# Lab 26 — Profile YAML TODOs

## Required files
application-dev.yaml

## Base keys
```yaml
spring:
    application:
        name: 
server:
    port:
datasource:
logging:
features:
```

## dev example key
```yaml
h2:
jpa:
```

## prod secret pattern
```yaml
northstar.integration.api-base-url:
$ {NORTHSTAR_API_BASE_URL}
```

## Scope
Pre-lab only. No real passwords.
