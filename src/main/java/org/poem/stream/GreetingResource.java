package org.poem.stream;

import org.poem.rest.client.CountriesService;
import org.poem.rest.client.APIKey;
import org.poem.rest.client.FormDto;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import io.smallrye.common.annotation.Blocking;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dev_fetch_api_key")
public class GreetingResource {
    @Inject
    @RestClient
    CountriesService countriesService;

    @GET
    @Blocking
    // @Produces(MediaType.TEXT_PLAIN)
    public APIKey hello() {
        FormDto formData = new FormDto();
        formData.email = "huang.h@pannucorp.com";
        return countriesService.getAPIKey(formData);
    }
}