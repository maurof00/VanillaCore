package it.mauro.vanillacore.database;

import it.mauro.vanillacore.MauroSurvival;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {
    private final MauroSurvival plugin;
    private Connection connection;

    public DatabaseManager(MauroSurvival plugin) {
        this.plugin = plugin;
        setupDatabase();
    }

    private void setupDatabase() {
        String username = "u24427_80WYfbBfAo";
        String password = "ZFfTnccA3^xxew.exyG^2ops";
        int port = 3306;

        String url = "jdbc:mysql://u24427_80WYfbBfAo:ZFfTnccA3%5Exxew.exyG%5E2ops@sql1.revivenode.com:3306/s24427_vanilla_data";

        try {
            connection = DriverManager.getConnection(url, username, password);
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS player_economy (" +
                            "player_uuid VARCHAR(36) PRIMARY KEY," +
                            "balance DOUBLE NOT NULL)"
            );
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getBalance(Player player) {
        UUID playerUUID = player.getUniqueId();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT balance FROM player_economy WHERE player_uuid = ?");
            statement.setString(1, playerUUID.toString());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void setBalance(Player player, double balance) {
        UUID playerUUID = player.getUniqueId();
        try {
            PreparedStatement statement = connection.prepareStatement("REPLACE INTO player_economy (player_uuid, balance) VALUES (?, ?)");
            statement.setString(1, playerUUID.toString());
            statement.setDouble(2, balance);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBalance(Player player, double amount) {
        double currentBalance = getBalance(player);
        setBalance(player, currentBalance + amount);
    }

    public void subtractBalance(Player player, double amount) {
        double currentBalance = getBalance(player);
        setBalance(player, currentBalance - amount);
    }
}
