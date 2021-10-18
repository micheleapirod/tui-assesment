package com.tui.proof.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "STREET")
  private String street;

  @Column(name = "POST_CODE")
  private String postcode;

  @Column(name = "CITY")
  private String city;

  @Column(name = "COUNTRY")
  private String country;

//  @OneToOne
//  @JoinColumn(name = "order_id")
//  private Order order;

}
