package com.myproject.universe.finder.impl;

import com.myproject.universe.base.Graph;
import com.myproject.universe.finder.GraphFinder;
import com.myproject.universe.representation.Star;
import com.myproject.universe.representation.StarEdge;
import com.myproject.universe.utils.StarGraphCreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class is the implementation of graphfinder which is a variation of star graph finder.
 */

public class StarGraphFinder implements GraphFinder {

    List<String> currentPath;
    List<List<String>> simplePaths;
    private Integer travelTime = 0;
    private Integer timeLimit = 0;
    private Integer maxStops = 0;
    private Integer stopsInBetween = 0;

    private Graph<Star> graph;

    public StarGraphFinder(Graph<Star> graph) {

        this.graph = graph;
    }

    /**
     * @param stars
     * @return Integer
     * <p>
     * This method takes the list of stars and returns the distance of the star path and if there is no path
     * then returns zero.
     */
    @Override
    public Integer findDistanceOfPath(Star ... stars) {
        return distance(Arrays.asList(stars),graph);
    }

    /**
     * @param source
     * @param target
     * @param stops
     * @return List of possible paths
     * <p>
     * This method finds the paths between 2 stars with maximum of specified stops.
     */
    @Override
    public List<List<String>> findPathsWithStopsLessthan(Star source, Star target, Integer stops) {
        this.maxStops = stops;
        this.currentPath = new ArrayList<>();
        this.simplePaths = new ArrayList<>();
        findPathsWithMax(source,target,0);
        simplePaths.sort((l1, l2) -> l1.size() - l2.size());
        return simplePaths;
    }

    /**
     * @param source
     * @param target
     * @param stops
     * @return List of possible paths
     * <p>
     * This method finds the paths between 2 stars with exactly number of stops between them
     */
    @Override
    public List<List<String>> findPathsWithExactStopsInbetween(Star source, Star target, Integer stops) {
        this.stopsInBetween = stops;
        this.currentPath = new ArrayList<>();
        this.simplePaths = new ArrayList<>();
        findPathsInBetween(source,target,0);
        return simplePaths;
    }

    /**
     * @param source
     * @param target
     * @param travelTime
     * @return List of possible paths
     * <p>
     * This method finds the paths between 2 stars with travel time less than specified
     */
    @Override
    public List<List<String>> findPathsWithTravelTime(Star source, Star target, Integer travelTime) {
        this.timeLimit = travelTime;
        this.currentPath = new ArrayList<>();
        this.simplePaths = new ArrayList<>();
        findPathsWithTravelTime(source,target,0);
        simplePaths.sort((l1, l2) -> l1.size() - l2.size());
        return simplePaths;
    }

    /**
     * @param source
     * @param target
     * @return the minimum hours of travel
     * <p>
     * This method finds the minimum hours to travel between 2 stars.
     */
    @Override
    public Integer findShortestPathDuration(Star source, Star target) {
        this.graph = StarGraphCreater.getGraph();
        return computeMinimumDistance(source,target);
    }

    private Integer distance(List<Star> stars, Graph<Star> graph){
        Star source = graph.findNode(stars.get(0));
        Set<Star> childStars;

        Integer totalWeight = 0;
        Boolean isRoute = Boolean.TRUE;

        for (Star star : stars.subList(1,stars.size())) {
            childStars = source.getChildrens();
            if(childStars.contains(star)){
                totalWeight += source.getEdgeTo(star)!=null?source.getEdgeTo(star).getWeight():0;
                source = graph.findNode(star);
            }else {
                isRoute = Boolean.FALSE;
                totalWeight = 0;
                break;
            }
        }
        if(!isRoute){
            return 0;
        }

        return totalWeight;
    }

    private void findPathsInBetween(Star source, Star target,Integer depth){

        Star startingStar = graph.findNode(source);

        currentPath.add(startingStar.getName());
        if(currentPath.size()>stopsInBetween+2){
            currentPath.remove(currentPath.size()-1);
            return ;
        }
        if(currentPath.size() == stopsInBetween+2 &&depth>0 && currentPath.get(currentPath.size()-1).equals(target.getName())){

            if(!simplePaths.contains(currentPath)) {
                simplePaths.add(new ArrayList<>(currentPath));
            }

        }
        for (Star nextStar : startingStar.getChildrens()){
            depth = depth + 1;
            findPathsInBetween(nextStar,target,depth);
        }
        currentPath.remove(currentPath.size()-1);
    }

    private void findPathsWithMax(Star source, Star target,Integer depth){

        Star startingStar = graph.findNode(source);

        currentPath.add(startingStar.getName());
        if(currentPath.size()>maxStops+1){
            currentPath.remove(currentPath.size()-1);
            return ;
        }
        if(depth>0 && currentPath.get(currentPath.size()-1).equals(target.getName())){

            if(!simplePaths.contains(currentPath)) {
                simplePaths.add(new ArrayList<>(currentPath));
            }

        }
        for (Star nextStar : startingStar.getChildrens()){
            depth = depth + 1;
            findPathsWithMax(nextStar,target,depth);
        }
        currentPath.remove(currentPath.size()-1);
    }

    private void findPathsWithTravelTime(Star source, Star target, int depth){

        Star startingStar = graph.findNode(source);

        currentPath.add(startingStar.getName());

        if(travelTime >= timeLimit){

            if(currentPath.size()>2) {
                Star previousStar = graph.findNode(currentPath.get(currentPath.size() - 2));
                travelTime -= previousStar.getEdgeTo(startingStar).getWeight();
            }
            currentPath.remove(currentPath.size()-1);
            return;
        }

        if(depth>0 && currentPath.get(currentPath.size()-1).equals(target.getName())){

            if(!simplePaths.contains(currentPath)) {
                simplePaths.add(new ArrayList<>(currentPath));
            }

        }

        for (Star nextStar : startingStar.getChildrens()){
            travelTime += startingStar.getEdgeTo(nextStar).getWeight();
            depth += 1;
            findPathsWithTravelTime(nextStar,target,depth);

        }
        if(currentPath.size()>2) {
            Star previousStar = graph.findNode(currentPath.get(currentPath.size() - 2));
            travelTime -= previousStar.getEdgeTo(startingStar).getWeight();
        }
        currentPath.remove(currentPath.size()-1);
    }

    private  Integer computeMinimumDistance(Star source, Star target) {
        Star start = graph.findNode(source);
        PriorityQueue<Star> starPriorityQueue = new PriorityQueue<>();
        starPriorityQueue.add(start);
        int distance;
        int i = 0;
        while (!starPriorityQueue.isEmpty()) {
            Star out = starPriorityQueue.poll();
            if(target.equals(out) && i > 1){
                return out.getMinDistance();
            }
            for (StarEdge e : out.getEdges()) {
                Star s = e.getTo();
                distance = out.equals(source)?e.getWeight():out.getMinDistance() + e.getWeight();
                if (distance < s.getMinDistance()) {
                    s.setMinDistance(distance);
                    starPriorityQueue.add(s);
                }
            }
            i++;
        }
        return 0;
    }
}
