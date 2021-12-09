package org.poem.rest.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestHeader;

@Path("/v1")
@RegisterRestClient
public interface DraftService {
    // get drafts
    @GET
    @Path("/drafts")    
    @Produces(MediaType.TEXT_PLAIN)
    Response getDrafts(
        @RestHeader("Authorization") String authHeader
    ) throws MyException;  
    
    // create drafts
    @POST
    @Path("/drafts")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)      
    @Produces(MediaType.TEXT_PLAIN)
    Response createDrafts(
        @FormParam("drafts") String drafts,        
        @RestHeader("Authorization") String authHeader
    ) throws MyException;
    
    // edit a draft
    @PATCH
    @Path("/drafts/{draft_id}")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)      
    @Produces(MediaType.TEXT_PLAIN)
    Response editDraft(
        @PathParam("draft_id") String draftId,
        @FormParam("draft") String draft,        
        @RestHeader("Authorization") String authHeader
    ) throws MyException; 
    
    // delete a draft
    @DELETE
    @Path("/drafts/{draft_id}")    
    Response deleteDraft(
        @PathParam("draft_id") String draftId,        
        @HeaderParam("Authorization") String headerValue
    ) throws MyException;    
}
