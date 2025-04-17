/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;

import cosas.Artefacto;
import java.io.File;
import personajes.Asesino;
import personajes.RangoAsesinos;
import personajes.Templario;
import universo.Gremio;
import universo.Temple;

/**
 *
 * @author mery
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gremio gremio = new Gremio();
        Temple temple = new Temple();

        boolean carpeta = new File("data").mkdirs();
        if (!carpeta) {
            if (carpeta) {
                System.out.println("Directorio 'data' creado correctamente.");
            } else {
                System.out.println("No se pudo crear el directorio 'data'.");
            }
        }

        String asesinosListBin = "data\\asesinos.bin";
        String templariosList = "data\\templarios.dat";
        String inventarioList = "data\\inventario.dat";
        String asesinosTxt = "data\\asesinos.txt";
        String templariosTxt = "data\\templarios.txt";

        // Creación de los objetos Asesino
        gremio.insertarAsesino(new Asesino("Ezio Auditore", 32, RangoAsesinos.MENTOR, "Hoja oculta doble"));
        gremio.insertarAsesino(new Asesino("Altaïr Ibn-La'Ahad", 25, RangoAsesinos.MAESTRO, "Espada de los Asesinos"));
        gremio.insertarAsesino(new Asesino("Shao Jun", 27, RangoAsesinos.INICIADO, "Hoja de zapato"));

        // Muestra la lista de asesinos
        gremio.mostrarAsesinos();

        // Vuelve a añadir a Shao Jun
        System.out.println("\nSe vuelve a añadir a la asesina Shao Jun.");
        gremio.insertarAsesino(new Asesino("Shao Jun", 27, RangoAsesinos.INICIADO, "Hoja de zapato"));

        // Muestra la lista de asesinos
        gremio.mostrarAsesinos();

        // Elimina a Shao Jun
        System.out.println("\nSe hace UNA eliminación de Shao Jun");
        gremio.eliminarAsesino("Shao Jun");

        // Muestra de nuevo la lista de asesinos
        gremio.mostrarAsesinos();
        gremio.guardarAsesinosEnArchivo(asesinosListBin);
        gremio.exportarDAtos(asesinosTxt);
        // Creación de los objetos Templario
        temple.insertar(new Templario("Rodrigo Borgia", "Orden del Temple", 9));
        temple.insertar(new Templario("Haytham Kenway", "Abstergo", 8));
        temple.insertar(new Templario("César Borgia", "Orden del Temple", 10));

        // Muestra los templarios ordenados por nivel de peligrosidad.
        temple.mostrar(false);
        temple.gurdarTemplariosEnArchivo(templariosList);
        temple.exportarDAtos(templariosTxt);
        // Crea los artefactos y al inventario
        gremio.agregarAInventario(new Artefacto("Fruto del Edén", "Precursores", "Control mental y manipulación"));
        gremio.agregarAInventario(new Artefacto("Bastón de Hermes", "Precursores", "Otorga inmortalidad"));
        gremio.agregarAInventario(new Artefacto("Espada del Edén", "Precursores", "Poder destructivo y habilidades sobrenaturales"));
        gremio.agregarAInventario(new Artefacto("Cáliz del Edén", "Precursores", "Sanación y longevidad"));

        // Muestra el inventario
        gremio.mostrarInventario();

        // Añade de nuevo un fruto del Edén
        System.out.println("\nSe vuelve a añadir una Espada del Edén");
        gremio.agregarAInventario(new Artefacto("Espada del Edén", "Precursores", "Poder destructivo y habilidades sobrenaturales"));
        gremio.mostrarInventario();

        // Elimina la Espada del Edén
        System.out.println("\nSe elimina una Espada del Edén");
        gremio.eliminarDeInventario("Espada del Edén");

        // Muestra el inventario
        gremio.mostrarInventario();
        gremio.guardarInventarioEnArchivo(inventarioList);
        //menu de la aplicación
        Menu menu = new Menu();
        menu.iniciarMenu();
    }

}
