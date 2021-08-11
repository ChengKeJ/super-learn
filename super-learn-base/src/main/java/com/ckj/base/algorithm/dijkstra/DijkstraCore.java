package com.ckj.base.algorithm.dijkstra;

import com.ckj.base.algorithm.graph.Edge;
import com.ckj.base.algorithm.graph.Vertex;
import lombok.Data;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author c.kj
 * @Description Dijkstra
 * @Date 2021/8/8
 * @Time 11:02 AM
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Fork(1)
public class DijkstraCore {

    private Vertex<String> vertexA;


    public HashMap<Vertex, Integer> dijkstra(Vertex from) {
        // 从 from 出发到所有点的最小距离
        // key 从from 出发到达key
        // value 从from 出发到达key的最小距离
        // 如果在表中，没有T的记录，含义是从from 出发到T的距离为正无穷
        HashMap<Vertex, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HashSet<Vertex> selectedNode = new HashSet<>();
        Vertex minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNode);
        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);
            Iterator<Edge> edgeIterators = minNode.getEdgeIterator();
            while (edgeIterators.hasNext()) {
                Edge next = edgeIterators.next();
                Vertex endVertex = next.getEndVertex();
                double v = next.getWeight();
                if (distanceMap.containsKey(endVertex)) {
                    double v1 = Math.min(distanceMap.get(endVertex), v + distance);
                    distanceMap.put(endVertex, (int) (v1));
                } else {
                    distanceMap.put(endVertex, (int) (distance + v));
                }
            }
            selectedNode.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNode);
        }
        return distanceMap;
    }

    private Vertex getMinDistanceAndUnselectedNode(HashMap<Vertex, Integer> distanceMap, HashSet<Vertex> selectedNode) {
        final Vertex[] vertex = {null};
        final int[] minDistance = {Integer.MAX_VALUE};
        distanceMap.forEach((k, v) -> {
            if (!selectedNode.contains(k) && v < minDistance[0]) {
                vertex[0] = k;
                minDistance[0] = v;
            }
        });
        return vertex[0];
    }


    /**
     * 利用优先级队列 实现的 Dijkstra 降低时间复杂度O
     */

    HashMap<Vertex, Integer> distraQueue(Vertex from) {
        // this map -> from to current vertex distance
        HashMap<Vertex, Integer> distanceMap = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        distanceMap.put(from, 0);
        queue.add(new Node(from, 0));
        // 寻找路径最短的顶点继续寻找
        final Node[] minNode = {queue.poll()};
        while (minNode[0] != null) {
            Optional.of(minNode[0]).ifPresent(f -> {
                f.getVertex().getEdgeList().stream().forEach(t -> {
                    Edge edge = (Edge) t;
                    if (distanceMap.containsKey(edge.getEndVertex())) {
                        int min = Math.min((int) edge.getWeight() + f.getDistance(), distanceMap.get(edge.getEndVertex()));
                        distanceMap.put(edge.getEndVertex(), min);
                        queue.add(new Node(edge.getEndVertex(), min));
                    } else {
                        distanceMap.put(edge.getEndVertex(), (int) edge.getWeight() + f.getDistance());
                        queue.add(new Node(edge.getEndVertex(), (int) edge.getWeight() + f.getDistance()));
                    }
                });
                minNode[0] = queue.poll();
            });

        }
        return distanceMap;
    }


    @Data
    static class Node implements Comparable {
        Vertex vertex;
        Integer distance;

        Node(Vertex vertex, Integer distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Node) {
                Integer distanceobj = ((Node) o).distance;
                if (this.distance > distanceobj) {
                    return 1;
                } else if (this.distance.equals(distanceobj)) {
                    return 0;
                } else {
                    return -1;
                }
            }
            return 0;
        }
    }

    @Setup
    public void prepare() {
        Vertex<String> vertexA = new Vertex<>("A", 0);
        Vertex<String> vertexB = new Vertex<>("B", 0);
        Vertex<String> vertexC = new Vertex<>("C", 0);
        Vertex<String> vertexD = new Vertex<>("D", 0);
        Vertex<String> vertexE = new Vertex<>("E", 0);
        Edge edgeAB = new Edge(vertexA, vertexB, 3);
        Edge edgeAC = new Edge(vertexA, vertexC, 4);
        Edge edgeAD = new Edge(vertexA, vertexD, 5);
        vertexA.setEdgeList(Arrays.asList(edgeAB, edgeAC, edgeAD));
        Edge edgeCB = new Edge(vertexC, vertexB, 1);
        Edge edgeCE = new Edge(vertexC, vertexE, 3);
        vertexC.setEdgeList(Arrays.asList(edgeCB, edgeCE));
        Edge edgeBE = new Edge(vertexB, vertexE, 1);
        vertexB.setEdgeList(Arrays.asList(edgeBE));
        Edge edgeDE = new Edge(vertexD, vertexE, 2);
        vertexD.setEdgeList(Arrays.asList(edgeDE));

        this.vertexA = vertexA;
    }


    @Benchmark
    @Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MICROSECONDS)
    @Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MICROSECONDS)
    public int dijkstraQueue() throws InterruptedException {
        /**
         * Benchmark                   Mode  Cnt    Score     Error  Units
         * DijkstraCore.dijkstraQueue  avgt    5  472.235 ± 336.026   us/op
         */
        DijkstraCore dijkstraCore = new DijkstraCore();
        HashMap<Vertex, Integer> dijkstra = dijkstraCore.distraQueue(vertexA);
        dijkstra.forEach((k, v) -> System.out.println("dijkstraQueue from start to " + k.getLabel() + " 最短距离 distance is " + v));
        return 0;

    }

    @Benchmark
    @Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MICROSECONDS)
    @Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MICROSECONDS)
    public int dijkstra() throws InterruptedException {
        /**
         * Benchmark                   Mode  Cnt    Score     Error  Units
         * DijkstraCore.dijkstraQueue  avgt    5  369.093 ± 216.399  us/op
         */
        DijkstraCore dijkstraCore = new DijkstraCore();
        HashMap<Vertex, Integer> dijkstra = dijkstraCore.dijkstra(vertexA);
        dijkstra.forEach((k, v) -> System.out.println("dijkstra from start to " + k.getLabel() + " 最短距离 distance is " + v));
        return 0;
    }


    public static void main(String[] args) {
        Vertex<String> vertexA = new Vertex<>("A", 0);
        Vertex<String> vertexB = new Vertex<>("B", 0);
        Vertex<String> vertexC = new Vertex<>("C", 0);
        Vertex<String> vertexD = new Vertex<>("D", 0);
        Vertex<String> vertexE = new Vertex<>("E", 0);
        Edge edgeAB = new Edge(vertexA, vertexB, 3);
        Edge edgeAC = new Edge(vertexA, vertexC, 4);
        Edge edgeAD = new Edge(vertexA, vertexD, 5);
        vertexA.setEdgeList(Arrays.asList(edgeAB, edgeAC, edgeAD));
        Edge edgeCB = new Edge(vertexC, vertexB, 1);
        Edge edgeCE = new Edge(vertexC, vertexE, 3);
        vertexC.setEdgeList(Arrays.asList(edgeCB, edgeCE));
        Edge edgeBE = new Edge(vertexB, vertexE, 1);
        vertexB.setEdgeList(Arrays.asList(edgeBE));
        Edge edgeDE = new Edge(vertexD, vertexE, 2);
        vertexD.setEdgeList(Arrays.asList(edgeDE));
        DijkstraCore dijkstraCore = new DijkstraCore();
        HashMap<Vertex, Integer> dijkstra = dijkstraCore.distraQueue(vertexA);
        dijkstra.forEach((k, v) -> System.out.println("from start to " + k.getLabel() + " 最短距离 distance is " + v));

        HashMap<Vertex, Integer> dijkstra2 = dijkstraCore.dijkstra(vertexA);
        dijkstra2.forEach((k, v) -> System.out.println("from start2 to " + k.getLabel() + " 最短距离 distance is " + v));


    }


}
