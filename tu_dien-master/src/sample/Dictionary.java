package sample;
import java.io.*;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Dictionary {
    private Word[] dictionary;

    public void show() {
        for (int i = 0; i < dictionary.length; i++) {
            System.out.println(dictionary[i].getWord_target());
        }
    }

    public Dictionary(String path) {
        try {
            Word ac = new Word();
            ac.setWord_target("a");
            ac.setWord_explain("b");
            dictionary = new Word[1];
            dictionary[0] = new Word();
            dictionary[0] = ac;
            System.out.println(dictionary[0].getWord_target());
            File myFile = new File(path);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while ((line = reader.readLine()) != null) {
                int m = 0;
                for (int i = 0; i < line.length(); i++) {
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
        } catch (Exception e) {
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
        } else {
            if (s1.equals(s2.substring(0, s1.length()))) {
                return true;
            }
        }
        return false;
    }

    public String[] Suggest(String s) {
        String[] suggest = new String[20];
        for (int i = 0; i < 20; i++) {
            suggest[i] = " ";
        }
        int suggest_n = 0;
        for (int i = 0; i < dictionary.length; i++) {
            if (check_w(s, dictionary[i].getWord_target()) && suggest_n < 20) {
                suggest[suggest_n] = dictionary[i].getWord_target();
                suggest_n++;
            }
        }
        String[] l_suggest = new String[suggest_n];
        for (int i = 0; i < suggest_n; i++) {
            l_suggest[i] = suggest[i];
        }
        return l_suggest;
    }

    public void remove(String s, String path) {
        int num = binarysearch(dictionary, s, 0, dictionary.length - 1);
        if (num != -1) {
            try {
                File file1 = new File(path);
                file1.delete();
                if (!file1.exists()) {
                    file1.createNewFile();
                }
                FileWriter fw = new FileWriter(file1);
                for (int i = 1; i < dictionary.length; i++) {
                    if (i != num) {
                        fw.write(dictionary[i].getWord_target() + dictionary[i].getWord_explain() + "\n");
                    }
                }
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ko thay tu nay");
        }
    }

    public void add(String s, String smean) {
        System.out.println(binarysearch(dictionary, s, 0, dictionary.length - 1));
        if (binarysearch(dictionary, s, 0, dictionary.length - 1) >= 0) {
            System.out.println("co roi");
        } else {
            int num = 0;
            for (int i = 0; i < dictionary.length; i++) {
                if (s.compareTo(dictionary[i].getWord_target()) < 0) {
                    num = i - 1;
                    break;
                }
            }
            try {
                File file1 = new File("E_V.txt");
                file1.delete();
                if (!file1.exists()) {
                    file1.createNewFile();
                }
                FileWriter fw = new FileWriter(file1);
                for (int i = 1; i < num; i++) {
                    fw.write(dictionary[i].getWord_target() + dictionary[i].getWord_explain() + "\n");
                }
                fw.write(s + "<html><i>" + s + "</i><br/><ul><li><font color='#cc0000'><b>" + smean + "</b></font></li></ul></html>" + "\n");
                for (int i = num; i < dictionary.length; i++) {
                    fw.write(dictionary[i].getWord_target() + dictionary[i].getWord_explain() + "\n");
                }
                fw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}

