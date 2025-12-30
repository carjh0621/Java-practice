import java.util.Scanner;

public class Labeledbreak {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        int n, tmp=0;
        read_data:
        while(true){
            for(int i=0;i<5;i++){
                System.out.print("Enter a number >=0: ");
                n=in.nextInt();
                tmp += n;
                if(n<0)
                    break read_data;//break out of read_data loop
            }

            System.out.println("current sum of n: " + tmp);

        }
        if(n<0){
            System.out.println("n must be positive");
        }
        else {
            System.out.println("it would not be execute");
        }
    }
}
