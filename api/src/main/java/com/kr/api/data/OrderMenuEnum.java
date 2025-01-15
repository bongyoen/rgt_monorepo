package com.kr.api.data;


import lombok.Getter;

@Getter
public enum OrderMenuEnum {
    burger("버거"),
    chicken("치킨"),
    pizza("피자");

    private final String menuName;

    OrderMenuEnum(String menuName) {
        this.menuName = menuName;
    }

}
