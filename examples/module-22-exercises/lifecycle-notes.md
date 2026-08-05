# Lab 22 — Bean Lifecycle Callbacks

## Lifecycle order
Create → Inject → @PostConstruct → Use → @PreDestroy

## @PostConstruct purpose
Initialize

## @PreDestroy purpose
Clean everything that the bean used before destroying it and losing those connections

## What not to do in init
Use methods

## Scope
Pre-lab only.