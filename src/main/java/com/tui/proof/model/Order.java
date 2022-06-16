package com.tui.proof.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_TABLE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "address_id")
  private Address address;

  @Column(name = "ORDER_TOTAL")
  private Double orderTotal;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;
}
