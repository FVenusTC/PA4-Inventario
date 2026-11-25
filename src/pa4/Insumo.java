package pa4;

public class Insumo extends Material {

    private final String proveedor;

    public Insumo(String codigo, String nombre, double stock, String unidad, String proveedor) {
        super(codigo, nombre, stock, unidad);

        if (proveedor == null || proveedor.trim().isEmpty()) {
            throw new IllegalArgumentException("El proveedor no puede estar vacio");
        }
        this.proveedor = proveedor.trim();
    }

    public String getProveedor() {
        return proveedor;
    }

    @Override
    public String getTipo() {
        return "Insumo";
    }

    @Override
    public String getDetalle() {
        return "Proveedor: " + proveedor;
    }
}
