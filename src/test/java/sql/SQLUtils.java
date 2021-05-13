package sql;

import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtils {
    public static Connection getConnection() throws SQLException {
        String Urldbase = System.getProperty("url.dbase");
        final Connection connection = DriverManager.getConnection(
                Urldbase, "app", "pass");
              //  "jdbc:mysql://127.0.0.1:3306/app", "app", "pass");
        //"jdbc:postgresql://localhost:5432/postgres", "app", "pass");
        return connection;
    }

    public static String getStatusPaymentCard() throws SQLException {
        String status = null;
        val sqlCode = "select  status from payment_entity order by created desc limit 1";

        try (val conn = getConnection();
             val codeStmt = conn.createStatement();
        ) {

            try (val rs = codeStmt.executeQuery(sqlCode)) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    public static int getAmountPaymentCard() throws SQLException {
        int amount = 0;
        val sqlCode = "select  amount from payment_entity order by created desc limit 1";

        try (val conn = getConnection();
             val codeStmt = conn.createStatement();
        ) {

            try (val rs = codeStmt.executeQuery(sqlCode)) {
                if (rs.next()) {
                    amount = rs.getInt("amount");
                }
            }
        }
        return amount;
    }

    public static String getStatusCredit() throws SQLException {
        String status = null;
        val sqlCode = "select  status from credit_request_entity order by created desc limit 1";

        try (val conn = getConnection();
             val codeStmt = conn.createStatement();
        ) {

            try (val rs = codeStmt.executeQuery(sqlCode)) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }
}