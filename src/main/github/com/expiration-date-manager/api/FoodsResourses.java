package org.acme.hibernate.orm;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

@Path("/foods")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FoodsResourses {

    @Inject
    EntityManager em;
    
    // Create
    @POST
    public Uni<Response> create(Food food) {
        return Panache.<Food>withTransaction(food::persist)
            .onItem().transform(inserted -> Response.created(URI.create("/foods/" + inserted.id)).build());
    }

    // Read
    @GET
    public Uni<List<Food>> get() {
        return Food.listAll(Sort.by("id"));
    }
    
    @GET
    @Path("/{id}")
    public Uni<Food> getSingle(Long id) {
        return Food.findById(id);
    }

    @GET
    @Path("/{name}")
    public Uni<List<Food>> getByName(String name) {
        return Food.list("name", name);
    }

    // Update

    // Delete
    @DELETE
    @Path("/{id}")
    public Uni<Boolean> delete(Long id){
        return Food.deleteById(id);
    }
}
