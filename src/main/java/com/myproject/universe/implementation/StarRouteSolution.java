package com.myproject.universe.implementation;

import com.myproject.universe.finder.GraphFinder;
import com.myproject.universe.representation.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StarRouteSolution {

    @Autowired
    @Qualifier("graphFinder")
    GraphFinder graphFinder;


    public void findSolutions(){

        Star starA = new Star("A","Solar System");
        Star starB = new Star("B","Alpha Century");
        Star starC = new Star("C","Sirius");
        Star starD = new Star("D","Betelgeuse");
        Star starE = new Star("E","Vega");

        System.out.println("1: The distance of route Solar System -> Alpha Centauri -> Sirius");

        printAnswer(graphFinder.findDistanceOfPath(starA,starB,starC));

        System.out.println("2: The distance of route Solar System -> Betelgeuse");

        printAnswer(graphFinder.findDistanceOfPath(starA,starD));

        System.out.println("3: The distance of route Solar System -> Betelgeuse -> Sirius");

        printAnswer(graphFinder.findDistanceOfPath(starA,starD,starC));

        System.out.println("4: The distance of route Solar System -> Vega -> Alpha Centauri -> Sirius -> Betelgeuse");

        printAnswer(graphFinder.findDistanceOfPath(starA,starE,starB,starC,starD));

        System.out.println("5: The distance of route Solar System -> Vega -> Betelgeuse");

        printAnswer(graphFinder.findDistanceOfPath(starA,starE,starD));

        System.out.println("6: Determine all routes starting at Sirius and ending at Sirius with a maximum of 3 stops");
        System.out.println("Solution :");
        graphFinder.findPathsWithStopsLessthan(starC,starC,3).forEach(e -> printAnswer(e));
        System.out.println();

        System.out.println("7: Determine the number of routes starting at the solar system and ending at Sirius with exactly 3 stops in between");
        System.out.println("Solution :");

        graphFinder.findPathsWithExactStopsInbetween(starA,starC,3).forEach(e -> printAnswer(e));
        System.out.println();

        System.out.println("8: Determine the duration of the shortest routes (in traveltime) between solar system and  Sirius");

        printAnswer(graphFinder.findShortestPathDuration(starA,starC));

        System.out.println("9: Determine the duration of the shortest routes (in traveltime) starting at Alpha Centauri and ending at Alpha Centauri");

        printAnswer(graphFinder.findShortestPathDuration(starB,starB));

        System.out.println("10: Determine all different routes starting at Sirius and ending at Sirius with an over traveltime less than 30.");
        System.out.println("Solution :");
        graphFinder.findPathsWithTravelTime(starC,starC,30).forEach(e -> printAnswer(e));
        System.out.println();

    }

    private void printAnswer(Integer hour){

        String result = hour == 0? "NO SUCH ROUTE": +hour+" hours";
        System.out.println("Solution: "+result);
        System.out.println();

    }

    private void printAnswer(List<String> path){
        System.out.println(String.join(" -> ",path));
    }



}
