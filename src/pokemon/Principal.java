/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Gabriela Ramos
 */
public class Principal extends Application {
    
    
    public static ArrayList<Entrenador> entrenadores= new ArrayList<>();
    public static ArrayList<Pokemon> pokemonsLibres=new ArrayList<>();

    public static void main(String[] args) {

        llenarDatosEntrenador();
        launch(args);
    }
    public static void llenarDatosEntrenador(){
        entrenadores.add(new Entrenador("Ash"));
        entrenadores.add(new Entrenador("Missi"));
        entrenadores.add(new Entrenador("Brooke"));
        lecturaArchivoSimple("pokemonslibres.txt");
        
    }
    
    public static void lecturaArchivoSimple(String inFilename) {
       Charset charset = Charset.forName("ISO-8859-1");
       try{
           ArrayList<String> lineas= (ArrayList<String>) Files.readAllLines(Paths.get(inFilename), charset);
           int d=0;
           for(String c:lineas){
               String[] datos=c.split(",");
               d++;
               if (d!=1)
                pokemonsLibres.add(new Pokemon(datos[0],datos[1], datos[2], datos[3], datos[4]));
           }
                      
       }catch(IOException ex){
           System.out.println("Error en lectura de archivos NIO: "+ex);
           
       }
        
    }

    @Override
    public void start(Stage primaryStage) {
           Scene sc = new Scene(new PanelPokemon().getRoot(),600,400);
           primaryStage.setScene(sc);
           primaryStage.setOnCloseRequest(e->System.exit(0));
           primaryStage.show();
           sc.setCursor(new ImageCursor(new Image("/images/Pokeball_icon-icons.com_67448.png")));
    }
}
