import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, String> users; // Store userID as key, username,password as value
    private String filename;
    private int nextUserId;

    public UserManager(String filename) {
        this.filename = filename;
        users = new HashMap<>();
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int maxUserId = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int userId = Integer.parseInt(parts[0]);
                    String username = parts[1];
                    String password = parts[2];
                    users.put(parts[1], parts[0] + "," + parts[2]); // Store username, id,password
                    if (userId > maxUserId) {
                        maxUserId = userId;
                    }
                }
            }
            nextUserId = maxUserId + 1; // Initialize next user ID
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        String userId = String.valueOf(nextUserId++);
        users.put(username, userId + "," + password);
        saveUsers();
        return true;
    }

    public boolean isValidUser(String username, String password) {
        String userInfo = users.get(username);
        if (userInfo != null) {
            String[] parts = userInfo.split(",");
            return password.equals(parts[1]); // Check if password matches
        }
        return false;
    }

    public int getUserId(String username) {
        String userInfo = users.get(username);
        if (userInfo != null) {
            return Integer.parseInt(userInfo.split(",")[0]);
        }
        return -1; // Indicate user not found
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                String[] parts = entry.getValue().split(",");
                writer.write(parts[0] + "," + entry.getKey() + "," + parts[1]);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
