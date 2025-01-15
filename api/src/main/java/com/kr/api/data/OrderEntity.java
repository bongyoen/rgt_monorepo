package com.kr.api.data;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    Integer orderNo;
    OrderMenuEnum menu;
    Integer qntty;
    LocalDate ordDt;
}
