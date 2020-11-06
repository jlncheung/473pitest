/*
 * (C) Copyright 2018-2020, by Joris Kinable and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */
package org.jgrapht.alg.color;

import org.jgrapht.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for BrownBacktrackColoring
 *
 * @author Joris Kinable
 */
public class BrownBacktrackColoringTest
{

    /**
     * .Clique of size 6
     */
    @Test
    public void testClique()
    {
        Graph<Integer, DefaultEdge> completeGraph = new SimpleGraph<>(
            SupplierUtil.createIntegerSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
        CompleteGraphGenerator<Integer, DefaultEdge> completeGraphGenerator =
            new CompleteGraphGenerator<>(6);
        completeGraphGenerator.generateGraph(completeGraph);
        BrownBacktrackColoring<Integer, DefaultEdge> bbc =
            new BrownBacktrackColoring<>(completeGraph);
        assertEquals(6, bbc.getChromaticNumber());
        verifyColoring(completeGraph, 6, bbc.getColoring());
    }

    /**
     * myciel3.col 11 vertices, 20 edges chromatic number: 4
     */
    @Test
    public void myciel3Test()
    {
        int[][] edges = { { 1, 2 }, { 1, 4 }, { 1, 7 }, { 1, 9 }, { 2, 3 }, { 2, 6 }, { 2, 8 },
            { 3, 5 }, { 3, 7 }, { 3, 10 }, { 4, 5 }, { 4, 6 }, { 4, 10 }, { 5, 8 }, { 5, 9 },
            { 6, 11 }, { 7, 11 }, { 8, 11 }, { 9, 11 }, { 10, 11 } };
        Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        for (int[] edge : edges)
            Graphs.addEdgeWithVertices(g, edge[0], edge[1]);
        BrownBacktrackColoring<Integer, DefaultEdge> bbc = new BrownBacktrackColoring<>(g);
        assertEquals(4, bbc.getChromaticNumber());
        verifyColoring(g, 4, bbc.getColoring());
    }

    /**
     * myciel4.col 23 vertices, 71 edges chromatic number: 5
     */
    @Test
    public void myciel4Test()
    {
        int[][] edges = { { 1, 2 }, { 1, 4 }, { 1, 7 }, { 1, 9 }, { 1, 13 }, { 1, 15 }, { 1, 18 },
            { 1, 20 }, { 2, 3 }, { 2, 6 }, { 2, 8 }, { 2, 12 }, { 2, 14 }, { 2, 17 }, { 2, 19 },
            { 3, 5 }, { 3, 7 }, { 3, 10 }, { 3, 13 }, { 3, 16 }, { 3, 18 }, { 3, 21 }, { 4, 5 },
            { 4, 6 }, { 4, 10 }, { 4, 12 }, { 4, 16 }, { 4, 17 }, { 4, 21 }, { 5, 8 }, { 5, 9 },
            { 5, 14 }, { 5, 15 }, { 5, 19 }, { 5, 20 }, { 6, 11 }, { 6, 13 }, { 6, 15 }, { 6, 22 },
            { 7, 11 }, { 7, 12 }, { 7, 14 }, { 7, 22 }, { 8, 11 }, { 8, 13 }, { 8, 16 }, { 8, 22 },
            { 9, 11 }, { 9, 12 }, { 9, 16 }, { 9, 22 }, { 10, 11 }, { 10, 14 }, { 10, 15 },
            { 10, 22 }, { 11, 17 }, { 11, 18 }, { 11, 19 }, { 11, 20 }, { 11, 21 }, { 12, 23 },
            { 13, 23 }, { 14, 23 }, { 15, 23 }, { 16, 23 }, { 17, 23 }, { 18, 23 }, { 19, 23 },
            { 20, 23 }, { 21, 23 }, { 22, 23 } };
        Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        for (int[] edge : edges)
            Graphs.addEdgeWithVertices(g, edge[0], edge[1]);
        BrownBacktrackColoring<Integer, DefaultEdge> bbc = new BrownBacktrackColoring<>(g);
        assertEquals(5, bbc.getChromaticNumber());
        verifyColoring(g, 5, bbc.getColoring());
    }

    /**
     * queen5_5.col 25 vertices, 320 edges chromatic number: 5
     */
    @Test
    public void queen5Test()
    {
        int[][] edges = { { 1, 7 }, { 1, 13 }, { 1, 19 }, { 1, 25 }, { 1, 2 }, { 1, 3 }, { 1, 4 },
            { 1, 5 }, { 1, 6 }, { 1, 11 }, { 1, 16 }, { 1, 21 }, { 2, 8 }, { 2, 14 }, { 2, 20 },
            { 2, 6 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 7 }, { 2, 12 }, { 2, 17 }, { 2, 22 },
            { 2, 1 }, { 3, 9 }, { 3, 15 }, { 3, 7 }, { 3, 11 }, { 3, 4 }, { 3, 5 }, { 3, 8 },
            { 3, 13 }, { 3, 18 }, { 3, 23 }, { 3, 2 }, { 3, 1 }, { 4, 10 }, { 4, 8 }, { 4, 12 },
            { 4, 16 }, { 4, 5 }, { 4, 9 }, { 4, 14 }, { 4, 19 }, { 4, 24 }, { 4, 3 }, { 4, 2 },
            { 4, 1 }, { 5, 9 }, { 5, 13 }, { 5, 17 }, { 5, 21 }, { 5, 10 }, { 5, 15 }, { 5, 20 },
            { 5, 25 }, { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 6, 12 }, { 6, 18 }, { 6, 24 },
            { 6, 7 }, { 6, 8 }, { 6, 9 }, { 6, 10 }, { 6, 11 }, { 6, 16 }, { 6, 21 }, { 6, 2 },
            { 6, 1 }, { 7, 13 }, { 7, 19 }, { 7, 25 }, { 7, 11 }, { 7, 8 }, { 7, 9 }, { 7, 10 },
            { 7, 12 }, { 7, 17 }, { 7, 22 }, { 7, 6 }, { 7, 3 }, { 7, 2 }, { 7, 1 }, { 8, 14 },
            { 8, 20 }, { 8, 12 }, { 8, 16 }, { 8, 9 }, { 8, 10 }, { 8, 13 }, { 8, 18 }, { 8, 23 },
            { 8, 7 }, { 8, 6 }, { 8, 4 }, { 8, 3 }, { 8, 2 }, { 9, 15 }, { 9, 13 }, { 9, 17 },
            { 9, 21 }, { 9, 10 }, { 9, 14 }, { 9, 19 }, { 9, 24 }, { 9, 8 }, { 9, 7 }, { 9, 6 },
            { 9, 5 }, { 9, 4 }, { 9, 3 }, { 10, 14 }, { 10, 18 }, { 10, 22 }, { 10, 15 },
            { 10, 20 }, { 10, 25 }, { 10, 9 }, { 10, 8 }, { 10, 7 }, { 10, 6 }, { 10, 5 },
            { 10, 4 }, { 11, 17 }, { 11, 23 }, { 11, 12 }, { 11, 13 }, { 11, 14 }, { 11, 15 },
            { 11, 16 }, { 11, 21 }, { 11, 7 }, { 11, 6 }, { 11, 3 }, { 11, 1 }, { 12, 18 },
            { 12, 24 }, { 12, 16 }, { 12, 13 }, { 12, 14 }, { 12, 15 }, { 12, 17 }, { 12, 22 },
            { 12, 11 }, { 12, 8 }, { 12, 7 }, { 12, 6 }, { 12, 4 }, { 12, 2 }, { 13, 19 },
            { 13, 25 }, { 13, 17 }, { 13, 21 }, { 13, 14 }, { 13, 15 }, { 13, 18 }, { 13, 23 },
            { 13, 12 }, { 13, 11 }, { 13, 9 }, { 13, 8 }, { 13, 7 }, { 13, 5 }, { 13, 3 },
            { 13, 1 }, { 14, 20 }, { 14, 18 }, { 14, 22 }, { 14, 15 }, { 14, 19 }, { 14, 24 },
            { 14, 13 }, { 14, 12 }, { 14, 11 }, { 14, 10 }, { 14, 9 }, { 14, 8 }, { 14, 4 },
            { 14, 2 }, { 15, 19 }, { 15, 23 }, { 15, 20 }, { 15, 25 }, { 15, 14 }, { 15, 13 },
            { 15, 12 }, { 15, 11 }, { 15, 10 }, { 15, 9 }, { 15, 5 }, { 15, 3 }, { 16, 22 },
            { 16, 17 }, { 16, 18 }, { 16, 19 }, { 16, 20 }, { 16, 21 }, { 16, 12 }, { 16, 11 },
            { 16, 8 }, { 16, 6 }, { 16, 4 }, { 16, 1 }, { 17, 23 }, { 17, 21 }, { 17, 18 },
            { 17, 19 }, { 17, 20 }, { 17, 22 }, { 17, 16 }, { 17, 13 }, { 17, 12 }, { 17, 11 },
            { 17, 9 }, { 17, 7 }, { 17, 5 }, { 17, 2 }, { 18, 24 }, { 18, 22 }, { 18, 19 },
            { 18, 20 }, { 18, 23 }, { 18, 17 }, { 18, 16 }, { 18, 14 }, { 18, 13 }, { 18, 12 },
            { 18, 10 }, { 18, 8 }, { 18, 6 }, { 18, 3 }, { 19, 25 }, { 19, 23 }, { 19, 20 },
            { 19, 24 }, { 19, 18 }, { 19, 17 }, { 19, 16 }, { 19, 15 }, { 19, 14 }, { 19, 13 },
            { 19, 9 }, { 19, 7 }, { 19, 4 }, { 19, 1 }, { 20, 24 }, { 20, 25 }, { 20, 19 },
            { 20, 18 }, { 20, 17 }, { 20, 16 }, { 20, 15 }, { 20, 14 }, { 20, 10 }, { 20, 8 },
            { 20, 5 }, { 20, 2 }, { 21, 22 }, { 21, 23 }, { 21, 24 }, { 21, 25 }, { 21, 17 },
            { 21, 16 }, { 21, 13 }, { 21, 11 }, { 21, 9 }, { 21, 6 }, { 21, 5 }, { 21, 1 },
            { 22, 23 }, { 22, 24 }, { 22, 25 }, { 22, 21 }, { 22, 18 }, { 22, 17 }, { 22, 16 },
            { 22, 14 }, { 22, 12 }, { 22, 10 }, { 22, 7 }, { 22, 2 }, { 23, 24 }, { 23, 25 },
            { 23, 22 }, { 23, 21 }, { 23, 19 }, { 23, 18 }, { 23, 17 }, { 23, 15 }, { 23, 13 },
            { 23, 11 }, { 23, 8 }, { 23, 3 }, { 24, 25 }, { 24, 23 }, { 24, 22 }, { 24, 21 },
            { 24, 20 }, { 24, 19 }, { 24, 18 }, { 24, 14 }, { 24, 12 }, { 24, 9 }, { 24, 6 },
            { 24, 4 }, { 25, 24 }, { 25, 23 }, { 25, 22 }, { 25, 21 }, { 25, 20 }, { 25, 19 },
            { 25, 15 }, { 25, 13 }, { 25, 10 }, { 25, 7 }, { 25, 5 }, { 25, 1 } };
        Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        for (int[] edge : edges)
            Graphs.addEdgeWithVertices(g, edge[0], edge[1]);
        BrownBacktrackColoring<Integer, DefaultEdge> bbc = new BrownBacktrackColoring<>(g);
        assertEquals(5, bbc.getChromaticNumber());
        verifyColoring(g, 5, bbc.getColoring());
    }

    /**
     * queen6_6.col 36 vertices, 580 edges chromatic number: 7
     */
    @Test
    public void queen6Test()
    {
        int[][] edges = { { 1, 8 }, { 1, 15 }, { 1, 22 }, { 1, 29 }, { 1, 36 }, { 1, 2 }, { 1, 3 },
            { 1, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 13 }, { 1, 19 }, { 1, 25 }, { 1, 31 },
            { 2, 9 }, { 2, 16 }, { 2, 23 }, { 2, 30 }, { 2, 7 }, { 2, 3 }, { 2, 4 }, { 2, 5 },
            { 2, 6 }, { 2, 8 }, { 2, 14 }, { 2, 20 }, { 2, 26 }, { 2, 32 }, { 2, 1 }, { 3, 10 },
            { 3, 17 }, { 3, 24 }, { 3, 8 }, { 3, 13 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 9 },
            { 3, 15 }, { 3, 21 }, { 3, 27 }, { 3, 33 }, { 3, 2 }, { 3, 1 }, { 4, 11 }, { 4, 18 },
            { 4, 9 }, { 4, 14 }, { 4, 19 }, { 4, 5 }, { 4, 6 }, { 4, 10 }, { 4, 16 }, { 4, 22 },
            { 4, 28 }, { 4, 34 }, { 4, 3 }, { 4, 2 }, { 4, 1 }, { 5, 12 }, { 5, 10 }, { 5, 15 },
            { 5, 20 }, { 5, 25 }, { 5, 6 }, { 5, 11 }, { 5, 17 }, { 5, 23 }, { 5, 29 }, { 5, 35 },
            { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 6, 11 }, { 6, 16 }, { 6, 21 }, { 6, 26 },
            { 6, 31 }, { 6, 12 }, { 6, 18 }, { 6, 24 }, { 6, 30 }, { 6, 36 }, { 6, 5 }, { 6, 4 },
            { 6, 3 }, { 6, 2 }, { 6, 1 }, { 7, 14 }, { 7, 21 }, { 7, 28 }, { 7, 35 }, { 7, 8 },
            { 7, 9 }, { 7, 10 }, { 7, 11 }, { 7, 12 }, { 7, 13 }, { 7, 19 }, { 7, 25 }, { 7, 31 },
            { 7, 2 }, { 7, 1 }, { 8, 15 }, { 8, 22 }, { 8, 29 }, { 8, 36 }, { 8, 13 }, { 8, 9 },
            { 8, 10 }, { 8, 11 }, { 8, 12 }, { 8, 14 }, { 8, 20 }, { 8, 26 }, { 8, 32 }, { 8, 7 },
            { 8, 3 }, { 8, 2 }, { 8, 1 }, { 9, 16 }, { 9, 23 }, { 9, 30 }, { 9, 14 }, { 9, 19 },
            { 9, 10 }, { 9, 11 }, { 9, 12 }, { 9, 15 }, { 9, 21 }, { 9, 27 }, { 9, 33 }, { 9, 8 },
            { 9, 7 }, { 9, 4 }, { 9, 3 }, { 9, 2 }, { 10, 17 }, { 10, 24 }, { 10, 15 }, { 10, 20 },
            { 10, 25 }, { 10, 11 }, { 10, 12 }, { 10, 16 }, { 10, 22 }, { 10, 28 }, { 10, 34 },
            { 10, 9 }, { 10, 8 }, { 10, 7 }, { 10, 5 }, { 10, 4 }, { 10, 3 }, { 11, 18 },
            { 11, 16 }, { 11, 21 }, { 11, 26 }, { 11, 31 }, { 11, 12 }, { 11, 17 }, { 11, 23 },
            { 11, 29 }, { 11, 35 }, { 11, 10 }, { 11, 9 }, { 11, 8 }, { 11, 7 }, { 11, 6 },
            { 11, 5 }, { 11, 4 }, { 12, 17 }, { 12, 22 }, { 12, 27 }, { 12, 32 }, { 12, 18 },
            { 12, 24 }, { 12, 30 }, { 12, 36 }, { 12, 11 }, { 12, 10 }, { 12, 9 }, { 12, 8 },
            { 12, 7 }, { 12, 6 }, { 12, 5 }, { 13, 20 }, { 13, 27 }, { 13, 34 }, { 13, 14 },
            { 13, 15 }, { 13, 16 }, { 13, 17 }, { 13, 18 }, { 13, 19 }, { 13, 25 }, { 13, 31 },
            { 13, 8 }, { 13, 7 }, { 13, 3 }, { 13, 1 }, { 14, 21 }, { 14, 28 }, { 14, 35 },
            { 14, 19 }, { 14, 15 }, { 14, 16 }, { 14, 17 }, { 14, 18 }, { 14, 20 }, { 14, 26 },
            { 14, 32 }, { 14, 13 }, { 14, 9 }, { 14, 8 }, { 14, 7 }, { 14, 4 }, { 14, 2 },
            { 15, 22 }, { 15, 29 }, { 15, 36 }, { 15, 20 }, { 15, 25 }, { 15, 16 }, { 15, 17 },
            { 15, 18 }, { 15, 21 }, { 15, 27 }, { 15, 33 }, { 15, 14 }, { 15, 13 }, { 15, 10 },
            { 15, 9 }, { 15, 8 }, { 15, 5 }, { 15, 3 }, { 15, 1 }, { 16, 23 }, { 16, 30 },
            { 16, 21 }, { 16, 26 }, { 16, 31 }, { 16, 17 }, { 16, 18 }, { 16, 22 }, { 16, 28 },
            { 16, 34 }, { 16, 15 }, { 16, 14 }, { 16, 13 }, { 16, 11 }, { 16, 10 }, { 16, 9 },
            { 16, 6 }, { 16, 4 }, { 16, 2 }, { 17, 24 }, { 17, 22 }, { 17, 27 }, { 17, 32 },
            { 17, 18 }, { 17, 23 }, { 17, 29 }, { 17, 35 }, { 17, 16 }, { 17, 15 }, { 17, 14 },
            { 17, 13 }, { 17, 12 }, { 17, 11 }, { 17, 10 }, { 17, 5 }, { 17, 3 }, { 18, 23 },
            { 18, 28 }, { 18, 33 }, { 18, 24 }, { 18, 30 }, { 18, 36 }, { 18, 17 }, { 18, 16 },
            { 18, 15 }, { 18, 14 }, { 18, 13 }, { 18, 12 }, { 18, 11 }, { 18, 6 }, { 18, 4 },
            { 19, 26 }, { 19, 33 }, { 19, 20 }, { 19, 21 }, { 19, 22 }, { 19, 23 }, { 19, 24 },
            { 19, 25 }, { 19, 31 }, { 19, 14 }, { 19, 13 }, { 19, 9 }, { 19, 7 }, { 19, 4 },
            { 19, 1 }, { 20, 27 }, { 20, 34 }, { 20, 25 }, { 20, 21 }, { 20, 22 }, { 20, 23 },
            { 20, 24 }, { 20, 26 }, { 20, 32 }, { 20, 19 }, { 20, 15 }, { 20, 14 }, { 20, 13 },
            { 20, 10 }, { 20, 8 }, { 20, 5 }, { 20, 2 }, { 21, 28 }, { 21, 35 }, { 21, 26 },
            { 21, 31 }, { 21, 22 }, { 21, 23 }, { 21, 24 }, { 21, 27 }, { 21, 33 }, { 21, 20 },
            { 21, 19 }, { 21, 16 }, { 21, 15 }, { 21, 14 }, { 21, 11 }, { 21, 9 }, { 21, 7 },
            { 21, 6 }, { 21, 3 }, { 22, 29 }, { 22, 36 }, { 22, 27 }, { 22, 32 }, { 22, 23 },
            { 22, 24 }, { 22, 28 }, { 22, 34 }, { 22, 21 }, { 22, 20 }, { 22, 19 }, { 22, 17 },
            { 22, 16 }, { 22, 15 }, { 22, 12 }, { 22, 10 }, { 22, 8 }, { 22, 4 }, { 22, 1 },
            { 23, 30 }, { 23, 28 }, { 23, 33 }, { 23, 24 }, { 23, 29 }, { 23, 35 }, { 23, 22 },
            { 23, 21 }, { 23, 20 }, { 23, 19 }, { 23, 18 }, { 23, 17 }, { 23, 16 }, { 23, 11 },
            { 23, 9 }, { 23, 5 }, { 23, 2 }, { 24, 29 }, { 24, 34 }, { 24, 30 }, { 24, 36 },
            { 24, 23 }, { 24, 22 }, { 24, 21 }, { 24, 20 }, { 24, 19 }, { 24, 18 }, { 24, 17 },
            { 24, 12 }, { 24, 10 }, { 24, 6 }, { 24, 3 }, { 25, 32 }, { 25, 26 }, { 25, 27 },
            { 25, 28 }, { 25, 29 }, { 25, 30 }, { 25, 31 }, { 25, 20 }, { 25, 19 }, { 25, 15 },
            { 25, 13 }, { 25, 10 }, { 25, 7 }, { 25, 5 }, { 25, 1 }, { 26, 33 }, { 26, 31 },
            { 26, 27 }, { 26, 28 }, { 26, 29 }, { 26, 30 }, { 26, 32 }, { 26, 25 }, { 26, 21 },
            { 26, 20 }, { 26, 19 }, { 26, 16 }, { 26, 14 }, { 26, 11 }, { 26, 8 }, { 26, 6 },
            { 26, 2 }, { 27, 34 }, { 27, 32 }, { 27, 28 }, { 27, 29 }, { 27, 30 }, { 27, 33 },
            { 27, 26 }, { 27, 25 }, { 27, 22 }, { 27, 21 }, { 27, 20 }, { 27, 17 }, { 27, 15 },
            { 27, 13 }, { 27, 12 }, { 27, 9 }, { 27, 3 }, { 28, 35 }, { 28, 33 }, { 28, 29 },
            { 28, 30 }, { 28, 34 }, { 28, 27 }, { 28, 26 }, { 28, 25 }, { 28, 23 }, { 28, 22 },
            { 28, 21 }, { 28, 18 }, { 28, 16 }, { 28, 14 }, { 28, 10 }, { 28, 7 }, { 28, 4 },
            { 29, 36 }, { 29, 34 }, { 29, 30 }, { 29, 35 }, { 29, 28 }, { 29, 27 }, { 29, 26 },
            { 29, 25 }, { 29, 24 }, { 29, 23 }, { 29, 22 }, { 29, 17 }, { 29, 15 }, { 29, 11 },
            { 29, 8 }, { 29, 5 }, { 29, 1 }, { 30, 35 }, { 30, 36 }, { 30, 29 }, { 30, 28 },
            { 30, 27 }, { 30, 26 }, { 30, 25 }, { 30, 24 }, { 30, 23 }, { 30, 18 }, { 30, 16 },
            { 30, 12 }, { 30, 9 }, { 30, 6 }, { 30, 2 }, { 31, 32 }, { 31, 33 }, { 31, 34 },
            { 31, 35 }, { 31, 36 }, { 31, 26 }, { 31, 25 }, { 31, 21 }, { 31, 19 }, { 31, 16 },
            { 31, 13 }, { 31, 11 }, { 31, 7 }, { 31, 6 }, { 31, 1 }, { 32, 33 }, { 32, 34 },
            { 32, 35 }, { 32, 36 }, { 32, 31 }, { 32, 27 }, { 32, 26 }, { 32, 25 }, { 32, 22 },
            { 32, 20 }, { 32, 17 }, { 32, 14 }, { 32, 12 }, { 32, 8 }, { 32, 2 }, { 33, 34 },
            { 33, 35 }, { 33, 36 }, { 33, 32 }, { 33, 31 }, { 33, 28 }, { 33, 27 }, { 33, 26 },
            { 33, 23 }, { 33, 21 }, { 33, 19 }, { 33, 18 }, { 33, 15 }, { 33, 9 }, { 33, 3 },
            { 34, 35 }, { 34, 36 }, { 34, 33 }, { 34, 32 }, { 34, 31 }, { 34, 29 }, { 34, 28 },
            { 34, 27 }, { 34, 24 }, { 34, 22 }, { 34, 20 }, { 34, 16 }, { 34, 13 }, { 34, 10 },
            { 34, 4 }, { 35, 36 }, { 35, 34 }, { 35, 33 }, { 35, 32 }, { 35, 31 }, { 35, 30 },
            { 35, 29 }, { 35, 28 }, { 35, 23 }, { 35, 21 }, { 35, 17 }, { 35, 14 }, { 35, 11 },
            { 35, 7 }, { 35, 5 }, { 36, 35 }, { 36, 34 }, { 36, 33 }, { 36, 32 }, { 36, 31 },
            { 36, 30 }, { 36, 29 }, { 36, 24 }, { 36, 22 }, { 36, 18 }, { 36, 15 }, { 36, 12 },
            { 36, 8 }, { 36, 6 }, { 36, 1 } };
        Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        for (int[] edge : edges)
            Graphs.addEdgeWithVertices(g, edge[0], edge[1]);
        BrownBacktrackColoring<Integer, DefaultEdge> bbc = new BrownBacktrackColoring<>(g);
        assertEquals(7, bbc.getChromaticNumber());
        verifyColoring(g, 7, bbc.getColoring());
    }

    /**
     * mug100_1.col 100 vertices, 166 edges chromatic number: 4
     */
    // @Test
    // @Category(OptionalTests.class)
    // public void insertions2_3Test()
    // {
    //     int[][] edges = { { 1, 3 }, { 1, 4 }, { 1, 9 }, { 1, 23 }, { 2, 6 }, { 2, 8 }, { 2, 11 },
    //         { 2, 32 }, { 3, 4 }, { 3, 5 }, { 4, 21 }, { 4, 22 }, { 5, 7 }, { 5, 45 }, { 5, 46 },
    //         { 6, 18 }, { 6, 19 }, { 6, 44 }, { 7, 12 }, { 7, 13 }, { 7, 17 }, { 8, 9 }, { 8, 10 },
    //         { 9, 10 }, { 10, 24 }, { 10, 25 }, { 11, 15 }, { 11, 41 }, { 11, 62 }, { 12, 13 },
    //         { 12, 26 }, { 13, 64 }, { 13, 65 }, { 14, 15 }, { 14, 16 }, { 14, 53 }, { 14, 59 },
    //         { 15, 16 }, { 16, 43 }, { 16, 47 }, { 17, 19 }, { 17, 30 }, { 17, 31 }, { 18, 29 },
    //         { 18, 50 }, { 19, 51 }, { 19, 52 }, { 20, 21 }, { 20, 22 }, { 20, 33 }, { 20, 34 },
    //         { 21, 22 }, { 23, 24 }, { 23, 25 }, { 24, 25 }, { 26, 27 }, { 26, 28 }, { 27, 28 },
    //         { 27, 54 }, { 27, 55 }, { 28, 36 }, { 28, 37 }, { 29, 30 }, { 29, 31 }, { 30, 31 },
    //         { 32, 33 }, { 32, 34 }, { 33, 34 }, { 35, 36 }, { 35, 37 }, { 35, 60 }, { 35, 61 },
    //         { 36, 71 }, { 37, 39 }, { 37, 40 }, { 38, 39 }, { 38, 40 }, { 38, 73 }, { 38, 95 },
    //         { 39, 40 }, { 41, 42 }, { 41, 78 }, { 41, 83 }, { 42, 43 }, { 42, 48 }, { 42, 49 },
    //         { 43, 77 }, { 44, 46 }, { 44, 87 }, { 44, 88 }, { 45, 46 }, { 45, 86 }, { 47, 48 },
    //         { 47, 57 }, { 47, 58 }, { 48, 49 }, { 49, 99 }, { 49, 100 }, { 50, 52 }, { 50, 93 },
    //         { 50, 94 }, { 51, 52 }, { 51, 92 }, { 53, 54 }, { 53, 55 }, { 54, 55 }, { 56, 58 },
    //         { 56, 69 }, { 56, 70 }, { 56, 98 }, { 57, 58 }, { 57, 68 }, { 59, 60 }, { 59, 61 },
    //         { 60, 75 }, { 60, 76 }, { 61, 74 }, { 62, 63 }, { 62, 64 }, { 63, 64 }, { 63, 66 },
    //         { 63, 67 }, { 65, 66 }, { 65, 67 }, { 66, 67 }, { 68, 69 }, { 68, 70 }, { 69, 70 },
    //         { 71, 72 }, { 71, 73 }, { 72, 73 }, { 72, 96 }, { 72, 97 }, { 74, 75 }, { 74, 76 },
    //         { 75, 76 }, { 77, 78 }, { 77, 79 }, { 78, 90 }, { 78, 91 }, { 79, 81 }, { 79, 82 },
    //         { 79, 89 }, { 80, 81 }, { 80, 82 }, { 80, 84 }, { 80, 85 }, { 81, 82 }, { 83, 84 },
    //         { 83, 85 }, { 84, 85 }, { 86, 87 }, { 86, 88 }, { 87, 88 }, { 89, 90 }, { 89, 91 },
    //         { 90, 91 }, { 92, 93 }, { 92, 94 }, { 93, 94 }, { 95, 96 }, { 95, 97 }, { 96, 97 },
    //         { 98, 99 }, { 98, 100 }, { 99, 100 } };
    //     Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
    //     for (int[] edge : edges)
    //         Graphs.addEdgeWithVertices(g, edge[0], edge[1]);
    //     BrownBacktrackColoring<Integer, DefaultEdge> bbc = new BrownBacktrackColoring<>(g);
    //     assertEquals(4, bbc.getChromaticNumber());
    //     verifyColoring(g, 4, bbc.getColoring());
    // }

    /**
     * jean.col 80 vertices, 508 edges chromatic number: 10
     */
    // @Test
    // @Category(OptionalTests.class)
    // public void jeanTest()
    // {
    //     int[][] edges = { { 1, 14 }, { 2, 37 }, { 2, 75 }, { 2, 14 }, { 3, 54 }, { 3, 46 },
    //         { 3, 37 }, { 3, 28 }, { 3, 5 }, { 3, 60 }, { 3, 57 }, { 3, 44 }, { 3, 63 }, { 3, 40 },
    //         { 3, 69 }, { 3, 25 }, { 3, 27 }, { 3, 73 }, { 3, 33 }, { 4, 50 }, { 4, 79 }, { 4, 7 },
    //         { 4, 72 }, { 4, 47 }, { 4, 19 }, { 4, 34 }, { 4, 68 }, { 4, 9 }, { 4, 66 }, { 5, 22 },
    //         { 5, 10 }, { 5, 20 }, { 5, 39 }, { 5, 17 }, { 5, 37 }, { 5, 28 }, { 5, 3 }, { 6, 57 },
    //         { 6, 16 }, { 6, 48 }, { 6, 72 }, { 6, 37 }, { 6, 35 }, { 6, 55 }, { 6, 58 }, { 6, 28 },
    //         { 7, 15 }, { 7, 47 }, { 7, 50 }, { 7, 4 }, { 7, 79 }, { 7, 9 }, { 7, 66 }, { 7, 38 },
    //         { 7, 34 }, { 7, 72 }, { 7, 68 }, { 7, 19 }, { 8, 72 }, { 8, 56 }, { 9, 37 }, { 9, 38 },
    //         { 9, 35 }, { 9, 28 }, { 9, 47 }, { 9, 50 }, { 9, 19 }, { 9, 79 }, { 9, 66 }, { 9, 7 },
    //         { 9, 72 }, { 9, 4 }, { 9, 68 }, { 9, 15 }, { 9, 34 }, { 10, 5 }, { 10, 37 }, { 10, 22 },
    //         { 10, 20 }, { 10, 39 }, { 10, 17 }, { 11, 42 }, { 11, 72 }, { 12, 14 }, { 13, 43 },
    //         { 14, 37 }, { 14, 80 }, { 14, 41 }, { 14, 65 }, { 14, 32 }, { 14, 24 }, { 14, 12 },
    //         { 14, 2 }, { 14, 75 }, { 14, 1 }, { 15, 79 }, { 15, 72 }, { 15, 7 }, { 15, 19 },
    //         { 15, 48 }, { 15, 57 }, { 15, 33 }, { 15, 37 }, { 15, 59 }, { 15, 68 }, { 15, 9 },
    //         { 15, 34 }, { 15, 66 }, { 15, 26 }, { 15, 38 }, { 15, 76 }, { 15, 23 }, { 15, 77 },
    //         { 15, 29 }, { 16, 6 }, { 16, 35 }, { 16, 48 }, { 16, 72 }, { 16, 57 }, { 16, 55 },
    //         { 16, 58 }, { 17, 22 }, { 17, 10 }, { 17, 20 }, { 17, 5 }, { 17, 39 }, { 17, 37 },
    //         { 18, 44 }, { 18, 57 }, { 18, 48 }, { 19, 15 }, { 19, 38 }, { 19, 68 }, { 19, 50 },
    //         { 19, 79 }, { 19, 9 }, { 19, 34 }, { 19, 72 }, { 19, 47 }, { 19, 4 }, { 19, 66 },
    //         { 19, 7 }, { 20, 5 }, { 20, 37 }, { 20, 22 }, { 20, 10 }, { 20, 39 }, { 20, 17 },
    //         { 22, 5 }, { 22, 37 }, { 22, 10 }, { 22, 20 }, { 22, 39 }, { 22, 17 }, { 23, 15 },
    //         { 23, 77 }, { 24, 14 }, { 25, 3 }, { 25, 63 }, { 25, 40 }, { 25, 69 }, { 25, 27 },
    //         { 25, 73 }, { 25, 33 }, { 26, 59 }, { 26, 77 }, { 26, 15 }, { 26, 29 }, { 27, 3 },
    //         { 27, 63 }, { 27, 40 }, { 27, 69 }, { 27, 25 }, { 27, 73 }, { 27, 33 }, { 28, 30 },
    //         { 28, 59 }, { 28, 72 }, { 28, 9 }, { 28, 6 }, { 28, 35 }, { 28, 55 }, { 28, 44 },
    //         { 28, 58 }, { 28, 64 }, { 28, 57 }, { 28, 46 }, { 28, 31 }, { 28, 3 }, { 28, 5 },
    //         { 28, 37 }, { 28, 43 }, { 29, 37 }, { 29, 59 }, { 29, 15 }, { 29, 77 }, { 29, 26 },
    //         { 29, 36 }, { 29, 45 }, { 30, 37 }, { 30, 28 }, { 30, 59 }, { 31, 28 }, { 31, 37 },
    //         { 32, 14 }, { 33, 59 }, { 33, 15 }, { 33, 3 }, { 33, 63 }, { 33, 40 }, { 33, 69 },
    //         { 33, 25 }, { 33, 27 }, { 33, 73 }, { 34, 47 }, { 34, 50 }, { 34, 19 }, { 34, 79 },
    //         { 34, 48 }, { 34, 38 }, { 34, 7 }, { 34, 72 }, { 34, 4 }, { 34, 68 }, { 34, 9 },
    //         { 34, 66 }, { 34, 15 }, { 35, 9 }, { 35, 16 }, { 35, 48 }, { 35, 6 }, { 35, 28 },
    //         { 35, 44 }, { 35, 37 }, { 35, 55 }, { 35, 58 }, { 35, 57 }, { 36, 29 }, { 37, 29 },
    //         { 37, 77 }, { 37, 66 }, { 37, 9 }, { 37, 72 }, { 37, 30 }, { 37, 6 }, { 37, 35 },
    //         { 37, 55 }, { 37, 58 }, { 37, 15 }, { 37, 78 }, { 37, 64 }, { 37, 57 }, { 37, 44 },
    //         { 37, 59 }, { 37, 22 }, { 37, 10 }, { 37, 20 }, { 37, 5 }, { 37, 39 }, { 37, 17 },
    //         { 37, 31 }, { 37, 61 }, { 37, 46 }, { 37, 3 }, { 37, 28 }, { 37, 43 }, { 37, 53 },
    //         { 37, 70 }, { 37, 14 }, { 37, 75 }, { 37, 2 }, { 37, 67 }, { 37, 60 }, { 37, 62 },
    //         { 38, 79 }, { 38, 72 }, { 38, 19 }, { 38, 66 }, { 38, 9 }, { 38, 7 }, { 38, 34 },
    //         { 38, 68 }, { 38, 48 }, { 38, 52 }, { 38, 15 }, { 39, 22 }, { 39, 10 }, { 39, 20 },
    //         { 39, 5 }, { 39, 17 }, { 39, 37 }, { 40, 3 }, { 40, 63 }, { 40, 69 }, { 40, 25 },
    //         { 40, 27 }, { 40, 73 }, { 40, 33 }, { 41, 14 }, { 42, 11 }, { 42, 72 }, { 43, 13 },
    //         { 43, 78 }, { 43, 28 }, { 43, 37 }, { 44, 74 }, { 44, 28 }, { 44, 35 }, { 44, 55 },
    //         { 44, 58 }, { 44, 18 }, { 44, 48 }, { 44, 37 }, { 44, 59 }, { 44, 57 }, { 44, 3 },
    //         { 45, 76 }, { 45, 29 }, { 46, 28 }, { 46, 3 }, { 46, 37 }, { 46, 54 }, { 47, 9 },
    //         { 47, 72 }, { 47, 34 }, { 47, 7 }, { 47, 4 }, { 47, 19 }, { 47, 66 }, { 48, 34 },
    //         { 48, 6 }, { 48, 35 }, { 48, 55 }, { 48, 16 }, { 48, 58 }, { 48, 38 }, { 48, 15 },
    //         { 48, 57 }, { 48, 44 }, { 48, 18 }, { 50, 68 }, { 50, 4 }, { 50, 19 }, { 50, 79 },
    //         { 50, 9 }, { 50, 66 }, { 50, 34 }, { 50, 7 }, { 50, 72 }, { 51, 57 }, { 52, 38 },
    //         { 53, 37 }, { 54, 3 }, { 54, 46 }, { 55, 48 }, { 55, 72 }, { 55, 16 }, { 55, 6 },
    //         { 55, 28 }, { 55, 44 }, { 55, 37 }, { 55, 35 }, { 55, 58 }, { 55, 57 }, { 56, 8 },
    //         { 57, 6 }, { 57, 72 }, { 57, 16 }, { 57, 35 }, { 57, 55 }, { 57, 58 }, { 57, 18 },
    //         { 57, 59 }, { 57, 48 }, { 57, 15 }, { 57, 28 }, { 57, 37 }, { 57, 51 }, { 57, 76 },
    //         { 57, 3 }, { 57, 44 }, { 58, 48 }, { 58, 72 }, { 58, 16 }, { 58, 6 }, { 58, 28 },
    //         { 58, 44 }, { 58, 37 }, { 58, 35 }, { 58, 55 }, { 58, 57 }, { 59, 29 }, { 59, 77 },
    //         { 59, 28 }, { 59, 30 }, { 59, 26 }, { 59, 57 }, { 59, 33 }, { 59, 15 }, { 59, 64 },
    //         { 59, 37 }, { 59, 44 }, { 60, 3 }, { 60, 37 }, { 61, 37 }, { 62, 37 }, { 63, 3 },
    //         { 63, 40 }, { 63, 69 }, { 63, 25 }, { 63, 27 }, { 63, 73 }, { 63, 33 }, { 64, 28 },
    //         { 64, 59 }, { 64, 37 }, { 65, 14 }, { 66, 37 }, { 66, 38 }, { 66, 68 }, { 66, 50 },
    //         { 66, 79 }, { 66, 9 }, { 66, 7 }, { 66, 72 }, { 66, 47 }, { 66, 19 }, { 66, 4 },
    //         { 66, 34 }, { 66, 15 }, { 67, 37 }, { 68, 19 }, { 68, 66 }, { 68, 79 }, { 68, 50 },
    //         { 68, 38 }, { 68, 7 }, { 68, 72 }, { 68, 4 }, { 68, 15 }, { 68, 9 }, { 68, 34 },
    //         { 69, 3 }, { 69, 63 }, { 69, 40 }, { 69, 25 }, { 69, 27 }, { 69, 73 }, { 69, 33 },
    //         { 70, 37 }, { 72, 37 }, { 72, 15 }, { 72, 38 }, { 72, 28 }, { 72, 47 }, { 72, 50 },
    //         { 72, 4 }, { 72, 19 }, { 72, 79 }, { 72, 9 }, { 72, 66 }, { 72, 7 }, { 72, 34 },
    //         { 72, 68 }, { 72, 57 }, { 72, 55 }, { 72, 16 }, { 72, 58 }, { 72, 6 }, { 72, 11 },
    //         { 72, 42 }, { 72, 8 }, { 73, 3 }, { 73, 63 }, { 73, 40 }, { 73, 69 }, { 73, 25 },
    //         { 73, 27 }, { 73, 33 }, { 74, 44 }, { 74, 77 }, { 75, 37 }, { 75, 2 }, { 75, 14 },
    //         { 76, 15 }, { 76, 45 }, { 76, 57 }, { 77, 37 }, { 77, 59 }, { 77, 26 }, { 77, 23 },
    //         { 77, 15 }, { 77, 29 }, { 77, 74 }, { 78, 37 }, { 78, 43 }, { 79, 15 }, { 79, 38 },
    //         { 79, 68 }, { 79, 50 }, { 79, 4 }, { 79, 19 }, { 79, 9 }, { 79, 66 }, { 79, 34 },
    //         { 79, 7 }, { 79, 72 }, { 80, 14 } };
    //     Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
    //     for (int[] edge : edges)
    //         Graphs.addEdgeWithVertices(g, edge[0], edge[1]);
    //     BrownBacktrackColoring<Integer, DefaultEdge> bbc = new BrownBacktrackColoring<>(g);
    //     assertEquals(10, bbc.getChromaticNumber());
    //     verifyColoring(g, 10, bbc.getColoring());
    // }

    /**
     * huck.col 74 vertices, 602 edges chromatic number: 11
     */
    @Test
    public void huckTest()
    {
        int[][] edges = { { 1, 44 }, { 1, 4 }, { 1, 69 }, { 1, 59 }, { 1, 13 }, { 1, 29 },
            { 1, 40 }, { 1, 11 }, { 1, 50 }, { 1, 5 }, { 1, 25 }, { 1, 10 }, { 1, 63 }, { 1, 22 },
            { 1, 9 }, { 1, 55 }, { 1, 72 }, { 1, 49 }, { 2, 46 }, { 2, 55 }, { 2, 62 }, { 2, 4 },
            { 2, 74 }, { 2, 43 }, { 2, 51 }, { 2, 57 }, { 2, 41 }, { 3, 53 }, { 3, 52 }, { 3, 55 },
            { 3, 9 }, { 4, 69 }, { 4, 1 }, { 4, 9 }, { 4, 22 }, { 4, 49 }, { 4, 63 }, { 4, 68 },
            { 4, 60 }, { 4, 19 }, { 4, 56 }, { 4, 27 }, { 4, 46 }, { 4, 55 }, { 4, 2 }, { 4, 73 },
            { 4, 43 }, { 4, 62 }, { 4, 47 }, { 4, 67 }, { 4, 12 }, { 4, 57 }, { 4, 32 }, { 4, 71 },
            { 4, 45 }, { 4, 41 }, { 5, 59 }, { 5, 13 }, { 5, 29 }, { 5, 40 }, { 5, 11 }, { 5, 50 },
            { 5, 25 }, { 5, 49 }, { 5, 1 }, { 5, 55 }, { 6, 64 }, { 6, 23 }, { 6, 21 }, { 6, 17 },
            { 6, 34 }, { 6, 39 }, { 6, 20 }, { 6, 55 }, { 7, 70 }, { 8, 14 }, { 8, 44 }, { 8, 9 },
            { 8, 52 }, { 8, 38 }, { 8, 31 }, { 8, 18 }, { 9, 69 }, { 9, 4 }, { 9, 72 }, { 9, 10 },
            { 9, 66 }, { 9, 1 }, { 9, 49 }, { 9, 8 }, { 9, 14 }, { 9, 44 }, { 9, 38 }, { 9, 31 },
            { 9, 18 }, { 9, 3 }, { 9, 53 }, { 9, 52 }, { 9, 22 }, { 9, 55 }, { 10, 72 }, { 10, 1 },
            { 10, 49 }, { 10, 55 }, { 10, 9 }, { 11, 59 }, { 11, 13 }, { 11, 29 }, { 11, 40 },
            { 11, 50 }, { 11, 5 }, { 11, 25 }, { 11, 49 }, { 11, 1 }, { 11, 55 }, { 12, 55 },
            { 12, 73 }, { 12, 43 }, { 12, 62 }, { 12, 4 }, { 12, 47 }, { 12, 67 }, { 12, 57 },
            { 12, 41 }, { 13, 59 }, { 13, 29 }, { 13, 40 }, { 13, 11 }, { 13, 50 }, { 13, 5 },
            { 13, 25 }, { 13, 49 }, { 13, 1 }, { 13, 55 }, { 14, 8 }, { 14, 44 }, { 14, 9 },
            { 14, 52 }, { 14, 38 }, { 14, 31 }, { 14, 18 }, { 15, 22 }, { 15, 55 }, { 16, 65 },
            { 16, 55 }, { 17, 28 }, { 17, 30 }, { 17, 64 }, { 17, 23 }, { 17, 21 }, { 17, 6 },
            { 17, 34 }, { 17, 39 }, { 17, 20 }, { 17, 55 }, { 18, 22 }, { 18, 8 }, { 18, 14 },
            { 18, 44 }, { 18, 9 }, { 18, 52 }, { 18, 38 }, { 18, 24 }, { 18, 31 }, { 18, 55 },
            { 19, 68 }, { 19, 55 }, { 19, 60 }, { 19, 73 }, { 19, 4 }, { 19, 56 }, { 19, 27 },
            { 19, 57 }, { 19, 41 }, { 20, 64 }, { 20, 23 }, { 20, 21 }, { 20, 17 }, { 20, 6 },
            { 20, 34 }, { 20, 39 }, { 20, 55 }, { 21, 64 }, { 21, 23 }, { 21, 17 }, { 21, 6 },
            { 21, 34 }, { 21, 39 }, { 21, 20 }, { 21, 55 }, { 22, 18 }, { 22, 44 }, { 22, 69 },
            { 22, 49 }, { 22, 4 }, { 22, 66 }, { 22, 63 }, { 22, 1 }, { 22, 61 }, { 22, 26 },
            { 22, 57 }, { 22, 41 }, { 22, 15 }, { 22, 42 }, { 22, 55 }, { 22, 9 }, { 23, 30 },
            { 23, 64 }, { 23, 21 }, { 23, 17 }, { 23, 6 }, { 23, 34 }, { 23, 39 }, { 23, 20 },
            { 23, 55 }, { 24, 18 }, { 24, 31 }, { 24, 42 }, { 24, 55 }, { 25, 59 }, { 25, 13 },
            { 25, 29 }, { 25, 40 }, { 25, 11 }, { 25, 50 }, { 25, 5 }, { 25, 49 }, { 25, 1 },
            { 25, 55 }, { 26, 22 }, { 27, 68 }, { 27, 55 }, { 27, 60 }, { 27, 19 }, { 27, 73 },
            { 27, 57 }, { 27, 41 }, { 27, 4 }, { 27, 56 }, { 27, 62 }, { 28, 17 }, { 29, 59 },
            { 29, 13 }, { 29, 40 }, { 29, 11 }, { 29, 50 }, { 29, 5 }, { 29, 25 }, { 29, 49 },
            { 29, 1 }, { 29, 55 }, { 30, 23 }, { 30, 55 }, { 30, 17 }, { 31, 8 }, { 31, 14 },
            { 31, 44 }, { 31, 9 }, { 31, 52 }, { 31, 38 }, { 31, 24 }, { 31, 18 }, { 31, 55 },
            { 32, 4 }, { 32, 71 }, { 33, 49 }, { 34, 64 }, { 34, 23 }, { 34, 21 }, { 34, 17 },
            { 34, 6 }, { 34, 39 }, { 34, 20 }, { 34, 55 }, { 35, 48 }, { 35, 58 }, { 36, 60 },
            { 36, 55 }, { 36, 57 }, { 36, 41 }, { 37, 72 }, { 37, 49 }, { 38, 8 }, { 38, 14 },
            { 38, 44 }, { 38, 9 }, { 38, 52 }, { 38, 31 }, { 38, 18 }, { 39, 64 }, { 39, 23 },
            { 39, 21 }, { 39, 17 }, { 39, 6 }, { 39, 34 }, { 39, 20 }, { 39, 55 }, { 40, 59 },
            { 40, 13 }, { 40, 29 }, { 40, 11 }, { 40, 50 }, { 40, 5 }, { 40, 25 }, { 40, 49 },
            { 40, 1 }, { 40, 55 }, { 41, 68 }, { 41, 60 }, { 41, 19 }, { 41, 73 }, { 41, 56 },
            { 41, 27 }, { 41, 46 }, { 41, 2 }, { 41, 74 }, { 41, 43 }, { 41, 51 }, { 41, 62 },
            { 41, 47 }, { 41, 67 }, { 41, 12 }, { 41, 36 }, { 41, 4 }, { 41, 57 }, { 41, 22 },
            { 41, 55 }, { 42, 22 }, { 42, 55 }, { 42, 24 }, { 43, 47 }, { 43, 67 }, { 43, 12 },
            { 43, 73 }, { 43, 4 }, { 43, 2 }, { 43, 74 }, { 43, 51 }, { 43, 57 }, { 43, 41 },
            { 44, 22 }, { 44, 1 }, { 44, 55 }, { 44, 49 }, { 44, 8 }, { 44, 14 }, { 44, 9 },
            { 44, 52 }, { 44, 38 }, { 44, 31 }, { 44, 18 }, { 45, 4 }, { 45, 71 }, { 46, 55 },
            { 46, 62 }, { 46, 4 }, { 46, 57 }, { 46, 41 }, { 46, 2 }, { 47, 55 }, { 47, 73 },
            { 47, 43 }, { 47, 62 }, { 47, 4 }, { 47, 67 }, { 47, 12 }, { 47, 57 }, { 47, 41 },
            { 48, 35 }, { 48, 58 }, { 49, 44 }, { 49, 22 }, { 49, 69 }, { 49, 59 }, { 49, 13 },
            { 49, 29 }, { 49, 40 }, { 49, 11 }, { 49, 50 }, { 49, 5 }, { 49, 25 }, { 49, 4 },
            { 49, 33 }, { 49, 10 }, { 49, 9 }, { 49, 37 }, { 49, 1 }, { 49, 55 }, { 49, 72 },
            { 50, 59 }, { 50, 13 }, { 50, 29 }, { 50, 40 }, { 50, 11 }, { 50, 5 }, { 50, 25 },
            { 50, 49 }, { 50, 1 }, { 50, 55 }, { 51, 2 }, { 51, 74 }, { 51, 43 }, { 51, 57 },
            { 51, 41 }, { 52, 8 }, { 52, 14 }, { 52, 44 }, { 52, 38 }, { 52, 31 }, { 52, 18 },
            { 52, 3 }, { 52, 53 }, { 52, 55 }, { 52, 9 }, { 53, 3 }, { 53, 52 }, { 53, 55 },
            { 53, 9 }, { 54, 55 }, { 55, 44 }, { 55, 59 }, { 55, 13 }, { 55, 29 }, { 55, 40 },
            { 55, 11 }, { 55, 50 }, { 55, 5 }, { 55, 25 }, { 55, 69 }, { 55, 10 }, { 55, 66 },
            { 55, 1 }, { 55, 72 }, { 55, 49 }, { 55, 68 }, { 55, 60 }, { 55, 19 }, { 55, 73 },
            { 55, 56 }, { 55, 27 }, { 55, 46 }, { 55, 4 }, { 55, 2 }, { 55, 62 }, { 55, 47 },
            { 55, 67 }, { 55, 12 }, { 55, 36 }, { 55, 57 }, { 55, 41 }, { 55, 15 }, { 55, 30 },
            { 55, 64 }, { 55, 23 }, { 55, 21 }, { 55, 17 }, { 55, 6 }, { 55, 34 }, { 55, 39 },
            { 55, 20 }, { 55, 65 }, { 55, 16 }, { 55, 54 }, { 55, 18 }, { 55, 31 }, { 55, 3 },
            { 55, 53 }, { 55, 52 }, { 55, 22 }, { 55, 42 }, { 55, 24 }, { 55, 9 }, { 56, 68 },
            { 56, 55 }, { 56, 60 }, { 56, 19 }, { 56, 73 }, { 56, 57 }, { 56, 41 }, { 56, 4 },
            { 56, 27 }, { 57, 68 }, { 57, 60 }, { 57, 19 }, { 57, 73 }, { 57, 56 }, { 57, 27 },
            { 57, 46 }, { 57, 2 }, { 57, 74 }, { 57, 43 }, { 57, 51 }, { 57, 62 }, { 57, 47 },
            { 57, 67 }, { 57, 12 }, { 57, 36 }, { 57, 4 }, { 57, 41 }, { 57, 22 }, { 57, 55 },
            { 58, 35 }, { 58, 48 }, { 59, 13 }, { 59, 29 }, { 59, 40 }, { 59, 11 }, { 59, 50 },
            { 59, 5 }, { 59, 25 }, { 59, 49 }, { 59, 1 }, { 59, 55 }, { 60, 36 }, { 60, 68 },
            { 60, 55 }, { 60, 19 }, { 60, 73 }, { 60, 4 }, { 60, 56 }, { 60, 27 }, { 60, 57 },
            { 60, 41 }, { 61, 22 }, { 62, 46 }, { 62, 2 }, { 62, 55 }, { 62, 4 }, { 62, 47 },
            { 62, 67 }, { 62, 12 }, { 62, 57 }, { 62, 41 }, { 62, 27 }, { 63, 4 }, { 63, 1 },
            { 63, 22 }, { 64, 23 }, { 64, 21 }, { 64, 17 }, { 64, 6 }, { 64, 34 }, { 64, 39 },
            { 64, 20 }, { 64, 55 }, { 65, 16 }, { 65, 55 }, { 66, 22 }, { 66, 55 }, { 66, 9 },
            { 67, 55 }, { 67, 73 }, { 67, 43 }, { 67, 62 }, { 67, 4 }, { 67, 47 }, { 67, 12 },
            { 67, 57 }, { 67, 41 }, { 68, 55 }, { 68, 60 }, { 68, 19 }, { 68, 73 }, { 68, 4 },
            { 68, 56 }, { 68, 27 }, { 68, 57 }, { 68, 41 }, { 69, 4 }, { 69, 22 }, { 69, 9 },
            { 69, 1 }, { 69, 49 }, { 69, 55 }, { 70, 7 }, { 71, 32 }, { 71, 4 }, { 71, 45 },
            { 72, 10 }, { 72, 9 }, { 72, 37 }, { 72, 1 }, { 72, 55 }, { 72, 49 }, { 73, 68 },
            { 73, 55 }, { 73, 60 }, { 73, 19 }, { 73, 56 }, { 73, 27 }, { 73, 57 }, { 73, 41 },
            { 73, 47 }, { 73, 67 }, { 73, 12 }, { 73, 43 }, { 73, 4 }, { 74, 2 }, { 74, 43 },
            { 74, 51 }, { 74, 57 }, { 74, 41 } };
        Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        for (int[] edge : edges)
            Graphs.addEdgeWithVertices(g, edge[0], edge[1]);
        BrownBacktrackColoring<Integer, DefaultEdge> bbc = new BrownBacktrackColoring<>(g);
        assertEquals(11, bbc.getChromaticNumber());
        verifyColoring(g, 11, bbc.getColoring());
    }

    /**
     * 2-Insertions_3.col 37 vertices, 72 edges chromatic number: 4
     */
    @Test
    public void mugg100Test()
    {
        int[][] edges = { { 1, 2 }, { 1, 4 }, { 1, 11 }, { 1, 13 }, { 2, 3 }, { 2, 10 }, { 2, 12 },
            { 3, 6 }, { 3, 11 }, { 3, 15 }, { 4, 5 }, { 4, 10 }, { 4, 14 }, { 5, 8 }, { 5, 13 },
            { 5, 17 }, { 6, 7 }, { 6, 12 }, { 6, 16 }, { 7, 9 }, { 7, 15 }, { 7, 18 }, { 8, 9 },
            { 8, 14 }, { 8, 18 }, { 9, 16 }, { 9, 17 }, { 10, 20 }, { 10, 22 }, { 11, 19 },
            { 11, 21 }, { 12, 20 }, { 12, 24 }, { 13, 19 }, { 13, 23 }, { 14, 22 }, { 14, 26 },
            { 15, 21 }, { 15, 25 }, { 16, 24 }, { 16, 27 }, { 17, 23 }, { 17, 27 }, { 18, 25 },
            { 18, 26 }, { 19, 29 }, { 19, 31 }, { 20, 28 }, { 20, 30 }, { 21, 29 }, { 21, 33 },
            { 22, 28 }, { 22, 32 }, { 23, 31 }, { 23, 35 }, { 24, 30 }, { 24, 34 }, { 25, 33 },
            { 25, 36 }, { 26, 32 }, { 26, 36 }, { 27, 34 }, { 27, 35 }, { 28, 37 }, { 29, 37 },
            { 30, 37 }, { 31, 37 }, { 32, 37 }, { 33, 37 }, { 34, 37 }, { 35, 37 }, { 36, 37 } };
        Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        for (int[] edge : edges)
            Graphs.addEdgeWithVertices(g, edge[0], edge[1]);
        BrownBacktrackColoring<Integer, DefaultEdge> bbc = new BrownBacktrackColoring<>(g);
        assertEquals(4, bbc.getChromaticNumber());
        verifyColoring(g, 4, bbc.getColoring());
    }

    private static <V, E> void verifyColoring(
        Graph<V, E> g, int chromaticNumber, VertexColoringAlgorithm.Coloring<V> coloring)
    {
        Map<V, Integer> colorAssignment = coloring.getColors();
        List<Set<V>> colorClasses = coloring.getColorClasses();

        // check chromatic number
        assertEquals(chromaticNumber, coloring.getNumberColors());
        assertEquals(chromaticNumber, new HashSet<>(colorAssignment.values()).size());
        assertEquals(chromaticNumber, colorClasses.size());

        // All vertices are assigned a color
        assertEquals(g.vertexSet(), colorAssignment.keySet());

        // Neighbors cannot have the same color
        for (E e : g.edgeSet())
            assertNotEquals(
                colorAssignment.get(g.getEdgeSource(e)), colorAssignment.get(g.getEdgeTarget(e)));

        // All vertices in the same color class must have the same color
        for (Set<V> colorClass : colorClasses)
            assertEquals(1, colorClass.stream().mapToInt(colorAssignment::get).distinct().count());
    }
}
