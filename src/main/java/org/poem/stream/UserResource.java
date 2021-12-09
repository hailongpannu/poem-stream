package org.poem.stream;

import io.smallrye.common.annotation.Blocking;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.poem.rest.client.CountriesService;
import org.poem.rest.client.formdata.APIKeyFormData;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.jboss.resteasy.reactive.RestHeader;
@Path("/api/v1")
public class UserResource {
    @Inject
    @RestClient
    CountriesService countriesService;

    @GET
    @Path("/dev_fetch_api_key")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getDevAPIKey() {
        APIKeyFormData formData = new APIKeyFormData();
        formData.username = "huang.h@pannucorp.com";

        Response res;
        try {
            res = countriesService.getAPIKey(formData);
        } catch(ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }

    @GET
    @Path("/users/me")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMe(@RestHeader("Authorization") String authHeader) {
        Response res;
        try {
            res = countriesService.getMe(authHeader);
        } catch(ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }

    @GET
    @Path("/users")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllUsers(@RestHeader("Authorization") String authHeader) {
        Response res;
        try {
            res = countriesService.getAllUsers(authHeader);
        } catch(ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }

    @GET
    @Path("/users/{user_id}")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUser(@PathParam("user_id") String userId, @RestHeader("Authorization") String authHeader) {
        Response res;
        try {
            res = countriesService.getUser(userId, authHeader);
        } catch(ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }

    @GET
    @Path("/users/{email}")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserByEmail(@PathParam("email") String email, @RestHeader("Authorization") String authHeader) {
        Response res;
        try {
            res = countriesService.getUserByEmail(email, authHeader);
        } catch(ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
}