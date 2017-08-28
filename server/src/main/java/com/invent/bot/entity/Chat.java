package com.invent.bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chat {
    public int id;
    public String type;
    public String title;
    public String username;
    public String first_name;
    public String last_name;
    public boolean all_members_are_administrators;
    public ChatPhoto photo;
    public String description;
    public String invite_link;
    public Message pinned_message;
}
