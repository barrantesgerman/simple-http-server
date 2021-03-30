package org.habv.shs.http;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Herman Barrantes
 */
public enum MimeType {
    BMP("image/bmp"),
    GIF("image/gif"),
    JPG("image/jpeg"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    ICO("image/x-icon"),
    CSS("text/css"),
    HTM("text/html"),
    HTML("text/html"),
    TXT("text/plain"),
    CSV("text/csv"),
    JS("application/javascript"),
    JSON("application/json"),
    XML("application/xml"),
    PDF("application/pdf"),
    ZIP("application/zip"),
    RAR("application/x-rar-compressed"),
    JAR("application/java-archive"),
    OTF("font/opentype"),
    WOFF("font/woff"),
    WOFF2("font/woff2"),
    DEFAULT("application/octet-stream");

    private final String contentType;

    MimeType(String contentType) {
        this.contentType = contentType;
    }

    // ---------- Utils ---------- >
    private static final Map<String, MimeType> CACHE = new HashMap<String, MimeType>();

    static {
        for (MimeType mimeType : MimeType.values()) {
            CACHE.put(mimeType.name(), mimeType);
        }
    }

    public static String getContentType(String filename) {
        int index = filename.lastIndexOf('.');

        String extension = "";
        if (index != -1 || index != filename.length() - 1) {
            extension = filename.substring(index + 1);
        }

        MimeType mimeType = CACHE.get(extension.toUpperCase());
        if (mimeType == null) {
            mimeType = DEFAULT;
        }

        return mimeType.contentType;
    }
}
