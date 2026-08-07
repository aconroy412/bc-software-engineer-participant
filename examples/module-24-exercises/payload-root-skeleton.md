# Lab 24 — PayloadRoot Skeleton

## Class annotation
@EndPoint

## @PayloadRoot localPart
(namespace = NAMESPACE, localpart = "GetCustomerRequest")

## Method inputs/outputs
response getCustomer(@RequestPayload GetCustomerRequest req)

## Delegation line (words)
NAMESPACE much match customer.xsd target namespace

## Scope
Pre-lab only.