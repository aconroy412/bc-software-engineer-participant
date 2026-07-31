# Lab 16 — Catch Order

## Step 1 — List types

NotFoundException, ConflictException, ValidationException, Exception.

## Step 2 — Order

Write the catch/handler order top-to-bottom specific → general.

NotFoundException -> ConflictException -> ValidationException -> Exception

## Step 3 — Why

One sentence: broad catch first would shadow domain mapping.

## Step 4 — Prep only

Write: *Do not complete full Lab 16 advice wiring in pre-lab.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.