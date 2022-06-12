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

import sira.bean.Membre;

public class MembreDAO extends DAO<Membre> {

    public MembreDAO( Connection connection ) {
        super( connection );
    }

    @Override
    public void inserer( Membre user ) {

        String query = "INSERT INTO membre VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, Now())";
        try {
            PreparedStatement statement = getConnection().prepareStatement( query );
            statement.setString( 3, user.getCivility() );
            statement.setString( 4, user.getPrenom() );
            statement.setString( 5, user.getNom() );
            statement.setString( 1, user.getPseudo() );
            statement.setString( 2, user.getMdp() );
            statement.setString( 6, user.getEmail() );
            statement.setString( 7, user.getStatut() );

            statement.executeUpdate();
        } catch ( SQLException e ) {
            System.out.println( "inserer " + e.getMessage() );
        }
    }

    @Override
    public Membre getOne( int id ) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement( "SELECT * FROM membre WHERE id_membre = ?" );
            stmt.setInt( 1, id );
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
            System.out.println( e.getMessage() );
        }
        return null;
    }

    @Override
    public void update( Membre user ) {
        try {
            String query = """
                    UPDATE membre SET
                    civility = ?,
                    prenom = ?,
                    nom = ?,
                    pseudo = ?,
                    mdp = ?,
                    email = ?,
                    statut = ?
                    WHERE id_membre = ?
                    """;
            PreparedStatement stmt = getConnection().prepareStatement( query );
            stmt.setString( 1, user.getCivility() );
            stmt.setString( 2, user.getPrenom() );
            stmt.setString( 3, user.getNom() );
            stmt.setString( 4, user.getPseudo() );
            stmt.setString( 5, user.getMdp() );
            stmt.setString( 6, user.getEmail() );
            stmt.setString( 7, user.getStatut() );
            stmt.setInt( 8, user.getId_membre() );
            stmt.executeUpdate();
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }

    }

    @Override
    public void delete( Membre user ) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement( "DELETE FROM membre WHERE id_membre = ?" );
            stmt.setInt( 1, user.getId_membre() );
            stmt.executeUpdate();
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }

    }

    public List<Membre> getAll() {
        List<Membre> users = new ArrayList<Membre>();
        ResultSet resultSet = getAll( "SELECT * FROM membre" );
        try {
            while ( resultSet.next() ) {
                Membre membre = new Membre(
                        resultSet.getInt( "id_membre" ),
                        resultSet.getString( "civility" ),
                        resultSet.getString( "prenom" ),
                        resultSet.getString( "nom" ),
                        resultSet.getString( "pseudo" ),
                        resultSet.getString( "mdp" ),
                        resultSet.getString( "email" ),
                        resultSet.getString( "statut" ),
                        resultSet.getString( "date_enregistrement" ) );
                users.add( membre );
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        return users;

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
