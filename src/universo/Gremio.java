/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universo;

import cosas.Artefacto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import personajes.Asesino;
import personajes.RangoAsesinos;

/**
 *
 * @author mery
 */
public class Gremio {

    private LinkedHashSet<Asesino> lista_asesinos;
    private ArrayList<Artefacto> inventario;

    public Gremio() {
        lista_asesinos = new LinkedHashSet();
        inventario = new ArrayList();
    }

    public void insertarAsesino(Asesino asesino) {
        lista_asesinos.add(asesino);
    }

    public boolean eliminarAsesino(String nombre) {
        return lista_asesinos.remove(new Asesino(nombre, 0, null, null));
    }

    public void mostrarAsesinos() {
        System.out.println("\nLISTADO DE ASESINOS");
        // Creamos un iterador a partir de la lista para recorrerla
        Iterator<Asesino> it = lista_asesinos.iterator();
        Asesino a;

        while (it.hasNext()) {
            a = it.next();
            System.out.println(a);
        }
    }

    public void agregarAInventario(Artefacto a) {
        inventario.add(a);
    }

    public void mostrarInventario() {
        System.out.println("\nCONTENIDO DEL INVENTARIO");
        Iterator<Artefacto> it = inventario.iterator();

        Artefacto a;

        while (it.hasNext()) {
            a = it.next();
            System.out.println(a);
        }
    }

    public boolean eliminarDeInventario(String nombre) {
        Artefacto a;
        boolean eliminado = false;

        for (int i = 0; i < inventario.size() && !eliminado; i++) {
            a = inventario.get(i);

            if (a.getNombre().equals(nombre)) {
                inventario.remove(a);
                eliminado = true;
            }
        }

        return eliminado;
    }

    public void guardarAsesinosEnArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        DataOutputStream escribir = null;
        try {
            escribir = new DataOutputStream(new FileOutputStream(archivo));
            escribir.writeInt(this.lista_asesinos.size());
            for (Asesino asesino : this.lista_asesinos) {
                escribir.writeUTF(asesino.getNombre());
                escribir.writeInt(asesino.getEdad());
                escribir.writeUTF(asesino.getRango().name());
                escribir.writeUTF(asesino.getArma_favorita());
                System.out.println("guardando " + asesino);
            }
            System.out.println(archivo + " ha sido guardado correctamente.");
        } catch (FileNotFoundException fnf) {
            System.out.println("No se encuentra el archivo: " + fnf.getMessage());
        } catch (IOException ioe) {
            System.out.println("Hay un error " + ioe.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } finally {
            try {
                if (escribir != null) {
                    escribir.close();
                }
            } catch (IOException ex) {
                System.err.println("Error, el archivo no ha podido cerrarse: " + ex.getMessage());
            }
        }
    }

    public LinkedHashSet<Asesino> cargarAsesisnosDesdeArchivo(String nombreArchivo) {
        DataInputStream leer = null;
        LinkedHashSet<Asesino> nuevaLista = new LinkedHashSet<>();
        try {
            leer = new DataInputStream(new FileInputStream(nombreArchivo));
            int cantidadAsesinos = leer.readInt();
            for (int i = 0; i < cantidadAsesinos; i++) {
                try {
                    String nombre = leer.readUTF();
                    int edad = leer.readInt();
                    RangoAsesinos rango = RangoAsesinos.valueOf(leer.readUTF());
                    String arma = leer.readUTF();
                    Asesino a = new Asesino(nombre, edad, rango, arma);
                    nuevaLista.add(a);
                } catch (EOFException eof) {
                    System.out.println("Datos Leidos: " + eof);
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("No se encuentra el archivo: " + fnf.getMessage());
        } catch (IOException ioe) {
            System.out.println("Hay un error " + ioe.getMessage());
        } finally {
            try {
                if (leer != null) {
                    leer.close();
                }
            } catch (IOException ex) {
                System.err.println("Error, el archivo no ha podido cerrarse: " + ex.getMessage());
            }
        }
        return this.lista_asesinos = nuevaLista;
    }

    public void guardarInventarioEnArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        ObjectOutputStream escribir = null;
        try {
            escribir = new ObjectOutputStream(new FileOutputStream(archivo));
            escribir.writeObject(this.inventario);
            System.out.println(nombreArchivo + " ha sido guardado correctamente.");
        } catch (FileNotFoundException fnf) {
            System.out.println("No se encuentra el archivo: " + fnf.getMessage());
        } catch (IOException ioe) {
            System.out.println("Hay un error " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } finally {
            try {
                if (escribir != null) {
                    escribir.close();
                }
            } catch (IOException ex) {
                System.err.println("Error, el archivo no ha podido cerrarse: " + ex.getMessage());
            }
        }
    }

    public ArrayList<Artefacto> cargarInventarioDesdeArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        ArrayList<Artefacto> inventarioGremio = new ArrayList<>();
        ObjectInputStream leer = null;
        try {
            leer = new ObjectInputStream(new FileInputStream(archivo));
            inventarioGremio = (ArrayList<Artefacto>) leer.readObject();
        } catch (FileNotFoundException fnf) {
            System.out.println("No se encuentra el archivo: " + fnf.getMessage());
        } catch (IOException ioe) {
            System.out.println("Hay un error " + ioe.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gremio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (leer != null) {
                    leer.close();
                }
            } catch (IOException ex) {
                System.err.println("Error, el archivo no ha podido cerrarse: " + ex.getMessage());
            }
        }
        return inventario = inventarioGremio;
    }

    public void exportarDAtos(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (Asesino asesino : lista_asesinos) {
                bw.write(asesino.getNombre() + "," + asesino.getEdad() + "," + asesino.getRango().name() + "," + asesino.getArma_favorita() + "\n");
            }
        } catch (IOException ioe) {
            System.out.println("Hay un error " + ioe.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                System.err.println("Error, el archivo no ha podido cerrarse: " + ex.getMessage());
            }
        }
    }

    public LinkedHashSet<Asesino> importarDatos(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        BufferedReader br = null;
        LinkedHashSet<Asesino> nuevaLista = new LinkedHashSet<>();
        String linea;
        try {
            br = new BufferedReader(new FileReader(archivo));
            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(",");
                String nombre = atributos[0];
                int edad = Integer.parseInt(atributos[1]);
                RangoAsesinos rango = RangoAsesinos.valueOf(atributos[2]);
                String arma = atributos[3];
                Asesino a = new Asesino(nombre, edad, rango, arma);
                nuevaLista.add(a);
            }

        } catch (IOException ioe) {
            System.out.println("Hay un error " + ioe.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.err.println("Error, el archivo no ha podido cerrarse: " + ex.getMessage());
            }
        }
        return lista_asesinos = nuevaLista;
    }

}
