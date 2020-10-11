package sample;
import java.io.*;
import java.util.Arrays;

public class Dictionary {
    private Word [] dictionary;
    public void show() {
        for ( int i = 0; i< dictionary.length; i++) {
            System.out.println(dictionary[i].getWord_target());
        }
    }
    public Dictionary () {
        try {
            Word ac = new Word();
            ac.setWord_target("a");
            ac.setWord_explain("b");
            dictionary = new Word[1];
            dictionary[0] = new Word();
            dictionary[0] = ac;
            System.out.println(dictionary[0].getWord_target());
            File myFile = new File("D:\\New folder\\tu_dien_data\\E_V.txt");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while((line = reader.readLine()) != null) {
                int m = 0;
                for ( int i = 0; i< line.length(); i++) {
                    if (line.charAt(i) == '<') {
                        m = i;
                        break;
                    }
                }
                Word a = new Word();
                a.setWord_target(line.substring(0, m));
                a.setWord_explain(line.substring(m, line.length()));
                dictionary = Arrays.copyOf(dictionary, dictionary.length + 1);
                dictionary[dictionary.length - 1] = new Word();
                dictionary[dictionary.length - 1] = a;

            }
            System.out.println(dictionary[1].getWord_target());
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public int binarysearch(Word[] words, String s, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (s.compareTo(words[mid].getWord_target()) == 0) {
                return mid;
            }
            if (s.compareTo(words[mid].getWord_target()) > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public String get(String s) {
        if (binarysearch(dictionary, s, 0, dictionary.length - 1) >= 0) {
            return dictionary[binarysearch(dictionary, s, 0, dictionary.length - 1)].getWord_explain();
        } else {
            return " ";
        }
    }
    public boolean check_w(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        else {
            if (s1.equals(s2.substring(0, s1.length()))) {
                return true;
            }
        }
        return false;
    }
    public String [] Suggest(String s) {
        String [] suggest = new String[20];
        for (  int i = 0; i< 20; i++) {
            suggest[i] = " ";
        }
        int suggest_n = 0;
        for ( int i = 0; i< dictionary.length ; i++) {
            if (check_w(s, dictionary[i].getWord_target()) && suggest_n < 20) {
                suggest[suggest_n] = dictionary[i].getWord_target();
                suggest_n++;
            }
        }
        return suggest;
    }
}
