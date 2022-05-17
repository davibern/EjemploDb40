package entities;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class Pintor {
    
    private String nombre;
    private String estilo;
    private String nacionalidad;

    public Pintor() {
    }
    
    public Pintor(String nombre, String estilo, String nacionalidad) {
        this.nombre = nombre;
        this.estilo = estilo;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Pintor{" + "nombre=" + nombre + ", estilo=" + estilo + ", nacionalidad=" + nacionalidad + '}';
    }
    
}
