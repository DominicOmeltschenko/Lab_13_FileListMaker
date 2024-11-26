import java.util.Scanner;

public class SafeInput {
    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }
    public static int getInt(Scanner pipe, String prompt)
    {
        int retInt = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt())
            {
                retInt = pipe.nextInt();
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You said your favorite integer was: " + trash);
                System.out.println("Input a valid integer.");
            }
        } while (!done);

        pipe.nextLine();

        return retInt;
    }
    public static Double getDouble(Scanner pipe, String prompt)
    {
        double retDouble = 0.00;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");

            if (pipe.hasNextDouble())
            {
                retDouble = pipe.nextDouble();
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You said your favorite number was: " + trash);
                System.out.println("Input a valid number.");
            }

        } while (!done);

        pipe.nextLine();

        return retDouble;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int retRangedInt = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt())
            {
                retRangedInt = pipe.nextInt();
                if ( retRangedInt >= low && retRangedInt <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You said your integer was: " + retRangedInt);
                    System.out.println("Input a valid integer." + " [" + low + "-" + high + "]");
                    pipe.nextLine();
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You said your integer was: " + trash);
                System.out.println("Input a valid integer." + " [" + low + "-" + high + "]");
            }
        } while (!done);

        pipe.nextLine();

        return retRangedInt;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double retRangedDouble = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble())
            {
                retRangedDouble = pipe.nextDouble();
                if ( retRangedDouble >= low && retRangedDouble <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You said your value was: " + retRangedDouble);
                    System.out.println("Input a valid number." + " [" + low + "-" + high + "]");
                    pipe.nextLine();
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You said your value was: " + trash);
                System.out.println("Input a valid number." + " [" + low + "-" + high + "]");
            }
        } while (!done);

        pipe.nextLine();

        return retRangedDouble;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        String confirmYN;
        String trash = "";
        boolean done = false;
        boolean confirmAnswer = false;
        do {
            System.out.print("\n" + prompt + ": ");
            confirmYN = pipe.nextLine();
            if (confirmYN.equalsIgnoreCase("Y") || confirmYN.equalsIgnoreCase("N"))
            {

                if (confirmYN.equalsIgnoreCase("Y"))
                {
                    confirmAnswer = true;
                    done = true;
                }
                else
                {
                    confirmAnswer = false;
                    done = true;
                }
            }
            else
            {

                System.out.println("You said: " + confirmYN);
                System.out.println("Input Y or N!");
            }
        } while (!done);


        return confirmAnswer;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String value = " ";
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            value = pipe.nextLine();
            if (value.matches(regEx))
            {
                done = true;
            }
            else
            {
                System.out.println("You said: " + value);
                System.out.println("Please enter your choice in a valid format");
            }

        } while (!done);



        return value;
    }
    public static void prettyHeader(String msg)
    {


        for (int headerWidth = 60; headerWidth >= 0; headerWidth--)
        {
            System.out.print("*");
        }
        System.out.println();
        for (int leftSide = 3; leftSide > 0; leftSide--)
        {
            System.out.print("*");
        }
        for (int centeredMessageLeft = (60 - 6 - msg.length() )/2; centeredMessageLeft >= 0; centeredMessageLeft --)
        {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int centeredMessageRight = (60 - 6 - msg.length() )/2; centeredMessageRight >= 0; centeredMessageRight --)
        {
            System.out.print(" ");
        }
        for (int rightSide = 3; rightSide > 0; rightSide--)
        {
            System.out.print("*");
        }
        System.out.println();
        for (int headerWidth = 60; headerWidth >= 0; headerWidth--)
        {
            System.out.print("*");
        }
        System.out.println();


    }

}
