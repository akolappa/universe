package com.myproject.universe.finder.impl;

import com.myproject.universe.base.Graph;
import com.myproject.universe.builder.GraphBuilder;
import com.myproject.universe.finder.GraphFinder;
import com.myproject.universe.representation.Star;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StarGraphFinderTest {

    static Graph<Star> starGraph;

    private GraphFinder finder;

    @BeforeAll
    static void setUp() {

        GraphBuilder graphBuilder = new GraphBuilder();

        Star starA = new Star("A","Solar System");
        Star starB = new Star("B","Alpha Century");
        Star starC = new Star("C","Sirius");
        Star starD = new Star("D","Betelgeuse");
        Star starE = new Star("E","Vega");

        starGraph = graphBuilder.from(starA).to(starB,5).to(starD,5).to(starE,7)
                .from(starB).to(starC,4)
                .from(starC).to(starD,8).to(starE,2)
                .from(starD).to(starC,8).to(starE,6)
                .from(starE).to(starB,3)
                .build();

    }

    @BeforeEach
    void init(){
        finder = new StarGraphFinder(starGraph);
    }

    @Test
    void findDistanceOfPaths_of_Stars_A_B_C() {
        Star starA = new Star("A","Solar System");
        Star starB = new Star("B","Alpha Century");
        Star starC = new Star("C","Sirius");
        Integer totalTime = finder.findDistanceOfPath(starA,starB,starC);

        assertEquals(9,totalTime);

    }

    @Test
    void findPathsWithStops_between_C_and_C_with_maximum_3_stops() {
        Star starC = new Star("C","Sirius");

        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList("Sirius", "Vega", "Alpha Century", "Sirius"));
        expected.add(Arrays.asList("Sirius", "Betelgeuse", "Sirius"));
        expected.sort((l1, l2) -> l1.size() - l2.size());

        List<List<String>> actual = finder.findPathsWithStopsLessthan(starC,starC,3);

        assertEquals(expected,actual);

    }

    @Test
    void findPathsWithExact_3_StopsInbetween_A_and_C() {
        Star starA = new Star("A","Solar System");
        Star starC = new Star("C","Sirius");

        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList("Solar System", "Alpha Century", "Sirius", "Betelgeuse", "Sirius"));
        expected.add(Arrays.asList("Solar System", "Betelgeuse", "Sirius", "Betelgeuse", "Sirius"));
        expected.add(Arrays.asList("Solar System", "Betelgeuse", "Vega", "Alpha Century", "Sirius"));

        List<List<String>> actual = finder.findPathsWithExactStopsInbetween(starA,starC,3);

        assertEquals(expected,actual);
    }

    @Test
    void findPathsWith_between_C_and_C_with_travelTime_lessthan_30() {

        Star starC = new Star("C","Sirius");

        List<List<String>> expected = new ArrayList<>();

        expected.add(Arrays.asList("Sirius","Vega","Alpha Century","Sirius"));
        expected.add(Arrays.asList("Sirius","Vega","Alpha Century","Sirius","Vega","Alpha Century","Sirius"));
        expected.add(Arrays.asList("Sirius","Vega","Alpha Century","Sirius","Vega","Alpha Century","Sirius","Vega","Alpha Century","Sirius"));
        expected.add(Arrays.asList("Sirius","Vega","Alpha Century","Sirius","Betelgeuse","Sirius"));
        expected.add(Arrays.asList("Sirius","Betelgeuse","Sirius"));
        expected.add(Arrays.asList("Sirius","Betelgeuse","Sirius","Vega","Alpha Century","Sirius"));
        expected.add(Arrays.asList("Sirius","Betelgeuse","Vega","Alpha Century","Sirius"));
        expected.sort((l1, l2) -> l1.size() - l2.size());

        List<List<String>> actual = finder.findPathsWithTravelTime(starC,starC,30);
        assertEquals(expected,actual);
    }

    @Test
    void findShortestPathDuration_between_A_and_C() {
        Star starA = new Star("A","Solar System");
        Star starC = new Star("C","Sirius");

        Integer totalTime = finder.findShortestPathDuration(starA,starC);

        assertEquals(9,totalTime);
    }

}