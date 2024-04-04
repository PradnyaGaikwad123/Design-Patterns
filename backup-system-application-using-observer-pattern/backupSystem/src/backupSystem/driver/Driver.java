package backupSystem.driver;

import backupSystem.util.BST;
import backupSystem.util.BSTBuilder;
import backupSystem.util.FileProcessor;
import backupSystem.util.MyLogger;
import backupSystem.util.Node;

public class Driver {
    public static void main (String[] args) {

        FileProcessor file_processor = new FileProcessor();
        if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {
            System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
            System.err.println("Usage: java Main <inputFile> <outputFile> <errorFile> <debugLevel> <updateValue>");
            file_processor.writeToErrorLogFile(args[2], "Error: Incorrect number of arguments. Program accepts 5 arguments.");
            System.exit(0);
        }

        // Read command line arguments
        String inputFile = args[0];
        String outputFile = args[1];
        int debugLevel = 0;
        int updateValue = 0;
        try {
            debugLevel = Integer.parseInt(args[3]);
            updateValue = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid debug level or update value. Please provide valid integers.");
            e.printStackTrace();          
            file_processor.writeToErrorLogFile(args[2], "Error: Invalid debug level or update value. Please provide valid integers.");
            System.exit(0);        
        } finally {

        }

        MyLogger.setDebugValue(debugLevel);
        MyLogger.writeMessage("DebugLevel set to: "+ debugLevel, MyLogger.DebugLevel.DRIVER);

        FileProcessor bstInputFileProcessor = new FileProcessor(inputFile, args[2]);
        BSTBuilder buildBSTrees = new BSTBuilder();
        buildBSTrees.buildBST(bstInputFileProcessor, args[2]);
        Node mainBSTRoot = buildBSTrees.getMainBST();
        Node backup1Root = buildBSTrees.getBackup1();
        Node backup2Root = buildBSTrees.getBackup2();
        Node backup3Root = buildBSTrees.getBackup3();

        // Call inorder traversal to print the values from the four trees
        MyLogger.writeMessage("// Inorder traversal", MyLogger.DebugLevel.TREE_BUILDER);
        FileProcessor fileWriter = new FileProcessor(outputFile);
        BST bstObj = new BST();
        bstObj.printInorderToFile(fileWriter, mainBSTRoot, backup1Root, backup2Root, backup3Root);

        try {
            // Call methods to print the sum of all the B-Numbers in each of the four trees.
            int mainSum = bstObj.calculateSumRec(mainBSTRoot);
            int backup1Sum = bstObj.calculateSumRec(backup1Root);
            int backup2Sum = bstObj.calculateSumRec(backup2Root);
            int backup3Sum = bstObj.calculateSumRec(backup3Root);
            // Print the sum to stdoutput
            MyLogger.writeMessage("\n// Sum of all the B-Numbers in each tree", MyLogger.DebugLevel.TREE_BUILDER);
            buildBSTrees.printSum(mainSum, backup1Sum, backup2Sum, backup3Sum);
            // Write the sum to the file
            MyLogger.writeMessage("Writing output to file", MyLogger.DebugLevel.FILE_WRITER);
            fileWriter.write("\n// Sum of all the B-Numbers in each tree\n");
            buildBSTrees.writeSumToFile(fileWriter, mainSum, backup1Sum, backup2Sum, backup3Sum);

            // Call a method to increment the nodes in the main BST with UPDATE_VALUE (via the observer pattern)
            bstObj.updateNodesRec(mainBSTRoot, updateValue);

            // Call methods to print the sum of all the B-Numbers in each of the four trees.
            int mainSumAfterUpdate = bstObj.calculateSumRec(mainBSTRoot);
            int backup1SumAfterUpdate = bstObj.calculateSumRec(backup1Root);
            int backup2SumAfterUpdate = bstObj.calculateSumRec(backup2Root);
            int backup3SumAfterUpdate = bstObj.calculateSumRec(backup3Root);
            // Print the sum to stdoutput
            MyLogger.writeMessage("\n// Sum of all the B-Numbers after increment", MyLogger.DebugLevel.TREE_BUILDER);
            buildBSTrees.printSum(mainSumAfterUpdate, backup1SumAfterUpdate, backup2SumAfterUpdate, backup3SumAfterUpdate);
            // Write the sum to the file
            fileWriter.write("\n// Sum of all the B-Numbers after increment\n");
            buildBSTrees.writeSumToFile(fileWriter, mainSumAfterUpdate, backup1SumAfterUpdate, backup2SumAfterUpdate, backup3SumAfterUpdate);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            
        }
    }
}