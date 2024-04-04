package backupSystem.util;

public class BSTBuilder {
    BST bst = new BST();

    Node mainBSTRootNode;
    Node backup1BSTRootNode;
    Node backup2BSTRootNode;
    Node backup3BSTRootNode;
   
    public BSTBuilder() {
        mainBSTRootNode = null;
        backup1BSTRootNode = null;
        backup2BSTRootNode = null;
        backup3BSTRootNode = null;
    }

    public void buildBST(FileProcessor fileProcessor, String errorFileName) {
        String line;
        while ((line = fileProcessor.read()) != null) {
            String[] parts = line.split(":");
            int bNumber = 0;
            try {
                bNumber = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid B-Number.");
                e.printStackTrace();
                fileProcessor.writeToErrorLogFile(errorFileName, "Error: Invalid B-Number.");
                System.exit(0);        
            } finally {
                
            }
            String fName[] = parts[1].split(";");
            String firstName = fName[0].toString();

            Node newMainNode = new Node(bNumber, firstName);
            mainBSTRootNode = bst.insert(mainBSTRootNode, newMainNode);

            Node newBackup1Node = new Node(bNumber, firstName);
            backup1BSTRootNode = bst.insert(backup1BSTRootNode, newBackup1Node);
            newMainNode.registerObserver(newBackup1Node, new FilterAllImpl());

            Node newBackup2Node = new Node(bNumber, firstName);
            backup2BSTRootNode = bst.insert(backup2BSTRootNode, newBackup2Node);
            newMainNode.registerObserver(newBackup2Node, new FilterAllImpl());

            Node newBackup3Node = new Node(bNumber, firstName);
            backup3BSTRootNode = bst.insert(backup3BSTRootNode, newBackup3Node);
            newMainNode.registerObserver(newBackup3Node, new FilterPrimeImpl());
        }

        fileProcessor.close();
    }

    /**
     * Retrieves the main Binary Search Tree (BST) root node.
     *
     * @return The main BST rrot node containing nodes constructed from the input data.
     */
    public Node getMainBST() {
        // return mainBST;
        return mainBSTRootNode;
    }

    public Node getBackup1() {
        return backup1BSTRootNode;
    }

    public Node getBackup2() {
        return backup2BSTRootNode;
    }

    public Node getBackup3() {
        return backup3BSTRootNode;
    }

    public void printSum(int mainSum, int backup1Sum, int backup2Sum, int backup3Sum) {
        MyLogger.writeMessage("BST: " + mainSum, MyLogger.DebugLevel.TREE_BUILDER);
        MyLogger.writeMessage("Backup-1: " + backup1Sum, MyLogger.DebugLevel.TREE_BUILDER);
        MyLogger.writeMessage("Backup-2: " + backup2Sum, MyLogger.DebugLevel.TREE_BUILDER);
        MyLogger.writeMessage("Backup-3: " + backup3Sum, MyLogger.DebugLevel.TREE_BUILDER);
    }

    public void writeSumToFile(FileProcessor fileWriter, int mainSum, int backup1Sum, int backup2Sum, int backup3Sum) {
        try {
            fileWriter.write("BST: " + mainSum + "\n");
            fileWriter.write("Backup-1: " + backup1Sum + "\n");
            fileWriter.write("Backup-2: " + backup2Sum + "\n");
            fileWriter.write("Backup-3: " + backup3Sum + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
    }
}
