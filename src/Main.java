import friendship.Friendship;
import song.MusicPlayer;
import song.Song;
import song.SongDatabase;
import user.User;
import userDynamic.UserDynamic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("*****欢迎来到网易云音乐*****");
        String username=User.login();

        while (true) {
            showMenu(username);
        }
    }

    public static void showMenu(String username) {
        System.out.println("请选择你要进行的操作(输入对应数字)");
        System.out.println("1:音乐功能");
        System.out.println("2:个人动态");
        System.out.println("3:好友系统");
        System.out.println("9:退出网易云音乐");

        Scanner scanner=new Scanner(System.in);
        int choose=Integer.parseInt(scanner.nextLine());
        if (choose==1){
            showMusicMenu(username);
        }else if (choose==2){
            showDynamicMenu(username);
        }else if (choose==3){
            showFriendshipMenu(username);
        } else if (choose==9){
            System.out.println("正在退出...");
            Runtime.getRuntime().exit(0);
        }

    }

    public static void showMusicMenu(String username){
        SongDatabase songDatabase=SongDatabase.getInstance();
        songDatabase.initializeDatabase();
        System.out.println("数据库加载成功！");
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.println("请选择操作：");
            System.out.println("1：显示数据库所有音乐");
            System.out.println("2：播放音乐");
            System.out.println("9：返回");
            int choose = Integer.parseInt(scanner.nextLine());
            if (choose == 1) {
                songDatabase.displayAllSongsInfo();
            }else if (choose==2){
                songDatabase.displayAllSongsInfo();
                System.out.println("请输入你要收听的音乐名称");
                String songName=scanner.nextLine();
                Song song=songDatabase.getSongByName(songName);
                System.out.println("开始播放");
                song.play();
            }else if (choose==3){

            }else if (choose==9){
                showMenu(username);
            }
        }
    }

    public static void showDynamicMenu(String username){
        System.out.println("欢迎来到个人动态界面");
        String filePath="D:/idea progect/rjgc/src/userDynamic/userDynamicFiles/dynamic.txt";
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.println("请选择操作：");
            System.out.println("1：显示当前用户个人动态");
            System.out.println("2：发布个人动态");
            System.out.println("3：删除个人动态");
            System.out.println("4：查看好友动态");
            System.out.println("9：返回");
            int choose = Integer.parseInt(scanner.nextLine());
            if (choose == 1) {
                System.out.println(username+"的个人动态如下：");
                UserDynamic.displayDynamicsByUsername(filePath,username);
            }else if (choose==2){
                UserDynamic userDynamic=new UserDynamic();
                userDynamic.setUsername(username);
                System.out.println("请输入动态内容");
                String content=scanner.nextLine();
                userDynamic.setContent(content);
                userDynamic.setCreateTime(new Date());
                UserDynamic.saveDynamicToFile(userDynamic,filePath);
            }else if (choose==3){
                System.out.println("请输入你要删除的动态行号(从1开始)");
                int line=Integer.parseInt(scanner.nextLine());
                UserDynamic.deleteDynamicByLineNumber(filePath,line);
            }else if (choose==4){
                String dynamicFilePath="D:/idea progect/rjgc/src/userDynamic/userDynamicFiles/dynamic.txt";
                String friendFilePath="D:/idea progect/rjgc/src/friendship/friendshipFiles/"+username+"friendships.txt";
                List<String> friends=Friendship.getFriendsFromUser(friendFilePath,username);
                System.out.println(friends);
                UserDynamic.displayDynamicsForFriends(friends,dynamicFilePath,username);
            }else if (choose==9){
                showMenu(username);
            }
        }
    }

    public static void showFriendshipMenu(String username){
        System.out.println("欢迎来到好友系统！");
        String filePath="D:/idea progect/rjgc/src/friendship/friendshipFiles/"+username+"friendships.txt";
        Scanner scanner=new Scanner(System.in);
        Friendship friendship=new Friendship(username,new ArrayList<>());

        while (true) {
            System.out.println("请选择操作：");
            System.out.println("1：显示好友列表");
            System.out.println("2：添加好友");
            System.out.println("3：删除好友");
            System.out.println("9：返回");
            int choose = Integer.parseInt(scanner.nextLine());
            if (choose == 1) {
                Friendship.displayFriendsOfUser(filePath,username);
            }else if (choose==2){
                System.out.println("请输入目标用户名");
                String friendName=scanner.nextLine();
                friendship.addFriend(friendName);
                Friendship.writeFriendshipToFile(filePath,friendship);
                System.out.println("好友添加成功");
            }else if (choose==3){
                System.out.println("请输入要删除的好友");
                String friendName=scanner.nextLine();
                Friendship.removeFriendFromUser(filePath,username,friendName);
                System.out.println("好友删除成功");
            }else if (choose==9){
                showMenu(username);
            }
        }
    }
}
