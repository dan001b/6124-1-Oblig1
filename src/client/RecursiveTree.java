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
 * 6124-1 21H Algoritmer og datastrukturer Oblig1-grupper 1
 *
 * @author Mustafa Waleed Alqaisy (studentnummer: 216557)
 * @author Sindre Andreas Olsen Strømnæss
 */
public class RecursiveTree extends Application {

    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;

    private OptionPanel optionPane = new OptionPanel();

    @Override
    public void start(Stage primaryStage) {

        // oppretter knapp for å tegne treet
        Button redraw = new Button("Tegn tre");

        // oppretter paneler for tegnebretter og for instillinger
        Pane canvas = new Pane();
        BorderPane mainboard = new BorderPane(canvas);
        canvas.setStyle(
                "-fx-background-color: #F0F8FF ; -fx-border-color: black;"
        );

        StackPane actionBox = new StackPane(redraw);
        actionBox.setPadding(new Insets(10, 10, 10, 10));
        mainboard.setTop(new HBox(optionPane, actionBox));

        mainboard.setCenter(canvas);

        Scene scene = new Scene(mainboard, WIDTH, HEIGHT);

        primaryStage.setTitle("Recursive tree");
        primaryStage.setScene(scene);
        primaryStage.show();

        // tegner treet ved oppstart
        drawTree(canvas, optionPane.getAngle(), optionPane.getDepth(), optionPane.getWeight(), optionPane.getTrunksize());

        // binder handling for tegne knappen
        redraw.setOnAction(e -> {

            // sletter alt innhold i tegnebrettet
            canvas.getChildren().removeAll(canvas.getChildren());

            //System.out.println(optionPane.getAngle()+"  :   "+optionPane.getDepth()+"  :   "+optionPane.getWeight()+"  :   "+optionPane.getTrunksize());
            // tegner nytt tre
            drawTree(canvas, optionPane.getAngle(), optionPane.getDepth(), optionPane.getWeight(), optionPane.getTrunksize());
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
     * tegner rekursiv tree
     * 
     * @param canvas panelet der linejene skal legges til
     * @param xStart x koordinat for punktet der treet starter
     * @param yStart y koordinat for punktet der treet starter
     * @param angle vinkel for første linje brukes i rekrusjon for å beregne
     * påfølgene vinkler
     * @param angleOffset endring i vinkel for påfølgende grener
     * @param depth antall nivåer som skal tegnes
     * @param weight vekting av hvor sannsynlig
     */
    public void drawTree(Pane canvas, int xStart, int yStart, double angle, double angleOffset, int depth, double weight, double trunkSize) {

        // Setter opp en par varslinger ved ugyldig verdi for variabler
        if (weight < 0 || weight > 1) {
            throw new IllegalArgumentException("weight must be greater or equal to 0 and smaller or equal to 1.0");
        }

        if (trunkSize < 0) {
            throw new IllegalArgumentException("trunkSize must be greater or equal to 0");
        }
        
        // Sørger for tilnærmet tilfeldig lengde basert på vekting
        int lengthRand = 5;
        if (Math.random() < weight) {
            lengthRand = (int) (10 * Math.random());
        }
        
        // finner koordinatene for punkt der vektoren ender, 
        // beregnes basert på vinkelen oppgitt for metoden 
        int xEnd = xStart + (int) (Math.cos(Math.toRadians(angle)) * depth * lengthRand);
        int yEnd = yStart + (int) (Math.sin(Math.toRadians(angle)) * depth * lengthRand);

        //lager 2D linjeobjekt med start og slutt koordinater
        Line ln = new Line(xStart, yStart, xEnd, yEnd);

        // beregner differanse i x og i y
        int difX = xEnd - xStart;
        int difY = yEnd - yStart;

        // beregnere lengden for linjen
        double length = Math.sqrt(difX * difX + difY * difY);

        // Base case hvis lengden er mindre enn 1 returneres
        if (length < 1) {
            return;
        }

        //storrelse paa trestamme (tykkelse)
        ln.setStrokeWidth(trunkSize);

        // Halverer trestamme kun hvis tykkelse er mer enn 1 pixel
        if (trunkSize > 1) {
            trunkSize = trunkSize * 0.5;
        }

        // legger til linjen på tegnebrettet
        canvas.getChildren().add(ln);

        // Følgende betingelser sørger for å hoppe over grener tilnærmet tilfeldig
        // Sannsynligheten vektes av sluttbruker
        double randAngle = 1;

        if (Math.random() > weight) {
            
            // Setter tilfeldig vinkel basert på vekting
            if (Math.random() < weight) {
                 randAngle = angleOffset * Math.random();
            }
            
            //tegner ny gren
            drawTree(canvas, xEnd, yEnd, angle - angleOffset*randAngle, angleOffset,  depth - 1, weight, trunkSize);
        }
        if (Math.random() > weight) {
            
            // Viss sansynlighet for å setter ny tilfeldig vinkel basert på vekting
            randAngle = 1;
            if (Math.random() < weight) {
                 randAngle = angleOffset * Math.random();
            }
            
            //tegner ny gren
            drawTree(canvas, xEnd, yEnd, angle + angleOffset*randAngle, angleOffset, depth - 1, weight, trunkSize);
        }
    }

    /**
     * Drivermetode Tar imot kun de essensielle variablene, regner og setter
     * resten
     */
    public void drawTree(Pane canvas, double angleOffset, int dybde, double weight, double trunkSize) {

        // treets start posisjon beregnes basert på størrelse for tegneflate
        int xForste = (int) canvas.getWidth() / 2;
        int yForste = (int) (canvas.getHeight() * 0.75);

        // kaller hjelpemetoden
        drawTree(canvas, xForste, yForste, -90, angleOffset, dybde, weight, trunkSize);

    }

}
