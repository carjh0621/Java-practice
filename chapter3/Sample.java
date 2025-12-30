import java.util.Scanner;

public class Sample
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = in.nextLine();
        String firstName = in.next();
        System.out.print("How old are you? ");
        int age = in.nextInt();

    }
}
