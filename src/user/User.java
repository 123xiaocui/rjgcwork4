package user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class User {

    private String username;
    private String password;

    public static String login(){
        System.out.println("请先登录");
        boolean loginFlag=false;
        Scanner scanner = new Scanner(System.in);
        while (loginFlag!=true){
            System.out.print("请输入用户名: ");
            String username = scanner.nextLine();
            System.out.print("请输入密码: ");
            String password = scanner.nextLine();
            boolean isValid = validateCredentials(username, password);

            if (isValid) {
                System.out.println("登录成功!");
                return username;
            } else {
                System.out.println("登录失败，请重新登录");
            }
        }
        return null;
//        scanner.close();
    }

    public static boolean validateCredentials(String username, String password) {
        String credentialsPath = "D:/idea progect/rjgc/src/user/userFiles/login.txt"; // 存放用户名和密码的文件路径

        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userPass = line.split(","); // 按照逗号分隔用户名和密码
                if (userPass[0].equals(username) && userPass[1].equals(password)) {
                    return true; // 找到匹配的用户名和密码
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // 没有找到匹配的用户名和密码
    }




    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
