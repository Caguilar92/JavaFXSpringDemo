package com.eaglewerx.javafxspringdemo;

import com.eaglewerx.PIR;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("/main.fxml")
public class FXController {
    private PIRDAO pirdao;
    private int pirCount;
    @FXML
    private VBox vBox;
    public FXController(PIRDAO pirdao) {
        this.pirdao = pirdao;
    }

    @FXML
    protected void savePIR() {
        /**
         * just adding 0 as a place holder not actaully used in the
         * dao because the table will auto generate one for you
         */
        PIR pirToSave = new PIR(0,"PIR " + ++pirCount);
        pirdao.saveNewPIR(pirToSave);
        vBox.getChildren().clear();
        List<PIR> savedPIRS = pirdao.getAllPIRs();
        for(int i = 0;  i < savedPIRS.size();  i++) {
            Label pirLabel = new Label(savedPIRS.get(i).getName());
            vBox.getChildren().add(pirLabel);
        }


    }

}
