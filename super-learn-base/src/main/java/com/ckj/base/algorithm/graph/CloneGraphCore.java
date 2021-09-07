//package com.ckj.base.algorithm.graph;
//
//import com.google.common.collect.Lists;
//import lombok.Data;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author c.kj
// * @Description 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
// * @Date 2021/8/11
// * @Time 5:37 PM
// **/
//public class CloneGraphCore {
//
//    @Data
//    class Node {
//        public int val;
//        public List<Node> neighbors;
//
//        public Node() {
//            val = 0;
//            neighbors = new ArrayList<Node>();
//        }
//
//        public Node(int _val) {
//            val = _val;
//            neighbors = new ArrayList<Node>();
//        }
//
//        public Node(int _val, ArrayList<Node> _neighbors) {
//            val = _val;
//            neighbors = _neighbors;
//        }
//    }
//
//
//    /**
//     * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
//     *
//     * @param node
//     * @return
//     */
//    public Node cloneGraph(Node node) {
//
//        Node currentNode = node;
//        while (currentNode != null) {
//            while (!currentNode.getNeighbors().isEmpty()) {
//                List<Node> neighborNods = Lists.newArrayList();
//                currentNode.getNeighbors().stream().forEach(f -> {
//                    Node neighborNode = new Node(f.getVal());
//                    neighborNods.add(neighborNode);
//                });
//
//                currentNode.setNeighbors(neighborNods);
//                currentNode = neighborNods.get(0);
//            }
//            currentNode.getNeighbors().remove(currentNode);
//        }
//
//
//        return null;
//    }
//
//    public static void main(String[] args) {
//
//    }
//
//
//}
