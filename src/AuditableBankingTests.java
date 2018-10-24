import java.util.Arrays;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AuditableBankingTests
// Files: AuditableBanking
// Course: CS300 Fall 2018
//
// Author: Reece Lardy
// Email: RLardy@wisc.edu
// Lecturer's Name: Alexander Brooks
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Nick Hayden
// Partner Email: nhayden@wisc.edu
// Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (NONE)
// Online Sources: (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class is made to test the methods of the Auditable Banking class
 * 
 * @author Reece Lardy & Nick Hayden
 *
 */
public class AuditableBankingTests {
    /**
     * main methd made to call other methods which test specific methods in Auditable Banking
     * 
     * @param args
     */
    public static void main(String args[]) {
        boolean works = testProcessCommand();
        if (works) { // Check if test works and prints if it does
            System.out.println("1st test works");
        }
        works = testCalculateNumberOfOverdrafts();
        if (works) { // Check if test works and prints if it does
            System.out.println("2nd test works");
        }
        works = testCurrentBalance();
        if (works) { // Check if test works and prints if it does
            System.out.println("3rd test works");
        }

    }

    /**
     * This method tests the processCommand method
     * 
     * @return works which is if the tested method works or not
     */
    public static boolean testProcessCommand() {
        boolean works = false; // create works array to show if tests work
        int[][] testArray = new int[4][]; // create oversized array to fill and test
        int[] array = {0, 1, 1, 0, 1}; // array made to be put inside oversized array
        testArray[0] = array; // put array into oversized array
        int allTransactionsCount = 1;
        // run processCommand with a situation that tests if it works for binary addition
        allTransactionsCount =
            AuditableBanking.processCommand("0 1 0 1 1", testArray, allTransactionsCount);
        for (int i = 0; i < allTransactionsCount; i++) { // for loop that prints out the contents of
                                                         // the
            for (int j = 0; j < testArray[i].length; j++) {// oversized array
                System.out.print(testArray[i][j] + " ");
            }
            System.out.println("");
        }
        if (testArray[1][1] == 1) { // check to see that the array is right
            works = true;
            System.out.println("PASSED TEST 1/2 of testProcessCommand");
        }
        allTransactionsCount =
            AuditableBanking.processCommand("1 20 27 -38", testArray, allTransactionsCount);
        if (testArray[2][3] == -38) { // check to see that the array can do normal deposits and
                                      // withdraws
            works = true; // If it works print that it does
            System.out.println("PASSED TEST 2/2 of testProcessCommand");
        }
        return works;
    }

    /**
     * This method tests the CalculateNumberOfOverdrafts method
     * 
     * @return foundProblem which is if the tested has a problem or not
     */
    public static boolean testCalculateNumberOfOverdrafts() {
        boolean foundProblem = false;
        int[][] transactions = new int[][] {{1, 10, -20, +30, -20, -20}, // +2 overdrafts (ending
                                                                         // balance: -20)
            {0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // +2 overdrafts (ending balance: -15)
            {1, 115}, // +0 overdrafts (ending balance: +100)
            {2, 3, 1, 0, 1}, // +1 overdrafts (ending balance: -100)
        };

        // test with a single transaction of the Integer Amount encoding
        int transactionCount = 1;
        int overdrafts =
            AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
        if (overdrafts != 2) {
            System.out.println(
                "FAILURE: calculateNumberOfOverdrafts did not return 2 when transactionCount = 1, and transactions contained: "
                    + Arrays.deepToString(transactions));
            foundProblem = true;
        } else
            System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts!!!"); // test
                                                                                         // with
                                                                                         // four
                                                                                         // transactions:
                                                                                         // including
                                                                                         // one of
                                                                                         // each
                                                                                         // encoding
        transactionCount = 4;
        overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
        if (overdrafts != 5) {
            System.out.println(
                "FAILURE: calculateNumberOfOverdrafts did not return 5 when transactionCount = 4, and transactions contained: "
                    + Arrays.deepToString(transactions));
            foundProblem = true;
        } else
            System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts!!!");

        return !foundProblem;
    }

    /**
     * This method tests the CurrentBalance method
     * 
     * @return foundProblem which is if the tested has a problem or not
     */
    public static boolean testCurrentBalance() {
        boolean foundProblem = false;
        int[][] transactions = new int[][] {{1, 10, -20, +30, -20, -20}, // (ending balance: -20)
            {0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // (ending balance: -15)
            {1, 115}, // (ending balance: +100)
            {2, 3, 1, 0, 1}, // (ending balance: -100)
        };

        // test with a single transaction of the Integer Amount encoding
        int transactionCount = 1;
        int totalCash = AuditableBanking.calculateCurrentBalance(transactions, transactionCount);
        if (totalCash != -20) { // If test fails print out that it did otherwise print out it passed
            System.out.println(
                "FAILURE: calculateCurrentBalance did not return -20 instead " + totalCash);
            foundProblem = true;
        } else
            System.out.println("PASSED TEST 1/2 of calculateCurrentBalance!!!");
        // test with four transactions: including one of each encoding
        transactionCount = 4;
        totalCash = AuditableBanking.calculateCurrentBalance(transactions, transactionCount);
        if (totalCash != -100) {
            System.out.println(
                "FAILURE: calculateCurrentBalance did not return 100 instead " + totalCash);
            foundProblem = true;
        } else
            System.out.println("PASSED TESTS 2/2 of calculateCurrentBalance!!!");

        return !foundProblem;
    }

}
