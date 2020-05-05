package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;

public class Main extends Application implements EventHandler<Event> {

    public static void main(String[] args) { launch(args);}

    public Stage main_stage;

    @Override
    public void start(Stage stage) {
        main_stage = stage;
        SetTask();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, this);
        stage.show();
    }



    @Override
    public void handle(Event event) {
        if(event instanceof KeyEvent){
            KeyEvent keve = (KeyEvent) event;
            switch (keve.getCode()){
                case R: {
                    SetTask();
                    break;
                }
            }
        }

    }

    private void SetTask() {
        Task task = new Task();
        Scene scene = task.getScene();
        Camera camera = new PerspectiveCamera;
        scene.setCamera(camera);
        scene.setFill(Color.WHITE);
        main_stage.setScene(scene);
    }


}
