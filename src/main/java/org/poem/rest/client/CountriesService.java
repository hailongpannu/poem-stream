package org.poem.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.MultipartForm;
import org.jboss.resteasy.reactive.RestForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Set;

@Path("/v1")
@RegisterRestClient
public interface CountriesService {

    @GET
    @Path("/name/{name}")
    Set<Country> getByName();

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    // @Produces(MediaType.TEXT_PLAIN)
    @Path("/dev_fetch_api_key")
    APIKey getAPIKey(@MultipartForm FormDto email);
}