/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ohtu.domain.User;

/**
 *
 * @author Arto
 */
public class FileUserDao implements UserDao {

    File file;

    public FileUserDao(String file) {
        this.file = new File(file);
    }

    public List<User> listAll() {
        List<User> list = new ArrayList<User>();
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                list.add(new User(scanner.nextLine(), scanner.nextLine()));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public User findByName(String name) {
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                User user = new User(scanner.nextLine(), scanner.nextLine());
                if (user.getUsername().equals(name)) {
                    scanner.close();
                    return user;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void add(User user) {
        List<User> users = listAll();
        users.add(user);
        try {
            FileWriter writer = new FileWriter(file);
            for (User user1 : users) {
                writer.append("\n" + user1.getUsername() + "\n" + user1.getPassword());
            }       
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
