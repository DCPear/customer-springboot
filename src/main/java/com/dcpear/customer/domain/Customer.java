package com.dcpear.customer.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    private @Id
    @GeneratedValue
    Integer id;

    @Column (nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    private Date dob;
    @Column
    @Enumerated
    private Level level;

}
