package edu.demosql.first.repository.dao;

import edu.demosql.first.repository.config.Config;
import edu.demosql.first.repository.domain.Printer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrinterDaoImpl {

    public static final String CREATE_TABLE_PRINTER =
            "CREATE TABLE IF NOT EXISTS printer(" +
                    "printer_id SERIAL PRIMARY KEY," +
                    "model INTEGER NOT NULL," +
                    "color VARCHAR(255) NOT NULL," +
                    "type VARCHAR(255)," +
                    "price FLOAT(2))";

    public static final String DROP_TABLE_PRINTER =
            "DROP TABLE IF EXISTS printer";

    public static final String GET_ALL_PRINTERS =
            "SELECT * FROM printer";

    public static final String SAVE_ONE_PRINTER =
            "INSERT INTO printer (" +
                    "model," +
                    "color," +
                    "type," +
                    "price)" +
                    "VALUES (?,?,?,?)";

    public static final String DELETE_ONE_PRINTER =
            "DELETE FROM printer WHERE printer_id = ?";

    public static final String UPDATE_ONE_PRINTER =
            "UPDATE printer SET " +
                    "model = ?, " +
                    "color = ?, " +
                    "type = ?, " +
                    "price = ? " +
                    "WHERE printer_id = ?";

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                    Config.getProperty(Config.DB_URL),
                    Config.getProperty(Config.DB_LOGIN),
                    Config.getProperty(Config.DB_PASSWORD));
        return connection;
    }

    public void createTablePrinter() throws SQLException {
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_PRINTER);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTablePrinter() throws SQLException {
        try (Connection connection = getConnection();
        Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE_PRINTER);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Printer> getAllPrinters() throws SQLException {
        List<Printer> printers = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(GET_ALL_PRINTERS)) {
            while (rs.next()) {
                printers.add(findPrinters(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return printers;
    }

    public Printer findPrinters(ResultSet resultSet) throws SQLException {
        Printer printer = new Printer();
        printer.setPrinter_id(resultSet.getLong("printer_id"));
        printer.setModel(resultSet.getInt("model"));
        printer.setColor(resultSet.getString("color"));
        printer.setType(resultSet.getString("type"));
        printer.setPrice(resultSet.getLong(("price")));
        return printer;
    }

    public void setSaveOnePrinter(Printer printer) throws SQLException {
        if (printer.getPrinter_id() == null) {
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(SAVE_ONE_PRINTER, Statement.RETURN_GENERATED_KEYS)) {
                connection.setAutoCommit(false);
                statement.setInt(1, printer.getModel());
                statement.setString(2, printer.getColor());
                statement.setString(3, printer.getType());
                statement.setFloat(4, printer.getPrice());
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    printer.setPrinter_id(rs.getLong(1));
                }
                rs.close();
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ONE_PRINTER)) {
                connection.setAutoCommit(false);
                statement.setInt(1, printer.getModel());
                statement.setString(2, printer.getColor());
                statement.setString(3, printer.getType());
                statement.setFloat(4, printer.getPrice());
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOnePrinter(Printer printer) throws SQLException {
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ONE_PRINTER)) {
            statement.setLong(1, printer.getPrinter_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}