package org.ron.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "inventory", indexes = {
        @Index(name = "inventory_idx_user_id", columnList = "user_id")})
public class Inventory implements Serializable {
    @Id
    @Column(name = "inventory_id", nullable = false, columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer inventoryId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "inventory_name")
    private String inventoryName;
}
