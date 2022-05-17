package entities;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class Cuadro {
    
    private String titulo;
    private Pintor pintor;
    private String tecnica;
    private int largo;
    private int alto;

    public Cuadro() {
    }

    public Cuadro(String titulo, Pintor pintor, String tecnica, int largo, int alto) {
        this.titulo = titulo;
        this.pintor = pintor;
        this.tecnica = tecnica;
        this.largo = largo;
        this.alto = alto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pintor getPintor() {
        return pintor;
    }

    public void setPintor(Pintor pintor) {
        this.pintor = pintor;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    @Override
    public String toString() {
        return "Cuadro{" + "titulo=" + titulo + ", pintor=" + pintor + ", tecnica=" + tecnica + ", largo=" + largo + ", alto=" + alto + '}';
    }
    
}
