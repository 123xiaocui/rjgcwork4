package song;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer implements Runnable {
    private String filePath;

    public MusicPlayer(String filePath) {
        this.filePath = filePath;
    }

    public void run() {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Player player = new Player(fis);
            player.play();
        } catch (JavaLayerException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("播放失败");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("播放被中断");
            }
        }
    }

    public void stopPlaying() {
        Thread.currentThread().interrupt(); // 中断线程以停止播放
    }
}
