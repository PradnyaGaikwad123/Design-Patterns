package backupSystem.util;

public class BST implements StdoutDisplayInterface{

    public BST() {
    }
    
    public Node insert(Node root, Node node) {
        if (root == null) {
            return node; // Return the new node as the root.
        } else {
            insertRec(root, node);
            return root; // Return the original root.
        }
    }

    public void insertRec(Node current, Node node) {
        if (node.bNumber < current.bNumber) {
            if (current.left == null) {
                current.left = node;
            } else {
                insertRec(current.left, node);
            }
        } else {
            if (current.right == null) {
                current.right = node;
            } else {
                insertRec(current.right, node);
            }
        }
    }

    /**
     * Recursively calculates the sum of the B-Numbers for all nodes in a binary search tree (BST) or its subtree.
     *
     * @param node The root node of the BST or a subtree to calculate the sum for.
     * @return The sum of B-Numbers for all nodes in the BST starting from the specified node.
     */
    public int calculateSumRec(Node node) {
        if (node == null) return 0;
        return node.bNumber + calculateSumRec(node.left) + calculateSumRec(node.right);
    }

    public void writeToStdOutput(String line) {
        MyLogger.writeMessage(line, MyLogger.DebugLevel.TREE_BUILDER);
    }

    public void printInorder(String line) {
        writeToStdOutput(line);
    }

    /**
     * Performs an inorder traversal of the binary search tree (BST) or its subtree and constructs a string representation of the nodes.
     *
     * @param root The root node of the BST or a subtree.
     * @return A string representation of the nodes in inorder traversal separated by commas and spaces.
     */
    private String inorderTraversal(Node root) {
        if (root == null) {
            return "";
        }

        String left = inorderTraversal(root.left);
        String nodeInfo = root.toString();
        String right = inorderTraversal(root.right);

        if (!left.isEmpty() && !right.isEmpty()) {
            return left + ", " + nodeInfo + ", " + right;
        } else if (!left.isEmpty()) {
            return left + ", " + nodeInfo + right;
        } else if (!right.isEmpty()) {
            return nodeInfo + ", " + right;
        } else {
            return nodeInfo;
        }
    }

    /**
     * Writes the inorder traversal of the main Binary Search Tree (BST) and its backup BSTs to a specified file.
     *
     * @param fileWriter        The FileProcessor used for writing to the output file.
     * @param mainBSTRootNode   The root node of the main BST.
     * @param backup1BSTRooNode The root node of the first backup BST.
     * @param backup2BSTRooNode The root node of the second backup BST.
     * @param backup3BSTRootNode The root node of the third backup BST.
     */
    public void printInorderToFile(FileProcessor fileWriter, Node mainBSTRootNode, Node backup1BSTRooNode, Node backup2BSTRooNode, Node backup3BSTRootNode) {
        try {
            fileWriter.write("// Inorder traversal\n");

            String mainBSTInorder = "BST: " + inorderTraversal(mainBSTRootNode);
            printInorder(mainBSTInorder);
            fileWriter.write(mainBSTInorder + "\n");

            String backup1Inorder = "Backup-1: " + inorderTraversal(backup1BSTRooNode);
            printInorder(backup1Inorder);
            fileWriter.write(backup1Inorder + "\n");

            String backup2Inorder = "Backup-2: " + inorderTraversal(backup2BSTRooNode);
            printInorder(backup2Inorder);
            fileWriter.write(backup2Inorder + "\n");

            String backup3Inorder = "Backup-3: " + inorderTraversal(backup3BSTRootNode);
            printInorder(backup3Inorder);
            fileWriter.write(backup3Inorder + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
    }

    public void updateNodesRec(Node node, int updateValue) {
        if (node != null) {
            // Update the B-Number value of the current node
            node.bNumber += updateValue;
            node.notifyAllObservers(updateValue);

            // Recursively update left and right subtrees
            updateNodesRec(node.left, updateValue);
            updateNodesRec(node.right, updateValue);
        }
    }
}
