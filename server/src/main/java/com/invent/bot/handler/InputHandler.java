package com.invent.bot.handler;

import com.invent.bot.entity.Message;

public abstract class InputHandler {
    abstract public Message onMessage(Message activity);
}
