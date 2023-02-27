package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.DiscountType;
import com.quarkus.bootcamp.nttdata.domain.entity.Promo;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.DiscountTypeD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.PromoD;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PromoMapper {
  public PromoD toEntity(Promo promo) {
    DiscountTypeD discountTypeD;
    if (promo.getDiscountType().equals(DiscountType.AMOUNT)) {
      discountTypeD = DiscountTypeD.AMOUNT;
    } else {
      discountTypeD = DiscountTypeD.PERCENTAGE;
    }
    PromoD promoD = new PromoD();
    promoD.setTitle(promo.getTitle());
    promoD.setStore(promo.getStore());
    promoD.setAmount(promo.getAmount());
    promoD.setDiscount(promo.getDiscount());
    promoD.setDiscountType(discountTypeD);
    promoD.setOriginalAmount(promo.getOriginalAmount());
    promoD.setDescription(promo.getDescription());
    promoD.setStartDate(promo.getStartDate());
    promoD.setEndDate(promo.getEndDate());
    promoD.setStock(promo.getStock());
    promoD.setImageUrl(promo.getImageUrl());
    return promoD;
  }

  public Promo toDto(PromoD promoD) {
    DiscountType discountType;
    if (promoD.getDiscountType().equals(DiscountTypeD.AMOUNT)) {
      discountType = DiscountType.AMOUNT;
    } else {
      discountType = DiscountType.PERCENTAGE;
    }
    Promo promo = new Promo();
    promo.setId(promoD.getId());
    promo.setTitle(promoD.getTitle());
    promo.setStore(promoD.getStore());
    promo.setAmount(promoD.getAmount());
    promo.setDiscount(promoD.getDiscount());
    promo.setDiscountType(discountType);
    promo.setOriginalAmount(promoD.getOriginalAmount());
    promo.setDescription(promoD.getDescription());
    promo.setStartDate(promoD.getStartDate());
    promo.setEndDate(promoD.getEndDate());
    promo.setStock(promoD.getStock());
    promo.setImageUrl(promoD.getImageUrl());
    return promo;
  }
}
