package pa4;

public class PruebaInventario {

    public static void main(String[] args) {
        Inventario inv = new Inventario();

        System.out.println("=== REGISTRO (esperado: true, true, true) ===");
        System.out.println(inv.registrar(new Insumo("INS-001", "Papel filtro", 12, "kg", "Proveedor A")));
        System.out.println(inv.registrar(new ProductoTerminado("PT-001", "Caja de infusiones", 40, "cajas", "Produccion")));
        System.out.println(inv.registrar(new Insumo("INS-002", "azucar", 5, "kg", "Proveedor B")));

        System.out.println("=== DUPLICADOS Y NULL (esperado: false, false, false) ===");
        System.out.println(inv.registrar(new Insumo("ins-001", "Otro nombre", 1, "kg", "Proveedor C")));
        System.out.println(inv.registrar(new ProductoTerminado("pt-001", "Otra caja", 1, "cajas", "Empaque")));
        System.out.println(inv.registrar(null));
        System.out.println("Cantidad (esperado 3): " + inv.cantidadMateriales());

        System.out.println("=== LISTADO (Insumo y ProductoTerminado en la misma coleccion) ===");
        for (Material m : inv.listar()) {
            System.out.println(m);
        }

        System.out.println("=== BUSQUEDA POR CODIGO ===");
        System.out.println("' ins-001 ' -> " + inv.buscarPorCodigo(" ins-001 "));
        System.out.println("XXX (esperado null) -> " + inv.buscarPorCodigo("XXX"));

        System.out.println("=== BUSQUEDA POR NOMBRE ===");
        System.out.println("PAPEL -> " + inv.buscarPorNombre("PAPEL").size() + " resultado(s)");
        for (Material m : inv.buscarPorNombre("caja")) {
            System.out.println("caja -> " + m);
        }
        System.out.println("zzz -> " + inv.buscarPorNombre("zzz").size() + " resultado(s)");
        System.out.println("vacio -> " + inv.buscarPorNombre("").size() + " resultado(s)");

        System.out.println("=== ORDENADO POR NOMBRE ===");
        for (Material m : inv.ordenarPorNombre()) {
            System.out.println(m.getNombre() + " (" + m.getTipo() + ")");
        }

        System.out.println("=== ELIMINACION ===");
        System.out.println(inv.eliminarPorCodigo("PT-001"));
        System.out.println(inv.eliminarPorCodigo("PT-001"));
        System.out.println("Buscar PT-001 (esperado null) -> " + inv.buscarPorCodigo("PT-001"));
        System.out.println("Cantidad (esperado 2): " + inv.cantidadMateriales());
    }
}