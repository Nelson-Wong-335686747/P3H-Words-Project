import java.net.*;    
import java.io.*;    
import java.util.ArrayList;
    /**
 * Write a description of class Reader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Reader  
{
    //This class reads and adds all the words from the given url to the given ArrayList:
    static String url = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english-no-swears.txt&quot";

    public static void readInto(ArrayList<String> list) throws Exception {
        URL wordsURL = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(wordsURL.openStream()));
        String word;
        while ((word = in.readLine()) != null){
            list.add(word);
        }
        in.close();
    }
}

