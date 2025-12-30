import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.file.Path;
public class FileIO {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Path.of("myfile.txt"),
                StandardCharsets.UTF_8);
        System.out.println(in.next());
        System.out.println(in.nextLine());

        try (PrintWriter out = new PrintWriter(
                "myout.txt", StandardCharsets.UTF_8)) {

            out.println("Hello");
            int x = 3, y = 7;
            out.printf("%d %d%n", x, y);
        }
    }
}
