/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.TreeSet;
import personajes.Templario;

/**
 *
 * @author mery
 */
public class Temple {

    private TreeSet<Templario> lista_templarios;

    public Temple() {
        lista_templarios = new TreeSet(new ComparadorTemplarios());
    }

    public void insertar(Templario templario) {
        lista_templarios.add(templario);
    }

    public boolean eliminar(String nombre) {
        boolean resultado = false;
        Iterator i = lista_templarios.iterator();
        Templario t;
        while (i.hasNext()) {
            t = (Templario) i.next();
            if (t.getNombre().equals(nombre)) {
                i.remove();
            }
        }
        return resultado;
    }

    public void mostrar(boolean ascendente) {
        Iterator<Templario> it;
        System.out.println("\nTEMPLARIOS ORDENADOS POR ORDEN " + (ascendente ? "ASCENDENTE" : "DESCENDENTE") + " DE PELIGROSIDAD");
        // Creamos un iterador a partir de la lista para recorrerla
        if (ascendente) {
            it = lista_templarios.iterator();
        } else {
            it = lista_templarios.descendingIterator();
        }

        Templario t;

        while (it.hasNext()) {
            t = it.next();
            System.out.println(t);
        }
    }

    public void gurdarTemplariosEnArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        ObjectOutputStream escribir = null;
        try {
            escribir = new ObjectOutputStream(new FileOutputStream(archivo));
            escribir.writeObject(lista_templarios);
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

    public TreeSet<Templario> cargarTemplariosDesdeArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        TreeSet<Templario> templarios = new TreeSet<>(new ComparadorTemplarios());
        ObjectInputStream leer = null;
        try {
            leer = new ObjectInputStream(new FileInputStream(archivo));
            System.out.println(leer.readObject().toString());
            templarios = (TreeSet<Templario>) leer.readObject();
        } catch (FileNotFoundException fnf) {
            System.out.println("No se encuentra el archivo: " + fnf.getMessage());
        } catch (IOException ioe) {
            System.out.println("Hay un error: " + ioe.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Ha ocurrido un error: " + ex.getMessage());
        } finally {
            try {
                if (leer != null) {
                    leer.close();
                }
            } catch (IOException ex) {
                System.err.println("Error, el archivo no ha podido cerrarse: " + ex.getMessage());
            }
        }
        return this.lista_templarios = templarios;
    }

    public void exportarDAtos(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (Templario templario : lista_templarios) {
                bw.write(templario.getNombre() + "," + templario.getOrganizacion() + "," + templario.getNivel_peligrosidad() + "\n");
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

    public TreeSet<Templario> importarDAtos(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        BufferedReader br = null;
        TreeSet<Templario> nuevaLista = new TreeSet<>(new ComparadorTemplarios());
        String linea;
        try {
            br = new BufferedReader(new FileReader(archivo));
            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(",");
                String nombre = atributos[0];
                String organizacion = atributos[1];
                int nivel_peligrosidad = Integer.parseInt(atributos[2]);
                Templario t = new Templario(nombre, organizacion, nivel_peligrosidad);
                nuevaLista.add(t);
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
        return this.lista_templarios = nuevaLista;
    }
}
