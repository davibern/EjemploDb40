package entities.charlasponentes;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import db.CharlaPintoresDb4o;
import java.util.List;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class Main {
    
    private static ObjectContainer db;
    private static final Ponente p1 = new Ponente("11A", "Antonio Camacho", "acamacho@gmail.es", 300);
    private static final Ponente p2 = new Ponente("22B", "Isabel Pérez", "iperez@hotmail.es", 100);
    private static final Ponente p3 = new Ponente("33C", "Ana Navarro", "anavarro@yahoo.com", 200);
    private static final Ponente p4 = new Ponente("44D", "Pedro Sánchez", "psanchez@mixmail.com", 90);
    private static final Charla c1 = new Charla("Java I", 2);
    private static final Charla c2 = new Charla("Java II", 4);
    private static final Charla c3 = new Charla("MySQL y PostgreSQL", 3);
    private static final Charla c4 = new Charla("Db4o", 2);
    
    public static void main(String[] args) {
        db = CharlaPintoresDb4o.crear();
        
        try {
            System.out.println("Almacenar Ponentes y Charlas\n------------------------");
            almacenarDatos(db);
            System.out.println("Mostrar Ponenntes\n------------------------");
            consultaPonentes(db);
            System.out.println("Mostrar Ponenntes de cache 200\n------------------------");
            consultaPonentes200(db);
            System.out.println("Mostrar Ponenntes por nombre\n------------------------");
            consultaPonenteNombre(db, "Pedro Sánchez");
            System.out.println("Mostrar Ponenntes usando SODA\n------------------------");
            consultaSODAPonentes(db);
            System.out.println("Mostrar Ponenntes usando SODA de cache 200\n------------------------");
            consultaSODACache200(db);
            System.out.println("Mostrar Ponenntes usando SODA de cache 50 a 200\n------------------------");
            consutlaSODACache50Y500(db);
            System.out.println("Consultar Ponentes NQ por caché\n---------------------");
            consultaPonenteNQCache200(db);
            System.out.println("Actualizar email de ponente\n---------------------");
            actualizarEmailCliente(db, "11A", "email.nuevo@gmail.com");
            System.out.println("Eliminar ponente\n---------------------");
            eliminarPonenteNif(db, "11A");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            db.close();
        }
    }
    
    public static void almacenarDatos(ObjectContainer db) {
        // Almacenar ponentes
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);
        
        // Asignar ponentes
        c1.setPonente(p1);
        c2.setPonente(p2);
        c3.setPonente(p3);
        c4.setPonente(p1);
        
        // Almacenar charlas
        db.store(c1);
        db.store(c2);
        db.store(c3);
        db.store(c4);
    }
    
    // Plantilla
    public static void mostrarConsulta(ObjectSet result) {
        System.out.println("Recuperados " + result.size() + " objetos");
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }
    
    // Consultas de Ponentes
    public static void consultaPonentes(ObjectContainer db) {
        Ponente ponente = new Ponente(null, null, null, 0);
        ObjectSet result = db.queryByExample(ponente);
        mostrarConsulta(result);
    }
    
    public static void consultaPonentes200(ObjectContainer db) {
        Ponente ponente = new Ponente(null, null, null, 200);
        ObjectSet result = db.queryByExample(ponente);
        mostrarConsulta(result);
    }
    
    public static void consultaPonenteNombre(ObjectContainer db, String nombre) {
        Ponente ponente = new Ponente(null, nombre, null, 0);
        ObjectSet result = db.queryByExample(ponente);
        mostrarConsulta(result);
    }
    
    public static void consultaSODAPonentes(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Ponente.class);
        ObjectSet result = query.execute();
        mostrarConsulta(result);
    }
    
    public static void consultaSODACache200(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Ponente.class);
        query.descend("cache").constrain(200);
        ObjectSet result = query.execute();
        mostrarConsulta(result);
    }
    
    public static void consutlaSODACache50Y500(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Ponente.class);
        Constraint constraint = query.descend("cache").constrain(200).smaller();
        query.descend("cache").constrain(50).greater().and(constraint);
        ObjectSet result = query.execute();
        mostrarConsulta(result);
    }
    
    public static void consultaPonenteNQCache200(ObjectContainer db) {
        List res = db.query(new Predicate() {
            public boolean match(Ponente p) {
                return p.getCache() == 200;
            }
            
            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Not supported yet");
            }
        });
    }
    
    public static void actualizarEmailCliente(ObjectContainer db, String nif, String email) {
        ObjectSet result = db.queryByExample(new Ponente(nif, null, null, 0));
        Ponente ponente = (Ponente) result.next();
        ponente.setEmail(email);
        db.store(db);
    }
    
    public static void eliminarPonenteNif(ObjectContainer db, String nif) {
        ObjectSet result = db.queryByExample(new Ponente(nif, null, null, 0));
        Ponente ponente = (Ponente) result.next();
        db.delete(ponente);
    }
}
