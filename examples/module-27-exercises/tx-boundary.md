# Lab 27 — Transaction Boundary Placement

## Place annotation on
TransferService.transfer

## Avoid
Placing @Transaction on controller

## Why (one sentence)
Controller should not handle the transactions as that is a business logic thing

## Self-invocation risk
this.transfer same class skips proxy

## Scope
Pre-lab only.