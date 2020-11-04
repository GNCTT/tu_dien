package sample;


import com.jfoenix.controls.JFXButton;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    @FXML
    private Button AV;

    @FXML
    private Button VA;

    @FXML
    private Button btSearch;

    @FXML
    private TextField tfSearchedWord;

    @FXML
    private Button btSpeak;

    @FXML
    private ListView<String> lvWords;

    @FXML
    private WebView WebSearch;


    @FXML
    private Pane main;

    @FXML
    private Button Remove;

    @FXML
    private TextField TaddW;

    @FXML
    private TextField MaddW;

    @FXML
    private Button BtAddW;

    @FXML
    private Button Add_Word;

    @FXML
    private Pane them_tu;


    public String path = "E_V.txt";
    Dictionary dic;
    Dictionary dic1 = new Dictionary("E_V.txt");
    Dictionary dic2 = new Dictionary("V_E.txt");


    @FXML
    public void handleClick(ActionEvent e) {
        dic = dic1;
        main.setVisible(true);
        them_tu.setVisible(false);
        WebSearch.setVisible(true);
        lvWords.setVisible(false);
        btSpeak.setVisible(false);
        Remove.setVisible(false);


        if (e.getSource() == AV || e.getSource() == VA) {
            main.setVisible(true);
            Remove.setVisible(false);
            them_tu.setVisible(false);
            if (e.getSource() == AV) {
                dic = dic1;
                path = "E_V.txt";
                tfSearchedWord.clear();
                TaddW.clear();
                MaddW.clear();
            }
            else if (e.getSource() == VA) {
                dic = dic2;
                path = "V_E.txt";
                tfSearchedWord.clear();
                TaddW.clear();
                MaddW.clear();
            }
            WebSearch.setVisible(true);
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
                    WebSearch.getEngine().loadContent(wordMeaning);
                    btSpeak.setVisible(true);
                    WebSearch.setVisible(true);
                    Remove.setVisible(true);
                    lvWords.setVisible(false);
                }

            });

            lvWords.setOnMouseClicked(event -> {
                String searchedWord = lvWords.getSelectionModel().getSelectedItem();
                if (searchedWord != null && searchedWord.equals("") == false) {
                    System.out.println("Searched World: " + searchedWord);
                    String wordMeaning = dic.get(searchedWord);
                    tfSearchedWord.setText(searchedWord);
                    WebSearch.getEngine().loadContent(wordMeaning);
                    btSpeak.setVisible(true);
                    Remove.setVisible(true);
                    lvWords.setVisible(false);
                }

            });

            btSpeak.setOnMouseClicked(event -> {
                Voice voice;
                VoiceManager vm = VoiceManager.getInstance();
                voice = vm.getVoice("kevin16");
                voice.allocate();
                voice.speak(tfSearchedWord.getText());

            });


            Remove.setOnMouseClicked(event -> {
                System.out.println(tfSearchedWord.getText());
                dic.remove(tfSearchedWord.getText(), path);
                dic = new Dictionary(path);
            });
        }
        else if (e.getSource() == Add_Word) {
            WebSearch.setVisible(false);
            main.setVisible(false);
            them_tu.setVisible(true);

            BtAddW.setOnMouseClicked(event -> {
                dic.add(TaddW.getText(), MaddW.getText());
                dic1 = new Dictionary("E_V.txt");
            });


        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(path);

}

    public void initializeWordList()  {

    }
}
