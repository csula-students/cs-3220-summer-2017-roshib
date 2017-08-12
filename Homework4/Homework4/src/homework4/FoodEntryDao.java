package homework4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class FoodEntryDao implements DAO<FoodEntry> {
    public List<FoodEntry> list() {
        List<FoodEntry> list = new ArrayList<>();
        Database db = new Database();
        try (Connection c = db.connection()) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM food_items");
            while (rs.next()) {
                list.add(new FoodEntry(rs.getInt("id"), rs.getString("food_name"), rs.getString("price"),
						rs.getString("description"), rs.getString("image")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
        return list;
        
    }

    public Optional<FoodEntry> get(int id) {
        Optional<FoodEntry> toReturn = Optional.empty();
        Database db = new Database();
        try (Connection c = db.connection()) {
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM food_items WHERE food_items.id = ? ");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                FoodEntry updatedFood = new FoodEntry(rs.getInt("id"), rs.getString("food_name"), rs.getString("price"),
						rs.getString("description"), rs.getString("image"));
                toReturn = Optional.of(updatedFood);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public void add(FoodEntry entry) {
        Database db = new Database();
        try (Connection c = db.connection()) {
        	PreparedStatement pstmt = c
					.prepareStatement("INSERT INTO food_items (food_name, price,description,image) VALUES (?,?,?,?)");
        	pstmt.setString(1, entry.getName());
			pstmt.setString(2, entry.getPrice());
			pstmt.setString(3, entry.getComment());
			pstmt.setString(4, entry.getImage());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(FoodEntry entry) {
        Database db = new Database();
        try (Connection c = db.connection()) {
            PreparedStatement pstmt = c.prepareStatement("UPDATE food_items SET  food_name = ?, price = ?, description = ?, image = ? WHERE id = ?");
            pstmt.setString(1, entry.getName());
			pstmt.setString(2, entry.getPrice());
			pstmt.setString(3, entry.getComment());
			pstmt.setString(4, entry.getImage());
            pstmt.setInt(5, entry.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Database db = new Database();
        try (Connection c = db.connection()) {
            PreparedStatement pstmt = c.prepareStatement("DELETE FROM food_items WHERE food_items.id = ? ");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}