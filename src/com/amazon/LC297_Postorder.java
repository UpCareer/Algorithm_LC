//package com.lc.az;
//
//import java.util.LinkedList;
//
//public class LC297_Postorder {
//    String SEP = ",";
//    String NULL = "#";
//
//    String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        serialize(root, sb);
//        return sb.toString();
//    }
//
//    void serialize(TreeNode root, StringBuilder sb) {
//        if (root == null) {
//            sb.append(NULL).append(SEP);
//            return;
//        }
//        serialize(root.left, sb);
//        serialize(root.right, sb);
//        // Post-order traversal
//        sb.append(root.val).append(SEP);
//    }
//
//    public TreeNode deserialize(String data) {
//        // Convert String to LinkedList
//        LinkedList<String> nodes = new LinkedList<>();
//        for (String s : data.split(SEP)) {
//            nodes.addLast(s);
//        }
//        return deserialize(nodes);
//    }
//
//
//}
