import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AuditableBanking
// Files: AuditableBankingTests
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
 * AuditBanking is a class that handles balance and calculates transactions for a certain bank
 * account
 * 
 * @author Reece Lardy & Nick Hayden
 *
 */
public class AuditableBanking {

    /**
     * Main method that creates the array and size and uses them to print out the menu and calculate
     * the balance and handle new transactions with the use of other methods
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Get scanner
        int[][] allTransactions = new int[100][]; // Create oversized array that will hold
                                                  // transactions
        int allTransactionsCount = 0; // Create variable to hold size of array
        System.out.println("========== Welcome to the Auditable Banking App ==========");
        printmenu();
        String response = sc.nextLine(); // Get user input
        while (response.toUpperCase().charAt(0) != 'Q') { // If q terminate the program
            allTransactionsCount = processCommand(response, allTransactions, allTransactionsCount); // Process
                                                                                                    // the
                                                                                                    // user's
                                                                                                    // response
            printmenu();
            response = sc.nextLine();// get user's next response
        }
        System.out.println("============ Thank you for using this App!!!! ============");
    }

    /**
     * Method that prints out the generic menu so that it can be easily repeated
     */
    public static void printmenu() {
        System.out.println("COMMAND MENU:");
        System.out
            .println("  Submit a Transaction (enter sequence of integers separated by spaces)");
        System.out.println("  Show Current [B]alance");
        System.out.println("  Show Number of [O]verdrafts");
        System.out.println("  [Q]uit Program");
        System.out.println("ENTER COMMAND: ");
    }

    /**
     * Adds a transaction group to an array of transaction groups. If the allTransactions array is
     * already full then this method will do nothing other than return allTransactionCount.
     * 
     * @param newTransactions is the new transaction group being added (perfect size).
     * @param allTransactions is the collection that newTransactions is being added to (oversize).
     * @param allTransactionsCount is the number of transaction groups within allTransactions
     *        (before newTransactions is added.
     * @return the number of transaction groups within allTransactions after newTransactions is
     *         added.
     */
    public static int submitTransactions(int[] newTransactions, int[][] allTransactions,
        int allTransactionsCount) {
        if (allTransactions.length == allTransactionsCount) // change nothing if the array is
                                                            // already full
        {
            return allTransactionsCount;// return the unchanged count
        }
        allTransactions[allTransactionsCount] = newTransactions;// add the new transaction perfect
                                                                // array into
                                                                // the oversized array
        allTransactionsCount++;// increment the size variable for the oversized array

        return allTransactionsCount; // return the size
    }


    /**
     * This method processes the sting that the user inputs and performs different actions based on
     * which chose and then does the chosen action based upon the string
     * 
     * @param command is the string that the user gives which gives the transaction info
     * @param allTransactions is the oversized arrays of previous transactions
     * @param allTransactionsCount is the number of transaction groups within allTransactions
     * @return
     */
    public static int processCommand(String command, int[][] allTransactions,
        int allTransactionsCount) {
        command = command.trim(); // Trim the string
        command = command.toUpperCase(); // convert string to uppercase
        if (command.charAt(0) == 'B') { // If user types B calculate and display the balance
            int totalCash = calculateCurrentBalance(allTransactions, allTransactionsCount);
            System.out.println("Current Balance " + totalCash);
            return allTransactionsCount;
        }
        if (command.charAt(0) == 'O') { // If user types O calculate and display the number of
                                        // overdrafts
            int overdrafts = calculateNumberOfOverdrafts(allTransactions, allTransactionsCount);
            System.out.println("Number of Overdrafts " + overdrafts);
            return allTransactionsCount;
        }
        String[] letterHolder = command.split(" ");// Split the String up into an array of strings
                                                   // to more
                                                   // easily process
        int[] newTransaction = new int[letterHolder.length]; // Create an array that can hold the
                                                             // ints provided
        for (int i = 0; i < letterHolder.length; i++) { // Parse String array for ints to put
                                                        // into the int array
            newTransaction[i] = Integer.parseInt(letterHolder[i]);
        }
        if (allTransactions.length == allTransactionsCount) {
            return allTransactionsCount;// return the unchanged count if all Transaction is full
        } else if (newTransaction[0] == 0 || newTransaction[0] == 1 || newTransaction[0] == 2) {
            // for(int i=0; i<letterHolder.length;i++) { //Parse String array for ints to put
            // into the int array
            // newTransaction[i]=Integer.parseInt(letterHolder[i]);
            // }
            allTransactionsCount =
                submitTransactions(newTransaction, allTransactions, allTransactionsCount);
            return allTransactionsCount; // return oversized array size
        } else { // If the first String isn not 0 1
            return allTransactionsCount;
        }
        // put transactions into oversized array
    }

