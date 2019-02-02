package org.ron.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String userEmail;
}
