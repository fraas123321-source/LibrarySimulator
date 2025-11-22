//group f
// feras ali alshayiqi 446104553
// mohamdmed abduihadi algahtani 446103549
// rakan ali alajlan 446103734 

package LibrarySimulator;

public class Member {  
    // Instance attributes (session + persistent borrowedCount)  
    private int id;  
    private String name;  
    private int borrowedCount;      // number of books currently borrowed 
    private int numViewBorrowed;    // number of times view borrowed count used
    private int numBorrows;         // number of borrow operations in this session  
    private int numReturns;         // number of return operations in this session  
    private double sessionFees;     // total fees incurred in the session  
  
    // Class-level totals
    public static double TotalRevenue = 0.0;  
    public static int TotalViewBorrowed = 0;  
    public static int TotalBorrows = 0;  
    public static int TotalReturns = 0;  
  
    // Constructor  
    public Member(int id, String name, int borrowedCount) {  
        this.id = id;  
        this.name = name;  
        this.borrowedCount = borrowedCount;  
      
    }  
  
    // Private helpers for constraints  
    private boolean canBorrow() {  
        return borrowedCount < 5;   //if true meaning that can borrow  
    }  
  
    private boolean canReturn() {  
        return borrowedCount > 0; //if true meaning that can return  
    }  
  
    // View current borrowed count (increments view counters)  
    public void viewBorrowedCount() {  
        numViewBorrowed++;  
        TotalViewBorrowed++;  
        System.out.println("Books currently borrowed: " + borrowedCount);  
    }  
  
    // Borrow one book: returns true if success, false otherwise  
    public boolean borrowOne() {  
        if (!canBorrow()) {  
            System.out.println("You cannot borrow more than 5 books.");  
            return false;  
        }  
        borrowedCount++;  
        numBorrows++;  
        TotalBorrows++;  
        sessionFees += 0.50;  
        TotalRevenue += 0.50;  
        System.out.printf("Book borrowed successfully. Fee: %.2f\n", 0.50);  
        return true;  
    }  
  
    // Return one book: returns true if success, false otherwise  
    public boolean returnOne() {  
        if (!canReturn()) {  
            System.out.println("You have no books to return.");  
            return false;  
        }  
        borrowedCount--;  
        numReturns++;  
        TotalReturns++;  
        System.out.println("Book returned successfully.");  
        return true;  
    }  
  
    // Display session statistics for this member  
    public void displayStatistics() {  
        System.out.println("====== Session Summary for " + name + " (ID: " + id + ") ======");  
        System.out.println("Books Borrowed (this session): " + numBorrows);  
        System.out.println("Books Returned (this session): " + numReturns);  
        System.out.println("Times View Borrowed Count used (this session): " + numViewBorrowed);  
        System.out.printf("Fees Incurred (this session): %.2f\n", sessionFees);  
    }  
  
    // Reset session statistics (used at the start of each login/session)  
    public void reset() {  
        this.numViewBorrowed = 0;  
        this.numBorrows = 0;  
        this.numReturns = 0;  
        this.sessionFees = 0.0;  
    }  
  
    // Getters & Setters as needed  
    public int getId() {  
        return id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public int getBorrowedCount() {  
        return borrowedCount;  
    }  
  
    public double getSessionFees() {  
        return sessionFees;  
    }  
  
    // Setter for borrowedCount if needed externally (not used in this program)  
    public void setBorrowedCount(int borrowedCount) {  
        this.borrowedCount = borrowedCount;  
    }  
}  