package friendship;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Friendship {

    private String username;
    private List<String> friends;

    public void addFriend(String friend) {
        this.friends.add(friend);
    }

    public static List<String> getFriendsFromUser(String friendFile, String username) {
        List<String> friends = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(friendFile))) {
            String line;
            boolean isTargetUser = false;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(username)) {
                    isTargetUser = true; // 找到目标用户
                    continue;
                }
                if (isTargetUser && !line.trim().isEmpty()) {
                    friends.add(line.trim()); // 添加好友
                }
                if (isTargetUser && line.trim().isEmpty() && !friends.isEmpty()) {
                    break; // 完成读取
                }
            }
        } catch (IOException e) {
            System.out.println("读取好友文件时出错: " + e.getMessage());
        }
        return friends;
    }

    public static void displayFriendsOfUser(String filePath, String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 读取第一行，检查是否是指定的用户名
            line = reader.readLine();
            if (line != null && line.trim().equals(username)) {
                // 如果找到指定的用户名，读取后续的好友名单
                System.out.println("好友列表：");
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        System.out.println(line.trim());
                    }
                }
            } else {
                System.out.println("指定的用户不存在：" + username);
            }
        } catch (IOException e) {
            System.out.println("读取文件时出错: " + e.getMessage());
        }
    }

    public static void writeFriendshipToFile(String filePath, Friendship friendship) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) { // true 表示追加模式
            writer.write(friendship.getUsername());
            writer.newLine();
            for (String friend : friendship.getFriends()) {
                writer.write(friend);
                writer.newLine();
            }
            writer.newLine(); // 用户信息后留一个空行
            System.out.println("好友信息写入成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("写入文件时出错: " + e.getMessage());
        }
    }

    public static void removeFriendFromUser(String filePath, String username, String friendName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".tmp"))) {
            String line;
            boolean isTargetUser = false;
            boolean isFriendRemoved = false;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(username)) {
                    isTargetUser = true; // 找到目标用户
                    writer.write(line);
                    writer.newLine();
                    continue;
                }
                if (isTargetUser && line.trim().isEmpty()) {
                    break; // 遇到下一个空行，停止处理
                }
                if (isTargetUser && !line.trim().equals(friendName)) {
                    // 写入非目标好友的名字
                    writer.write(line);
                    writer.newLine();
                } else if (isTargetUser && line.trim().equals(friendName)) {
                    // 找到要删除的好友，跳过不写入
                    isFriendRemoved = true;
                }
            }
            if (!isFriendRemoved) {
                System.out.println("好友未找到: " + friendName);
            } else {
                System.out.println("好友已从列表中删除: " + friendName);
            }
        } catch (IOException e) {
            System.out.println("处理文件时出错: " + e.getMessage());
        }

        // 删除原始文件
        File originalFile = new File(filePath);
        File tempFile = new File(filePath + ".tmp");
        if (originalFile.delete()) {
            System.out.println("原始文件已删除");
        }
        // 将临时文件重命名为原始文件
        if (tempFile.renameTo(originalFile)) {
            System.out.println("更改已保存");
        } else {
            System.out.println("更改保存失败");
        }
    }




    public Friendship() {
    }

    public Friendship(String username, List<String> friends) {
        this.username = username;
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "username='" + username + '\'' +
                ", friends=" + friends +
                '}';
    }
}
