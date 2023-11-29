package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.util.Scanner;

public class Update {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();

            // Atualizar dados
            st = conn.prepareStatement("UPDATE seller SET BaseSalary = BaseSalary + ? WHERE DepartmentId = ?",
                    Statement.RETURN_GENERATED_KEYS);

            System.out.print("Aumento: ");
            double salario = sc.nextDouble();
            System.out.print("Id do Departamento: ");
            int departamento = sc.nextInt();

            st.setDouble(1, salario);
            st.setInt(2, departamento);

            int rowsAffected = st.executeUpdate();

            System.out.println("Pronto! Linhas afetadas: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
