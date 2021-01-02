package com.dcpear.customer.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name="customer")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column (nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    private Date dob;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    @Enumerated(EnumType.STRING)
    private Level level;

}
