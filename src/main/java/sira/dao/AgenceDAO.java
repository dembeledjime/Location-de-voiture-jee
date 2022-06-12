package sira.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sira.bean.Agence;
import sira.bean.Membre;

public class AgenceDAO extends DAO<Agence> {

    public AgenceDAO( Connection connection ) {
        super( connection );
    }

    @Override
    public void inserer( Agence agence ) {

        String query = "INSERT INTO agence VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement( query );
            statement.setString( 1, agence.getTitre() );
            statement.setString( 2, agence.getAdresse() );
            statement.setInt( 3, agence.getCp() );
            statement.setString( 4, agence.getVille() );
            statement.setString( 5, agence.getDescription() );
            statement.setString( 6, agence.getPhoto() );

            statement.executeUpdate();
        } catch ( SQLException e ) {
            System.out.println( "inserer " + e.getMessage() );
        }
    }

    @Override
    public Agence getOne( int id ) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement( "SELECT * FROM agence WHERE id_agence = ?" );
            stmt.setInt( 1, id );
            ResultSet resultSet = stmt.executeQuery();
            if ( resultSet.next() ) {
                return new Agence(
                        resultSet.getInt( "id_agence" ),
                        resultSet.getString( "titre" ),
                        resultSet.getString( "adresse" ),
                        resultSet.getInt( "cp" ),
                        resultSet.getString( "ville" ),
                        resultSet.getString( "description" ),
                        resultSet.getString( "photo" ) );
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        return null;
    }

    @Override
    public void update( Agence agence ) {
        try {
            String query = """
                    UPDATE agence SET
                    titre = ?,
                    adresse = ?,
                    cp = ?,
                    ville = ?,
                    description = ?,
                    photo = ?
                    WHERE id_agence = ?
                    """;
            PreparedStatement stmt = getConnection().prepareStatement( query );
            stmt.setString( 1, agence.getTitre() );
            stmt.setString( 2, agence.getAdresse() );
            stmt.setInt( 3, agence.getCp() );
            stmt.setString( 4, agence.getVille() );
            stmt.setString( 5, agence.getDescription() );
            stmt.setString( 6, agence.getPhoto() );
            stmt.setInt( 7, agence.getId_agence() );

            stmt.executeUpdate();
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }

    }

    @Override
    public void delete( Agence agence ) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement( "DELETE FROM membre WHERE id_membre = ?" );
            // stmt.setInt( 1, user.getId_membre() );
            stmt.executeUpdate();
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }

    }

    public List<Agence> getAll() {
        List<Agence> agences = new ArrayList<Agence>();
        ResultSet resultSet = getAll( "SELECT * FROM agence" );
        try {
            while ( resultSet.next() ) {
                Agence agence = new Agence(
                        resultSet.getInt( "id_agence" ),
                        resultSet.getString( "titre" ),
                        resultSet.getString( "adresse" ),
                        resultSet.getInt( "cp" ),
                        resultSet.getString( "ville" ),
                        resultSet.getString( "description" ),
                        resultSet.getString( "photo" ) );
                agences.add( agence );
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        return agences;

    }

    public Membre connexion( String login, String mdp ) {
        try {
            String query = "SELECT * FROM membre WHERE pseudo = ? AND mdp = ?";
            PreparedStatement stmt = getConnection().prepareStatement( query );
            stmt.setString( 1, login );
            stmt.setString( 2, mdp );
            ResultSet resultSet = stmt.executeQuery();

            if ( resultSet.next() ) {

                return new Membre(
                        resultSet.getInt( "id_membre" ),
                        resultSet.getString( "civility" ),
                        resultSet.getString( "prenom" ),
                        resultSet.getString( "nom" ),
                        resultSet.getString( "pseudo" ),
                        resultSet.getString( "mdp" ),
                        resultSet.getString( "email" ),
                        resultSet.getString( "statut" ),
                        resultSet.getString( "date_enregistrement" ) );
            }

        } catch ( SQLException e ) {
            System.out.println( "connexion: " + e.getMessage() );
        }
        return null;
    }

    public Date dateFormat( String dt ) {
        DateFormat format;
        Date date = null;

        format = new SimpleDateFormat( "dd-MM-yyyy" );
        try {
            date = (Date) format.parse( dt );
        } catch ( ParseException e ) {
            System.out.println( "format date : " + e.getMessage() );
        }

        return date;
    }

}
