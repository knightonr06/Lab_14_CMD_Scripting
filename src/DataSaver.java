import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class DataSaver {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;

        boolean more = true;
        while (more) {
            System.out.print("First Name: ");
            String first = in.nextLine();

            System.out.print("Last Name: ");
            String last = in.nextLine();

            String id = String.format("%06d", idCounter++);

            System.out.print("Email: ");
            String email = in.nextLine();

            System.out.print("Year of Birth: ");
            String yob = in.nextLine();

            String record = String.join(", ", first, last, id, email, yob);
            records.add(record);

            System.out.print("Add another record? (Y/N): ");
            more = in.nextLine().equalsIgnoreCase("Y");
        }

        System.out.print("Enter filename to save (e.g., data.csv): ");
        String filename = in.nextLine();

        try (FileWriter writer = new FileWriter("src/" + filename)) {
            for (String rec : records) {
                writer.write(rec + "\n");
            }

            System.out.println("Data saved to src/" + filename);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
