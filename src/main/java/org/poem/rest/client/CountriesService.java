package org.poem.rest.client;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.MultipartForm;
import org.jboss.resteasy.reactive.RestForm;
import org.poem.rest.client.formdata.APIKeyFormData;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Base64;
import java.util.Set;

@Path("/v1")
@RegisterRestClient
public interface CountriesService {
    default String lookupAuth() {
        return "Basic " + 
            Base64.getEncoder().encodeToString("huang.h@pannucorp.com:M1XjqabqyhrD0xzeQ7We7kQYyRWLB11N".getBytes());
    }

    @GET
    @Path("/users/me")
    // @ClientHeaderParam(name = "Authorization", value = "{lookupAuth}")
    Response getMe(@HeaderParam("Authorization") String headerValue) throws MyException;

    @GET
    @Path("/users")
    Response getAllUsers(@HeaderParam("Authorization") String headerValue) throws MyException;

    @GET
    @Path("/users/{user_id}")
    Response getUser(@PathParam("user_id") String userId, @HeaderParam("Authorization") String headerValue) throws MyException;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    // @Produces(MediaType.TEXT_PLAIN)
    @Path("/dev_fetch_api_key")
    Response getAPIKey(@MultipartForm APIKeyFormData data) throws MyException;
}