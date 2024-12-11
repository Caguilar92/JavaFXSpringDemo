package com.eaglewerx.javafxspringdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.eaglewerx.javafxspringdemo.FXApplication.StageReadyEvent;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("${spring.application.title}")
    private String applicationTitle;
    private FxWeaver fxWeaver;


    public StageInitializer(FxWeaver fxWeaver) {
    this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {

        Stage stage = event.getStage();
        stage.setScene(new Scene(fxWeaver.loadView(FXController.class), 800, 600));
        stage.setTitle(applicationTitle);
        stage.show();
    }
}