    /**
     * This method takes the transaction groups and calculates the account balance based upon the
     * amounts put in and out
     * 
     * @param allTransactions The array full of all the transactions
     * @param allTransactionsCount The size of the allTransactions array
     * 
     * @return totalCash
     */
    public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {
        int totalCash = 0; // variable that holds the total balance of the bank account

        for (int i = 0; i < allTransactionsCount; i++) { // loop that goes for entire array size of
                                                         // allTransactions
            if (allTransactions[i][0] == 1) { // Check to see if the first number in the new
                                              // transaction is one
                for (int j = 1; j < allTransactions[i].length; j++) { // If it is one go through the
                                                                      // array and
                                                                      // handle the transaction how
                                                                      // that way should
                    totalCash += allTransactions[i][j]; // increase or decrease by the amount the
                                                        // user stated
                }
            }
            if (allTransactions[i][0] == 0) { // Check to see if the first number in newTransaction
                                              // is 0
                for (int j = 1; j < allTransactions[i].length; j++) { // If it is 0 it goes through
                                                                      // the array and
                                                                      // either adds or subtracts 1
                                                                      // for every input
                    if (allTransactions[i][j] == 0) { // If its a 0 subtract 1
                        totalCash--;
                    }
                    if (allTransactions[i][j] == 1) {// If its a 1 add one
                        totalCash++;
                    }
                }
            }
            if (allTransactions[i][0] == 2) { // Check to see if the first number in newTransaction
                                              // is 2
                for (int j = 1; j < allTransactions[i].length; j++) {
                    if (j < 3) { // If j<3 multiply by j for the correct amount to subtract
                        totalCash += -20 * j * allTransactions[i][j];
                    } else { // Else multiply j+1 in order to add the right amount
                        totalCash += -20 * (j + 1) * allTransactions[i][j];
                    }
                }
            }
        }

        return totalCash;
    }

    /**
     * This method calculates the amount of time that the user overdrafts by computing the balance
     * and seeing whenever goes into the negative and when it subtracts when it is already in the
     * negative.
     * 
     * @param allTransactions
     * @param allTransactionsCount
     * @return overdrafts
     */
    public static int calculateNumberOfOverdrafts(int[][] allTransactions,
        int allTransactionsCount) {
        int totalCash = 0;// measures the totalCash in the balance
        int overdrafts = 0; // holds number of overdrafts
        int tempCash = 0; // used to see if it was already negative
        for (int i = 0; i < allTransactionsCount; i++) { // loop that goes for entire array of
                                                         // allTransaction
            if (allTransactions[i][0] == 1) { // Check to see if the first number in the new
                                              // transaction is one
                for (int j = 1; j < allTransactions[i].length; j++) { // If it is one go through the
                                                                      // array and
                                                                      // handle the transaction how
                                                                      // that way should
                    tempCash = totalCash; // get old amount of totalCash to see if its subtracting
                    totalCash += allTransactions[i][j];
                    if (totalCash < 0 && totalCash - tempCash < 0) {// Do if less than 0 dollars and
                                                                    // if it is
                                                                    // subtracting from account
                        overdrafts++;
                    }
                }
            }
            if (allTransactions[i][0] == 0) {
                for (int j = 1; j < allTransactions[i].length; j++) { // Check to see if the first
                                                                      // number
                                                                      // in newTransaction is 0
                    if (allTransactions[i][j] == 0) { // If its a 0 subtract one
                        tempCash = totalCash; // get old amount of totalCash to see if its
                                              // subtracting
                        totalCash--;
                    }
                    if (allTransactions[i][j] == 1) { // If its a 1 add one
                        tempCash = totalCash; // get old amount of totalCash to see if its
                                              // subtracting
                        totalCash++;
                    }
                    if (totalCash < 0 && totalCash - tempCash < 0) {// Do if less than 0 dollars and
                                                                    // if it is
                                                                    // subtracting from account
                        overdrafts++;

                    }

                }
            }
            if (allTransactions[i][0] == 2) { // Check to see if the first number in newTransaction
                                              // is 2
                for (int j = 1; j < allTransactions[i].length; j++) {

                    if (j < 3) { // If j<3 multiply by j for the correct amount to subtract
                        totalCash += -20 * j * allTransactions[i][j];
                    } else { // Else multiply j+1 in order to add the right amount
                        totalCash += -20 * (j + 1) * allTransactions[i][j];
                    }

                    if (totalCash < 0 && allTransactions[i][0] != 0) {// Do if less than 0 dollars
                                                                      // don't need to
                                                                      // If its subtracting since it
                                                                      // always is
                        overdrafts++;
                    }

                }
            }

        }
        return overdrafts;
    }
}
