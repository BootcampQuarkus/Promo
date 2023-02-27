package com.quarkus.bootcamp.nttdata.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@MongoEntity(collection = "customerwallet")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class PromoD {
  protected ObjectId id;
  protected String title;
  protected String store;
  protected Double amount;
  protected Double discount;
  protected DiscountTypeD discountType;
  protected Double originalAmount;
  protected String description;
  protected String startDate;
  protected String endDate;
  protected Integer stock;
  protected String imageUrl;
  protected String createAt;
  protected String updateAt;
  protected String deleteAt;
}
