package main;
/**
 * controller dung de dieu khien file sample.fxml sample.fxml co the sua bang open screenbuider
 */

import backend.DictornaryManagement;
import backend.Word;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.speech.AudioException;
import javax.speech.EngineException;

public class ControllerSearch {

  private final DictornaryManagement dic = new DictornaryManagement();
  private final AnchorPane achorpane = null;
  @FXML
  protected TextField Height;
  @FXML
  protected TextArea textArea;
  @FXML
  protected ListView<String> listView;
  List<String> list = new LinkedList<String>();
  List<Word> ListWord;

  {
    try {
      ListWord = dic.InsetFromOnlineDatabase();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * cai nay la nhap tu vao textField
   */
  public void inputText(KeyEvent event) {
    String word_Input = Height.getText();
    if (word_Input != null) {
      listView.getItems().setAll(dic.dictionarySearcher(word_Input));
    }
    if (dic.searhWord(word_Input) != null) {
      textArea.setText(dic.searhWord(word_Input));
    }
    if (word_Input.equals("")) {
      textArea.clear();
    }
  }

  /**
   * cai nay dung chuot chon tu nhung tu goi y
   */
  @FXML
  public void HandleMouseListWord(MouseEvent event) {
    String word = listView.getSelectionModel().getSelectedItem();
    if (word.equals("")) {
      return;
    } else {
      Height.setText(word);
      textArea.setText(dic.searhWord(word));
    }
  }

  public void HandleButtonSpeech(ActionEvent event)
      throws PropertyVetoException, AudioException, EngineException, InterruptedException {
    String word = Height.getText();
    TextToSpeech textToSpeech = new TextToSpeech();
    textToSpeech.init("kevin16");
    textToSpeech.doSpeak(word);
  }

  public void gotoAddWord(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("controlAddWord.fxml"));
    Parent sampleParent = loader.load();
    Scene scene = new Scene(sampleParent);
    stage.setScene(scene);
  }

  public void gotoEditWord(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("editWord.fxml"));
    Parent sampleParent = loader.load();
    Scene scene = new Scene(sampleParent);
    stage.setScene(scene);
  }
}
