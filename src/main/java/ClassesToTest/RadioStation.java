package ClassesToTest;

import java.util.List;

public abstract class RadioStation {
    private String name;
    private String host;
    private String genre;
    private int songsInQueue = 0;
    List<String> availableSongs;

    RadioStation(String name, String host, String genre, List<String> songs) {
        this.name = name;
        this.host = host;
        this.genre = genre;
        this.availableSongs = songs;
    }

    public void sayHello(String destination) {
        final int packageNameLength = 14;
        System.out.println("Hello to the " + destination + " from the " + getClass().getName().substring(packageNameLength) + "!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void showAvailableSongs() {
        System.out.println("Available songs on the " + getName() + ":");
        availableSongs.forEach(System.out::println);
    }

    public static class SongNotFoundException extends Exception {
        SongNotFoundException(String message) {
            super(message);
        }
    }

    public void orderSong(String songName) throws SongNotFoundException {
        if (availableSongs.contains(songName)) {
            System.out.println("Ok, this song is ordered");
            songsInQueue++;
            return;
        }
        throw new SongNotFoundException("This song isn't in the list of available songs");
    }
}