package pa4;

// Representa un producto que ya termino su proceso de produccion.
public class ProductoTerminado extends Material {

    private final String areaProduccion;

    public ProductoTerminado(String codigo, String nombre, double stock, String unidad,
                             String areaProduccion) {
        super(codigo, nombre, stock, unidad);

        if (areaProduccion == null || areaProduccion.trim().isEmpty()) {
            throw new IllegalArgumentException("El area de produccion no puede estar vacia");
        }
        this.areaProduccion = areaProduccion.trim();
    }

    public String getAreaProduccion() {
        return areaProduccion;
    }

    @Override
    public String getTipo() {
        return "Producto terminado";
    }

    @Override
    public String getDetalle() {
        return "Area de produccion: " + areaProduccion;
    }
}
