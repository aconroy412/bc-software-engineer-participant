# Lab 33 — Component notes

## List keys

TODO: why `customerId` is the React key.
Sometimes the order of the components change, if this is the case then react doesn't know which component to render
by making customerid the react key, react knows exaclty whihc to render

## A11y

TODO: how StatusBadge / form labels support screen readers.

StatusBadge renders the type CustomerStatus as regular text so users can read the status
form lables allow for users to lexically distinguish different forms

