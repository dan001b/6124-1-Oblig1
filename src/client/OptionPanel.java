/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author mussal
 */
public class OptionPanel extends HBox {

    private Slider angleSlider;
    private Slider depthSlider;
    private Slider probSlider;

    public OptionPanel() {

        GridPane sliderP = new GridPane();
        sliderP.setPadding(new Insets(0, 5, 0, 5));

        angleSlider = new Slider(0, 90, 35);
        formatSlider(angleSlider,10);

        depthSlider = new Slider(0, 15, 9);
        formatSlider(depthSlider,2);
        
        probSlider = new Slider(0, 0.1, 0.01);
        formatSlider(probSlider, 0.01);
        
        // legger til slider i sliderP, dette er gridpane
        // settes ved å oppgi posisjon
        sliderP.add(new Label("Vinkel: "), 0, 0);
        sliderP.add(angleSlider, 1, 0);
        

        sliderP.add(new Label("Antall nivåer: "), 0, 1);
        sliderP.add(depthSlider, 1, 1);
        
        sliderP.add(new Label("Loss: "), 3, 0);
        sliderP.add(probSlider, 4, 0);

        getChildren().addAll(sliderP);

        //setStyle("-fx-background-color: lightgrey; -fx-border-style: dashed");

        setPadding(new Insets(10, 10, 10, 10));
    }
    
    private void formatSlider(Slider s, double majorTick){
        s.setSnapToTicks(true);
        s.setShowTickMarks(true);
        s.setShowTickLabels(true);
        s.setMajorTickUnit(majorTick);
        s.setMinorTickCount((int) majorTick/2);
        s.setPadding(new Insets(2, 20, 2, 0));
    }

    public double getAngle() {
        return angleSlider.getValue();
    }

    public int getDepth() {
        return (int) depthSlider.getValue();
    }
    
    public double getWeight() {
        return probSlider.getValue();
    }

}
