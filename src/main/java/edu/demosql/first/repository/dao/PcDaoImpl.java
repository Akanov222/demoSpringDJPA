package edu.demosql.first.repository.dao;

import edu.demosql.first.repository.config.Config;
import edu.demosql.first.repository.domain.Pc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class PcDaoImpl {

    public static final String CREATE_TABLE_PC =
            "CREATE TABLE IF NOT EXISTS pc(" +
                    "pc_id SERIAL PRIMARY KEY," +
                    "model INTEGER," +
                    "speed INTEGER," +
                    "ram INTEGER," +
                    "hd FLOAT(1)," +
                    "cd VARCHAR(255)," +
                    "price FLOAT(2))";

    public static final String DROP_TABLE_PC =
            "DROP TABLE IF EXISTS pc";

    public static final String GET_ALL_PC =
            "SELECT * FROM pc";

    public static final String SAVE_PC =
            "INSERT INTO pc (" +
                    "model," +
                    "speed," +
                    "ram," +
                    "hd," +
                    "cd," +
                    "price) " +
                    "VALUES (?,?,?,?,?,?)";

    public static final String UPDATE_PC =
            "UPDATE pc SET " +
                    "model = ?," +
                    "speed = ?," +
                    "ram = ?," +
                    "hd = ?," +
                    "cd = ?," +
                    "price = ? " +
                    "WHERE pc_id = ?";

    public static final String DELETE_ONE_PC =
            "DELETE FROM pc WHERE pc_id = ?";

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
            Config.getProperty(Config.DB_URL),
            Config.getProperty(Config.DB_LOGIN),
            Config.getProperty(Config.DB_PASSWORD));
        return connection;
    }

    public void createTablePc() throws SQLException {
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_PC);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTablePc() throws SQLException {
        try (Connection connection = getConnection();
        Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE_PC);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pc> getAllPc() throws SQLException {
        List<Pc> result = new ArrayList<>();
        try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_PC)) {
            while (resultSet.next()) {
                result.add(findPc(resultSet));
            }
        } return result;
    }

    private Pc findPc(ResultSet rs) throws SQLException {
        Pc onePc = new Pc();
        onePc.setModel(rs.getInt("model"));
        onePc.setSpeed(rs.getInt("speed"));
        onePc.setRam(rs.getInt("ram"));
        onePc.setHd(rs.getFloat("hd"));
        onePc.setCd(rs.getString("cd"));
        onePc.setPrice(rs.getFloat("price"));
        return onePc;
    }

    public void saveOnePc(Pc pc) throws SQLException {
        if (pc.getPc_id() == null) {
            try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    (SAVE_PC, Statement.RETURN_GENERATED_KEYS)) {
                connection.setAutoCommit(false);
                statement.setInt(1, pc.getModel());
                statement.setInt(2, pc.getSpeed());
                statement.setInt(3, pc.getRam());
                statement.setFloat(4, pc.getHd());
                statement.setString(5, pc.getCd());
                statement.setFloat(6, pc.getPrice());
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    pc.setPc_id(rs.getLong(1));
                }
                rs.close();
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PC)) {
                connection.setAutoCommit(false);
                statement.setInt(1, pc.getModel());
                statement.setInt(2, pc.getSpeed());
                statement.setInt(3, pc.getRam());
                statement.setFloat(4, pc.getHd());
                statement.setString(5, pc.getCd());
                statement.setFloat(6, pc.getPrice());
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOnePc(Long id) throws SQLException {
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ONE_PC)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}