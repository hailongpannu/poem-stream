package org.poem.rest.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIKey {
    public String result;
    public String msg;
    public String api_key;
    public String email;
    public String var_name;
    public String code;
}
