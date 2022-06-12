package sira.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO<T> {

    private Connection connection;

    public DAO( Connection connection ) {
        this.connection = connection;
    }

    abstract void inserer( T obj );

    abstract T getOne( int id );

    abstract void update( T obj );

    abstract void delete( T obj );

    public ResultSet getAll( String query ) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery( query );
            return resultSet;
        } catch ( SQLException e ) {
            System.out.println( "all users " + e.getMessage() );
        }

        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection( Connection connection ) {
        this.connection = connection;
    }

}
