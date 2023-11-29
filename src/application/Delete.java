package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.*;

public class Delete {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();

            // Deletar dados
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, 2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Pronto! Linhas afetadas: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
