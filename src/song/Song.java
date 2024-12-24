package song;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.*;

public class Song {

    private String name; // 歌曲名称
    private String artist; // 歌手
    private String filePath; // 文件路径


    public void play() {
        new Thread(() -> {
            try {
                Player player = new Player(new FileInputStream(filePath));
                player.play();
            } catch (JavaLayerException | FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("播放失败");
            }
        }).start();
    }

    public void saveSongToFile() {
        String mp3Path=this.getFilePath();
        String savePath=null;
        if (mp3Path.endsWith(".mp3")) {
            // 替换旧后缀为新后缀
            savePath=mp3Path.substring(0, mp3Path.length() - ".mp3".length()) + ".txt";
        }
        try (FileWriter writer = new FileWriter(savePath)) { // true表示追加模式
            writer.write("name:" + this.getName() + "\n");
            writer.write("artist:" + this.getArtist() + "\n");
            writer.write("filePath:" + this.getFilePath() + "\n");
        } catch (IOException e) {
            System.out.println("保存失败");
            e.printStackTrace();
        }
    }

    public static Song loadSongFromFile(String txtPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(txtPath))) {
            String name = null, artist = null, filePath = null;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("name:")) {
                    name = line.substring(5).trim();
                } else if (line.startsWith("artist:")) {
                    artist = line.substring(7).trim();
                } else if (line.startsWith("filePath:")) {
                    filePath = line.substring(9).trim();
                }
            }
            return new Song(name, artist, filePath);
        } catch (IOException e) {
            System.out.println("加载失败");
            e.printStackTrace();
            return null;
        }
    }

    public Song() {
    }

    public Song(String name, String artist, String filePath) {
        this.name = name;
        this.artist = artist;
        this.filePath = filePath;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
