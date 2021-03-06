package fund.cyber.search.handler

import com.fasterxml.jackson.databind.ObjectMapper
import fund.cyber.dao.ethereum.EthereumDaoService
import fund.cyber.node.common.stringValue
import fund.cyber.search.context.AppContext
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.Headers

class EthereumTxHandler(
        private val ethereumDaoService: EthereumDaoService = AppContext.ethereumDaoService,
        private val jsonSerializer: ObjectMapper = AppContext.jsonSerializer
) : HttpHandler {

    override fun handleRequest(exchange: HttpServerExchange) {

        val txHash = exchange.queryParameters["txHash"]?.stringValue()

        if (txHash == null || txHash.isEmpty()) {
            exchange.statusCode = 400
            return
        }

        val tx = ethereumDaoService.getTxByHash(txHash)

        if (tx == null) {
            exchange.statusCode = 404
            return
        }

        val rawResponse = jsonSerializer.writeValueAsString(tx)

        exchange.responseHeaders.put(Headers.CONTENT_TYPE, "application/json")
        exchange.responseSender.send(rawResponse)
    }
}