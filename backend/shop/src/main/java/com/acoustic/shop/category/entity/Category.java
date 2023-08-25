package com.acoustic.shop.category.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

}
