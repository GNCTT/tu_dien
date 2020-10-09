package sample;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    Word [] words;
    public Dictionary() {
        String [] wd = {"ability","Khả năng\n" ,
                "able","thể\n" ,
                "accept", "chấp nhận\n" ,
                "acceptable", "chấp nhận được\n" ,
                "accident", "Tai nạn\n" ,
                "according", "theo\n" ,
                "account", "tính toán\n" ,
                "accurate", "chính xác\n" ,
                "achieve", "đạt được\n" ,
                "act", "hành động\n" ,
                "action", "hành động\n" ,
                "active", "hoạt động\n" ,
                "activity", "Hoạt động\n" ,
                "actor", "Diễn viên\n" ,
                "actual", "thực tế\n" ,
                "ad", "Quảng cáo\n" ,
                "add", "thêm vào\n" ,
                "addition", "Thêm vào\n" ,
                "additional", "thêm\n" ,
                "administration", "Quyền quản trị\n" ,
                "administrative", "hành chính\n" ,
                "admit", "thừa nhận\n" ,
                "adult", "người lớn\n" ,
                "advertising", "Quảng cáo\n" ,
                "advice", "Lời khuyên\n" ,
                "affair", "Việc\n" ,
                "affect", "có ảnh hưởng đến\n" ,
                "afford", "đủ khả năng\n" ,
                "afraid", "sợ\n" ,
                "after", "sau\n" ,
                "afternoon", "buổi chiều"};
        words = new Word[wd.length/2];
        for ( int i = 0; i< wd.length/2; i++) {
            words[i] = new Word();
        }
        for ( int i = 0; i < wd.length/2; i++) {
            words[i].setWord_target(wd[i * 2]);
            words[i].setWord_explain(wd[2 * i + 1]);

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
        if (binarysearch(words, s, 0, words.length - 1) >= 0) {
            return words[binarysearch(words, s, 0, words.length - 1)].getWord_explain();
        } else {
            return "a";
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
    public Map<String, String> Suggest(String s) {
        Map<String, String> dictionary = new HashMap<String, String>();
        int suggest_n = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].getWord_target().compareTo(s) > 0 && check_w(s, words[i].getWord_target()) && suggest_n < 8 ) {
                dictionary.put(words[i].getWord_target(), words[i].getWord_explain());
                suggest_n++;
            }

        }
        return dictionary;
    }

}
