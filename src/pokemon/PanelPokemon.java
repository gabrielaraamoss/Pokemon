/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;
/**
 *
 * @author Gabriela Ramos
 */

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundPosition.CENTER;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;
import static javafx.scene.layout.BackgroundSize.DEFAULT;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class PanelPokemon {
    private final BorderPane root = new BorderPane();
    private final Pane paneJuego = new Pane();
    private final ComboBox<Entrenador> entrenadores = new ComboBox();
    private final HBox panelTop= new HBox(new Label("Entrenador: "),entrenadores);
    private final HBox panelInferior= new HBox(new Label("EN ESTA SECCION IRAN LOS DATOS DEL POKEMON QUE SE ATRAPARA"));
    private final FlowPane panelDerecho = new FlowPane(Orientation.VERTICAL);
    private VBox Der=new VBox();
    private PathTransition transition;
    public PanelPokemon() {

        
        entrenadores.getItems().addAll(Principal.entrenadores);
        root.setTop(panelTop);
        root.setBottom(panelInferior);
        
       paneJuego.setStyle("-fx-background-image: url('"+getClass().getResource("fondo.jpg").toExternalForm()+"');"
                + "-fx-background-repeat: stretch;"             
                + "-fx-background-position: top center;");
        
        entrenadores.setOnAction(e->llenarPanelDer());
        root.setCenter(paneJuego);
         
        root.setRight(panelDerecho);
        
        Thread t1 = new Thread(new HiloPokemon());
        t1.start();
    }
    
    public void aggInformacion(Pokemon p){
        Label nombre= new Label("Nombre: "+p.getNombre()+" | "+"Poder: "+p.getPoder()+" | "+"Velocidad: "+p.getVelocidad()+" | "+"Tipo: "+p.getTipo());
        panelInferior.getChildren().clear();
        panelInferior.getChildren().addAll(nombre);
        
    }
    public static  int indice;
    public ImageView agregarPokemon(){
       
        
        String ruta=Principal.pokemonsLibres.get(indice).getRuta();
        try{
            ImageView pokemon= new ImageView(ruta); 
            aggInformacion(Principal.pokemonsLibres.get(indice));
        }catch(Exception x){
            System.out.println(ruta);
            System.out.println(x);
            
        }
        ImageView pokemon= new ImageView(ruta);  
        pokemon.setFitHeight(150);
        pokemon.setFitWidth(100);
        paneJuego.getChildren().clear();
        paneJuego.getChildren().add(pokemon);
        pokemon.setOnMouseClicked(e->clickPokemon(Principal.pokemonsLibres.get(indice)));
        return pokemon;
    }
    
    private void llenarPanelDer(){
        Entrenador trainer = entrenadores.getValue();
        for(Entrenador e: Principal.entrenadores){
               if(e.equals(trainer)){
                   try{
                       panelDerecho.getChildren().clear();
                       
                       for(Pokemon p:e.getPokemonsTipo()){
                           ImageView imgPoke=new ImageView();
                           imgPoke.setImage(new Image(p.getRuta()));
                           Der=new VBox();
                           Der.setAlignment(Pos.BASELINE_CENTER);
                           Der.getChildren().addAll(new Label(p.getTipo().toUpperCase()),imgPoke,new Label(p.getNombre()),new Label(p.getPoder()),new Label("============"));
                           panelDerecho.getChildren().addAll(Der);
                           System.out.println(p);
                       }
                   }catch(NullPointerException ec){
                       System.out.println(ec);
                   }
               }
        }
           
       

    }
    
     private void clickPokemon(Pokemon p) {
        if(entrenadores.getValue()!=null){
            Entrenador trainer = entrenadores.getValue();
            trainer.registro(p);
            System.out.println(trainer);
            Principal.pokemonsLibres.remove(p);
            paneJuego.getChildren().clear();

            llenarPanelDer();
        }
        else{
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Atención");
        alert.setHeaderText("Porfavor seleccione un entrenador!");
        
        alert.showAndWait();
        }
    }
    
    void animar(){
        Random r = new Random();
        int num1 = r.nextInt((int) paneJuego.getWidth()-200);
        int num2 = r.nextInt((int) paneJuego.getHeight()-200);
        
        Rectangle linea = new Rectangle(num1, num2);
        linea.setLayoutX(200);
        linea.setLayoutY(200);
       
                           
         transition = new PathTransition();

         transition.setNode(agregarPokemon());
         transition.setDuration(Duration.millis(10000-(Integer.parseInt(Principal.pokemonsLibres.get(indice).getVelocidad())*1000)));
         transition.setCycleCount(Timeline.INDEFINITE);
         transition.setPath(linea);
         transition.setAutoReverse(true); 
         transition.play();
      }
    
    public BorderPane getRoot() {
        return root;
    }
    
       
    
    class HiloPokemon implements Runnable{
        @Override
        public void run(){
            while(!Principal.pokemonsLibres.isEmpty()){
                try {
                    Random r = new Random();
                    
                    if(Principal.pokemonsLibres.size()-1==0)
                        indice=0;
                    else
                        indice=r.nextInt(Principal.pokemonsLibres.size()-1);
                    
                    Platform.runLater(()->animar());

                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    System.out.println("Error: "+ex);
                }
            }
        }
    }
    
 
    
    
}
