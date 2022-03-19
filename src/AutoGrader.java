import java.util.*;
import java.io.*;

public class AutoGrader {

    public static void main(String[] args) throws IOException {

        File keyFile = new File("C:\\Users\\Welcome\\IdeaProjects\\CS UIL Programming Practice\\CS_UIL Examples\\src\\key.in");
        File answersFile = new File("C:\\Users\\Welcome\\IdeaProjects\\CS UIL Programming Practice\\CS_UIL Examples\\src\\answers.in");
        File resultsFile = new File("C:\\Users\\Welcome\\IdeaProjects\\CS UIL Programming Practice\\CS_UIL Examples\\src\\results.out");

        String[] key = new String[40];
        String[] answers = new String[40];

        PrintWriter out = new PrintWriter(resultsFile);
        Scanner in = new Scanner(keyFile);
        for(int i=0; i<40; i++) {
            String temp = in.next();
            if(temp.matches("\\d+[)]")) {
                int index = Integer.parseInt(temp.substring(0, temp.length()-1));
                String tempVal = in.next();
                key[index-1] = tempVal;
            } else {
                i--;
            }
        }
        in = new Scanner(answersFile);
        for(int i=0; i<40; i++) {
            String temp = in.next();
            if(isValid(temp)) {
                answers[i] = temp;
            } else {
                i--;
            }
        }
        int correct = 0;
        int incorrect = 0;
        out.println("   Answer:\tKey:");
        for(int i=0; i<40; i++) {
            String matchSymbol = "";
            if(answers[i].equals(key[i])) {
                matchSymbol = "  ---  ";
                correct++;
            } else if(answers[i].equals("--")) {
                matchSymbol = "  S   ";
            } else {
                matchSymbol = "   X   ";
                incorrect++;
            }
            out.println(i+1 +  ") " + answers[i] + matchSymbol + key[i]);
        }

        out.println("\nCorrect: " + correct);
        out.println("Incorrect: " + incorrect);
        out.println("Score: " + (correct*6-incorrect*2));

        System.out.println("View results in \"results.out\"");

        in.close();
        out.close();
    }
    public static boolean isValid(String s) {
        return s.matches("[A-Za-z]") || s.matches("-?\\d+(\\.\\d+)?") || s.equals("--");
    }

}
