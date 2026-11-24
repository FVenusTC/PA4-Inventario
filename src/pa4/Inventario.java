package pa4;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Comparator;

public class Inventario {

    private final Set<Material> materiales;

    public Inventario() {
        materiales = new HashSet<>();
    }

    public boolean registrar(Material material) {
        return material != null && materiales.add(material);
    }

    public List<Material> listar() {
        List<Material> copia = new ArrayList<>();
        for (Material m : materiales) {
            copia.add(m);
        }
        return copia;
    }

    public int cantidadMateriales() {
        return materiales.size();
    }

    public Material buscarPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        String buscado = codigo.trim().toUpperCase(Locale.ROOT);
        for (Material m : materiales) {
            if (m.getCodigo().equals(buscado)) {
                return m;
            }
        }
        return null;
    }

    public List<Material> buscarPorNombre(String texto) {
        List<Material> encontrados = new ArrayList<>();
        if (texto == null || texto.trim().isEmpty()) {
            return encontrados;
        }
        String buscado = texto.trim().toLowerCase(Locale.ROOT);
        for (Material m : materiales) {
            if (m.getNombre().toLowerCase(Locale.ROOT).contains(buscado)) {
                encontrados.add(m);
            }
        }
        return encontrados;
    }

    public List<Material> ordenarPorNombre() {
        List<Material> ordenados = new ArrayList<>(materiales);
        ordenados.sort(new Comparator<Material>() {
            @Override
            public int compare(Material a, Material b) {
                return a.getNombre().compareToIgnoreCase(b.getNombre());
            }
        });
        return ordenados;
    }

    public boolean eliminarPorCodigo(String codigo) {
        Material encontrado = buscarPorCodigo(codigo);
        if (encontrado == null) {
            return false;
        }
        return materiales.remove(encontrado);
    }
}