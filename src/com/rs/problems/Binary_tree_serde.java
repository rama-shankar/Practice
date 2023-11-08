package com.rs.problems;

import com.rs.help.TreeNode;

import java.util.LinkedList;

public class Binary_tree_serde {
    /**
     * Serializes the binary tree to a string.
     *
     * @param root Root node of the binary tree.
     * @return String representation of a binary tree.
     */
    String serialize(TreeNode root) {
        if(root == null) return null;

        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        StringBuilder content = new StringBuilder();

        while(!nodes.isEmpty()){
            TreeNode node  = nodes.remove();
            if(node == null) content.append("#");
            else{
                content.append(node.data);
                nodes.add(node.left);
                nodes.add(node.right);
            }
            if(nodes.size() > 0){
                content.append(",");
            }
        }

        return content.toString();

    }

    /**
     * Deserializes the binary tree serialized using the
     * serialize method above.
     *
     * @param serialized Serialized string.
     * @return Root TreeNode of the deserialized tree.
     */
    TreeNode deserialize(String serialized) {

        if(serialized == null || serialized == "") return null;

        String [] contents = serialized.split(" ");

        TreeNode root = new TreeNode(Integer.parseInt(contents[0]));

        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        int i = 0;
        while(i < contents.length && !nodes.isEmpty()){
            TreeNode rootNode = nodes.pop();
            if(rootNode == null) continue;

            if(++i < contents.length){
                rootNode.left = "#".equals(contents[i]) ? null : new TreeNode(Integer.parseInt(contents[i]));
                nodes.add( rootNode.left);
            }
            if(++i < contents.length){
                rootNode.right =  "#".equals(contents[i]) ? null : new TreeNode(Integer.parseInt(contents[i]));
                nodes.add( rootNode.right);
            }


        }

        return root;

    }

    public static void main(String[] args) {
        Binary_tree_serde sol = new Binary_tree_serde();
        System.out.println(sol.deserialize("1 2 3 4 5 6 7").toTree());
    }
}
