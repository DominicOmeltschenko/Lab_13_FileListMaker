
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

public class Main {
    static ArrayList<String> arrList = new ArrayList<>();
    static Scanner pipe = new Scanner(System.in);
    static String currentChoice = " ";
    static boolean needToBeSaved = false;
    static String fileName = " ";
    public static void main(String[] args) {

        do {

            do {


                do {
                    display();
                    currentChoice = SafeInput.getRegExString(pipe, "What would you like to do? A (add item), D (delete item), I (insert an item), V (view your list), M (move an item), O (open a file from disk), S (save the current file to disk), C (clear all the items from the current list), Q (quit the program) ", "[AaDdIiVvMmOoSsCcQq]");

                    switch (currentChoice.toUpperCase()) {
                        case "A":
                            addItem();
                            break;
                        case "D":
                            deleteItem();
                            break;
                        case "I":
                            insertItem();
                            break;
                        case "V":
                            display();
                            System.out.println();
                            break;
                        case "M":
                            moveItem();
                            break;
                        case "O":
                            openFile();
                            break;
                        case "S":
                            saveFile();
                            break;
                        case "C":
                            clearList();
                            break;

                    }



                } while (!currentChoice.equalsIgnoreCase("q"));

            } while (!SafeInput.getYNConfirm(pipe, "Are you sure you want to quit?"));

            if (SafeInput.getYNConfirm(pipe, "You have unsaved work, would you like to save before you quit (y/n)"))
            {
               saveFile();
            }
            else
            {
                needToBeSaved = false;
            }

        }while (needToBeSaved);


    }

    public static void display() {
        System.out.println("The current list is:");
        for (int I = 0; I < arrList.size(); I++) {
            System.out.println((I + 1) + ". " + arrList.get(I));
        }
    }
    public static void addItem() {

        arrList.add(SafeInput.getNonZeroLenString(pipe,"What would you like to add to your list"));
        needToBeSaved = true;
    }
    public static void deleteItem()
    {
        display();
        arrList.remove((SafeInput.getRangedInt(pipe,"Which item would you like to remove from your list" + "[ " + 1 + " - " + arrList.size() + " ]" , 1 , arrList.size() )) - 1);
        needToBeSaved = true;
    }
    public static void insertItem() {

        arrList.add((SafeInput.getRangedInt(pipe,"Where would you like to insert your item" + "[ " + 1 + " - " + arrList.size() + " ]" , 1, arrList.size()) - 1), SafeInput.getNonZeroLenString(pipe,"What would you like to add to your list"));
        needToBeSaved = true;
    }
    public static void moveItem() {
        String itemToMove = arrList.remove((SafeInput.getRangedInt(pipe,"Which item would you like to move" + "[ " + 1 + " - " + arrList.size() + " ]" , 1, arrList.size()) - 1));
        System.out.println(itemToMove);
        arrList.add(SafeInput.getInt(pipe,"Where would you like to move it") - 1 , itemToMove);
        needToBeSaved = true;
    }
    public static void saveFile() {

        Scanner pipe = new Scanner(System.in);
        ArrayList<String> recs = new ArrayList<>();
        String fileName = "";
        for (int i = 0; i < arrList.size(); i++) {
            recs.add((i + 1) + ". " + arrList.get(i));
        }

        fileName = SafeInput.getNonZeroLenString(pipe, "What would you like to name your file");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src" + "\\" + fileName + ".txt");
        try
        {

            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        needToBeSaved = false;
    }

        public static void openFile () {
            do {
                if (needToBeSaved) {
                    if (SafeInput.getYNConfirm(pipe, "You have unsaved work, would you like to save before you quit (y/n)")) {
                        saveFile();
                    } else {
                        needToBeSaved = false;
                    }
                }
            } while (needToBeSaved);
            JFileChooser chooser = new JFileChooser();
            Scanner inFile;
            Path target = new File(System.getProperty("user.dir")).toPath();
            target = target.resolve("src");
            chooser.setCurrentDirectory(target.toFile());

            try {

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    target = chooser.getSelectedFile().toPath();
                    inFile = new Scanner(target);

                    while(inFile.hasNextLine())
                    {
                        arrList.add(inFile.nextLine());
                    }

                    System.out.println("File " + target.getFileName() + " successfully read!");

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


    public static void clearList(){
        arrList.clear();
    }



}