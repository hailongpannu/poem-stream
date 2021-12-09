package org.poem.stream;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.smallrye.common.annotation.Blocking;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.jboss.resteasy.reactive.RestHeader;
import org.poem.rest.client.DraftService;

@Path("/api/v1")
public class DraftResource {    
    @Inject
    @RestClient
    DraftService draftService;

    // get drafts
    @GET
    @Path("/drafts")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getDrafts(
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = draftService.getDrafts(
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }  
    
    // create drafts
    @POST
    @Path("/drafts")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response createDrafts(
        @FormParam("drafts") String drafts,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = draftService.createDrafts(
                drafts,            
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // edit a draft
    @PATCH
    @Path("/drafts/{draft_id}")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response editDraft(
        @PathParam("draft_id") String draftId,        
        @FormParam("draft") String draft,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = draftService.editDraft(
                draftId,
                draft,            
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // delete a draft
    @DELETE
    @Path("/drafts/{draft_id}")
    @Blocking
    public Response deleteDraft(
        @PathParam("draft_id") String draftId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = draftService.deleteDraft(draftId, authHeader);
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }     
}
