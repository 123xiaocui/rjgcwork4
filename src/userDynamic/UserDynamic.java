package userDynamic;

import java.io.*;
import java.util.Date;
import java.util.List;

public class UserDynamic {

    private String username;
    private String content; // 动态内容
    private Date createTime; // 创建时间

    public static void saveDynamicToFile(UserDynamic dynamic, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true 表示追加模式
            writer.write(dynamic.toString() + "\n");
            System.out.println("发布成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayDynamicsByUsername(String filePath, String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 1; // 添加一个变量来跟踪行号
            while ((line = reader.readLine()) != null) {
                if (line.contains("username='" + username + "'")) {
                    // 输出行号和行内容
                    System.out.println("Line " + lineCount + ": " + line);
                }
                lineCount++; // 每读取一行，行号增加1
            }
        } catch (IOException e) {
            System.out.println("读取文件时出错: " + e.getMessage());
        }
    }

    public static void deleteDynamicByLineNumber(String filePath, int lineNumber) {
        File originalFile = new File(filePath);
        File tempFile = new File(filePath + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            int currentLineNumber = 1;

            while ((line = reader.readLine()) != null) {
                if (currentLineNumber != lineNumber) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    System.out.println("删除行号为: " + currentLineNumber + " 的动态信息");
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 删除原始文件
        if (originalFile.delete()) {
            System.out.println("原始文件已删除");
        }

        // 将临时文件重命名为原始文件
        if (tempFile.renameTo(originalFile)) {
            System.out.println("动态删除成功");
        } else {
            System.out.println("动态删除失败，临时文件重命名失败");
        }
    }

    public static void displayDynamicsForFriends(List<String> friends, String dynamicFilePath, String username) {
        if (friends.isEmpty()) {
            System.out.println("用户 " + username + " 没有好友或好友列表为空。");
            return;
        }
        System.out.println("用户 " + username + " 的好友动态如下：");
        try (BufferedReader reader = new BufferedReader(new FileReader(dynamicFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 输出好友的动态信息
                for (String friend : friends) {
                    if (line.contains("username='" + friend + "'")) {
                        System.out.println(line);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("读取动态文件时出错: " + e.getMessage());
        }
    }

    public UserDynamic() {
    }

    public UserDynamic(String username, String content, Date createTime) {
        this.username = username;
        this.content = content;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserDynamic{" +
                "username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
