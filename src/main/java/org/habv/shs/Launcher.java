package org.habv.shs;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import org.habv.shs.http.FileHttpHandler;

/**
 *
 * @author Herman Barrantes
 */
public class Launcher {

    private static final int DEFAULT_PORT = 8080;
    private static final String DEFAULT_CONTENT_DIR = "static";

    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;
        String contentDir = DEFAULT_CONTENT_DIR;

        if (args.length >= 1 && args.length <= 2) {
            for (String arg : args) {
                if (isNumber(arg)) {
                    port = Integer.parseInt(arg);
                } else {
                    contentDir = arg;
                }
            }
        }

        System.out.println("Serving HTTP on http://localhost:" + port
                + ", and content directory is " + contentDir + "!\n"
                + "PS. You can press Ctrl + C to stop me!");

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new FileHttpHandler(contentDir));
        server.start();
    }

    private static boolean isNumber(String value) {
        return value.matches("\\d+");
    }

}
