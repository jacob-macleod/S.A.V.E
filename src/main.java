//Import packages
import java.util.*; 
import java.io.*;

class Main {
  
  public static void execute_command(String command) {
    try {
      //Start new process builder process
      Process process = Runtime.getRuntime().exec(command); 
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
      //System.exit(0);
    } 
    else {
      System.out.println("There's been an error with executing a command - please open an issue on github");
    }
  } catch (IOException e) {
    e.printStackTrace();
  } catch (InterruptedException e) {
    e.printStackTrace();
  }
  }


  public static void main(String[] args) {
    //Create a scanner object to read user input
    Scanner command_obj = new Scanner(System.in);
    System.out.print("Enter a terminal command, type e to execute the code in command_list.sh, or type f to upload a file to github: ");

    //Read what the user typed
    String command = command_obj.nextLine();
    //If e is pressed (I need to use != instead of == beacuse of black magic)
    if (command.equals("e")) {
      //Execute command_list.sh
      execute_command("bash src/command_list.sh");

    //Else, saved command user typed to command_list.sh
    } else if (command.equals("f")) {
      System.out.println("Step 1: Please clone a git repository inside your home directory, then copy the files you want to backup into that repository using the cp command");
      Scanner input_obj = new Scanner(System.in);
      System.out.println("Step 2: Next, please enter the name of the git repository: ");
      String input = input_obj.nextLine();
      try {
        execute_command("mv upload_to_git.sh %HOME/" + input);
      } catch(Exception e) {
        System.out.println("Unable to move upload_to_git.sh. Not to worry - the file is probaly already in the destination folder");
      }
      System.out.println("Step 3: Finally, please cd into the git repository and run 'bash upload_to_git.sh'");
      System.out.println("Helpful tip: Keep in mind you can automate steps 1 and 3 by setting up your device to run the commands automatically");
      
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