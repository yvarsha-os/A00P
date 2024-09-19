package abc;

import java.util.LinkedList;
import java.util.Scanner;

public class MusicPlaylist {
    private LinkedList<String> playlist;

    public MusicPlaylist() {
        playlist = new LinkedList<>();
    }

    // Method to add a song to the playlist
    public void addSong(String song) {
        playlist.add(song);
        System.out.println("Song added: " + song);
    }

    // Method to remove a song by name
    public void removeSong(String song) {
        if (playlist.remove(song)) {
            System.out.println("Song removed: " + song);
        } else {
            System.out.println("Song not found in the playlist.");
        }
    }

    // Method to move a song to a different position in the playlist
    public void moveSong(String song, int newPosition) {
        if (playlist.contains(song)) {
            playlist.remove(song);
            playlist.add(newPosition - 1, song);
            System.out.println("Moved song: " + song + " to position " + newPosition);
        } else {
            System.out.println("Song not found in the playlist.");
        }
    }

    // Method to display the playlist
    public void displayPlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("The playlist is empty.");
        } else {
            System.out.println("Current Playlist:");
            for (int i = 0; i < playlist.size(); i++) {
                System.out.println((i + 1) + ". " + playlist.get(i));
            }
        }
    }

    // Main method to run the playlist system
    public static void main(String[] args) {
        MusicPlaylist musicPlaylist = new MusicPlaylist();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMusic Playlist Menu:");
            System.out.println("1. Add a song to the playlist");
            System.out.println("2. Remove a song from the playlist");
            System.out.println("3. Move a song to a different position");
            System.out.println("4. Display the playlist");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the song to add: ");
                    String songToAdd = scanner.nextLine();
                    musicPlaylist.addSong(songToAdd);
                    break;
                case 2:
                    System.out.print("Enter the name of the song to remove: ");
                    String songToRemove = scanner.nextLine();
                    musicPlaylist.removeSong(songToRemove);
                    break;
                case 3:
                    System.out.print("Enter the name of the song to move: ");
                    String songToMove = scanner.nextLine();
                    System.out.print("Enter the new position: ");
                    int newPosition = scanner.nextInt();
                    musicPlaylist.moveSong(songToMove, newPosition);
                    break;
                case 4:
                    musicPlaylist.displayPlaylist();
                    break;
                case 5:
                    System.out.println("Exiting the playlist system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}