package song;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SongDatabase {


    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    // 单例实例
    private static SongDatabase instance = new SongDatabase();

    // 存放所有歌曲的列表
    private List<Song> songs;

    // 私有构造函数，防止外部创建实例
    private SongDatabase() {
        this.songs = new ArrayList<>();
    }

    // 提供全局访问点获取单例实例
    public static SongDatabase getInstance() {
        return instance;
    }

    // 添加歌曲到数据库

    // 初始化数据库
    public void initializeDatabase() {
        // 加载歌曲信息
        this.loadSongsFromDirectory("D:/idea progect/rjgc/src/song/MusicFiles");
    }

    public void loadSongsFromDirectory(String directoryPath) {
        Path dirPath = Paths.get(directoryPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.txt")) {
            for (Path filePath : stream) {
                Song song = Song.loadSongFromFile(filePath.toString());
                if (song != null) {
                    this.songs.add(song); // 将加载的歌曲添加到数据库中
                }
            }
        } catch (IOException e) {
            System.out.println("读取目录失败");
            e.printStackTrace();
        }
    }

    public List<Song> getSongs() {
        return songs;
    }

    // 显示数据库中所有歌曲的名字和作者信息
    public void displayAllSongsInfo() {
        if (songs == null || songs.isEmpty()) {
            System.out.println("数据库中没有歌曲。");
            return;
        }
        for (Song song : songs) {
            System.out.println("歌曲名称: " + song.getName() + "  作者: " + song.getArtist());
        }
    }

    // 根据歌曲名称获取歌曲对象
    public Song getSongByName(String name) {
        if (songs == null) {
            return null;
        }
        for (Song song : songs) {
            if (song.getName().equalsIgnoreCase(name)) {
                return song; // 返回找到的第一个匹配项
            }
        }
        return null; // 如果没有找到，返回null
    }



}
