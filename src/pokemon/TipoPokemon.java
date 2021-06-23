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
public class TipoPokemon implements Comparable<TipoPokemon>{
    private String tipo;

    public TipoPokemon(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    @Override
    public int compareTo(TipoPokemon t) {
        return this.tipo.compareTo(t.tipo);
    }
    
}
