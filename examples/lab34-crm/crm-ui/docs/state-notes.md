# Lab 34 — State notes

## Lifted state

TODO: why create/edit mode lives in App, not in CustomerCard.
information cannot be passed from child to parent, only parent to child. 

## Validation

TODO: client validation is UX only; server re-validates in Lab 35.
we do not want to get into the habit of seindin bad data into the database. so we sanitize it before sending it into the backend

## Filder

Filter is derived during render because we do not want states whose information is not wholly unique
States are much more resource-dependent than variables and we would like to have the least amount of them.

