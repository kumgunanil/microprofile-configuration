package com.anilit.resource;

import com.anilit.config.CustomConfig;
import com.anilit.constant.Constants;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class Resource {

//    @Inject
//    CustomConfig customConfig;

    @ConfigProperty(name = "interest_rate", defaultValue = "10")
    int interestRate;

    @GET
    @Path("calc_interest/{amount}")
    public Response calcInterest(@PathParam("amount") int amount) {
        return Response.ok(amount*interestRate/100).build();
    }

    @GET
    @Path("calc_interest/{branch}/{amount}")
    public Response calcInterest(@PathParam("branch") String branch, @PathParam("amount") int amount) {


        Integer value = ConfigProvider
                .getConfig()
                .getOptionalValue(branch.toLowerCase() + "_interest_rate", Integer.class)
                .orElse(5);
        return Response.ok(amount*value.intValue()/100).build();
    }
}
