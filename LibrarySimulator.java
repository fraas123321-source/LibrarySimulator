//group f
// feras ali alshayiqi 446104553
// mohamdmed abduihadi algahtani 446103549
// rakan ali alajlan 446103734 

package LibrarySimulator;


import java.util.Scanner;  
  
public class LibrarySimulator {  
    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);  
  
        // Predefined members  
        Member user1 = new Member(1, "User One", 0);  
        Member user2 = new Member(2, "User Two", 0);  
        Member user3 = new Member(3, "User Three", 0);  
  
        boolean running = true;  
  
        while (running) {  
            System.out.println("\n========== Welcome to the Library Simulation ==========");  
            System.out.println("Select an option:");  
            System.out.println("1. Login as " + user1.getName() + " (ID: " + user1.getId() + ")");  
            System.out.println("2. Login as " + user2.getName() + " (ID: " + user2.getId() + ")");  
            System.out.println("3. Login as " + user3.getName() + " (ID: " + user3.getId() + ")");  
            System.out.println("4. Login as Administrator");  
            System.out.println("5. Exit Program");  
            System.out.print("Enter your choice: ");  
  
            int choice = scanner.nextInt();  
            scanner.nextLine(); //consume newline  
  
            if (choice >= 1 && choice <= 3) {  
                Member current;  
                if (choice == 1)   
                    current = user1;  
                else if (choice == 2)   
                    current = user2;  
                else   
                    current = user3;  
                //current reset
 
                boolean sessionActive = true;  
                while (sessionActive) {  
                    System.out.println("\nWelcome, " + current.getName() + "!");  
                    System.out.println("1. View Borrowed Books Count");  
                    System.out.println("2. Borrow Book");  
                    System.out.println("3. Return Book");  
                    System.out.println("4. View Session Summary");  
                    System.out.println("5. Exit to Main Menu");  
                    System.out.print("Choose an option: ");  
  
                    int userOption = scanner.nextInt();  
                    scanner.nextLine();  
  
                    switch (userOption) {  
                        case 1:  
                            current.viewBorrowedCount();  
                            break;  
                        case 2:  
                            current.borrowOne();  
                            break;  
                        case 3:  
                            current.returnOne();  
                            break;  
                        case 4:  
                            current.displayStatistics();  
                            break;  
                        case 5:  
                            System.out.println("Session ended. Total books currently borrowed: " + current.getBorrowedCount());  
                            sessionActive = false;  
                            break;  
                        default:  
                            System.out.println("Invalid option. Try again.");  
                            break;  
                    }  
                }  
  
            } else if (choice == 4) {  
                System.out.print("Enter admin password: ");  
                String password = scanner.nextLine();  
  
                if (!password.equals("admin")) {  
                    System.out.println("Invalid password. Access denied.");  
                } else {  
                    boolean adminActive = true;  
                    while (adminActive) {  
                        System.out.println("\n===== Administrator Menu =====");  
                        System.out.println("1. View Total Revenue");  
                        System.out.println("2. Most Frequent Operation");  
                        System.out.println("3. Exit to Main Menu");  
                        System.out.print("Choose an option: ");  
  
                        int adminChoice = scanner.nextInt();  
                        scanner.nextLine();  
  
                        switch (adminChoice) {  
                            case 1:  
                                System.out.printf("Total Revenue from all borrow operations: %.2f\n", Member.TotalRevenue);  
                                break;  
                            case 2:  
                                System.out.println("Most Frequent Operation:");  
                                if (Member.TotalBorrows == 0 && Member.TotalReturns == 0) {  
                                    System.out.println("- No operations performed yet.");  
                                } else if (Member.TotalBorrows > Member.TotalReturns) {  
                                    System.out.println("- Borrow");  
                                } else if (Member.TotalReturns > Member.TotalBorrows) {  
                                    System.out.println("- Return");  
                                } else {  
                                    System.out.println("- Borrow and Return (Tie)");  
                                }  
                                break;  
                            case 3:  
                                adminActive = false;  
                                break;  
                            default:  
                                System.out.println("Invalid option. Try again.");  
                                break;  
                        }  
                    }  
                }  
  
            } else if (choice == 5) {  
                System.out.println("Thank you for using the Library Simulation. Goodbye!");  
                running = false;  
            } else {  
                System.out.println("Invalid option. Try again.");  
            }  
        }  
  
        scanner.close();  
    }  
}  



-----------------------------------------------------------------------------------------------------------------------------
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