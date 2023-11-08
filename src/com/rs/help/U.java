package com.rs.help;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class U {
    /**
     * //3->2->1->0->1->2->3
     *
     * @param tree
     * @return
     */
    public static ListNode DEFAULT_LINK_LIST = null;
    public static TreeNode DEFAULT_BI_TREE = null;

    static {
        DEFAULT_LINK_LIST = toListNode("1->2->3->4->5");
        DEFAULT_BI_TREE = toTreeNode("1 2 3 4 5 6 7");
    }

    public static ListNode toListNode(String dataString) {
        //3->2->1->0->1->2->3
        String D = null;
        if (dataString.indexOf("->") > 0) {
            D = "->";
        } else if (dataString.indexOf(",") > 0) {
            D = ",";
        }
        ListNode head = null;
        ListNode cur = null;
        String[] nodes = dataString.split(D);
        for (String data : nodes) {
            ListNode newNode = new ListNode(Integer.valueOf(data));
            if (head == null) {
                head = newNode;
                cur = newNode;
            } else {
                cur.next = newNode;
                cur = newNode;
            }

        }
        return head;
    }

    public static void swap(int[] obj, int i, int j) {
        int k = obj[i];
        obj[i] = obj[j];
        obj[j] = k;
    }

    public static TreeNode insertLevelOrder(String[] arr, TreeNode root, int i) {
        if (i < arr.length) {
            root = "#".equals(arr[i]) ? null : new TreeNode(Integer.parseInt(arr[i]));
            if (root != null) {
                // insert left child
                root.left = insertLevelOrder(arr, root.left,
                        2 * i + 1);

                // insert right child
                root.right = insertLevelOrder(arr, root.right,
                        2 * i + 2);
            }
        }
        return root;
    }

    /**
     * @return
     */
    public static TreeNode toTreeNode(String nodeString) {
        String[] data = nodeString.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(data[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean isLeftSet = false;
        for (int i = 1; i < data.length; i++) {
            TreeNode node = data[i].equals("#") ? null : new TreeNode(Integer.parseInt(data[i]));
            TreeNode rootNode = queue.peek();
            if(!isLeftSet){
                rootNode.left = node;
                isLeftSet = !isLeftSet;
            }else{
                rootNode.right = node;
                isLeftSet = !isLeftSet;
                queue.remove();
            }

            if(node != null){
                queue.add(node);
            }
        }

        return root;
    }

    public static ArrayList<ArrayList<Integer>> printLevel(TreeNode root) {
        if (root == null) return new ArrayList<ArrayList<Integer>>();
        Queue<TreeNode> current = new LinkedList<>();
        Queue<TreeNode> level = new LinkedList<>();

        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        current.add(root);
        ArrayList<Integer> lav = new ArrayList<Integer>();
        while (!current.isEmpty()) {
            TreeNode node = current.remove();
            lav.add(node.data);
            if (node.left != null) level.add(node.left);
            if (node.right != null) level.add(node.right);

            if (current.isEmpty()) {
                list.add(lav);
                current = level;
                level = new LinkedList<>();
                lav = new ArrayList<>();
            }
        }
        return list;
    }

    public static void printM(int matrix[][]) {
        for (int i = 0; i < matrix.length * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
        for (int i = 0; i < matrix.length * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printM(char matrix[][]) {
        for (int i = 0; i < matrix.length * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
        for (int i = 0; i < matrix.length * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static int[][] toM(String matString) {
        String[] rows = matString.split(";");

        int numRows = rows.length;
        int numCols = rows[0].split(",").length;

        int[][] matrix = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] rowValues = rows[i].split(",");
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = Integer.parseInt(rowValues[j]);
            }
        }

        return matrix;
    }

    public static char[][] toCharM(String matString) {
        String[] rows = matString.split(";");
        int numRows = rows.length;
        int numCols = rows[0].split(",").length;
        char[][] matrix = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] rowValues = rows[i].split(",");
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = rowValues[j].charAt(0);
            }
        }

        return matrix;
    }


    public static String toS(Object a) {
        if (a instanceof Object[])
            return Arrays.deepToString((Object[]) a);
        if (a instanceof String[][])
            return Arrays.deepToString((String[][]) a);
        if (a instanceof String[])
            return Arrays.deepToString((String[]) a);
        if (a instanceof int[])
            return Arrays.toString((int[]) a);
        if (a instanceof int[][])
            return Arrays.deepToString((int[][]) a);
        return a.toString();
    }
}
