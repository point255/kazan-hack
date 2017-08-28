package com.invent.bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    public int id;
    public boolean is_bot;
    public String first_name;
    public String last_name;
    public String username;
    public String language_code;
}
