package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button btSearch;

    @FXML
    public TextField tfSearchedWord;

    @FXML
    public ListView<String> lvWords;

    @FXML
    public TextArea taMeaning;

    Map<String, String> dictionary = new HashMap<String, String>();
    Dictionary display = new Dictionary();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tfSearchedWord.setOnKeyTyped(event -> {
            String newWord = tfSearchedWord.getText() + event.getCharacter();
            System.out.println(newWord);
            System.out.println(display.Suggest(newWord));
            lvWords.getItems().clear();

            lvWords.getItems().addAll(display.Suggest(newWord).keySet());

        });

        btSearch.setOnMouseClicked(event -> {
            System.out.println("Search!!!");
            String searchedWord = tfSearchedWord.getText();
            if (searchedWord != null && searchedWord.equals("") == false) {
                System.out.println("Searched World: " + searchedWord);
                String wordMeaning = display.get(searchedWord);

                taMeaning.setText(wordMeaning);
            }
        });
        this.initializeWordList();
        lvWords.setOnMouseClicked(event -> {
            String searchedWord = lvWords.getSelectionModel().getSelectedItem();
            if (searchedWord != null && searchedWord.equals("") == false) {
                System.out.println("Searched World: " + searchedWord);
                String wordMeaning = dictionary.get(searchedWord);
                taMeaning.setText(wordMeaning);
            }
        });
    }

    public void initializeWordList() {
        dictionary.put("hello", "Xin chao");
        dictionary.put("thank you", "Cam on");
        dictionary.put("school", "Truong hoc");
        dictionary.put("class", "Lop hoc");
        dictionary.put("in", "trong");
        lvWords.getItems().addAll(dictionary.keySet());
    }
}
