import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, String> users;
    private String filename;

    public UserManager(String filename) {
        this.filename = filename;
        users = new HashMap<>();
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, password);
        saveUsers();
        return true;
    }

    public boolean isValidUser(String username, String password) {
        return password.equals(users.get(username));
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
