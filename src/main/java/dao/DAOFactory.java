package dao;

import java.io.IOException;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import dao.daoClient.ClientDAO;
import dao.daoClient.ClientDaoImpI;
import dao.daoDemande.DemandeDAO;
import dao.daoDemande.DemandeDaoImpI;
import dao.daoService.ServiceDAO;
import dao.daoService.ServiceDaoImpI;
import dao.daoTechnicien.TechnicienDAO;
import dao.daoTechnicien.TechnicienDaoImpI;


public class DAOFactory {

    private static final String FICHIER_PROPERTIES       = "dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "root";
    private static final String PROPERTY_MOT_DE_PASSE    = "";

    private String              url;
    private String              username;
    private String              password;

    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
        return instance;
    }

    /* Méthode chargée de fournir une connexion à la base de données */
     /* package */ public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    /*
     * Méthodes de récupération de l'implémentation des différents DAO (un seul
     * pour le moment)
     */
     
     public DAOAuth getAuthDao() {
    	 return new DAOAuth(this);
     }


	public ServiceDAO getServiceDao() {
		
		return new ServiceDaoImpI(this);
	}
	public ClientDAO getClientDAO() { 
		
		return new ClientDaoImpI(this); 
	}
	public TechnicienDAO getTechnicientDAO() { 
		
		return new TechnicienDaoImpI(this); 
	}
	public DemandeDAO getDemandeDAO() { 
		
		return new DemandeDaoImpI(this); 
	}

	
     
}