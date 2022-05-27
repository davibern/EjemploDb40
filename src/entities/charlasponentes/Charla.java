package entities.charlasponentes;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class Charla {
    
    private String titulo;
    private float duracion;
    private Ponente ponente;

    public Charla() {
    }

    public Charla(String titulo, float duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.ponente = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public Ponente getPonente() {
        return ponente;
    }

    public void setPonente(Ponente pronente) {
        this.ponente = pronente;
    }

    @Override
    public String toString() {
        return "Charla{" + "titulo=" + titulo + ", duracion=" + duracion + ", pronente=" + ponente + '}';
    }
    
}
