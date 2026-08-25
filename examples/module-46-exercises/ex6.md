Readiness probe status
- Confirms crm-api instances are healthy and ready to receive traffic.

Error rate (5xx / consumer errors)
- Watch for an increase in API 5xx responses or Kafka consumer processing errors.

Consumer lag trend
- Watch whether consumers are keeping up with incoming events.
- A sustained increase indicates the consumer is falling behind.

Dead letter topic record count
- Watch for new records entering the DLT.
- An increase may indicate events are failing processing.


GO:
- Readiness remains healthy.
- Error rate stays within the expected range.
- Consumer lag remains stable or decreases.
- DLT count does not increase unexpectedly.

NO-GO / ROLLBACK:
- Readiness becomes unhealthy.
- 5xx or consumer errors increase.
- Consumer lag continually rises.
- New unexpected records appear in the DLT.


If consumer lag spikes after version 1.4.0 while agents fail on CUS-1001,
first check crm-api readiness and error signals, then inspect the logs and
correlate the CUS-1001 failure with the consumer errors and lag increase.

If the evidence shows that version 1.4.0 caused the failures and increasing
lag, stop the release and roll back.


The release is not considered complete immediately after deployment.
Continue watching readiness, error rate, consumer lag, and DLT count during
the release window.

The evidence from these signals should support an explicit GO, NO-GO, or
ROLLBACK decision rather than simply showing that the service is running.