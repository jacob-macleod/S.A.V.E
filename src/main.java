//Import packages
import java.util.*; 
import java.io.*;

class Main {
  public static void main(String[] args) {
    //Create a scanner object to read user input
    Scanner command_obj = new Scanner(System.in);
    System.out.print("Enter a terminal command, or type e to execute the code in command_list.sh: ");

    //Read what the user typed
    String command = command_obj.nextLine();
    //If e is pressed (I need to use != instead of == beacuse of black magic)
    if (command.equals("e")) {
      //Execute command_list.sh
      try {
        //Start new process builder process
        Process process = Runtime.getRuntime().exec("bash src/command_list.sh"); 
        StringBuilder output = new StringBuilder(); 
        //Define buffered reader and line variables
        BufferedReader reader = new BufferedReader(new InputStreamReader (process.getInputStream()));
        String line;
        //Append each line in the output of the command to output variable
        while((line = reader.readLine()) != null) {
          output.append(line + "\n");
        }

        //Manage errors
        int exitVal = process.waitFor();
        if (exitVal == 0) {
          System.out.println(output);
          System.exit(0);
        } else {
          System.out.println("There's been an error with running src/command_list.sh - please open an issue on github");
        }
          
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    //Else, saved command user typed to command_list.sh
    } else {
      //Open a file in append mode
      try(FileWriter fw = new FileWriter("src/command_list.sh", true);
      BufferedWriter bw = new BufferedWriter(fw);

      PrintWriter out = new PrintWriter(bw)) {
        //Append user's input to a txt file
        out.println(command);
        System.out.println("Command saved to command_list.sh!");
      //If there's an error
      } catch (IOException e) {
        System.out.println("An error occured relating to the writing of text to a file - please open an issue on github!");
      }
    }

  }
}