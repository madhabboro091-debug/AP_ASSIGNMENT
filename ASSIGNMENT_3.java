import java.util.ArrayList;
import java.util.Scanner;

class Assignment3 {

    public static void main(String[] args) {

        // Create an ArrayList to store book titles
        ArrayList<String> books = new ArrayList<>();

        // Adding at least 5 book titles
        books.add("Data Structures and Algorithms");
        books.add("Introduction to Java Programming");
        books.add("Operating System Concepts");
        books.add("Computer Networks");
        books.add("Design and Analysis of Algorithms");

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Ask user for the search word
        System.out.print("Enter a word to search in book titles: ");
        String word = sc.nextLine().toLowerCase();

        System.out.println("\nBooks containing \"" + word + "\":");

        boolean found = false;

        // Search for books containing the given word
        for (String book : books) {
            if (book.toLowerCase().contains(word)) {
                System.out.println(book);
                found = true;
            }
        }

        // If no book matches
        if (!found) {
            System.out.println("No matching books found.");
        }

        sc.close();
    }
}
