package com.ckj.base.algorithm.loop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author c.kj
 * @Description 合并两个有序链表
 * @Date 2021/8/23
 * @Time 9:39 AM
 **/
@Slf4j
public class MergeListCore {


    /**
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     * @param args
     */
    public static void main(String[] args) {

        ListNode listNodeA = new ListNode(Arrays.asList(1, 2, 4));

        ListNode listNodeB = new ListNode(Arrays.asList(1, 3, 4));

        /**
         * 递推公式:
         * l1.val < l2.val : merge(l1[2]:,l2)
         * otherwise : merge(l2[1],l1)
         */

        ListNode listNode = mergeNode(listNodeA, listNodeB);

        log.info(JSON.toJSONString(listNode));

    }

    static ListNode mergeNode(ListNode nodeA, ListNode nodeB) {

        if (nodeA == null) {
            return nodeB;
        }
        if (nodeB == null) {
            return nodeA;
        }
        if (nodeA.val < nodeB.val) {
            nodeA.next = mergeNode(nodeA.next, nodeB);
            return nodeA;
        } else {
            nodeB.next = mergeNode(nodeB.next, nodeA);
            return nodeB;
        }

    }


}
