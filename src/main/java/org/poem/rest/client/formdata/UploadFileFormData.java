package org.poem.rest.client.formdata;

import java.io.File;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.PartType;

public class UploadFileFormData {
    @FormParam("type")
    @PartType(MediaType.TEXT_PLAIN)
    public String type;

    @FormParam("to")
    @PartType(MediaType.TEXT_PLAIN)
    public String to;
    
    @FormParam("topic")
    @PartType(MediaType.TEXT_PLAIN)
    public String topic;
    
    @FormParam("content")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public File content;
    
    public UploadFileFormData () {}

    public UploadFileFormData (
        String type,
        String to,
        String topic,
        File content
    ) {
        this.type = type;
        this.to = to;
        this.topic = topic;
        this.content = content;
    }
}
