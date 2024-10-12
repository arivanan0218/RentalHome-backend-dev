package com.rentalhome_dev.rentalhome_dev.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "homes")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 1000)
    private String image;
    private String title;
    private Double price;
    private Integer capacity;
    private String body;
    private String address;

}
