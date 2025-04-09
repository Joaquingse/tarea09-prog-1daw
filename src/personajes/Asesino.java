/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personajes;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author mery
 */
public class Asesino extends Personaje implements Serializable {

    private int edad;
    public RangoAsesinos rango;
    private String arma_favorita;

    public Asesino(String nombre, int edad, RangoAsesinos rango, String armaFavorita) {
        super(nombre);
        this.edad = edad;
        this.rango = rango;
        this.arma_favorita = armaFavorita;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public RangoAsesinos getRango() {
        return rango;
    }

    public void setRango(RangoAsesinos rango) {
        this.rango = rango;
    }

    public String getArma_favorita() {
        return arma_favorita;
    }

    public void setArma_favorita(String arma_favorita) {
        this.arma_favorita = arma_favorita;
    }

    @Override
    public String toString() {
        return "Asesino{" + "nombre=" + this.nombre + ", edad=" + edad + ", rango=" + rango + ", arma_favorita=" + arma_favorita + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        return hash;
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
        final Asesino other = (Asesino) obj;
        return this.nombre.equals(other.nombre);
    }
}
