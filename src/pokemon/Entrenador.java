/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Gabriela Ramos
 */
public class Entrenador implements Comparable<Entrenador>{
    private String nombre;
    private Map<String, Set<Pokemon>> pokemonsTipo;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.pokemonsTipo= new TreeMap<>();
        
    }
    public Entrenador(String nombre,Map<String, Set<Pokemon>> pokemonsTipo) {
        this.nombre = nombre;
        this.pokemonsTipo= pokemonsTipo;
        
    }
    
    public void registro(Pokemon p){
           if (!(pokemonsTipo.containsKey(this.nombre)))
               pokemonsTipo.put(this.nombre, new TreeSet<>());
           pokemonsTipo.get(this.nombre).add(p);
           System.out.println(pokemonsTipo.get(this.nombre));
           
           try{
               for(Entrenador e: Principal.entrenadores){
                   if(e.equals(new Entrenador(nombre,pokemonsTipo))){
                       Principal.entrenadores.remove(e);
                       Principal.entrenadores.add(new Entrenador(nombre,pokemonsTipo));
                   }
               }
           }catch(Exception o){
               
           }
           
        System.out.println(Principal.entrenadores);
        
    }

    

    public Set<Pokemon> getPokemonsTipo() {
        
        return pokemonsTipo.get(this.nombre);
    }
    
    

    @Override
    public String toString() {
        return nombre ;
    }

    @Override
    public int compareTo(Entrenador o) {
        return o.nombre.compareTo(this.nombre) ;
    }

   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entrenador other = (Entrenador) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
    
}
