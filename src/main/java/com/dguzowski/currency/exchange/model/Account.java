package com.dguzowski.currency.exchange.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.TreeSet;
import java.util.UUID;

@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    @GeneratedValue
    UUID id;
    @Setter
    UUID requestId;
    @Setter
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    String firstName;
    @Setter
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    String lastName;
    @Setter
    @ElementCollection
    @Size(min = 2, max = 2)
    Collection<Currency> currencies;
}
