import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;


public class FileInspector
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line = "";
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());
        int numberOfLines = 0;
        int numberOfWords = 0;
        int numberOfChars = 0;

        try {

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();

                inFile = new Scanner(target);

                while(inFile.hasNextLine())
                {
                    line = inFile.nextLine();
                    System.out.println(line);
                    numberOfLines++;
                    for (String words : line.split(" "))
                    {
                        numberOfWords++;
                    }
                    numberOfChars = numberOfChars + line.length();
                }

                System.out.println("File " + target.getFileName() + " successfully read!");
                System.out.println("The text file has " + numberOfLines + " lines");
                System.out.println("The text file has " + numberOfWords + " words");
                System.out.println("The text file has " + numberOfChars + " characters");

                inFile.close();

            } else
            {
                System.out.println("You did not select a file, process terminating");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }

}
