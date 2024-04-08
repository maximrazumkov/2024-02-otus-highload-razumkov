drop index if exists first_name_second_name_btree;
create index first_name_second_name_btree on usr (
	first_name varchar_pattern_ops,
	second_name varchar_pattern_ops
);