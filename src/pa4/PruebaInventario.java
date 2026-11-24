package pa4;

public class PruebaInventario {

    // Clase temporal solo para probar, hasta tener Insumo y ProductoTerminado
    private static class MaterialPrueba extends Material {
        MaterialPrueba(String codigo, String nombre, double stock, String unidad) {
            super(codigo, nombre, stock, unidad);
        }

        @Override
        public String getTipo() { return "Prueba"; }

        @Override
        public String getDetalle() { return "-"; }
    }

    public static void main(String[] args) {
        Inventario inv = new Inventario();

        System.out.println("=== REGISTRO ===");
        System.out.println(inv.registrar(new MaterialPrueba("INS-001", "Papel filtro", 12, "kg")));
        System.out.println(inv.registrar(new MaterialPrueba("PT-001", "Caja de infusiones", 40, "cajas")));
        System.out.println(inv.registrar(new MaterialPrueba("INS-002", "azucar", 5, "kg")));

        System.out.println("=== DUPLICADO Y NULL (esperado: false, false) ===");
        System.out.println(inv.registrar(new MaterialPrueba("ins-001", "Otro nombre", 1, "kg")));
        System.out.println(inv.registrar(null));
        System.out.println("Cantidad (esperado 3): " + inv.cantidadMateriales());

        System.out.println("=== LISTADO ===");
        for (Material m : inv.listar()) {
            System.out.println(m);
        }

        System.out.println("=== BUSQUEDA POR CODIGO ===");
        System.out.println("' ins-001 ' -> " + inv.buscarPorCodigo(" ins-001 "));
        System.out.println("XXX (esperado null) -> " + inv.buscarPorCodigo("XXX"));

        System.out.println("=== BUSQUEDA POR NOMBRE ===");
        System.out.println("PAPEL -> " + inv.buscarPorNombre("PAPEL").size() + " resultado(s)");
        System.out.println("zzz -> " + inv.buscarPorNombre("zzz").size() + " resultado(s)");
        System.out.println("vacio -> " + inv.buscarPorNombre("").size() + " resultado(s)");

        System.out.println("=== ORDENADO POR NOMBRE ===");
        for (Material m : inv.ordenarPorNombre()) {
            System.out.println(m.getNombre());
        }

        System.out.println("=== ELIMINACION ===");
        System.out.println(inv.eliminarPorCodigo("PT-001"));
        System.out.println(inv.eliminarPorCodigo("PT-001"));
        System.out.println("Buscar PT-001 (esperado null) -> " + inv.buscarPorCodigo("PT-001"));
        System.out.println("Cantidad (esperado 2): " + inv.cantidadMateriales());
    }
}