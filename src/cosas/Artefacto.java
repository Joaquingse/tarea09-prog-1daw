/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosas;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author mery
 */
public class Artefacto implements Serializable {

    private String nombre;
    private String origen;
    private String poder;

    public Artefacto(String nombre, String origen, String poder) {
        this.nombre = nombre;
        this.origen = origen;
        this.poder = poder;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        this.poder = poder;
    }

    @Override
    public boolean equals(Object obj) {
        Artefacto a;
        boolean esIgual;

        if (super.equals(obj)) {
            esIgual = true;
        } else if (obj.getClass() != this.getClass()) {
            esIgual = false;
        } else {
            a = (Artefacto) obj;
            esIgual = this.getNombre().equals(a.getNombre())
                    && this.getOrigen().equals(a.getOrigen())
                    && this.getPoder().equals(a.getPoder());
        }
        return esIgual;
    }

    @Override
    public String toString() {
        return "Artefacto{" + "nombre=" + nombre + ", origen=" + origen + ", poder=" + poder + '}';
    }
}
