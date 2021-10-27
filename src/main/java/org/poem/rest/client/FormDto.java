package org.poem.rest.client;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.jboss.resteasy.reactive.PartType;

// @JsonPropertyOrder({"email"})
public class FormDto {
    @FormParam("email")
    @PartType(MediaType.TEXT_PLAIN)
    public String email;
}