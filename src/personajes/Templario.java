/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personajes;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author mery
 */
public class Templario extends Personaje implements Comparator, Serializable {

    private String organizacion;
    private int nivel_peligrosidad;

    public Templario(String nombre, String organizacion, int nivel_peligrosidad) {
        super(nombre);
        this.organizacion = organizacion;
        this.nivel_peligrosidad = nivel_peligrosidad;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public int getNivel_peligrosidad() {
        return nivel_peligrosidad;
    }

    public void setNivel_peligrosidad(int nivel_peligrosidad) {
        this.nivel_peligrosidad = nivel_peligrosidad;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Templario t1, t2;
        t1 = (Templario) o1;
        t2 = (Templario) o2;

        int resultado = Integer.compare(t1.getNivel_peligrosidad(), t2.getNivel_peligrosidad());

        // Si son iguales, utiliza otro criterio (organización):
        if (resultado == 0) {
            resultado = t1.getOrganizacion().compareTo(t2.getOrganizacion());
            // Si aún son iguales, compara por nombre:
            if (resultado == 0) {
                resultado = t1.getNombre().compareTo(t2.getNombre());
            }
        }

        return resultado;
    }

    @Override
    public String toString() {
        return "Templario{" + "nombre=" + this.getNombre() + ", organizacion=" + organizacion + ", nivel_peligrosidad=" + nivel_peligrosidad + '}';
    }

}
