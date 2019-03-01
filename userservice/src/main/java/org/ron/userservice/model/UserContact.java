package org.ron.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"countryCode", "phoneNumber"})})
public class UserContact {
    @Column(unique = true)
    private String email;

    private String countryCode;

    private String phoneNumber;
}
