package com.example.dbproject;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;

import static com.example.dbproject.SQLiteJDBC.*;

public class HelloController {
    @FXML
    private Label detectText;
    @FXML
    private TextField textInput;
    @FXML
    private TextField tableInput;
    @FXML
    private Button btnQuery;
    @FXML
    private ComboBox comboBox;

    @FXML
    private TextArea textArea;
    @FXML
    public void onButtonClick() {
        detectText.setText(textInput.getText());
        String r = textInput.getText();
        System.out.println(r.substring(0,6));

        switch (r.substring(0,6)){
            case "SELECT":
                selection(tableInput.getText(), textInput.getText());
                break;
            case "INSERT":
                insertion(textInput.getText());
                break;
            case "DELETE":
                deletion(textInput.getText());
                break;
            case "UPDATE":
                update(textInput.getText());
                break;
        }

    }

    /*
     * SELECT-FROM-WHERE: SELECT * FROM 'Hotel Group Brand' WHERE Country = 'Taiwan'
     * DELETE: DELETE FROM 'Hotel Group Brand' WHERE Name = 'CH2'
     * INSERT: INSERT INTO 'Hotel Group Brand' VALUES ('CH2','Taiwan',88613, 'No. 26, Zhitan 8th Street, Xindian District, New Taipei', '+886-944639031',98, 'L115964593')
     * UPDATE: UPDATE 'Hotel Group Brand' set Rating = 1 WHERE Name = 'AYI'
     * IN: SELECT * FROM 'Hotel Group Brand' WHERE "Business No" IN (98008,33003,31211,74010,55009)
     * NOT IN: SELECT * FROM 'Hotel Group Brand' WHERE "Business No" NOT IN (98008,33003,31211,74010,55009)
     * EXISTS:
     * NOT EXISTS:
     * COUNT: SELECT Country, COUNT(*) FROM 'Hotel Group Brand' GROUP BY Country;
     * SUM: SELECT SUM(Amount) FROM Payment;
     * MAX: SELECT max(Amount) FROM Payment;
     * MIN: SELECT min(Amount) FROM Payment;
     * AVG: SELECT avg(Amount) FROM Payment;
     * HAVING: SELECT "HGB_Business No", COUNT(*) FROM Room GROUP BY "HGB_Business No" HAVING count(*) > 10;
    */
    @FXML
    public void dropDownMenu(){
//        List<String> items = List.of("SELECT-FROM-WHERE", "DELETE", "INSERT","UPDATE", "IN","NOT IN", "EXISTS","NOT EXISTS"
////                , "COUNT","SUM", "MAX","MIN", "AVG","HAVING");
////        vbox.setPadding(new Insets(20, 0, 0, 0));
////        vbox.setSpacing(10);
////        comboBox.getItems().addAll(items);
        String commands[] = { "SELECT-FROM-WHERE", "DELETE", "INSERT","UPDATE", "IN","NOT IN", "EXISTS","NOT EXISTS"
                        , "COUNT","SUM", "MAX","MIN", "AVG","HAVING" };
        comboBox.getItems().addAll(commands);
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(comboBox, btnQuery);
    }

}
