package util;

import java.util.Comparator;
import modelo.Producto;

public class ProductoNombreComparator implements Comparator<Producto> {

    @Override
    public int compare(Producto p1, Producto p2) {
        return p1.getNombre().compareToIgnoreCase(p2.getNombre());
    }

}