# PA4 - Sistema de Inventario de Almacén

Sistema Java de consola para registrar insumos y productos terminados, controlar stock, buscar y ordenar materiales. 



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
Las capturas de pantalla del funcionamiento y del código se guardarán en la carpeta `capturas`.

## Integrantes
- Integrante 1: Flor de Venus Torres Condori ( Identificable y Material)
- Integrante 2: Donny Scrach Gaspar Araujo (Insumo y ProductoTerminado)
- Integrante 3: Kathleen Carola Durand Quispe (Inventario)
Integrante 4: Fabriccio Marticorena Colan (Main e integración)

## Repositorio


## Video de YouTube

