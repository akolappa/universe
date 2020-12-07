package com.myproject.universe.representation;

import com.myproject.universe.base.Graph;
import com.myproject.universe.base.Node;

import java.util.Set;

public class StarGraphRepresentation implements Graph {

    private Set<Star> starGraph;

    public StarGraphRepresentation(Set<Star> starGraph) {
        this.starGraph = starGraph;
    }

    @Override
    public Set<Star> getNodes() {
        return starGraph;
    }

    @Override
    public Boolean addNode(Node n) {
        return starGraph.add((Star) n);
    }

    @Override
    public Star findNode(Node n) {
        return starGraph.stream().
                filter(e -> e.equals(n)).findFirst().orElse(null);
    }

    @Override
    public Star findNode(String n) {
        return starGraph.stream().
                filter(e -> e.getName().equals(n)).findFirst().orElse(null);
    }
}
