package repositorio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.Producto;
import java.util.Collections;
import util.ProductoNombreComparator;

public class ProductoRepositorio {

    private static List<Producto> productos;
    private static Set<String> codigos;

    public ProductoRepositorio() {

        if (productos == null) {
            productos = new ArrayList<>();
            codigos = new HashSet<>();
        }

    }

    public List<Producto> listar() {
        return productos;
    }

    public boolean existeCodigo(String codigo) {
        return codigos.contains(codigo);
    }

    public void agregar(Producto producto) {
        productos.add(producto);
        codigos.add(producto.getCodigo());
    }
    
    public void eliminar(String codigo) {

    Producto productoEliminar = null;

    for (Producto p : productos) {

        if (p.getCodigo().equals(codigo)) {
            productoEliminar = p;
            break;
        }

    }

    if (productoEliminar != null) {
        productos.remove(productoEliminar);
        codigos.remove(codigo);
    }

}
    
    public List<Producto> buscar(String texto) {

    List<Producto> resultado = new ArrayList<>();

    for (Producto p : productos) {

        if (p.getCodigo().toLowerCase().contains(texto.toLowerCase())
                || p.getNombre().toLowerCase().contains(texto.toLowerCase())) {

            resultado.add(p);

        }

    }

    return resultado;

}
    
    public Producto buscarPorCodigo(String codigo) {

    for (Producto p : productos) {

        if (p.getCodigo().equals(codigo)) {
            return p;
        }

    }

    return null;

}
    
    public void actualizar(Producto producto) {

    for (int i = 0; i < productos.size(); i++) {

        if (productos.get(i).getCodigo().equals(producto.getCodigo())) {

            productos.set(i, producto);
            return;

        }

    }

}
    
    public void ordenarPorNombre() {

    Collections.sort(productos, new ProductoNombreComparator());

}
    
    public void exportarInventario(String ruta) throws java.io.IOException {

    java.io.BufferedWriter bw =
            new java.io.BufferedWriter(
                    new java.io.FileWriter(ruta));

    bw.write("========= INVENTARIO =========");
    bw.newLine();
    bw.newLine();

    for (Producto p : productos) {

        bw.write("Código: " + p.getCodigo());
        bw.newLine();

        bw.write("Nombre: " + p.getNombre());
        bw.newLine();

        bw.write("Categoría: " + p.getCategoria());
        bw.newLine();

        bw.write("Cantidad: " + p.getCantidad());
        bw.newLine();

        bw.write("Precio: " + p.getPrecio());
        bw.newLine();

        bw.write("Disponible: " + p.isDisponible());
        bw.newLine();

        bw.write("Descripción: " + p.getDescripcion());
        bw.newLine();

        bw.write("-----------------------------------------");
        bw.newLine();
        bw.newLine();

    }

    bw.close();

}
}