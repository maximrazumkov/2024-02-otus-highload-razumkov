DO $$
BEGIN
   IF NOT EXISTS (
      SELECT 1 FROM pg_roles WHERE rolname = 'replicator'
   ) THEN
      CREATE USER replicator WITH LOGIN REPLICATION PASSWORD 'replicator';
   END IF;
END $$;