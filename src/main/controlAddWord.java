package main;

import backend.DictornaryManagement;
import backend.Word;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controlAddWord {

  @FXML
  private TextField InputAddWordTagert;
  @FXML
  private TextArea InputAddWordExplain;
  private final DictornaryManagement dictornaryManagement = new DictornaryManagement();
  private final List<Word> list = dictornaryManagement.InsetFromOnlineDatabase();

  public controlAddWord() throws IOException {
  }

  private boolean check(String wordTarget) {
    for (Word word : list) {
      if (word.getWord_target().equals(wordTarget)) {
        return false;
      }
    }
    return true;
  }

  @FXML
  public void saveWord(ActionEvent event) throws IOException {
    String WordTarget = InputAddWordTagert.getText();
    String WordExplain = InputAddWordExplain.getText();
    Word wordNew = new Word(WordTarget, WordExplain);
    if (check(WordTarget)) {
      try (FileWriter fw = new FileWriter("data/OnlineDatabase.txt", true);
          BufferedWriter bw = new BufferedWriter(fw);
          PrintWriter out = new PrintWriter(bw)) {
        out.println("@" + WordTarget);
        //more code
        out.println(WordExplain);
        out.print("@");
        out.println("");
        //more code

      } catch (IOException e) {
      }
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setContentText("successful");
      alert.show();
    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setContentText("ERROR'S");
      alert.show();
    }

  }

  public void backToDictionary(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("sample.fxml"));
    Parent sampleParent = loader.load();
    Scene scene = new Scene(sampleParent);
    stage.setScene(scene);
  }
}


