package negocio;

import excepciones.DatoInvalidoException;
import excepciones.ProductoDuplicadoException;
import modelo.Producto;
import repositorio.ProductoRepositorio;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

public class ProductoNegocio {

    private ProductoRepositorio repositorio;

    public ProductoNegocio() {
        repositorio = new ProductoRepositorio();
    }

    public void agregar(Producto producto)
            throws DatoInvalidoException, ProductoDuplicadoException {

        if (producto.getCodigo() == null || producto.getCodigo().trim().isEmpty()) {
            throw new DatoInvalidoException("El código es obligatorio.");
        }

        if (producto.getNombre() == null || producto.getNombre().trim().length() < 3) {
            throw new DatoInvalidoException("El nombre debe tener mínimo 3 caracteres.");
        }

        if (producto.getCantidad() < 0) {
            throw new DatoInvalidoException("La cantidad no puede ser negativa.");
        }

        if (producto.getPrecio() <= 0) {
            throw new DatoInvalidoException("El precio debe ser mayor que cero.");
        }

        if (repositorio.existeCodigo(producto.getCodigo())) {
            throw new ProductoDuplicadoException("Ya existe un producto con ese código.");
        }

        repositorio.agregar(producto);
    }

    public List<Producto> listar() {
        return repositorio.listar();
    }
    
    public void eliminar(String codigo) {
    repositorio.eliminar(codigo);
}
    
    public List<Producto> buscar(String texto) {
    return repositorio.buscar(texto);
}
    
    public Producto buscarPorCodigo(String codigo) {
    return repositorio.buscarPorCodigo(codigo);
}
    
    public void actualizar(Producto producto)
        throws DatoInvalidoException {

    if (producto.getNombre() == null || producto.getNombre().trim().length() < 3) {
        throw new DatoInvalidoException("El nombre debe tener mínimo 3 caracteres.");
    }

    if (producto.getCantidad() < 0) {
        throw new DatoInvalidoException("Cantidad inválida.");
    }

    if (producto.getPrecio() <= 0) {
        throw new DatoInvalidoException("Precio inválido.");
    }

    repositorio.actualizar(producto);

}
    
    public void ordenarPorNombre() {
    repositorio.ordenarPorNombre();
}
    
    public int totalProductos() {

    return repositorio.listar().size();

}
    public int productosDisponibles() {

    int contador = 0;

    for (Producto p : repositorio.listar()) {

        if (p.isDisponible()) {
            contador++;
        }

    }

    return contador;

}
    public double valorInventario() {

    double total = 0;

    for (Producto p : repositorio.listar()) {

        total += p.getCantidad() * p.getPrecio();

    }

    return total;

}
    public Map<String, Integer> productosPorCategoria() {

    Map<String, Integer> mapa = new HashMap<>();

    for (Producto p : repositorio.listar()) {

        if (mapa.containsKey(p.getCategoria())) {

            mapa.put(
                    p.getCategoria(),
                    mapa.get(p.getCategoria()) + 1);

        } else {

            mapa.put(p.getCategoria(), 1);

        }

    }

    return mapa;

}

    public void exportarInventario(String ruta)
        throws java.io.IOException {

    repositorio.exportarInventario(ruta);

}
}
