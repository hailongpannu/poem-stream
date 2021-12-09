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
import org.jboss.resteasy.reactive.MultipartForm;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.jboss.resteasy.reactive.RestHeader;
import org.poem.rest.client.MessageService;
import org.poem.rest.client.formdata.UploadFileFormData;

@Path("/api/v1")
public class MessageResource {    
    @Inject
    @RestClient
    MessageService messageService;

    // send a message
    @POST
    @Path("/messages")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendMessage(
        @FormParam("type") String type,
        @FormParam("content") String content,
        @FormParam("topic") String topic,
        @FormParam("to") String to,
        @FormParam("queue_id") String queueId,
        @FormParam("local_id") String localId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.sendMessage(
                type, 
                content, 
                topic, 
                to, 
                queueId, 
                localId, 
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }

    // upload a file
    @POST
    @Path("/user_uploads")
    @Blocking
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadFile(
        @MultipartForm UploadFileFormData uploadFileFormData,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.uploadFile(uploadFileFormData, authHeader);
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }    

    // edit a message
    @PATCH
    @Path("/messages/{message_id}")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response editMessage(
        @PathParam("message_id") String messageId,
        @FormParam("topic") String topic,
        @FormParam("propagate_mode") String propagateMode,
        @FormParam("send_notification_to_old_thread") String sendNotificationToOldThread,
        @FormParam("send_notification_to_new_thread") String sendNotificationToNewThread,
        @FormParam("content") String content,
        @FormParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.editMessage(
                messageId,
                topic, 
                propagateMode, 
                sendNotificationToOldThread, 
                sendNotificationToNewThread, 
                content, 
                streamId, 
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }   

    // delete a message
    @DELETE
    @Path("/messages/{message_id}")
    @Blocking
    public Response deleteMessage(
        @PathParam("message_id") String messageId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.deleteMessage(messageId, authHeader);
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }  
    
    // get messages
    @GET
    @Path("/messages")
    @Blocking
    public Response getMessages(
        @QueryParam("anchor") String anchor,
        @QueryParam("num_before") Integer numBefore,
        @QueryParam("num_after") Integer numAfter,
        @QueryParam("narrow") String narrow,
        @QueryParam("client_gravatar") Boolean client_gravatar,
        @QueryParam("apply_markdown") Boolean applyMarkdown,
        @QueryParam("client_gravatar") Boolean clientGravatar,
        @QueryParam("use_first_unread_anchor") String useFirstUnreadAnchor,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        System.out.println(narrow);
        try {
            res = messageService.getMessages(
                anchor, 
                numBefore,
                numAfter,
                narrow,
                client_gravatar,
                applyMarkdown,
                clientGravatar,
                useFirstUnreadAnchor,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }    
    
    // add a emoji reaction
    @POST
    @Path("/messages/{message_id}/reactions")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response addEmojiReaction(
        @PathParam("message_id") String messageId,
        @FormParam("emoji_name") String emojiName,
        @FormParam("emoji_code") String emojiCode,
        @FormParam("reaction_type") String reactionType,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.addEmojiReaction(
                messageId,
                emojiName, 
                emojiCode, 
                reactionType, 
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }    

    // delete a emoji reaction
    @DELETE
    @Path("/messages/{message_id}/reactions")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEmojiReaction(
        @PathParam("message_id") String messageId,
        @FormParam("emoji_name") String emojiName,
        @FormParam("emoji_code") String emojiCode,
        @FormParam("reaction_type") String reactionType,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.addEmojiReaction(
                messageId,
                emojiName, 
                emojiCode, 
                reactionType, 
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }      
    // render a message
    @POST
    @Path("/messages/render")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response renderMessage(
        @FormParam("content") String content,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.renderMessage(
                content, 
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }

    // get a message's markdown
    @GET
    @Path("/messages/{message_id}")
    @Blocking
    public Response getMessageMarkdown(
        @PathParam("message_id") String messageId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.getMessageMarkdown(messageId, authHeader);
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }    

    // check if messages match a narrow
    @GET
    @Path("/messages/matches_narrow")
    @Blocking
    public Response checkMatchNarrow(
        @QueryParam("msg_ids") List<String> msgIds,
        @QueryParam("narrow") String narrow,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        System.out.println(narrow);
        try {
            res = messageService.checkMatchNarrow(
                msgIds,
                narrow,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }     
    
    // get a message's edit history
    @GET
    @Path("/messages/{message_id}/history")
    @Blocking
    public Response getMessageEditHistory(
        @PathParam("message_id") String messageId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.getMessageEditHistory(messageId, authHeader);
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }
    
    // update personal message flags
    @POST
    @Path("/messages/flags")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePersonalMessageFlags(
        @FormParam("messages") String messages,
        @FormParam("op") String op,
        @FormParam("flag") String flag,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.updatePersonalMessageFlags(
                messages,
                op,
                flag,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    } 

    // mark all messages as read
    @POST
    @Path("/mark_all_as_read")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response markAllMessagesAsRead(
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.markAllMessagesAsRead(
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }   
    
    // mark messages in a stream as read
    @POST
    @Path("/mark_stream_as_read")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response markStreamMessagesAsRead(
        @FormParam("stream_id") String streamId,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.markStreamMessagesAsRead(
                streamId,
                authHeader
            );
        } catch (ClientWebApplicationException ex) {
            res = ex.getResponse();
        }
        return res;
    }     

    // mark messages in a topic as read
    @POST
    @Path("/mark_topic_as_read")
    @Blocking
    @Produces(MediaType.TEXT_PLAIN)
    public Response markTopicMessagesAsRead(
        @FormParam("stream_id") String streamId,
        @FormParam("topic_name") String topicName,
        @RestHeader("Authorization") String authHeader
    ) throws Exception {
        Response res;
        try {
            res = messageService.markTopicMessagesAsRead(
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
