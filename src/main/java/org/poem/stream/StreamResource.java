package org.poem.stream;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.smallrye.common.annotation.Blocking;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hibernate.annotations.GeneratorType;
import org.jboss.resteasy.reactive.MultipartForm;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.jboss.resteasy.reactive.RestHeader;
import org.poem.rest.client.StreamService;
import org.poem.rest.client.formdata.UploadFileFormData;

@Path("/api/v1")
public class StreamResource {    
    @Inject
    @RestClient
    StreamService streamService;

    // get subscribed streams
    @GET
    @Path("/users/me/subscriptions")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSubscribedStreams(
        @QueryParam("include_subscribers") Boolean includeSubscribers,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.getSubscribedStreams(
                includeSubscribers,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }           

    // subscribe to a stream
    @POST
    @Path("/users/me/subscriptions")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response subscribeToStream(
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
    ) throws Exception {
        Response res;
        try {
            res = streamService.subscribeToStream(
                subscriptions,
                principals,     
                authorizationErrorsFatal,
                announce,
                inviteOnly,
                isWebPublic,
                historyPublicToSubscribers,
                streamPostPolicy,
                messageRetentionDays,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // unsubscribe from a stream
    @DELETE
    @Path("/users/me/subscriptions")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    @Produces(MediaType.TEXT_PLAIN)
    Response unsubscribeFromStream(
        @FormParam("subscriptions") String subscriptions,
        @FormParam("principals") String principals,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.unsubscribeFromStream(
                subscriptions,
                principals,     
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // get subscribed status
    @GET
    @Path("/users/{user_id}/subscriptions/{stream_id}")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSubscribedStatus(
        @PathParam("user_id") String userId,
        @PathParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.getSubscribedStatus(
                userId,
                streamId,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // get all subscribers
    @GET
    @Path("/streams/{stream_id}/members")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllSubscribers(
        @PathParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.getAllSubscribers(
                streamId, 
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }

    // update subscription settings
    @POST
    @Path("/users/me/subscriptions/properties")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSubscriptionSettings(
        @FormParam("subscription_data") String subscriptionData,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.updateSubscriptionSettings(
                subscriptionData,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // get all streams
    @GET
    @Path("/streams")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllStreams(
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.getAllStreams(
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    } 
    
    // get stream id
    @GET
    @Path("/get_stream_id")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStreamId(
        @QueryParam("stream") String stream,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.getStreamId(
                stream,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // update a stream
    @PATCH
    @Path("/streams/{stream_id}")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateStream(
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
    ) throws Exception {
        Response res;
        try {
            res = streamService.updateStream(
                streamId,
                description,     
                newName,
                isPrivate,
                isWebPublic,
                streamPostPolicy,
                historyPublicToSubscribers,
                messageRetentionDays,
                isAnnouncementOnly,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }

    // archive a stream
    @DELETE
    @Path("/streams/{stream_id}")    
    @Produces(MediaType.TEXT_PLAIN)
    public Response archiveStream(
        @PathParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.archiveStream(
                streamId,     
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // get topics in a stream
    @GET
    @Path("/users/me/{stream_id}/topics")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTopics(
        @PathParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.getTopics(
                streamId,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // topic muting
    @PATCH
    @Path("/users/me/subscriptions/muted_topics")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response muteTopic(
        @QueryParam("stream") String stream,
        @QueryParam("stream_id") String streamId,
        @QueryParam("topic") String topic,
        @QueryParam("op") String op,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.muteTopic(
                stream,
                streamId,
                topic,
                op,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // delete a topic
    @POST
    @Path("/streams/{stream_id}/delete_topic")    
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteTopic(
        @PathParam("stream_id") String streamId,
        @FormParam("topic_name") String topicName,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = streamService.deleteTopic(
                streamId,
                topicName,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }    
}
