package dao;

import Utils.JDBCUtils;
import Utils.SqlUtils;
import entities.Lot;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LotRepository {
    public void save(List<Lot> lots) {
        lots.forEach(this::save);
    }

    public void save(Lot lot) {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO lot(parking_lot_tag, parking_log_number, car_number) VALUES (?, ?, ?)";
            SqlUtils.executeUpdate(conn, sql, lot.getParkingLotTag(), lot.getParkingLotNumber(), lot.getCarNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(List<Lot> lots) {
        lots.forEach(this::save);
    }

    public void update(Lot lot) {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "UPDATE lot SET parking_lot_tag=?, parking_log_number=?, car_number=? WHERE id = ?";
            SqlUtils.executeUpdate(conn, sql, lot.getParkingLotTag(), lot.getParkingLotNumber(), lot.getCarNumber(), lot.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteAll() {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "TRUNCATE lot";
            SqlUtils.executeUpdate(conn, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteById(int id) {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "DELETE FROM lot WHERE id = ?";
            SqlUtils.executeUpdate(conn, sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lot> queryAll() {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "SELECT id, parking_lot_tag, parking_log_number, car_number FROM lot";
            return SqlUtils.executeQuery(conn, sql, Lot.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Lot queryById(int id) {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "SELECT id, parking_lot_tag, parking_log_number, car_number FROM lot Where id=?";
            return SqlUtils.executeQuerySingle(conn, sql, Lot.class, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Lot> queryAvailableLots() {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "SELECT id, parking_lot_tag, parking_log_number, car_number " +
                "FROM lot " +
                "WHERE car_number IS NULL " +
                "ORDER BY parking_lot_tag, parking_log_number";
            List<Lot> lots = SqlUtils.executeQuery(conn, sql, Lot.class);
            if (lots.size() == 0) {
                throw new ParkingLotFullException();
            }
            return lots;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Lot queryByTicket(String ticket) {
        String[] ticketDetails = ticket.split(",");
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "SELECT id, parking_lot_tag, parking_log_number, car_number " +
                "FROM lot " +
                "WHERE parking_lot_tag = ? " +
                "  AND parking_log_number = ? " +
                "  AND car_number = ?";
            Lot lot = SqlUtils.executeQuerySingle(conn, sql, Lot.class,
                ticketDetails[0], ticketDetails[1], ticketDetails[2]);
            if (lot == null) {
                throw new InvalidTicketException();
            }
            return lot;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
