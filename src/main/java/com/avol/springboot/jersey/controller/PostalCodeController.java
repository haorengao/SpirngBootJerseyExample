package com.avol.springboot.jersey.controller;

import com.avol.springboot.jersey.api.JerseyResponse;
import com.avol.springboot.jersey.api.PostalCode;
import com.avol.springboot.jersey.domain.PostalCodeDomain;
import com.avol.springboot.jersey.service.PostalCodeService;
import com.avol.springboot.jersey.util.ObjectTransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class to accepts JSON and produces JSON.
 *
 * @Path /postal.
 *
 * Created by Durga on 7/10/2015.
 */

@Path("/postal")
@Produces(MediaType.APPLICATION_JSON)
public class PostalCodeController {

    @Autowired
    private PostalCodeService postalCodeService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid PostalCode postalCode) {
        try{
            postalCode.setAreaName(null);
            postalCode.getAreaName().length();
            Long id = postalCodeService.save(ObjectTransferUtil.postalCodeDomain(postalCode));
            JerseyResponse jerseyResponse = JerseyResponse.builder()
                    .withStatusCode(Response.Status.CREATED.getStatusCode())
                    .withMessage("Request processed successfully.").build();
            Resource resource = new Resource<JerseyResponse>(jerseyResponse);
            resource.add(JaxRsLinkBuilder.linkTo(PostalCodeController.class).slash(id).withSelfRel());
            return Response.status(Response.Status.CREATED).entity(resource).build();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") final Long id) {
        try {
            PostalCodeDomain postalCodeDomain = postalCodeService.findById(id);
            JerseyResponse jerseyResponse = JerseyResponse.builder()
                    .withStatusCode(Response.Status.OK.getStatusCode())
                    .withMessage("Request processed successfully.")
                    .withPostalCode(ObjectTransferUtil.postalCode(postalCodeDomain)).build();
            Resource resource = new Resource<JerseyResponse>(jerseyResponse);
            resource.add(JaxRsLinkBuilder.linkTo(PostalCodeController.class).slash("/list").withSelfRel());
            return Response.status(Response.Status.OK).entity(resource).build();
        } catch (Exception e) {
            throw e;
        }

    }

    @GET
    @Path("/list")
    public Response findAll() {
        try {
            List<PostalCodeDomain> postalCodeDomainList = postalCodeService.findAll();
            JerseyResponse jerseyResponse = JerseyResponse.builder()
                    .withStatusCode(Response.Status.OK.getStatusCode())
                    .withMessage("Request processed successfully.")
                    .withPostalCodes(postalCodeDomainList.stream().map(postalCodeDomain ->
                            ObjectTransferUtil.postalCode(postalCodeDomain)).collect(Collectors.toList()))
                    .build();
            Resource resource = new Resource<JerseyResponse>(jerseyResponse);
            if (jerseyResponse.getPostalCodes() != null) {
                jerseyResponse.getPostalCodes().stream()
                        .forEach(postalCode ->
                                resource.add(JaxRsLinkBuilder.linkTo(PostalCodeController.class).slash(postalCode.getId())
                                        .withSelfRel()));
            }
            return Response.status(Response.Status.OK).entity(resource).build();
        } catch (Exception e) {
            throw e;
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final Long id) {
        try {
            postalCodeService.delete(id);
            JerseyResponse jerseyResponse = JerseyResponse.builder()
                    .withStatusCode(Response.Status.OK.getStatusCode())
                    .withMessage("Request processed successfully.").build();
            Resource resource = new Resource<JerseyResponse>(jerseyResponse);
            resource.add(JaxRsLinkBuilder.linkTo(PostalCodeController.class).withSelfRel());
            return Response.status(Response.Status.OK).entity(resource).build();
        } catch (Exception e) {
            throw e;
        }
    }
}
