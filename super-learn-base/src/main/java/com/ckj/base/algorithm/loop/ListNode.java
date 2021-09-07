package com.ckj.base.algorithm.loop;

import lombok.Data;

import java.util.List;

/**
 * @author c.kj
 * @Description
 * @Date 2021/8/23
 * @Time 1:54 PM
 **/
@Data
public class ListNode {

    int val;

    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    ListNode(List<Integer> list) {
        final ListNode[] currentNode = {this};
        list.forEach(g -> {
            ListNode next = new ListNode(g);
            currentNode[0].next = next;
            currentNode[0] = next;
        });
    }
}
