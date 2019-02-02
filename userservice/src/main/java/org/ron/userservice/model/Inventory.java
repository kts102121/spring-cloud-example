package org.ron.userservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class Inventory {
    private Integer inventoryId;
    private String inventoryName;
}
