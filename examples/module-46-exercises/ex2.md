Producer retries after a timeout can re-send an already-delivered message.
Consumer group rebalances can re-deliver messages near the last commit.
At-least-once delivery is Kafka's default -- exactly-once requires extra design.


upsert: update customer's projected status
email: send a notification when appropriate
audit row: record change in status


dedupe strategy
idempotency keys based on event id

Scenario

Cus-1002 prospect -> active

consumer recieves event and handles all of the effects listed above
message is deliverd again because the broker retries delivery or the consumer crashes before acknowledging the first delivery

without idempotency, the second message could unknowningly deilver all of the side effects.
Because of the event id used as the key, the second message is recognized by the broker as a duplicte message and its side effects are not performaned