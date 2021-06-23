/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.util.Set;

/**
 *
 * @author Gabriela Ramos
 */
public class Pokemon implements Comparable<Pokemon>{
    private String nombre;
    private String poder;
    private String velocidad;
    private String ruta;
    private String tipo;

    public Pokemon(String nombre, String poder,String velocidad, String ruta, String tipo) {
        this.nombre = nombre;
        this.poder = poder;
        this.ruta= ruta;
        this.velocidad= velocidad;
        this.tipo= tipo;
  
    }

    public String getNombre() {
        return nombre;
    }

    public String getPoder() {
        return poder;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public String getRuta() {
        return ruta;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Pokemon{" + "nombre=" + nombre + ", poder=" + poder + ", velocidad=" + velocidad + ", tipo=" + tipo + '}';
    }

    @Override
    public int compareTo(Pokemon o) {
        if(this.tipo.equals(o.tipo)){
            if(Double.parseDouble(this.poder)==Double.parseDouble(o.poder)){
                if(this.nombre.equals(o.nombre)){
                    return 0;
                }else{
                    return this.nombre.compareTo(o.nombre);
                }
                    
            }else if(Double.parseDouble(this.poder)<Double.parseDouble(o.poder)){
                return -1;
            }else if(Double.parseDouble(this.poder)>Double.parseDouble(o.poder)){
                return 1;
            }
        }else{
            return this.tipo.compareTo(o.tipo);
        }
        
        return 0;
    }
    
    
    
         
}
