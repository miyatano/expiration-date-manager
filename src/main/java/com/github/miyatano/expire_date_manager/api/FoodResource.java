package com.github.miyatano.expire_date_manager.api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.miyatano.expire_date_manager.dao.Food;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/food")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FoodResource {

    private static final Logger LOGGER = Logger.getLogger(FoodResource.class.getName());
    
    @Inject
    EntityManager em;

    @GET
    public List<Food> get() {
        return em.createNamedQuery("Food.findAll", Food.class)
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Food getSingle(@PathParam Integer id) {
        Food entity = em.find(Food.class, id);
        if (entity == null) {
            throw new WebApplicationException("Food with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Food food) {
        if (food.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        em.persist(food);
        return Response.ok(food).status(201).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }

}
