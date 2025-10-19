package org.example;

import java.util.*;

class Library {

    // --- CORE COLLECTIONS ---

    // Initialized!
    private Map<String, Song> masterCatalog = new HashMap<>();
    private Map<String, List<Song>> songsByArtist = new HashMap<>();
    private Map<String, Set<Song>> songsInAlbum = new HashMap<>();

    // Initialized! This is critical.
    private Map<String, Playlist> userPlaylists = new HashMap<>();

    // (Queue is for Tier 3, so we'll leave it for now)
    // private Queue<Song> nowPlaying = new LinkedList<>();

    // --- TIER 1 METHODS ---

    /**
     * Adds a new song to all internal collections.
     * This is the "correct" way to do it, not with a Scanner inside.
     */
    public void addNewSong(String title, String artist, String album, int playTime) {
        Song newSong = new Song(title, artist, album, playTime);

        // 1. Add to Master Catalog
        // We create a unique key. Let's use the hashCode as a simple key.
        // Or better, use the song's title+artist
        String key = newSong.title + "-" + newSong.artist;
        if (masterCatalog.containsKey(key)) {
            System.out.println("Error: Song already exists in catalog.");
            return;
        }
        masterCatalog.put(key, newSong);

        // 2. Add to songsByArtist Map

        // Get the list for this artist. If it doesn't exist, create it.
        songsByArtist.putIfAbsent(artist, new ArrayList<>()); // this creates a entry for the artist if this artist is new
        // Now get the list and add the song
        List<Song> artistSongs = songsByArtist.get(artist);
        artistSongs.add(newSong);

        // 3. Add to songsInAlbum Map
        // Get the set for this album. If it doesn't exist, create it.
        songsInAlbum.putIfAbsent(album, new HashSet<>());
        // Now get the set and add the song
        Set<Song> albumSongs = songsInAlbum.get(album);
        albumSongs.add(newSong);

        System.out.println("Added: " + newSong);
    }

    public void displayCatalog() {
        System.out.println("--- Master Song Catalog ---");
        for (Song s : masterCatalog.values()) {
            System.out.println(s);
        }
    }

    public void listAllArtists() {
        System.out.println("--- All Artists ---");
        // Just iterate over the keySet, much cleaner
        for (String artist : songsByArtist.keySet()) {
            System.out.println(artist);
        }
    }

    public void listSongsByArtists(String artistName) {
        System.out.println("--- Songs by " + artistName + " ---");

        // THIS IS THE EFFICIENT FIX: Use get()
        List<Song> songs = songsByArtist.get(artistName);

        if (songs == null || songs.isEmpty()) {
            System.out.println("No songs found for " + artistName);
        } else {
            // THIS IS THE PRINTING FIX: Use System.out.println()
            for (Song s : songs) {
                System.out.println(s);
            }
        }
    }

    // --- TIER 2 METHODS ---

    public void createPlaylist(String name) {
        if (userPlaylists.containsKey(name)) {
            System.out.println("Error: Playlist '" + name + "' already exists.");
        } else {
            Playlist newPlaylist = new Playlist(name);
            userPlaylists.put(name, newPlaylist);
            System.out.println("Created playlist: " + name);
        }
    }

    /**
     * Adds a song to a playlist.
     * Note the parameters: We need the *song's key* and the *playlist's name*.
     */
    public void addSongToPlaylist(String songKey, String playlistName) {
        // 1. Find the playlist (EFFICIENTLY)
        Playlist playlist = userPlaylists.get(playlistName);
        if (playlist == null) {
            System.out.println("Error: Playlist '" + playlistName + "' not found.");
            return;
        }

        // 2. Find the song (EFFICIENTLY)
        Song song = masterCatalog.get(songKey);
        if (song == null) {
            System.out.println("Error: Song '" + songKey + "' not found in master catalog.");
            return;
        }

        // 3. Add the song
        playlist.addSong(song);
        System.out.println("Added '" + song.title + "' to playlist '" + playlistName + "'.");
    }

    public void displaySongsInPlaylist(String name) {
        // 1. Find the playlist (EFFICIENTLY)
        Playlist playlist = userPlaylists.get(name);

        if (playlist == null) {
            System.out.println("Error: Playlist '" + name + "' not found.");
            return;
        }

        System.out.println("--- Songs in Playlist: " + playlist.name + " ---");

        if (playlist.songs.isEmpty()) {
            System.out.println(" (Playlist is empty)");
        } else {
            // 2. Print the songs (FIXED)
            for (Song s : playlist.songs) {
                System.out.println(s);
            }
        }

        // 3. Print total duration
        int totalSeconds = playlist.getPlaylistDuration();
        System.out.println("Total Time: " + (totalSeconds / 60) + "m " + (totalSeconds % 60) + "s");
    }

    public void listSongsByAlbum(String albumName){
        //find the album

        if(this.songsInAlbum.get(albumName).isEmpty() ){
            System.out.println("The album is not having any songs currently");
            return;
        }

        Set<Song> result = this.songsInAlbum.get(albumName);

        for(Song s : result){
            System.out.println(s);
        }
    }

    public void shufflePlaylist(String name){
        //find the playlist

        if(! this.userPlaylists.containsKey(name)){
            System.out.println("There is no such playlist");
        }

        Collections.shuffle(this.userPlaylists.get(name).songs);
        System.out.println("Playlist shuffeled....");
    }
}