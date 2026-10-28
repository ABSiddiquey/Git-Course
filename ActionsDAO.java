package dao;

import model.Actions;
import java.sql.*;
import java.time.LocalDateTime;

public class ActionsDAO {

    public void addAction(Actions t) throws SQLException {
        String sql = "INSERT INTO actions(member_id, book_id, action_type, action_time) VALUES(?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getMemberId());
            ps.setInt(2, t.getBookId());
            ps.setString(3, t.getActionType());
            ps.setTimestamp(4, Timestamp.valueOf(
                    t.getActionTime() != null ? t.getActionTime() : LocalDateTime.now()
            ));
            ps.executeUpdate();
        }
    }
}
