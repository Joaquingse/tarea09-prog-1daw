/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import cosas.Artefacto;
import java.util.Scanner;
import personajes.Asesino;
import personajes.RangoAsesinos;
import personajes.Templario;
import universo.Gremio;
import universo.Temple;
import java.io.File;

/**
 *
 * @author joaqu
 */
public class Menu {

    Gremio gremio = new Gremio();
    Temple temple = new Temple();

    String asesinosListBin = "data\\asesinos.bin";
    String templariosList = "data\\templarios.dat";
    String inventarioList = "data\\inventario.dat";
    String asesinosTxt = "data\\asesinos.txt";
    String templariosTxt = "data\\templarios.txt";

    public void menu() {
        System.out.println("\nMenú principal");
        System.out.println("1- Gestión de asesinos");
        System.out.println("2- Gestión de templarios");
        System.out.println("3- Gestión de artefactos");
        System.out.println("4- Salir");
    }

    public void menuAsesinos() {
        System.out.println("Gestion de asesinos");
        System.out.println("1- Añadir asesino");
        System.out.println("2- Eliminar asesino");
        System.out.println("3- Guardar");
        System.out.println("4- Cargar");
        System.out.println("5- Exportar a TXT");
        System.out.println("6- Importar desde TXT");
        System.out.println("7- Salir");
    }

    public void menuTemplarios() {
        System.out.println("Gestion de templarios");
        System.out.println("1- Añadir templario");
        System.out.println("2- Eliminar templario");
        System.out.println("3- Guardar");
        System.out.println("4- Cargar");
        System.out.println("5- Exportar a TXT");
        System.out.println("6- Importar desde TXT");
        System.out.println("7- Salir");
    }

    public void menuArtefactos() {
        System.out.println("Gestion de artefactos");
        System.out.println("1- Añadir al inventario");
        System.out.println("2- Eliminar del inventario");
        System.out.println("3- Guardar");
        System.out.println("4- Cargar");
        System.out.println("5- Salir");
    }

