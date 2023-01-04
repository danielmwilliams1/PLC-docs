import java.util.Scanner;

public class Question8 {

    public static void main(String[] args) {

        // Write a program that accepts multiline comments in java and prints the
        // comments

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter a multiline comment: ");
        String comment = kb.nextLine();
        System.out.println("The comment is: " + comment);

        kb.close();
    }

}
