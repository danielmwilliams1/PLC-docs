import java.util.Scanner;

public class Question10 {
    public static void main(String[] args) {
        // Write a program that can accept a string that has your name in it

        try (Scanner kb = new Scanner(System.in)) {
            System.out.println("Enter a string: "); // Prompt user for input
            String str = kb.nextLine(); // Read user input
            System.out.println("Enter your name: "); // Prompt user for input
            String name = kb.nextLine(); // Read user input
            String[] arr = str.split(" "); // Split the string into an array
            int count = 0; // Initialize count to 0
            for (int i = 0; i < arr.length; i++) { // Loop through the array
                if (arr[i].equals(name)) { // If the element in the array is equal to the name
                    count++; // Increment count
                }
            }
            System.out.println(name + " appears " + count + " times in the string."); // Print the result
        }
    }
}
