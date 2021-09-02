/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author mussal
 */
public class main extends Application {
    
    final int WIDTH = 800;
    final int HEIGHT = 800;
    OptionPanel optionpane = new OptionPanel();
    
    @Override
    public void start(Stage primaryStage) {
        
        Button redraw = new Button("Tegn tre");
             
        Pane canvas = new Pane();
        BorderPane mainboard = new BorderPane(canvas);
        canvas.setStyle(
                "-fx-background-color: #F0F8FF ; -fx-border-color: black;"
        );
        
        StackPane actionBox = new StackPane(redraw);
        actionBox.setPadding(new Insets(10,10,10,10));
        mainboard.setTop(new HBox(optionpane, actionBox));
        
        mainboard.setCenter(canvas);
        

        Scene scene = new Scene(mainboard, WIDTH, HEIGHT);

        primaryStage.setTitle("Recursive tree");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // tegner treet ved oppstart
        main.this.drawTree(canvas, optionpane.getAngle(), optionpane.getDepth(), optionpane.getWeight());
        
        // binder handling for tegne knappen
        redraw.setOnAction(e -> {
            canvas.getChildren().removeAll(canvas.getChildren());
            System.out.println(optionpane.getAngle()+"  :   "+optionpane.getDepth()+"  :   "+optionpane.getWeight());
            main.this.drawTree(canvas, optionpane.getAngle(), optionpane.getDepth(), optionpane.getWeight());
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
     /**
      * rekursiv hjelpemetode
     */
    public void drawTree(Pane canvas, int xForste, int yForste, double angle, double angleOffset, int depth, double weight) {
        
        // Base case hvis 
        if (depth < 0) {
            return;
        }
        
        // finner 
        int xAndre = xForste + (int) (Math.cos(Math.toRadians(angle)) * depth * 5);
        int yAndre = yForste + (int) (Math.sin(Math.toRadians(angle)) * depth * 5);

        canvas.getChildren().addAll(new Line(xForste, yForste, xAndre, yAndre));
        if (Math.random()>weight){
            drawTree(canvas, xAndre, yAndre, angle - angleOffset,angleOffset, depth - 1,weight);
        }
        if (Math.random()>weight){
            drawTree(canvas, xAndre, yAndre, angle + angleOffset,angleOffset, depth - 1,weight);
        }
    }
    
    /**
    * Drivermetode
    */
    public void drawTree(Pane canvas,double angleOffset, int dybde, double weight){
        
        // treets posisjon basert på størrelse for tegneflate
        int xForste = (int) canvas.getWidth() / 2;
        int yForste = (int) (canvas.getHeight()*0.75);
        
        // kaller hjelpemetoden
        drawTree(canvas, xForste, yForste, -90, angleOffset, dybde, weight);
        
    }
    
    

}
