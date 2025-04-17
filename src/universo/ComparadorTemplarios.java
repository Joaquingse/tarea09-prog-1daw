/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universo;

import java.io.Serializable;
import java.util.Comparator;
import personajes.Templario;

/**
 *
 * @author mery
 */
public class ComparadorTemplarios implements Comparator, Serializable {

    @Override
    public int compare(Object o1, Object o2) {
        Templario t1 = (Templario) o1;
        Templario t2 = (Templario) o2;

        return t1.compare(t1, t2);
    }

}
