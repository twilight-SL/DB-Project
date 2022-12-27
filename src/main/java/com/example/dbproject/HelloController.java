package com.example.dbproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.dbproject.SQLiteJDBC.*;

public class HelloController implements Initializable {
    @FXML
    private Label detectText;
    @FXML
    private TextField textInput;
    @FXML
    private TextField tableInput;
    @FXML
    private TextArea textArea;
    static TextArea staticTxtArea;
    @FXML
    private Button btnQuery;
    @FXML
    private Button btnSelect;
    @FXML
    private ComboBox<String> comboBox;

    private String[] options = {"SELECT-FROM-WHERE", "DELETE", "INSERT","UPDATE", "IN","NOT IN", "EXISTS", "NOT EXISTS", "COUNT","SUM", "MAX","MIN", "AVG","HAVING"};
    private ObservableList<String> OptionsList = FXCollections.observableArrayList(options);


    @FXML
    public void onButtonClick() {
        detectText.setText(textInput.getText());
        String r = textInput.getText();
        switch (r.substring(0,6)){
            case "SELECT":
                switch (r.substring(7,10)){
                    case "COU":
                        if(r.substring(17, 18).equals("H"))
                            selectPart("HAVING", textInput.getText());
                        else
                            selectPart("COUNT", textInput.getText());
                        break;
                    case "SUM":
                        selectPart("SUM", textInput.getText());
                        break;
                    case "max":
                        selectPart("MAX", textInput.getText());
                        break;
                    case "min":
                        selectPart("MIN", textInput.getText());
                        break;
                    case "avg":
                        selectPart("AVG", textInput.getText());
                        break;
                    default:
                        selection(tableInput.getText(), textInput.getText());
                        break;
                }
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
     * EXISTS: SELECT Name FROM "Hotel Group Brand" WHERE EXISTS(SELECT * FROM "Hotel Group Brand" WHERE Country = 'Japan');
     * NOT EXISTS: SELECT Name FROM "Hotel Group Brand" WHERE NOT EXISTS(SELECT * FROM "Hotel Group Brand" WHERE Country = 'Japan');
     * COUNT: SELECT Country, COUNT(*) FROM 'Hotel Group Brand' GROUP BY Country;
     * SUM: SELECT SUM(Amount) FROM Payment;
     * MAX: SELECT max(Amount) FROM Payment;
     * MIN: SELECT min(Amount) FROM Payment;
     * AVG: SELECT avg(Amount) FROM Payment;
     * HAVING: SELECT "HGB_Business No", COUNT(*) FROM Room GROUP BY "HGB_Business No" HAVING count(*) > 10;
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comboBox.setItems(OptionsList);
        staticTxtArea = textArea;
    }

    @FXML
    public void onComboBoxClick() {
        detectText.setText(comboBox.getValue());
        String tableName;
        String textCommands;
        String targetCommand;

        switch (comboBox.getValue()){
            case "SELECT-FROM-WHERE":
                tableName = "Hotel Group Brand";
                textCommands = "SELECT * FROM 'Hotel Group Brand' WHERE Country = 'Taiwan'";
                selection(tableName, textCommands);
                break;

            case "DELETE":
                tableName = "Hotel Group Brand";
                textCommands = "DELETE FROM 'Hotel Group Brand' WHERE Name = 'CH2'";
                deletion(textCommands);
                selection(tableName, "SELECT * FROM 'Hotel Group Brand';");
                break;

            case "INSERT":
                tableName = "Hotel Group Brand";
                textCommands = "INSERT INTO 'Hotel Group Brand' VALUES ('CH2','Taiwan',88613, 'No. 26, Zhitan 8th Street, Xindian District, New Taipei', '+886-944639031',98, 'L115964593')";
                insertion(textCommands);
                selection(tableName, "SELECT * FROM 'Hotel Group Brand';");
                break;

            case "UPDATE":
                tableName = "Hotel Group Brand";
                textCommands = "UPDATE 'Hotel Group Brand' set Rating = 1 WHERE Name = 'AYI'";
                update(textCommands);
                selection(tableName, "SELECT * FROM 'Hotel Group Brand';");
                break;

            case "IN":
                tableName = "Hotel Group Brand";
                textCommands = "SELECT * FROM 'Hotel Group Brand' WHERE \"Business No\" IN (98008,33003,31211,74010,55009);";
                selection(tableName, textCommands);
                break;

            case "NOT IN":
                tableName = "Hotel Group Brand";
                textCommands = "SELECT * FROM 'Hotel Group Brand' WHERE \"Business No\" NOT IN (98008,33003,31211,74010,55009);";
                selection(tableName, textCommands);
                break;

            case "EXISTS":
                tableName = "Hotel Group Brand";
                textCommands = "SELECT * FROM \"Hotel Group Brand\" WHERE EXISTS(SELECT * FROM \"Hotel Group Brand\" WHERE Country = 'Japan');";
                selection(tableName, textCommands);
                break;

            case "NOT EXISTS":
                tableName = "Hotel Group Brand";
                textCommands = "SELECT * FROM \"Hotel Group Brand\" WHERE NOT EXISTS(SELECT * FROM \"Hotel Group Brand\" WHERE Country = 'Japan');";
                selection(tableName, textCommands);
                break;

            case "COUNT":
                targetCommand = "COUNT";
                textCommands = "SELECT Country, COUNT(*) FROM 'Hotel Group Brand' GROUP BY Country;";
                selectPart(targetCommand, textCommands);
                break;

            case "SUM":
                targetCommand = "SUM";
                textCommands = "SELECT SUM(Amount) FROM Payment;";
                selectPart(targetCommand, textCommands);
                break;

            case "MAX":
                targetCommand = "MAX";
                textCommands = "SELECT max(Amount) FROM Payment;";
                selectPart(targetCommand, textCommands);
                break;

            case "MIN":
                targetCommand = "MIN";
                textCommands = "SELECT min(Amount) FROM Payment;";
                selectPart(targetCommand, textCommands);
                break;

            case "AVG":
                targetCommand = "AVG";
                textCommands = "SELECT avg(Amount) FROM Payment;";
                selectPart(targetCommand, textCommands);
                break;

            case "HAVING":
                targetCommand = "HAVING";
                textCommands = "SELECT \"HGB_Business No\", COUNT(*) FROM Room GROUP BY \"HGB_Business No\" HAVING count(*) > 10;";
                selectPart(targetCommand, textCommands);
                break;
        }
    }
}
