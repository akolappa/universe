package com.myproject.universe.representation;

import com.myproject.universe.base.Edge;
import com.myproject.universe.base.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Star implements Node, Comparable<Star> {
    private final String id;
    private final String name;
    private Set<StarEdge> edges;
    private Integer minDistance = Integer.MAX_VALUE;


    public Star(String id, String name) {
        this.id = id;
        this.name = name;
        edges = new HashSet<>();
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<StarEdge> getEdges() {
        return edges;
    }

    public Integer getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(Integer minDistance) {
        this.minDistance = minDistance;
    }

    public Set<Star> getChildrens(){
        return edges.stream().map(Edge::getTo).collect(Collectors.toSet());
    }

    public Edge getEdgeTo(Star star){
        return edges.stream().filter(e -> e.getTo().equals(star)).findFirst().orElse(null);
    }

    public void addEdge(StarEdge edge) {
        edges.add(edge);
    }

    @Override
    public int compareTo(Star o) {
        return Integer.compare(minDistance,o.minDistance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Star star = (Star) o;

        if (getId() != null ? !getId().equals(star.getId()) : star.getId() != null) return false;
        return getName() != null ? getName().equals(star.getName()) : star.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Star{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
