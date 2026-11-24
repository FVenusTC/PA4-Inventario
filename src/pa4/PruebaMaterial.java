
package pa4;

import java.util.HashSet;

/*
 * PA4 - Programacion Orientada a Objetos
 * Integrante: Torres Condori Flor de Venus
 * Pruebas funcionales de la clase Material
 */

public class PruebaMaterial {

    // Clase auxiliar para probar la clase abstracta
    static class MaterialPrueba extends Material {

        public MaterialPrueba(String codigo, String nombre,
double stock, String unidad) {
           super(codigo, nombre, stock, unidad);
        }

        @Override
        public String getTipo() {
            return "Insumo de prueba";
        }

        @Override
        public String getDetalle() {
            return "Material para pruebas funcionales";
        }
    }

    public static void main(String[] args) {

        Material material = new MaterialPrueba(
            "INS-001", "Papel filtro", 12, "kg"
        );

        System.out.println("================================");
        System.out.println(" PRUEBAS FUNCIONALES - VENUS");
        System.out.println("================================");

        // Prueba 1: Stock inicial
        System.out.println("1. Stock inicial: "
                + material.getStock() + " kg");

        // Prueba 2: Entrada de stock
        boolean entrada = material.registrarEntrada(5);

        System.out.println("2. Entrada de 5 kg: "
                + (entrada ? "ACEPTADA" : "RECHAZADA"));

        System.out.println("   Stock actual: "
                + material.getStock() + " kg");

        // Prueba 3: Salida de stock
        boolean salida = material.registrarSalida(3);

        System.out.println("3. Salida de 3 kg: "
                + (salida ? "ACEPTADA" : "RECHAZADA"));

        System.out.println("   Stock actual: "
                + material.getStock() + " kg");

        // Prueba 4: Stock insuficiente
        boolean salidaExcesiva = material.registrarSalida(20);

        System.out.println("4. Salida de 20 kg: "
                + (salidaExcesiva ? "ACEPTADA" : "RECHAZADA"));

        // Prueba 5: Cantidad negativa
        boolean entradaNegativa = material.registrarEntrada(-5);

        System.out.println("5. Entrada negativa: "
                + (entradaNegativa ? "ACEPTADA" : "RECHAZADA"));

        // Prueba 6: Prevencion de duplicados
        HashSet<Material> materiales = new HashSet<>();

        materiales.add(material);

        Material duplicado = new MaterialPrueba(
            "INS-001", "Otro material", 10, "kg"
        );

        boolean agregado = materiales.add(duplicado);

        System.out.println("6. Codigo duplicado: "
                + (agregado ? "ACEPTADO" : "RECHAZADO"));

        System.out.println("   Materiales registrados: "
                + materiales.size());

        System.out.println("Stock final: "
                + material.getStock() + " kg");

        System.out.println("================================");
        System.out.println(" FIN DE LAS PRUEBAS");
        System.out.println("================================");
    }
}
