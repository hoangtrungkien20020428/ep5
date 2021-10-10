package backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryCommandline {

  private Dictornary dictor;
  private DictornaryManagement manage;

  public DictionaryCommandline() {
    dictor = new Dictornary();
    manage = new DictornaryManagement(dictor);
  }

  public DictionaryCommandline(Dictornary used_dictor) {
    dictor = used_dictor;
    manage = new DictornaryManagement(dictor);
  }

  public void ShowAllWord() {
    List<Word> dictornary_word = dictor.get_word_with_prefix(null);
    System.out.println("No   |English \t\t |Vietnamese");
    for (int i = 0; i < dictornary_word.size(); i++) {
      StringBuilder output = new StringBuilder();
      output.setLength(40);
      String number = Integer.toString(i + 1);
      for (int j = 0; j < output.length(); j++)
        output.setCharAt(j, ' ');
      for (int j = 0; j < number.length(); j++)
        output.setCharAt(j, number.charAt(j));
      for (int j = 0; j < dictornary_word.get(i).getWord_target().length(); j++) {
        output.setCharAt(j + 6, dictornary_word.get(i).getWord_target().charAt(j));
      }
      for (int j = 0; j < dictornary_word.get(i).getWord_explain().length(); j++) {
        output.setCharAt(j + 26, dictornary_word.get(i).getWord_explain().charAt(j));
      }
      output.setCharAt(5, '|');
      output.setCharAt(25, '|');
      System.out.println(output);
    }
  }

  public void dictionaryBasic() throws IOException {
    //manage.insertFromCommandline();
    manage.insertFromFile();
    this.ShowAllWord();
  }

  public void dictionaryAdvanced() throws IOException {
    manage.insertFromFile();
    this.ShowAllWord();
    manage.dictionaryLookup();
  }


  public static void main(String[] args) throws IOException {
    DictionaryCommandline test = new DictionaryCommandline();
    test.dictionaryBasic();
    test.manage.dictionaryExportToFile("data/Dictionaries.txt");
    test.ShowAllWord();
  }
}
