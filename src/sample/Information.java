package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.*;

public class Information {
    @FXML
    private ComboBox<String> combo_list;
    @FXML
    private Label text_label;
    @FXML
    private void initialize() {

        try {
            fileread();//вызов метода подключения текстового файла с названиями
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileread() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("src/sample/list.txt"));//подключение файла с названиями заболеваний
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {//добавление элементов в список combobox
                combo_list.getItems().add(line);

                sb.append(line);//вставляет элемент в список
                line = br.readLine();

                combo_list.setOnAction(event -> {
                    info();
                });
            }

        } finally {
            br.close();
        }
        combo_list.getSelectionModel().select(0);

    }
    private void info(){
        try {
            BufferedReader br2 = new BufferedReader(new FileReader("src/sample/symptom.txt"));
            String line_two;
            while (br2.lines() != null) {
                line_two = br2.readLine();
                while (line_two.contains(combo_list.getSelectionModel().getSelectedItem())){
                    text_label.setText(line_two);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
