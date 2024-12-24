package song;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Test {

    public static void main(String[] args) {
        Song song=new Song();
        String path=new File("").getAbsolutePath()+"\\src\\song\\MusicFiles\\别看我只是一只羊.mp3";
        File file=new File(path);
        song.setName("别看我只是一只羊");
        song.setArtist("群星");
        song.setFilePath(path);
        song.saveSongToFile();
        song.play();

//        Song song=Song.loadSongFromFile("D:\\idea progect\\rjgc\\src\\song\\MusicFiles\\只因你太美.txt");
//        System.out.println(song.toString());

    }
}
