package db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import entities.Cuadro;
import java.io.File;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class BaseDatosDb4o {
    
    private static final String BASE_DE_DATOS = "cuadros.db40";
    private static ObjectContainer db = null;
    private static EmbeddedConfiguration config;
    
    public static ObjectContainer crear() {
        File file = new File(BASE_DE_DATOS);
        
        if (file.exists()) {
            file.delete();
            return instanciar();
        } else {
            return instanciar();
        }
    }
    
    public static ObjectContainer instanciar() {
        config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Cuadro.class).cascadeOnDelete(true);
        db = Db4oEmbedded.openFile(BASE_DE_DATOS);
        return db;
    }
    
    public static void cerrar() {
        db.close();
    }
    
}
