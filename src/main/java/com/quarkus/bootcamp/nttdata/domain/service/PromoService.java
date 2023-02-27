package com.quarkus.bootcamp.nttdata.domain.service;

import com.quarkus.bootcamp.nttdata.domain.entity.DiscountType;
import com.quarkus.bootcamp.nttdata.domain.entity.Promo;
import com.quarkus.bootcamp.nttdata.domain.mapper.PromoMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.DiscountTypeD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.PromoD;
import com.quarkus.bootcamp.nttdata.infraestructure.repository.PromoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PromoService {
  @Inject
  PromoRepository repository;
  @Inject
  PromoMapper mapper;

  public Uni<List<Promo>> getAll() {
    return repository.listAll()
          .flatMap(p ->
                Uni.createFrom()
                      .item(p.stream()
                            .filter(q -> q.getDeleteAt() == null)
                            .map(r -> mapper.toDto(r))
                            .toList())
          );
  }

  public Uni<Promo> getById(String id) {
    return repository.findById(new ObjectId(id))
          .flatMap(p -> {
            if(p.getDeleteAt() != null){
              return Uni.createFrom().item(new Promo());
            }
            return Uni.createFrom().item(mapper.toDto(p));
          });
  }

  public Uni<List<Promo>> getToDay() {
    return Uni.createFrom().item(new ArrayList<>());
  }

  public Uni<Promo> create(Promo promo) {
    PromoD promoD = mapper.toEntity(promo);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
    String date = ZonedDateTime.now(ZoneId.systemDefault()).format(formatter);
    promoD.setCreateAt(date);
    return repository.persist(promoD)
          .flatMap(p -> Uni.createFrom().item(mapper.toDto(p)));
  }

  public Uni<Promo> update(String id, Promo promo) {
    return repository.findById(new ObjectId(id))
          .flatMap(p -> {
            DiscountTypeD discountTypeD;
            if(p.getDeleteAt() != null){
              return Uni.createFrom().item(new Promo());
            }
            if (promo.getDiscountType().equals(DiscountType.AMOUNT)) {
              discountTypeD = DiscountTypeD.AMOUNT;
            } else {
              discountTypeD = DiscountTypeD.PERCENTAGE;
            }
            p.setTitle(promo.getTitle());
            p.setStore(promo.getStore());
            p.setAmount(promo.getAmount());
            p.setDiscount(promo.getDiscount());
            p.setDiscountType(discountTypeD);
            p.setOriginalAmount(promo.getOriginalAmount());
            p.setDescription(promo.getDescription());
            p.setStartDate(promo.getStartDate());
            p.setEndDate(promo.getEndDate());
            p.setStock(promo.getStock());
            p.setImageUrl(promo.getImageUrl());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
            String date = ZonedDateTime.now(ZoneId.systemDefault()).format(formatter);
            p.setUpdateAt(date);
            return repository.update(p).flatMap(q -> Uni.createFrom().item(mapper.toDto(q)));
          });
  }

  public Uni<Promo> delete(String id) {
    return repository.findById(new ObjectId(id))
          .flatMap(p -> {
            DiscountTypeD discountTypeD;
            if(p.getDeleteAt() != null){
              return Uni.createFrom().item(new Promo());
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
            String date = ZonedDateTime.now(ZoneId.systemDefault()).format(formatter);
            p.setDeleteAt(date);
            return repository.update(p).flatMap(q -> Uni.createFrom().item(mapper.toDto(q)));
          });
  }
}
