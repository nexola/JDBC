package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Insert {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        // Inserir dados
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO seller(Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            System.out.print("Nome: ");
            String nome = sc.next();
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Salário: ");
            double salario = sc.nextDouble();
            System.out.print("Id do Departamento: ");
            int departamento = sc.nextInt();

            st.setString(1, nome);
            st.setString(2, email);
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            st.setDouble(4, salario);
            st.setInt(5, departamento);

            int rowsAffected = st.executeUpdate();
            rs = st.getGeneratedKeys();

            while (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Pronto! Id = " + id);
            }

            st = conn.prepareStatement("INSERT INTO department(Name) VALUES('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS);
            rowsAffected = st.executeUpdate();
            rs = st.getGeneratedKeys();

            System.out.println("Pronto, linhas afetadas: " + rowsAffected);

        } catch (SQLException | ParseException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}