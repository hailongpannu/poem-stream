package org.poem.rest.client.formdata;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.PartType;

// @JsonPropertyOrder({"email"})
public class APIKeyFormData {
    @FormParam("username")
    @PartType(MediaType.TEXT_PLAIN)
    public String username;
}