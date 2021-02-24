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
    CSS("text/css"),
    HTM("text/html"),
    HTML("text/html"),
    TEXT("text/plain"),
    JS("application/javascript"),
    JSON("application/json"),
    OTF("font/opentype"),
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
