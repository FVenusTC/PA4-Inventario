package pa4;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();
        int opcion = -1;

        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1 -> registrarMaterial(scanner, inventario);
                case 2 -> listarMateriales(inventario);
                case 3 -> buscarPorCodigo(scanner, inventario);
                case 4 -> buscarPorNombre(scanner, inventario);
                case 5 -> ordenarPorNombre(inventario);
                case 6 -> actualizarStock(scanner, inventario);
                case 7 -> eliminarPorCodigo(scanner, inventario);
                case 0 -> System.out.println("Saliendo del sistema de inventario...");
                default -> System.out.println("Opcion no valida. Intente nuevamente.");
            }

            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== SISTEMA DE INVENTARIO DE ALMACEN ===");
        System.out.println("1. Registrar material (Insumo / Producto Terminado)");
        System.out.println("2. Listar materiales");
        System.out.println("3. Buscar por codigo");
        System.out.println("4. Buscar por nombre");
        System.out.println("5. Ordenar por nombre");
        System.out.println("6. Actualizar stock (entrada / salida)");
        System.out.println("7. Eliminar por codigo");
        System.out.println("0. Salir");
    }

    private static void registrarMaterial(Scanner scanner, Inventario inventario) {
        System.out.println("--- Registrar Material ---");
        System.out.println("1. Insumo");
        System.out.println("2. Producto Terminado");
        int tipo = leerEntero(scanner, "Tipo de material: ");

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo invalido. Registro cancelado.");
            return;
        }

        String codigo = leerTextoNoVacio(scanner, "Codigo: ");
        String nombre = leerTextoNoVacio(scanner, "Nombre: ");
        double stock = leerDoubleNoNegativo(scanner, "Stock inicial: ");
        String unidad = leerTextoNoVacio(scanner, "Unidad (ej. und, kg, lt): ");

        Material nuevoMaterial;
        try {
            if (tipo == 1) {
                String proveedor = leerTextoNoVacio(scanner, "Proveedor: ");
                // Ajustar parametros del constructor segun Insumo.java
                nuevoMaterial = new Insumo(codigo, nombre, stock, unidad, proveedor);
            } else {
                String areaProduccion = leerTextoNoVacio(scanner, "Area de produccion: ");
                // Ajustar parametros del constructor segun ProductoTerminado.java
                nuevoMaterial = new ProductoTerminado(codigo, nombre, stock, unidad, areaProduccion);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear el material: " + e.getMessage());
            return;
        }

        boolean registrado = inventario.registrar(nuevoMaterial);
        if (registrado) {
            System.out.println("Material registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar: ya existe un material con ese codigo.");
        }
            
    }

    private static void listarMateriales(Inventario inventario) {
        System.out.println("--- Lista de Materiales ---");
        List<Material> materiales = inventario.listar();
        if (materiales.isEmpty()) {
            System.out.println("No hay materiales registrados.");
            return;
        }
        for (Material m : materiales) {
            System.out.println(m);
        }
        System.out.println("Total de materiales: " + inventario.cantidadMateriales());
    }

    private static void buscarPorCodigo(Scanner scanner, Inventario inventario) {
        String codigo = leerTextoNoVacio(scanner, "Ingrese el codigo a buscar: ");
        Material encontrado = inventario.buscarPorCodigo(codigo);
        if (encontrado != null) {
            System.out.println("Material encontrado: " + encontrado);
        } else {
            System.out.println("No se encontro ningun material con el codigo: " + codigo);
        }
    }

    private static void buscarPorNombre(Scanner scanner, Inventario inventario) {
        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre o parte del nombre: ");
        List<Material> resultados = inventario.buscarPorNombre(nombre);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron coincidencias para: " + nombre);
            return;
        }
        System.out.println("--- Resultados de Busqueda ---");
        for (Material m : resultados) {
            System.out.println(m);
        }
    }

    private static void ordenarPorNombre(Inventario inventario) {
        List<Material> ordenados = inventario.ordenarPorNombre();
        if (ordenados.isEmpty()) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        System.out.println("--- Materiales Ordenados por Nombre ---");
        for (Material m : ordenados) {
            System.out.println(m);
        }
    }

    private static void actualizarStock(Scanner scanner, Inventario inventario) {
        String codigo = leerTextoNoVacio(scanner, "Ingrese el codigo del material: ");
        Material material = inventario.buscarPorCodigo(codigo);
        if (material == null) {
            System.out.println("Material no encontrado.");
            return;
        }

        System.out.println("Material actual: " + material);
        System.out.println("1. Entrada de stock");
        System.out.println("2. Salida de stock");
        int movimiento = leerEntero(scanner, "Seleccione movimiento: ");
        double cantidad = leerDoublePositivo(scanner, "Cantidad: ");

        if (movimiento == 1) {
            if (material.registrarEntrada(cantidad)) {
                System.out.println("Entrada registrada. Stock actual: " + material.getStock() + " " + material.getUnidad());
            } else {
                System.out.println("No se pudo registrar la entrada.");
            }
        } else if (movimiento == 2) {
            if (material.registrarSalida(cantidad)) {
                System.out.println("Salida registrada. Stock actual: " + material.getStock() + " " + material.getUnidad());
            } else {
                System.out.println("Stock insuficiente o cantidad invalida. Stock disponible: "
                        + material.getStock() + " " + material.getUnidad());
            }
        } else {
            System.out.println("Movimiento invalido.");
        }
    }

    private static void eliminarPorCodigo(Scanner scanner, Inventario inventario) {
        String codigo = leerTextoNoVacio(scanner, "Ingrese el codigo a eliminar: ");
        boolean eliminado = inventario.eliminarPorCodigo(codigo);
        if (eliminado) {
            System.out.println("Material eliminado correctamente.");
        } else {
            System.out.println("No se encontro ningun material con ese codigo.");
        }
    }

    private static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("El campo no puede estar vacio.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(linea);
                if (!Double.isFinite(valor)) {
                    System.out.println("Ingrese un numero valido.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    private static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        double valor;
        do {
            valor = leerDouble(scanner, mensaje);
            if (valor < 0) {
                System.out.println("El valor no puede ser negativo.");
            }
        } while (valor < 0);
        return valor;
    }

    private static double leerDoublePositivo(Scanner scanner, String mensaje) {
        double valor;
        do {
            valor = leerDouble(scanner, mensaje);
            if (valor <= 0) {
                System.out.println("Ingrese un numero mayor que cero.");
            }
        } while (valor <= 0);
        return valor;
    }
}