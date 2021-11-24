package org.poem.rest.client;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.reactive.ClientWebApplicationException;

public class MyException extends ClientWebApplicationException{
    private Response response;

    public MyException() {
    }

    public MyException(String message) {
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause, Response response) {
        super(message, cause);
        this.response = response;
    }

    public Response getResponse() {
        return response;    
    }
}
