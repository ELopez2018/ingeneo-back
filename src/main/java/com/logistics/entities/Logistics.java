package com.logistics.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "logistics")
public class Logistics {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(name = "productType")
  private String productType;

  @Column(name = "productQty")
  private Long productQty;

  @Column(name = "registerDate")
  private String registerDate;

  @Column(name = "deliveryDate")
  private String deliveryDate;

  @Column(name = "deliveryWarehouse")
  private String deliveryWarehouse;

  @Column(name = "shippingPrice")
  private Double shippingPrice;

  @Column(name = "discount")
  private Double discount;

  @Column(name = "guideNumber")
  private String guideNumber;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "client_Id", referencedColumnName = "id")
  private User client;

  @Column(name = "transport_Id")
  private String transportId;

}
