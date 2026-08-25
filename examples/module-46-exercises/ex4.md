Primary topic: crm.customer.events
Dead letter topic: crm.customer.events.DLT
Consumer group: crm-customer-projection-v1 | Correlation: lab-request-001


Approach

Use Spring's DefaultErrorHandler along with DeadLetterPublishingRecoverer.

DefaultErrorHandler handles processing failures and retries the record if able to.
DeadLetterPublishingRecoverer then pushes the message to the topic's DLT, in this case `crm.customer.events.DLT`

Headers

original topic
exception class
correlationID


PII rule

prefer custoemr IDs over emails or phone numbers. Don't use sensitive informaiton in metrics