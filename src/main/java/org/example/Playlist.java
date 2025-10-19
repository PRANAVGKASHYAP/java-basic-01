package org.example;

import java.util.ArrayList;
import java.util.List;

class Playlist {
    List<Song> songs = new ArrayList<>();
    String name;

    public Playlist(String name) {
        this.name = name;
    }

    public void addSong(Song s) {
        this.songs.add(s);
    }

    public void removeSong(Song s) {
        // No try...catch needed.
        // This returns a boolean, which you could use.
        boolean removed = this.songs.remove(s);
        if (removed) {
            System.out.println("Removed " + s.title);
        } else {
            System.out.println(s.title + " was not in the playlist.");
        }
    }

    public int getPlaylistDuration() {
        int totalTime = 0;
        for (Song s : this.songs) {
            // Add a check for null to prevent crashes
            if (s.playTime != null) {
                totalTime += s.playTime;
            }
        }
        return totalTime;
    }
}