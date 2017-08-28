package com.invent.bot;

import com.invent.bot.handler.InputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MBotManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MBotManager.class);

    private static MBotManager INSTANCE = null;

    private final Map<Integer, InputHandler> mInputHandlers;

    private MBotManager() {
        mInputHandlers = new TreeMap<>();
    }

    public static MBotManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MBotManager();
        }

        return INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public List<InputHandler> getInputHandlers() {
        return new LinkedList<>(mInputHandlers.values());
    }

    public void registerInputHandler(int priority, InputHandler handler) {
        mInputHandlers.put(priority, handler);
    }
}

