import java.util.Scanner;
import java.util.*; // Import the ArrayList class

public class Question9 {

    public static void main(String[] args) {
        try (Scanner kb = new Scanner(System.in)) {
            System.out.println("Enter a comment: ");
            String str = kb.nextLine();
            String[] arr = str.split(" ");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals("/*")) {
                    for (int j = i + 1; j < arr.length; j++) {
                        if (arr[j].equals("*/")) {
                            break;
                        }
                        System.out.println(arr[j]);
                    }
                }
            }
        }
    }
}
