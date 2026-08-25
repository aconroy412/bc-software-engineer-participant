-- TODO: EXPLAIN (ANALYZE, BUFFERS) email lookup for a known address
-- TODO: EXPLAIN list ACTIVE customers ORDER BY created_at, customer_id LIMIT 50 OFFSET 0

ANALYZE customer;

EXPLAIN (ANALYZE, BUFFERS, FORMAT TEXT)
SELECT * FROM customer WHERE email_normalized = 'amina@example.com';