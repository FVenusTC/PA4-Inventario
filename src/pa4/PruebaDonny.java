package pa4;

public class PruebaDonny {

    public static void main(String[] args) {

        Insumo insumo = new Insumo(
            "INS-001",
            "Papel filtro",
            25,
            "kg",
            "Proveedor Nacional"
        );

        ProductoTerminado producto = new ProductoTerminado(
            "PT-001",
            "Caja de infusiones",
            100,
            "cajas",
            "Producción"
        );

        System.out.println("=== PRUEBA DE DONNY ===");

        System.out.println(insumo);
        System.out.println("Proveedor: " + insumo.getProveedor());

        System.out.println();

        System.out.println(producto);
        System.out.println("Área: " + producto.getAreaProduccion());
    }
}