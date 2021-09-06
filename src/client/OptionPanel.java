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
 * 6124-1 21H Algoritmer og datastrukturer Oblig1-grupper 1
 *
 * @author Mustafa Waleed Alqaisy (studentnummer: 216557)
 * @author Sindre Andreas Olsen Strømnæss (studentnummer: 233595)
 *
 * Klassen lager slidere for flere verdier tilbyr enkle get metoder for å hente
 * verdiene
 */
public class OptionPanel extends HBox {

    private Slider angleSlider;
    private Slider depthSlider;
    private Slider weightSlider;
    private Slider trunkSlider;

    public OptionPanel() {

        GridPane sliderP = new GridPane();
        sliderP.setPadding(new Insets(0, 5, 0, 5));

        angleSlider = new Slider(0, 90, 20);
        formatSlider(angleSlider, 10);

        depthSlider = new Slider(0, 15, 12);
        formatSlider(depthSlider, 2);

        weightSlider = new Slider(0, 10, 1);
        formatSlider(weightSlider, 2);

        trunkSlider = new Slider(1, 30, 20);
        formatSlider(trunkSlider, 10);

        // legger til slider i sliderP, dette er gridpane
        // posisjon settes ved å oppgi koordinater
        sliderP.add(new Label("Vinkel: "), 0, 0);
        sliderP.add(angleSlider, 1, 0);

        sliderP.add(new Label("Antall nivåer: "), 0, 1);
        sliderP.add(depthSlider, 1, 1);

        sliderP.add(new Label("Tilfeldighet: "), 3, 0);
        sliderP.add(weightSlider, 4, 0);

        sliderP.add(new Label("Stamme tykkelse:"), 3, 1);
        sliderP.add(trunkSlider, 4, 1);

        getChildren().addAll(sliderP);

        setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * metoden formaterer slider
     *
     * @param s slider som skal formateres
     * @param majourTick intervall for den største markør hoppet det minste
     * hoppet settes som halvparten av den største
     */
    private void formatSlider(Slider s, double majorTick) {
        s.setSnapToTicks(true);
        s.setShowTickMarks(true);
        s.setShowTickLabels(true);
        s.setMajorTickUnit(majorTick);
        s.setMinorTickCount((int) majorTick / 2);
        s.setPadding(new Insets(2, 20, 2, 0));
    }

    public double getAngle() {
        return angleSlider.getValue();
    }

    public int getDepth() {
        return (int) depthSlider.getValue();
    }

    public double getWeight() {
        return weightSlider.getValue()/100;
    }

    public double getTrunksize() {
        return trunkSlider.getValue();
    }

}
