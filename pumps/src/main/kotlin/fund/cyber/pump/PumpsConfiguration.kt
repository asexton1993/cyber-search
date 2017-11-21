package fund.cyber.pump

import com.datastax.driver.core.Cluster
import fund.cyber.dao.system.SystemDaoService
import fund.cyber.node.common.*
import org.apache.http.impl.client.HttpClients


object AppContext {

    val pumpsConfiguration = PumpsConfiguration()

    val cassandra = Cluster.builder()
            .addContactPoints(*pumpsConfiguration.cassandraServers.toTypedArray())
            .withPort(pumpsConfiguration.cassandraPort)
            .withMaxSchemaAgreementWaitSeconds(30)
            .build().init()!!

    val httpClient = HttpClients.createDefault()!!

    val systemDaoService = SystemDaoService(cassandra)
}


class PumpsConfiguration(
        val cassandraServers: List<String> = env(CASSANDRA_HOSTS, CASSANDRA_HOSTS_DEFAULT).split(","),
        val cassandraPort: Int = env(CASSANDRA_PORT, CASSANDRA_PORT_DEFAULT),
        val elasticHttpPort: Int = env(ELASTIC_HTTP_PORT, ELASTIC_HTTP_PORT_DEFAULT),
        val chainsToPump: String = env("CS_CHAINS_TO_PUMP", ""),
        val processLastBlock: Long = env("CS_LAST_PROCESSED_BLOCK", -1)
)