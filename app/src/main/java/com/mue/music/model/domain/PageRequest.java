package com.mue.music.model.domain;

import java.util.ArrayList;
import java.util.StringJoiner;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageRequest {
    private int page;
    public int size;
    public Sort sort;

    public static PageRequest of(int page, int size) {
        return new PageRequest(page, size, Sort.by(new ArrayList<>()));
    }

    public String toSortString(){
        StringJoiner stringJoiner = new StringJoiner(",");
        for (Sort.Order order: sort.getOrders()) {
            stringJoiner.add(order.getProperty()).add(order.getDirection().toString());
        }
        return stringJoiner.toString();
    }

    public static PageRequest of(int page, int size, String property, Sort.Direction direction){
        return new PageRequest(page, size, Sort.by(property, direction));
    }
}
