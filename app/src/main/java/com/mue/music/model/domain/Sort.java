package com.mue.music.model.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Sort {

   private  List<Order> orders = new ArrayList<>();

    @Getter
    @AllArgsConstructor
    public static class Order{
        private String property;
        private Direction direction;


    }

    public enum Direction{
        DESC,
        ASC
    }

    public static Sort by(String property, Direction direction){
        return new Sort(List.of(new Order(property, direction)));
    }

    public static Sort by(Direction direction, String... properties){
        List<Order> orders = Arrays.stream(properties).map((property) -> new Order(property, direction)).collect(Collectors.toList());
        return new Sort(orders);
    }

    public static Sort by(List<Order> orders){
        return new Sort(orders);
    }
}
