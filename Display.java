package sample;

public class Display {
    Dictionary display;

    public String get(String s) {
        display = new Dictionary();
        String m = "a";
        for ( int i = 0; i < display.words.length; i++) {
            if ( display.words[i].getWord_target().equals(s)) {
                m = display.words[i].getWord_explain();
            }
        }
        return m;
    }
}
