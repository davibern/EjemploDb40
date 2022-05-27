package entities.charlasponentes;

/**
 *
 * @author davibern
 * @version 1.0
 */
class Ponente {
    
    private String nif;
    private String nombre;
    private String email;
    private float cache;

    public Ponente() {
    }

    public Ponente(String nif, String nombre, String email, float cache) {
        this.nif = nif;
        this.nombre = nombre;
        this.email = email;
        this.cache = cache;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getCache() {
        return cache;
    }

    public void setCache(float cache) {
        this.cache = cache;
    }

    @Override
    public String toString() {
        return "Ponente{" + "nif=" + nif + ", nombre=" + nombre + ", email=" + email + ", cache=" + cache + '}';
    }
    
}
