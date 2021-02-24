package org.habv.shs.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Herman Barrantes
 */
public class OkHandler implements HttpHandler {

    private static final String CONTENT_TYPE = "Content-Type";
    private final Status status;
    private final File file;

    public OkHandler(Status status, File file) {
        this.status = status;
        this.file = file;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set(CONTENT_TYPE, MimeType.getContentType(file.getPath()));
        exchange.sendResponseHeaders(status.getCode(), 0);

        try (OutputStream os = exchange.getResponseBody();
                FileInputStream fis = new FileInputStream(file)) {
            int count;
            byte[] buffer = new byte[1024];
            while ((count = fis.read(buffer)) >= 0) {
                os.write(buffer, 0, count);
            }
        }
    }

}
