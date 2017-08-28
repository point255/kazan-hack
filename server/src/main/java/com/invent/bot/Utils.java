package com.invent.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Utils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    public static String jsonToString(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
        }

        return "";
    }

    private void sendWebHook() {
        /*
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                final HttpsURLConnection connection = (HttpsURLConnection) event.getUri().toURL().openConnection();
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(connection.getOutputStream(), event.getActivity());

                int responseCode = connection.getResponseCode();
                LOGGER.debug("webhook responce code: " + responseCode);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        });
        */
    }
}