    public static int validarOpcionMenu(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println(
                    "Seleccione entre las opciones disponibles: Asesinos (1), Templarios (2), Artefactos (3), salir (4):");
            sc.next();
        }
        int opcionValida = sc.nextInt();
        while (opcionValida < 1 || opcionValida > 4) {
            System.out.println("Seleccione una opcion valida:");
            opcionValida = sc.nextInt();
        }
        return opcionValida;
    }

    public static int validarOpcionSubMenu(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println(
                    "Seleccione entre las opciones disponibles:\n añadir (1), eliminar (2), guardar (3), cargar (4), exportar (5), importar (6), salir (7):");
            sc.next();
        }
        int opcionValida = sc.nextInt();
        while (opcionValida < 1 || opcionValida > 7) {
            System.out.println("Seleccione una opcion valida:");
            opcionValida = sc.nextInt();
        }
        return opcionValida;
    }

    public void iniciarMenu() {
        boolean carpeta = new File("data").mkdirs();
        if (!carpeta) {
            if (carpeta) {
                System.out.println("Directorio 'data' creado correctamente.");
            } else {
                System.out.println("No se pudo crear el directorio 'data'.");
            }
        }

        Scanner sc = new Scanner(System.in);
        int opcionPrincipal;
        int opcionSub;

        do {
            menu();
            opcionPrincipal = validarOpcionMenu(sc);

            switch (opcionPrincipal) {
                //gestion de asesinos
                case 1 -> {
                    do {
                        menuAsesinos();
                        opcionSub = validarOpcionSubMenu(sc);
                        switch (opcionSub) {
                            case 1 -> {
                                sc.nextLine();
                                System.out.println("Introduce los siguientes datos -->\n Nombre:");
                                String nombre = sc.nextLine();
                                System.out.println("Edad:");
                                int edad = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Rango:");
                                String rango = sc.nextLine();
                                System.out.println("Arma favorita:");
                                String arma = sc.nextLine();
                                Asesino asesino = new Asesino(nombre, edad, RangoAsesinos.valueOf(rango), arma);
                                gremio.cargarAsesisnosDesdeArchivo(asesinosListBin);
                                gremio.insertarAsesino(asesino);
                                System.out.println("Añadiendo asesino: " + asesino);
                            }

                            case 2 -> {
                                sc.nextLine();
                                System.out.println("Introduce el nombre del asesino a eliminar:");
                                String nombre = sc.nextLine();
                                gremio.eliminarAsesino(nombre);
                                System.out.println("Eliminando asesino");
                            }

                            case 3 -> {
                                gremio.guardarAsesinosEnArchivo(asesinosListBin);
                                System.out.println("Guardado");
                            }

                            case 4 -> {
                                gremio.cargarAsesisnosDesdeArchivo(asesinosListBin);
                                gremio.mostrarAsesinos();
                            }
                            case 5 -> {
                                gremio.exportarDAtos(asesinosTxt);
                                System.out.println("Guardado");
                            }
                            case 6 -> {
                                gremio.importarDatos(asesinosTxt);
                                gremio.mostrarAsesinos();
                            }
                            case 7 ->
                                System.out.println("Volviendo al menú principal");
                        }
                    } while (opcionSub != 7);
                }
                //gestion de templarios
                case 2 -> {
                    do {
                        menuTemplarios();
                        opcionSub = validarOpcionSubMenu(sc);
                        switch (opcionSub) {
                            case 1 -> {
                                sc.nextLine();
                                System.out.println("Introduce los siguientes datos -->\n Nombre:");
                                String nombre = sc.nextLine();
                                System.out.println("Organización:");
                                String organizacion = sc.nextLine();
                                System.out.println("Nivel de peligrosisdad");
                                int peligro = sc.nextInt();
                                temple.cargarTemplariosDesdeArchivo(templariosList);
                                Templario templario = new Templario(nombre, organizacion, peligro);
                                temple.insertar(templario);
                                System.out.println("Añadiendo templario");
                            }
                            case 2 -> {
                                sc.nextLine();
                                System.out.println("Introduce el nombre del templario a eliminar:");
                                String nombre = sc.nextLine();
                                temple.eliminar(nombre);
                                System.out.println("Eliminado templario");
                            }
                            case 3 -> {
                                temple.gurdarTemplariosEnArchivo(templariosList);
                                System.out.println("Guardado");
                            }
                            case 4 -> {
                                temple.cargarTemplariosDesdeArchivo(templariosList);
                                temple.mostrar(true);
                            }
                            case 5 -> {
                                temple.exportarDAtos(templariosTxt);
                                System.out.println("Guardado");
                            }
                            case 6 -> {
                                temple.importarDAtos(templariosTxt);
                                temple.mostrar(false);
                            }
                            case 7 ->
                                System.out.println("Volviendo al menú principal...");
                        }
                    } while (opcionSub != 7);
                }
                //gestion del inventario
                case 3 -> {
                    do {
                        menuArtefactos();
                        opcionSub = validarOpcionSubMenu(sc);
                        switch (opcionSub) {
                            case 1 -> {
                                sc.nextLine();
                                System.out.println("Introduce los siguientes datos -->\n Nombre:");
                                String nombre = sc.nextLine();
                                System.out.println("Origen:");
                                String origen = sc.nextLine();
                                System.out.println("Poder:");
                                String poder = sc.nextLine();
                                Artefacto artefacto = new Artefacto(nombre, origen, poder);
                                gremio.cargarInventarioDesdeArchivo(inventarioList);
                                gremio.agregarAInventario(artefacto);
                                System.out.println("Añadiendo artefacto al inventario");
                            }
                            case 2 -> {
                                sc.nextLine();
                                System.out.println("Artefacto a eliminar:");
                                String nombre = sc.nextLine();
                                gremio.eliminarDeInventario(nombre);
                                System.out.println("Eliminando artefacto del inventario");
                            }
                            case 3 -> {
                                gremio.guardarInventarioEnArchivo(inventarioList);
                                System.out.println("Guardado");
                            }
                            case 4 -> {
                                gremio.cargarInventarioDesdeArchivo(inventarioList);
                                gremio.mostrarInventario();
                            }
                            case 5 ->
                                System.out.println("Volviendo al menú principal");
                        }
                    } while (opcionSub != 5);
                }
                case 4 ->
                    System.out.println("Saliendo de la aplicación");
            }
        } while (opcionPrincipal != 4);

        sc.close();
    }

}
