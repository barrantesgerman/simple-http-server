package org.habv.shs.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Herman Barrantes
 */
public class ErrorHandler implements HttpHandler {

    private final Status status;

    public ErrorHandler(Status status) {
        this.status = status;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(
                status.getCode(),
                status.getMessage().getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(status.getMessage().getBytes());
        }
    }

}
