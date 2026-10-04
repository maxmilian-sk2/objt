package com.example.s2z2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class HelloController {
    @FXML private Label resMM, resCM, resM, resKM;
    @FXML private TextField inputNum;
    @FXML private RadioButton isMM, isCM, isM, isKM;
    @FXML private ToggleGroup selector;

    @FXML protected void calculateUnits(){
        Float num = Float.parseFloat(inputNum.getText());
        RadioButton selected = (RadioButton) selector.getSelectedToggle();
        if (selected == isMM){
            resMM.setText("mm: " + String.valueOf(num));
            resCM.setText("cm: " + String.valueOf(num/10));
            resM.setText("m: " + String.valueOf(num/1000));
            resKM.setText("km: " + String.valueOf(num/1000000));
        } else if (selected == isCM) {
            resMM.setText("mm: " + String.valueOf(num*10));
            resCM.setText("cm: " + String.valueOf(num));
            resM.setText("m: " + String.valueOf(num/100));
            resKM.setText("km: " + String.valueOf(num/100000));
        }  else if (selected == isM) {
            resMM.setText("mm: " + String.valueOf(num*1000));
            resCM.setText("cm: " + String.valueOf(num/100));
            resM.setText("m: " + String.valueOf(num));
            resKM.setText("km: " + String.valueOf(num/1000));
        }  else {
            resMM.setText("mm: " + String.valueOf(num*1000000));
            resCM.setText("cm: " + String.valueOf(num*100000));
            resM.setText("m: " + String.valueOf(num*1000));
            resKM.setText("km: " + String.valueOf(num));
        }
    }

}
