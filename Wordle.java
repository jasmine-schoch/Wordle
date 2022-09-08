
  
// Importing required classes
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

  
// Main class
class Wordle {

    public static final String DICTIONARY_FILE = #INSERT PATH TO DICTIONARY FILE HERE, given: five_letter_words.txt;
  
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
    // Declaring the background color
    public static final String GREEN = "\u001B[42m";
  
    public static final String YELLOW = "\u001B[43m";

    public static final String WHITE = "\u001B[47m";

    public static int GuessesLeft;
    public static List<String> dictionary = new ArrayList<String>();

    //retrieves a random 5 letter word for use
    public static List<Character> getWord()
    {   Random rand = new Random();
        String word = dictionary.get(rand.nextInt(dictionary.size()));
        //String word = dictionary.get(5346);
        word = word.toLowerCase();
        List<Character> answer = new ArrayList<>();
        for(int i = 0; i < word.length(); i++)
        {   answer.add(word.charAt(i));
        }
        return answer;
    }
    //returns a string as an Arraylist
    public static List<Character> input(String word)
    {
        List<Character> input = new ArrayList<>();
        for(int i = 0; i < word.length(); i++)
        {   input.add(word.charAt(i));
        }
        return input;
    }

    //determines if the user's word exists in the dictionary
    public static boolean exists(String guess)
    {
        if(!dictionary.contains(guess))
        {
            System.out.println("This word does not exist in our dictionary.");
            return false;
        }
        else
            GuessesLeft--;
            return true;
    }
    //plays the actual game
    public static void playGame(Scanner console, List<Character> answer){
        String guess = console.next().toLowerCase();
            if(exists(guess))
            {
                List<Character> word = input(guess);
                for(int i = 0; i < word.size(); i++)
                {   if(word.get(i)==answer.get(i))
                        System.out.print(GREEN + word.get(i)+ ANSI_RESET);
                    else if(answer.contains(word.get(i))){
                        System.out.print(YELLOW + word.get(i) + ANSI_RESET);
                    }else{
                    System.out.print(WHITE + word.get(i) + ANSI_RESET);
                    }
                }
                System.out.println();
                if(word.equals(answer)){
                    GuessesLeft = 0;
                    System.out.println("Good job!");
                }
            }
    }


    // Main driver method
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("\n \nWelcome to Wordle!" + 
            "\nThe object of the game is to guess the given 5 letter word in six attempts or less." +
            "\nIf the letter is found within the word and in the correct position, it will print in " + GREEN + "green."+ ANSI_RESET +
            "\nIf the letter is found within the word but not in the correct position, it will print in " + YELLOW + "yellow." + ANSI_RESET +
            "\nIf the letter is not present in the word at all, it will print in " + WHITE + "white." + ANSI_RESET +
            "\n \nBegin by guessing any 5 letter word below.\n");
        
        Scanner input = new Scanner(new File(DICTIONARY_FILE));
        while (input.hasNext()) {
            dictionary.add(input.next().toLowerCase());
        }
        
        List<Character> answer = getWord();
        GuessesLeft = 6;
        Scanner console = new Scanner(System.in);

        while(GuessesLeft > 0){
            playGame(console, answer);
        }
        if(GuessesLeft == 0){
            System.out.print("The correct word was ");
            for(int i = 0; i < answer.size(); i++)
                System.out.print(WHITE + answer.get(i)+ ANSI_RESET);
            System.out.print(". ");
        }
        
    }
}
