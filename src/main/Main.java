package main;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import db.CuadroPintoresDb4o;
import entities.Cuadro;
import entities.Pintor;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class Main {
    
    private static ObjectContainer db;
    private static final Pintor pintor1 = new Pintor("Picasso", "Cubismo", "España");
    private static final Pintor pintor2 = new Pintor("Josef Albers", "Abstracto", "Alemania");
    private static final Pintor pintor3 = new Pintor("Hanna Pauli", "Impresionista", "Suecia");
    private static final Cuadro cuadro1 = new Cuadro("Guernica", pintor1, "óleo", 349, 776);
    private static final Cuadro cuadro2 = new Cuadro("Las señoritas de Avignon", pintor1, "óleo", 244, 234);
    private static final Cuadro cuadro3 = new Cuadro("Homage to the Square", pintor2, "masonite", 100, 130);
    private static final Cuadro cuadro4 = new Cuadro("Graphic Tectonic", pintor2, "pluma", 46, 59);
    private static final Cuadro cuadro5 = new Cuadro("George Pauli", pintor3, "óleo", 100, 130);
    private static final Cuadro cuadro6 = new Cuadro("Margret", pintor3, "óleo", 100, 130);
 
    public static void main(String[] args) {
        db = CuadroPintoresDb4o.crear();
        
        try {
            mensaje("Almacenar cuadros y pintores");
            almacenarDatos(db);
            mensaje("Mostrar títulos de cuadros y su pintor");
            consultarCuadros(db);
            mensaje("Mostrar cuadros de un pintor");
            consultarCuadroPintor(db, "Picasso");
            mensaje("Mostrar cuadros por su largo");
            consultarCuadrosLongitud(db, 92, 146);
            mensaje("Eliminar un cuadro");
            eliminarCuadroTitulo(db, "Guernica");
            mensaje("Mostrar los pintores tras la eliminación del cuadro");
            consultarPintores(db);
            mensaje("Mostrar todos los cuadros antes de actualizar");
            consultarTodosLosCuadros(db);
            mensaje("Actualizar el largo del cuadro Margret");
            actualizarLargoCuadro(db, "Margret", 80);
            mensaje("Mostrar todos los cuadros después de actualizar");
            consultarTodosLosCuadros(db);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            CuadroPintoresDb4o.cerrar();
        }
    }
    
    public static void almacenarDatos(ObjectContainer db) {
        db.store(pintor1);
        db.store(pintor2);
        db.store(pintor3);
        db.store(cuadro1);
        db.store(cuadro2);
        db.store(cuadro3);
        db.store(cuadro4);
        db.store(cuadro5);
        db.store(cuadro6);
    }
    
    public static void mostrarConsulta(ObjectSet result) {
        System.out.println("Recuperados: " + result.size() + " objetos.");
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }
    
    public static void mostrarConsultaCuadro(ObjectSet result) {
        System.out.println("Recuperados: " + result.size() + " objetos.");
        while (result.hasNext()) {
            Cuadro c = (Cuadro) result.next();
            System.out.println("Título: " + c.getTitulo() + ", pintor: " + c.getPintor().getNombre());
        }
    }
    
    public static void consultarCuadros(ObjectContainer db) {
        Cuadro cuadro = new Cuadro();
        ObjectSet result = db.queryByExample(cuadro);
        mostrarConsultaCuadro(result);
    }
    
    public static void consultarTodosLosCuadros(ObjectContainer db) {
        Cuadro cuadro = new Cuadro();
        ObjectSet result = db.queryByExample(cuadro);
        mostrarConsulta(result);
    }
    
    public static void consultarPintores(ObjectContainer db) {
        Pintor pintor = new Pintor();
        ObjectSet result = db.queryByExample(pintor);
        mostrarConsulta(result);
    }
    
    public static void consultarCuadroPintor(ObjectContainer db, String pintor) {
        Pintor p = new Pintor(pintor, null, null);
        Cuadro c = new Cuadro(null, null, null, 0, 0);
        c.setPintor(p);
        ObjectSet result = db.queryByExample(c);
        mostrarConsulta(result);
    }
    
    public static void consultarCuadrosLongitud(ObjectContainer db, int desde, int hasta) {
        Query query = db.query();
        query.constrain(Cuadro.class);
        Constraint constraint = query.descend("largo").constrain(hasta).smaller();
        query.descend("largo").constrain(desde).greater().and(constraint);
        ObjectSet result = query.execute();
        mostrarConsulta(result);
    }
    
    public static void eliminarCuadroTitulo(ObjectContainer db, String tituloCuadro) {
        Query query = db.query();
        query.constrain(Cuadro.class);
        query.descend("titulo").constrain(tituloCuadro);
        ObjectSet result = query.execute();
        while (result.hasNext()) {
            Cuadro c = (Cuadro) result.next();
            System.out.println("Eliminado cuadro: " + c);
            db.delete(c);
        }
    }
    
    public static void actualizarLargoCuadro(ObjectContainer db, String titulo, int largo) {
        ObjectSet result = db.queryByExample(new Cuadro(titulo, null, null, 0, 0));
        Cuadro c = (Cuadro) result.next();
        c.setLargo(largo);
        db.store(c);
    }
    
    public static void mensaje(String mensaje) {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------------\n");
        sb.append(mensaje + "\n");
        sb.append("-------------------------------------");
        System.out.println(sb);
    }
    
}
