CREATE KEYSPACE IF NOT EXISTS search
    WITH REPLICATION = {'class' : 'NetworkTopologyStrategy','LOCAL_DEVELOPMENT' : 1};

CREATE KEYSPACE IF NOT EXISTS bitcoin
    WITH REPLICATION = {'class' : 'NetworkTopologyStrategy','LOCAL_DEVELOPMENT' : 1};

CREATE KEYSPACE IF NOT EXISTS ethereum
    WITH REPLICATION = {'class' : 'NetworkTopologyStrategy','LOCAL_DEVELOPMENT' : 1};