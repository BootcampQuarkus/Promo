package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.Promo;
import com.quarkus.bootcamp.nttdata.domain.service.PromoService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/promo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PromoResource {
  @Inject
  PromoService service;

  @GET
  public Uni<List<Promo>> getAll() {
    return service.getAll();
  }

  @GET
  @Path("/{id}")
  public Uni<Promo> getById(@PathParam("id") String id) {
    return service.getById(id);
  }

  @GET
  @Path("/today")
  public Uni<List<Promo>> getToDay() {
    return service.getToDay();
  }

  @POST
  @Transactional
  public Uni<Promo> create(Promo promo) {
    return service.create(promo);
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Uni<Promo> update(@PathParam("id") String id, Promo promo) {
    return service.update(id, promo);
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Uni<Promo> delete(@PathParam("id") String id) {
    return service.delete(id);
  }
}
