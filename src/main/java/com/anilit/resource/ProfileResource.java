package com.anilit.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/")
public class ProfileResource {

    @ConfigProperty(name = "CEO", defaultValue = "AnilIT")
    String ceo;

    @ConfigProperty(name = "profile", defaultValue = "NONE")
    String profile;

    @ConfigProperty(name = "interest_rate", defaultValue = "5")
    String interestRate;

    @GET
    @Path("ceo")
    public Response getCEOName(){
        return Response.ok(ceo + "  " + profile + interestRate).build();
    }
}
