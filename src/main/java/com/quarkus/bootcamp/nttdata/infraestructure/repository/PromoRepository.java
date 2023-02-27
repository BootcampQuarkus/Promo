package com.quarkus.bootcamp.nttdata.infraestructure.repository;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.PromoD;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PromoRepository implements ReactivePanacheMongoRepository<PromoD> {
}
