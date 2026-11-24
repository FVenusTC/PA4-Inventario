package pa4;

import java.lang.reflect.Constructor;
import java.util.Locale;

//Declaración de la clase abstracta
public abstract class Material implements Identificable {
    //Atributos comunes
    private final String codigo;
    private final String nombre;
    private double stock;
    private final String unidad;

    //Constructor y validaciones
    public Material(String codigo, String nombre, double stock, String unidad) {
        if (codigo == null || codigo.trim().isEmpty() || nombre == null
                || nombre.trim().isEmpty() || unidad == null || unidad.trim().isEmpty()
                || !Double.isFinite(stock) || stock < 0) {
            throw new IllegalArgumentException("Datos del material invalidos");
        }
        this.codigo = codigo.trim().toUpperCase(Locale.ROOT);
        this.nombre = nombre.trim();
        this.stock = stock;
        this.unidad = unidad.trim();
    }

    @Override
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getStock() { return stock; }
    public String getUnidad() { return unidad; }

// Registrar entrada de stock y validar la cantidad
    public boolean registrarEntrada(double cantidad) {
        if (!Double.isFinite(cantidad) || cantidad <= 0
                || !Double.isFinite(stock + cantidad)) return false;
        stock += cantidad;
        return true;
    }

   // Registrar salida de stock y evitar existencias negativas
    public boolean registrarSalida(double cantidad) {
        if (!Double.isFinite(cantidad) || cantidad <= 0 || cantidad > stock) return false;
        stock -= cantidad;
        return true;
    }

    // Métodos abstractos que implementarán las clases hijas
    public abstract String getTipo();
    public abstract String getDetalle();

    // Comparar materiales mediante su código
    @Override
    public String toString() {
        return codigo + " | " + nombre + " | " + stock + " " + unidad
                + " | " + getTipo() + " | " + getDetalle();
    }
    // Generar hash por código
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Material)) return false;
        Material otro = (Material) obj;
        return codigo.equals(otro.codigo);
    }

    @Override
    public final int hashCode() { return codigo.hashCode(); }
}
