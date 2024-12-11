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
        PIR pirToSave = new PIR(0,"PIR 1");
        pirdao.saveNewPIR(pirToSave);


        List<PIR> savedPIRS = pirdao.getAllPIRs();
        for (PIR pir : savedPIRS) {
            Label pirLabel = new Label(pir.toString());
            vBox.getChildren().add(pirLabel);
        }

    }

}
