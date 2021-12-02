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
public interface MessageService {
    // send a message
    @POST
    @Path("/messages")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    Response sendMessage(
        @FormParam("type") String type,
        @FormParam("content") String content,
        @FormParam("topic") String topic,
        @FormParam("to") String to,
        @FormParam("queue_id") String queueId,
        @FormParam("local_id") String localId,        
        @HeaderParam("Authorization") String headerValue
    ) throws MyException;

    // upload a file
    @POST
    @Path("/user_uploads")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    Response uploadFile(
        @MultipartForm UploadFileFormData uploadFileFormData,        
        @HeaderParam("Authorization") String headerValue
    ) throws MyException;    

    // edit a message
    @PATCH
    @Path("/messages/{message_id}")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    Response editMessage(
        @PathParam("message_id") String messageId,        
        @FormParam("topic") String topic,
        @FormParam("propagate_mode") String propagateMode,
        @FormParam("send_notification_to_old_thread") String sendNotificationToOldThread,
        @FormParam("send_notification_to_new_thread") String sendNotificationToNewThread,
        @FormParam("content") String content,
        @FormParam("stream_id") String streamId,     
        @HeaderParam("Authorization") String headerValue
    ) throws MyException;   
    
    // delete a message
    @DELETE
    @Path("/messages/{message_id}")    
    Response deleteMessage(
        @PathParam("message_id") String messageId,        
        @HeaderParam("Authorization") String headerValue
    ) throws MyException; 
    
    // get messages
    @GET
    @Path("/messages")    
    @Produces(MediaType.TEXT_PLAIN)
    Response getMessages(
        @QueryParam("anchor") String anchor,
        @QueryParam("num_before") Integer numBefore,
        @QueryParam("num_after") Integer numAfter,
        @QueryParam("narrow") String narrow,
        @QueryParam("client_gravatar") Boolean client_gravatar,
        @QueryParam("apply_markdown") Boolean applyMarkdown,
        @QueryParam("client_gravatar") Boolean clientGravatar,
        @QueryParam("use_first_unread_anchor") String useFirstUnreadAnchor,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;    

    // add a emoji reaction
    @POST
    @Path("/messages/{message_id}/reactions")  
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)      
    @Produces(MediaType.TEXT_PLAIN)
    Response addEmojiReaction(
        @PathParam("message_id") String messageId,
        @FormParam("emoji_name") String emojiName,
        @FormParam("emoji_code") String emojiCode,
        @FormParam("reaction_type") String reactionType,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;  
    
    // delete a emoji reaction
    @DELETE
    @Path("/messages/{message_id}/reactions")  
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)      
    @Produces(MediaType.TEXT_PLAIN)
    Response deleteEmojiReaction(
        @PathParam("message_id") String messageId,
        @FormParam("emoji_name") String emojiName,
        @FormParam("emoji_code") String emojiCode,
        @FormParam("reaction_type") String reactionType,
        @RestHeader("Authorization") String authHeader
    ) throws MyException; 
    
    // render a message
    @POST
    @Path("/messages/render")  
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)      
    @Produces(MediaType.TEXT_PLAIN)
    Response renderMessage(
        @FormParam("content") String content,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;  
    
    // get a message's markdown
    @GET
    @Path("/messages/{message_id}")    
    Response getMessageMarkdown(
        @PathParam("message_id") String messageId,        
        @HeaderParam("Authorization") String headerValue
    ) throws MyException;     

    // check if messages match a narrow
    @GET
    @Path("/messages/matches_narrow")    
    @Produces(MediaType.TEXT_PLAIN)
    Response checkMatchNarrow(
        @QueryParam("msg_ids") List<String> msgIds,
        @QueryParam("narrow") String narrow,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;   
    
    // get a message's edit history
    @GET
    @Path("/messages/{message_id}/history")    
    Response getMessageEditHistory(
        @PathParam("message_id") String messageId,        
        @HeaderParam("Authorization") String headerValue
    ) throws MyException;      

    // update personal message flags
    @POST
    @Path("/messages/flags")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    Response updatePersonalMessageFlags(
        @FormParam("messages") String messages,
        @FormParam("op") String op,
        @FormParam("flag") String flag,
        @RestHeader("Authorization") String authHeader
    ) throws MyException; 
    
    // mark all messages as read
    @POST
    @Path("/mark_all_as_read")    
    @Produces(MediaType.TEXT_PLAIN)
    Response markAllMessagesAsRead(
        @RestHeader("Authorization") String authHeader
    ) throws MyException;  
    
    // mark messages in a stream as read
    @POST
    @Path("/mark_stream_as_read")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    Response markStreamMessagesAsRead(
        @FormParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;     

    // mark messages in a topic as read
    @POST
    @Path("/mark_topic_as_read")    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    Response markTopicMessagesAsRead(
        @FormParam("stream_id") String streamId,
        @FormParam("topic_name") String topicName,
        @RestHeader("Authorization") String authHeader
    ) throws MyException;      
}