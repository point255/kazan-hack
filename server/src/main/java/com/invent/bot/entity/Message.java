package com.invent.bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    public int message_id;
    public User from;
    public int date;
    public Chat chat;
    public User forward_from;
    public Chat forward_from_chat;
    public int forward_from_message_id;
    public String forward_signature;
    public int forward_date;
    public Message reply_to_message;
    public int edit_date;
    public String author_signature;
    public String text;
    public MessageEntity entities;
}
