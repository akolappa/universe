package com.myproject.universe.representation;

import com.myproject.universe.base.Edge;

public class StarEdge implements Edge<Star>{

    private Star to;
    private Integer weight;

    public StarEdge(Star to, Integer weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public Star getTo() {
        return to;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }
}
