import java.util.Scanner;  // Import the Scanner class

class Main {
  public static void main(String[] args) {
    //Create a scanner object to read user input
    Scanner command_obj = new Scanner(System.in);
    System.out.print("Enter a terminal command: ");

    //Read what the user typed
    String command = command_obj.nextLine();
    System.out.println("Command was: " + command);
  }
}