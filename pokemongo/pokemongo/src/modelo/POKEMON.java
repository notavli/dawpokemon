/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Objects;


public class POKEMON {
    private int pokedex;
    private String nom;
    private String tipus;

    public POKEMON(int pokedex, String nom, String tipus) {
        this.pokedex = pokedex;
        this.nom = nom;
        this.tipus = tipus;
    }

    public int getPokedex() {
        return pokedex;
    }

    public String getNom() {
        return nom;
    }

    public String getTipus() {
        return tipus;
    }

    public void setPokedex(int pokedex) {
        this.pokedex = pokedex;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    @Override
    public String toString() {
        return "POKEMON{" + "con el numeroen la pokedex=" + pokedex + ", nom= " + nom + ", tipus=" + tipus + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof POKEMON)) {
            return false;
        }
        final POKEMON other = (POKEMON) obj;
        return Objects.equals(this.nom, other.nom);
    }
    
    
}
