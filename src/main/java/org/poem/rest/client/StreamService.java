package org.poem.rest.client;

import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.MultipartForm;
import org.jboss.resteasy.reactive.RestHeader;
import org.poem.rest.client.formdata.UploadFileFormData;

@Path("/v1")
@RegisterRestClient
public interface StreamService {
    // get subscribed streams
    @GET
    @Path("/users/me/subscriptions")    
    @Produces(MediaType.TEXT_PLAIN)
    Response getSubscribedStreams(
        @QueryParam("include_subscribers") Boolean includeSubscribers,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;  

    // subscribe to a stream
    @POST
    @Path("/users/me/subscriptions")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    @Produces(MediaType.TEXT_PLAIN)
    Response subscribeToStream(
        @FormParam("subscriptions") String subscriptions,
        @FormParam("principals") String principals,   
        @FormParam("authorization_errors_fatal") Boolean authorizationErrorsFatal,   
        @FormParam("announce") Boolean announce,
        @FormParam("invite_only") Boolean inviteOnly,
        @FormParam("is_web_public") Boolean isWebPublic,
        @FormParam("history_public_to_subscribers") Boolean historyPublicToSubscribers,
        @FormParam("stream_post_policy") String streamPostPolicy,
        @FormParam("message_retention_days") String messageRetentionDays,        
        @RestHeader("Authorization") String authHeader
    ) throws MyException;

    // unsubscribe from a stream
    @DELETE
    @Path("/users/me/subscriptions")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    @Produces(MediaType.TEXT_PLAIN)
    Response unsubscribeFromStream(
        @FormParam("subscriptions") String subscriptions,
        @FormParam("principals") String principals,         
        @RestHeader("Authorization") String authHeader
    ) throws MyException; 
    
    // get subscribed status
    @GET
    @Path("/users/{user_id}/subscriptions/{stream_id}")    
    @Produces(MediaType.TEXT_PLAIN)
    Response getSubscribedStatus(
        @PathParam("user_id") String userId,
        @PathParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;  
    
    // get the subscribers of a stream
    @GET
    @Path("/streams/{stream_id}/members")
    @Produces(MediaType.TEXT_PLAIN)
    Response getAllSubscribers(
        @PathParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;

    // update subscription settings
    @POST
    @Path("/users/me/subscriptions/properties")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    @Produces(MediaType.TEXT_PLAIN)
    Response updateSubscriptionSettings(
        @FormParam("subscription_data") String subscriptionData,      
        @RestHeader("Authorization") String authHeader
    ) throws MyException;
    
    // get all streams
    @GET
    @Path("/streams")
    @Produces(MediaType.TEXT_PLAIN)
    Response getAllStreams(
        @RestHeader("Authorization") String authHeader
    ) throws MyException; 
    
    // get stream id
    @GET
    @Path("/get_stream_id")
    @Produces(MediaType.TEXT_PLAIN)
    Response getStreamId(
        @QueryParam("stream") String stream,
        @RestHeader("Authorization") String authHeader
    ) throws MyException; 
    
    // update a stream
    @PATCH
    @Path("/streams/{stream_id}")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    @Produces(MediaType.TEXT_PLAIN)
    Response updateStream(
        @PathParam("stream_id") String streamId,
        @FormParam("description") String description,
        @FormParam("new_name") String newName,   
        @FormParam("is_private") String isPrivate,   
        @FormParam("is_web_public") String isWebPublic,
        @FormParam("stream_post_policy") String streamPostPolicy,
        @FormParam("history_public_to_subscribers") String historyPublicToSubscribers,
        @FormParam("message_retention_days") String messageRetentionDays,
        @FormParam("is_announcement_only") String isAnnouncementOnly,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;

    // archive a stream
    @DELETE
    @Path("/streams/{stream_id}")    
    @Produces(MediaType.TEXT_PLAIN)
    Response archiveStream(
        @PathParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;
    
    // get topics from a stream
    @GET
    @Path("/users/me/{stream_id}/topics")
    @Produces(MediaType.TEXT_PLAIN)
    Response getTopics(
        @PathParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;
    
    // muting topic
    @PATCH
    @Path("/users/me/subscriptions/muted_topics")
    @Produces(MediaType.TEXT_PLAIN)
    Response muteTopic(
        @QueryParam("stream") String stream,
        @QueryParam("stream_id") String streamId,
        @QueryParam("topic") String topic,
        @QueryParam("op") String op,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;
    
    // delete a topic
    @POST
    @Path("/streams/{stream_id}/delete_topic")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    @Produces(MediaType.TEXT_PLAIN)
    Response deleteTopic(
        @PathParam("stream_id") String streamId,
        @FormParam("topic_name") String topicName,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;    
}
