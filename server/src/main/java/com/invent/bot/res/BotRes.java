package com.invent.bot.res;

import com.invent.bot.MBotManager;
import com.invent.bot.Utils;
import com.invent.bot.entity.Message;
import com.invent.bot.handler.InputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class BotRes {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotRes.class);

    @GET
    @Produces("text/plain")
    public String getHelloText() {
        LOGGER.debug("+++ input text request +++");
        return String.valueOf("Test text");
    }

    @POST
    @Consumes("application/json")
    public Response postJson(@NotNull Message message) {
        LOGGER.debug("+++ input request +++");
        LOGGER.debug(Utils.jsonToString(message));

        List<InputHandler> inputHandlers = MBotManager.getInstance().getInputHandlers();
        for (InputHandler handler : inputHandlers) {
            message = handler.onMessage(message);
        }

        return Response.ok().build();
    }

}

