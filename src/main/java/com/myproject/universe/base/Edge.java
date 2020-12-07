package com.myproject.universe.base;

public interface Edge<T extends Node> {

    T getTo();

    Integer getWeight();

}
