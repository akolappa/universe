package com.myproject.universe.base;

import java.util.Set;

public interface Graph<T extends Node> {

    Set<T> getNodes();
    Boolean addNode(T n);
    T findNode(T n);
    T findNode(String n);

}
