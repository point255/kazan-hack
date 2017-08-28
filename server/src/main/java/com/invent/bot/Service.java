package com.invent.bot;

import com.invent.bot.res.BotRes;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class Service implements Daemon {
    private final static Logger LOGGER = LoggerFactory.getLogger(Service.class);

    private HttpServer mServer;

    @Override
    public void init(DaemonContext context) throws Exception {
    }

    @Override
    public void start() throws Exception {
        try {
            // register input handlers
//            MBotManager botManager = MBotManager.getInstance();
//            botManager.registerInputHandler(10, new RecipientInputHandler());
//            botManager.registerInputHandler(20, new UserInputHandler());
//            botManager.registerInputHandler(30, new ContentInputHandler());
//            botManager.registerInputHandler(40, new LogInputHandler());

            final ResourceConfig resourceConfig = new ResourceConfig(BotRes.class);
            resourceConfig.register(JacksonFeature.class);
            resourceConfig.register(new MyApplicationBinder());

            final URI uri = UriBuilder.fromUri("http://localhost")
                    .port(3002)
                    .build();
            mServer = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig, false);
            Runtime.getRuntime().addShutdownHook(new Thread(mServer::shutdownNow));
            mServer.start();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public void stop() throws Exception {
        if (mServer != null) {
            mServer.shutdown();
        }
    }

    @Override
    public void destroy() {
        if (mServer != null) {
            mServer.shutdown();
        }
    }
}

