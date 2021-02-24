package org.habv.shs.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Herman Barrantes
 */
public class FileHttpHandler implements HttpHandler {

    private static final String INDEX_FILE = "index.html";

    private final String contentDir;

    public FileHttpHandler(String contentDir) {
        this.contentDir = contentDir;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        if (path.equals("/")) {
            path += INDEX_FILE;
        }

        File file = new File(contentDir + path).getCanonicalFile();
        HttpHandler handler;

        if (path.contains("..")) {
            handler = new ErrorHandler(Status.FORBIDDEN);
        } else if (!file.isFile()) {
            handler = new ErrorHandler(Status.NOT_FOUND);
        } else {
            handler = new OkHandler(Status.OK, file);
        }

        try {
            handler.handle(exchange);
        } catch (IOException ex) {
            new ErrorHandler(Status.INTERNAL_SERVER_ERROR).handle(exchange);
        }
    }

}
