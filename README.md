# PA4 - Sistema de Inventario de Almacén
Curso: Programación Orientada a Objetos (30626).

Sistema Java de consola para registrar insumos y productos terminados, controlar stock, buscar y ordenar materiales. Estado: base inicial de Venus; integración pendiente.

## Integrantes
- Venus: Identificable y Material.
- Donny: Insumo y ProductoTerminado.
- Kathe: Inventario.
- Integrante 4 (nombre pendiente): Main e integración.

## Diseño
Material implementa Identificable y define atributos comunes y operaciones de stock. Las clases derivadas implementarán getTipo y getDetalle. La igualdad por código inmutable permitirá evitar duplicados en HashSet. El código se normaliza a mayúsculas y se eliminan espacios exteriores. El stock es double para admitir cantidades fraccionarias. Las operaciones de stock devuelven boolean; false indica rechazo sin modificar el stock.

## Compilación de la base
```bash
javac -d out src/pa4/*.java
```
## Ejecución después de integrar Main
```bash
java -cp out pa4.Main
```
## Evidencias
Guardar capturas reales en capturas/.

## Video de YouTube
Pendiente de grabación y publicación por el equipo.
