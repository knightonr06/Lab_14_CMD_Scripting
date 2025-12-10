import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileScan {
    public static void main(String[] args) {
        File selectedFile = null;

        // Check if command line argument was provided
        if (args.length > 0) {
            // Use the command line argument as filename
            selectedFile = new File(args[0]);

            // Check if file exists
            if (!selectedFile.exists()) {
                System.out.println("Error: File '" + args[0] + "' not found.");
                return;
            }
        } else {
            // No argument provided, launch JFileChooser
            JFileChooser chooser = new JFileChooser(new File("src"));
            chooser.setDialogTitle("Select a text file to inspect");

            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
            } else {
                System.out.println("No file was selected.");
                return;
            }
        }

        // Now scan the selected file (either from argument or chooser)
        try {
            List<String> lines = Files.readAllLines(selectedFile.toPath());

            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            System.out.println("----- File Content -----\n");

            for (String line : lines) {
                System.out.println(line);
                lineCount++;

                if (!line.trim().isEmpty()) {
                    wordCount += line.trim().split("\\s+").length;
                }

                charCount += line.length();
            }

            System.out.println("\n----- Summary Report -----");
            System.out.println("File Name: " + selectedFile.getName());
            System.out.println("Total Lines: " + lineCount);
            System.out.println("Total Words: " + wordCount);
            System.out.println("Total Characters: " + charCount);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
