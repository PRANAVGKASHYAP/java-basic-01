package org.example;

import java.util.Collections; // Make sure this is imported for shuffle

/**
 * Main class to run and test the MusicLibrary project.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Create a new library
        Library myLibrary = new Library();
        System.out.println("Command-Line Jukebox is running... Testing the workflows after changing the branch name ....");

        // --- TIER 1: Populating and Viewing Library ---
        System.out.println("\n--- [Test 1] Adding Songs to Library ---");

        // We use the new addNewSong() method
        myLibrary.addNewSong("Bohemian Rhapsody", "Queen", "A Night at the Opera", 355);
        myLibrary.addNewSong("Don't Stop Me Now", "Queen", "Jazz", 209);
        myLibrary.addNewSong("Another One Bites the Dust", "Queen", "The Game", 214);
        myLibrary.addNewSong("Smells Like Teen Spirit", "Nirvana", "Nevermind", 301);
        myLibrary.addNewSong("Come As You Are", "Nirvana", "Nevermind", 219);
        myLibrary.addNewSong("Stairway to Heaven", "Led Zeppelin", "Led Zeppelin IV", 482);

        // Test adding a duplicate song
        myLibrary.addNewSong("Bohemian Rhapsody", "Queen", "A Night at the Opera", 355);


        System.out.println("\n--- [Test 2] Viewing Full Catalog ---");
        myLibrary.displayCatalog();


        System.out.println("\n--- [Test 3] Viewing Artists and Songs by Artist ---");
        myLibrary.listAllArtists();

        System.out.println("---");
        myLibrary.listSongsByArtists("Queen");

        System.out.println("---");
        // Test a non-existent artist
        myLibrary.listSongsByArtists("The Beatles");


        System.out.println("\n--- [Test 4] Viewing Songs by Album ---");
        myLibrary.listSongsByAlbum("Nevermind");


        // --- TIER 2: Creating and Managing Playlists ---
        System.out.println("\n--- [Test 5] Creating Playlists ---");
        myLibrary.createPlaylist("Rock Anthems");
        myLibrary.createPlaylist("90s Hits");

        // Test creating a duplicate playlist
        myLibrary.createPlaylist("Rock Anthems");


        System.out.println("\n--- [Test 6] Adding Songs to Playlists ---");

        // Note: The key is "title-artist" as defined in your corrected Library class
        myLibrary.addSongToPlaylist("Bohemian Rhapsody-Queen", "Rock Anthems");
        myLibrary.addSongToPlaylist("Stairway to Heaven-Led Zeppelin", "Rock Anthems");

        myLibrary.addSongToPlaylist("Smells Like Teen Spirit-Nirvana", "90s Hits");
        myLibrary.addSongToPlaylist("Come As You Are-Nirvana", "90s Hits");

        // Test adding a non-existent song
        myLibrary.addSongToPlaylist("Yesterday-The Beatles", "Rock Anthems");
        // Test adding to a non-existent playlist
        myLibrary.addSongToPlaylist("Bohemian Rhapsody-Queen", "My Faves");


        System.out.println("\n--- [Test 7] Displaying Playlists ---");
        myLibrary.displaySongsInPlaylist("Rock Anthems");
        System.out.println("---");
        myLibrary.displaySongsInPlaylist("90s Hits");


        System.out.println("\n--- [Test 8] Shuffling a Playlist (Feature F9) ---");
        System.out.println("Before shuffle:");
        myLibrary.displaySongsInPlaylist("90s Hits");

        myLibrary.shufflePlaylist("90s Hits");

        System.out.println("\nAfter shuffle:");
        myLibrary.displaySongsInPlaylist("90s Hits");

        System.out.println("\n--- [Test 9] Test Empty Playlist ---");
        myLibrary.createPlaylist("Empty List");
        myLibrary.displaySongsInPlaylist("Empty List");
    }
}