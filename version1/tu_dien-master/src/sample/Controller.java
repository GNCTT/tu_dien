package sample;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    @FXML
    public Button btSearch;

    @FXML
    public TextField tfSearchedWord;

    @FXML
    public ListView<String> lvWords;

    @FXML
    public WebView browser;

    @FXML
    public Button speak;
    @FXML
    private Pane read;

    @FXML
    private Pane translate;
    @FXML
    private Pane add;

    @FXML
    private Button AV;

    @FXML
    private Button VA;

    @FXML
    private Button ADD;

    @FXML
    private Button Read;

    @FXML
    private TextArea sword;
    public String path = "D:\\New folder\\tu_dien_data\\E_V.txt";
    Dictionary dic;
    Dictionary dic1 = new Dictionary("E_V.txt");
    Dictionary dic2 = new Dictionary("D:\\New folder\\tu_dien_data\\V_E.txt");

    @FXML
    public void handleClick(ActionEvent e) {
        dic = dic1;
        if (e.getSource() == AV || e.getSource() == VA) {
            if (e.getSource() == AV) {
                dic = dic1;
            }
            else if (e.getSource() == VA) {
                dic = dic2;
            }
            translate.setVisible(true);
            add.setVisible(false);
            read.setVisible(false);
            translate.setVisible(true);
            add.setVisible(false);
            read.setVisible(false);
            lvWords.setVisible(false);



            tfSearchedWord.setOnKeyReleased(event -> {

                lvWords.setVisible(true);
                String newWord = tfSearchedWord.getText() ;
                System.out.println(newWord.length());
                System.out.println(newWord);
                lvWords.getItems().clear();
                lvWords.getItems().addAll(dic.Suggest(newWord));
                if (newWord.length() == 0) {
                    lvWords.getItems().clear();
                }

                lvWords.toFront();
            });
            btSearch.setOnMouseClicked(event -> {
                System.out.println("Search!!!");
                String searchedWord = tfSearchedWord.getText();
                if (searchedWord != null && searchedWord.equals("") == false) {
                    System.out.println("Searched World: " + searchedWord);
                    String wordMeaning = dic.get(searchedWord);
                    sword.setText(searchedWord);
                    browser.getEngine().loadContent(wordMeaning);
                    lvWords.setVisible(false);
                }

                browser.toFront();
                speak.toFront();
            });
            this.initializeWordList();
            lvWords.setOnMouseClicked(event -> {
                String searchedWord = lvWords.getSelectionModel().getSelectedItem();
                if (searchedWord != null && searchedWord.equals("") == false) {
                    System.out.println("Searched World: " + searchedWord);
                    String wordMeaning = dic.get(searchedWord);
                    tfSearchedWord.setText(searchedWord);
                    sword.setText(searchedWord);
                    browser.getEngine().loadContent(wordMeaning);
                    lvWords.setVisible(false);
                }

                browser.toFront();
                speak.toFront();
            });
        }
        else if (e.getSource() == ADD) {
            add.setVisible(true);
            translate.setVisible(false);
            read.setVisible(false);
            dic.remove("bye-bye");
            dic.add("zenx", "hello");
            dic1 = new Dictionary("E_V.txt");
        }
        else if (e.getSource() == Read) {
            read.setVisible(true);
            translate.setVisible(false);
            add.setVisible(false);
        }
        else if (e.getSource() == speak) {
            Voice voice;
            VoiceManager vm = VoiceManager.getInstance();
            voice = vm.getVoice("kevin16");
            voice.allocate();
            voice.speak(sword.getText());
        }

    }



    Map<String, String> dictionary = new HashMap<String, String>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(path);



}

    public void initializeWordList()  {

    }
}
