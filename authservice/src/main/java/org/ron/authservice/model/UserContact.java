package org.ron.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContact {

    private String email;

    private String countryCode;

    private String phoneNumber;
}