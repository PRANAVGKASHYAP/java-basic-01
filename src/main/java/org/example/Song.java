package org.example;

import java.util.Objects;

// Make this a top-level class (not inside another class)
// Implement the Comparable interface
class Song implements Comparable<Song> {
    String title;
    String artist;
    Integer playTime; // in seconds
    String album;

    // A constructor is highly recommended!
    public Song(String title, String artist, String album, Integer playTime) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.playTime = playTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        // Check for null and class type
        if (obj == null || getClass() != obj.getClass()) return false;

        Song song = (Song) obj;
        // Equality is based on title, artist, and album.
        return Objects.equals(title, song.title) &&
                Objects.equals(artist, song.artist) &&
                Objects.equals(album, song.album);
    }

    @Override
    public int hashCode() {
        // Must use the *same fields* as equals()
        return Objects.hash(title, artist, album);
    }

    @Override
    public String toString() {
        // A cleaner format
        int minutes = playTime / 60;
        int seconds = playTime % 60;
        // Use String.format for clean output
        return String.format("'%s' by %s (%s) - %d:%02d", title, artist, album, minutes, seconds);
    }

    @Override
    public int compareTo(Song other) {
        // This makes songs sort by title by default
        return this.title.compareTo(other.title);

        // If you want to sort by playtime (as you had):
        // return Integer.compare(this.playTime, other.playTime);
    }
}