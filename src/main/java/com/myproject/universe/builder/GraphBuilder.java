package com.myproject.universe.builder;

import com.myproject.universe.base.Graph;
import com.myproject.universe.representation.Star;
import com.myproject.universe.representation.StarEdge;
import com.myproject.universe.representation.StarGraphRepresentation;

import java.util.HashSet;
import java.util.Set;

public class GraphBuilder {

    private Set<Star> stars;

    private Star fromStar;

    public GraphBuilder() {
        this.stars = new HashSet<>();
    }

    public GraphBuilder from(Star star){
        this.fromStar = star;
        stars.add(star);
        return this;
    }

    public GraphBuilder to(Star star, Integer weight){

        fromStar.addEdge(new StarEdge(star,weight));
        stars.add(star);
        return this;
    }

    public Graph build(){
        return new StarGraphRepresentation(stars);
    }


}
