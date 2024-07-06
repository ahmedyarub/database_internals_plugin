---- RetrieveHeapUsage ----
SELECT tuple_len, tuple_percent, dead_tuple_len, free_space
FROM pgstattuple('foo');