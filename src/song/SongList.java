package song;

import java.util.List;

public class SongList {

    private Integer id; //歌单id
    private String name; // 歌单名称
    private List<Song> songs; // 歌单中的歌曲列表



    public SongList() {
    }

    public SongList(Integer id, String name, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "SongList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }
}
