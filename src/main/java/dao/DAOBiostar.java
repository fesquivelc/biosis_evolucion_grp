/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.personal.utiles.ParametrosUtil;
import com.personal.utiles.PropertiesUtil;
import static dao.DAOBiosis.em;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utiles.Encriptador;

/**
 *
 * @author RyuujiMD
 * @param <T>
 */
public class DAOBiostar<T> extends DAOBiosis {

    private final static String biostar_PU = "biostar-PU";
    private EntityManager emBiostar;

    public DAOBiostar(Class<T> clase) {
        super(clase);
    }

    @Override
    public EntityManager getEntityManager() {
//        if(super.getEntityManager().isOpen()){
//            super.getEntityManager().close();
//        }
        
        if (emBiostar == null) {
            Properties configuracion = PropertiesUtil.cargarProperties("config/biostar-config.properties");
            int tipoBD = Integer.parseInt(configuracion.getProperty("tipo"));

            String driver = ParametrosUtil.obtenerDriver(tipoBD);
            String url = configuracion.getProperty("url");
            String usuario = configuracion.getProperty("usuario");
            String password = configuracion.getProperty("password");

            Map<String, String> properties = new HashMap<>();
            properties.put("javax.persistence.jdbc.user", usuario.trim());
            properties.put("javax.persistence.jdbc.password", password.trim());
            properties.put("javax.persistence.jdbc.driver", driver);
            properties.put("javax.persistence.jdbc.url", url.trim());
            properties.put("javax.persistence.schema-generation.database.action", "none");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(biostar_PU, properties);
            emBiostar = emf.createEntityManager();
        }

        return emBiostar;
    }

}
