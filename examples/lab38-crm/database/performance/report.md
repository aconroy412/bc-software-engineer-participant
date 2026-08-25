# Lab 38 — Performance report

| Experiment | Plan hash / notes | Buffers | Median time | Write cost |
| ---------- | ----------------- | ------- | ----------- | ---------- |
| lab38-001 baseline email | seq scan on customer (row by row)  | shared hit=54 | 4.205 | n/a read-only |
| lab38-002 after email index | sequencial scan on only emails with indexing | shared hit=69 | 0.04 | n/a read-only |
| lab38-003 OFFSET deep page | TODO | TODO | TODO | TODO |
| lab38-004 keyset page | TODO | TODO | TODO | TODO |


### Baseline
```cmd
 ANALYZE
crm-postgres  |                                               QUERY PLAN                                               
crm-postgres  | -------------------------------------------------------------------------------------------------------
crm-postgres  |  Seq Scan on customer  (cost=0.00..1482.03 rows=1 width=102) (actual time=4.179..4.180 rows=1 loops=1)
crm-postgres  |    Filter: ((email_normalized)::text = 'amina@example.com'::text)
crm-postgres  |    Rows Removed by Filter: 50001
crm-postgres  |    Buffers: shared hit=857
crm-postgres  |  Planning:
crm-postgres  |    Buffers: shared hit=54
crm-postgres  |  Planning Time: 0.147 ms
crm-postgres  |  Execution Time: 4.205 ms
crm-postgres  | (8 rows)
```

### Index
```cmd
ANALYZE
crm-postgres  |                                                        QUERY PLAN                                                       
crm-postgres  | ------------------------------------------------------------------------------------------------------------------------
crm-postgres  |  Index Scan using email_index on customer  (cost=0.41..8.43 rows=1 width=102) (actual time=0.016..0.016 rows=1 loops=1)
crm-postgres  |    Index Cond: ((email_normalized)::text = 'amina@example.com'::text)
crm-postgres  |    Buffers: shared hit=4
crm-postgres  |  Planning:
crm-postgres  |    Buffers: shared hit=69
crm-postgres  |  Planning Time: 0.249 ms
crm-postgres  |  Execution Time: 0.040 ms
crm-postgres  | (7 rows)
```

## Why keyset beats deep OFFSET

TODO.
