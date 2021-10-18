package com.tui.proof.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_ACCESS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "ENABLE")
    private boolean enabled;


}
