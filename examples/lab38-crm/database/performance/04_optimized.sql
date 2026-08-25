-- TODO: replace DATE_TRUNC/TRUNC(created_at) filters with half-open tstz range
-- TODO: keyset page: WHERE (created_at, customer_id) < ($ts, $id) ORDER BY ... LIMIT 50
-- TODO: compare nested loop vs hash join hints/plans for customer→account

-- ============================================================
-- 1. Non-sargable query
-- ============================================================
-- Casting created_at to DATE applies the conversion to the column
-- and prevents a normal index on created_at from being used as a
-- straightforward index range condition.

SELECT customer_id
FROM customer
WHERE created_at::date = DATE '2026-07-01'
ORDER BY customer_id
LIMIT 10;


-- ============================================================
-- 2. Sargable query
-- ============================================================
-- Compare created_at directly against timestamp boundaries.
-- This allows an index on created_at to perform an index range scan.

SELECT customer_id
FROM customer
WHERE created_at >= TIMESTAMPTZ '2026-07-01 00:00:00+00'
  AND created_at <  TIMESTAMPTZ '2026-07-02 00:00:00+00'
ORDER BY customer_id
LIMIT 10;


-- ============================================================
-- 3. Compare result counts
-- ============================================================

SELECT
    (SELECT COUNT(*)
     FROM customer
     WHERE created_at::date = DATE '2026-07-01') AS non_sargable_count,

    (SELECT COUNT(*)
     FROM customer
     WHERE created_at >= TIMESTAMPTZ '2026-07-01 00:00:00+00'
       AND created_at <  TIMESTAMPTZ '2026-07-02 00:00:00+00') AS sargable_count;


-- ============================================================
-- 4. Prove no IDs exist only in the non-sargable result
-- ============================================================

SELECT COUNT(*) AS only_in_non_sargable
FROM (
    SELECT customer_id
    FROM customer
    WHERE created_at::date = DATE '2026-07-01'

    EXCEPT

    SELECT customer_id
    FROM customer
    WHERE created_at >= TIMESTAMPTZ '2026-07-01 00:00:00+00'
      AND created_at <  TIMESTAMPTZ '2026-07-02 00:00:00+00'
) AS differences;


-- ============================================================
-- 5. Prove no IDs exist only in the sargable result
-- ============================================================

SELECT COUNT(*) AS only_in_sargable
FROM (
    SELECT customer_id
    FROM customer
    WHERE created_at >= TIMESTAMPTZ '2026-07-01 00:00:00+00'
      AND created_at <  TIMESTAMPTZ '2026-07-02 00:00:00+00'

    EXCEPT

    SELECT customer_id
    FROM customer
    WHERE created_at::date = DATE '2026-07-01'
) AS differences;


-- ============================================================
-- 6. Execution plan: non-sargable version
-- ============================================================

EXPLAIN (ANALYZE, BUFFERS)
SELECT customer_id
FROM customer
WHERE created_at::date = DATE '2026-07-01';


-- ============================================================
-- 7. Execution plan: sargable version
-- ============================================================

EXPLAIN (ANALYZE, BUFFERS)
SELECT customer_id
FROM customer
WHERE created_at >= TIMESTAMPTZ '2026-07-01 00:00:00+00'
  AND created_at <  TIMESTAMPTZ '2026-07-02 00:00:00+00';


-- Step 9: Compare join strategies

-- Index supporting customer -> account lookup
CREATE INDEX IF NOT EXISTS ix_account_customer
ON account(customer_id);


-- ============================================================
-- Selective: one customer (Amina)
-- ============================================================

EXPLAIN (ANALYZE, BUFFERS)
SELECT c.public_id, a.account_id, a.balance
FROM customer c
JOIN account a
  ON a.customer_id = c.customer_id
WHERE c.public_id = 'CUS-1001';


-- ============================================================
-- Broad: many ACTIVE customers
-- ============================================================

EXPLAIN (ANALYZE, BUFFERS)
SELECT c.public_id, a.account_id, a.balance
FROM customer c
JOIN account a
  ON a.customer_id = c.customer_id
WHERE c.status = 'ACTIVE';