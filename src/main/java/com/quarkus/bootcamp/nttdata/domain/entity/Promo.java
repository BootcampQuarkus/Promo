package com.quarkus.bootcamp.nttdata.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
public class Promo {
  protected ObjectId id;
  protected String title;
  protected String store;
  protected Double amount;
  protected Double discount;
  protected DiscountType discountType;
  protected Double originalAmount;
  protected String description;
  protected String startDate;
  protected String endDate;
  protected Integer stock;
  protected String imageUrl;
}
