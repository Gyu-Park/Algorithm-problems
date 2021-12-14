/**
A playlist is considered a repeating playlist if any of the songs contain a reference to a previous song in the playlist. 
Otherwise, the playlist will end with the last song which points to null.

Implement a function isRepeatingPlaylist that, efficiently with respect to time used, 
returns true if a playlist is repeating or false if it is not.

For example, the following code prints "true" as both songs point to each other.

Song first = new Song("Hello");
Song second = new Song("Eye of the tiger");
    
first.setNextSong(second);
second.setNextSong(first);
    
System.out.println(first.isRepeatingPlaylist());
**/
package Problems;

import java.util.Set;
import java.util.HashSet;

public class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
        if (this.nextSong == null)
            return false;
        if (this.name.equals(this.nextSong.name))
            return true;

        Song pointer = this.nextSong;
        Set<Song> songs = new HashSet<>();
        songs.add(this);
        songs.add(pointer);

        while (pointer.nextSong != null) {
            if (songs.contains(pointer.nextSong)) {
                return true;
            }

            pointer = pointer.nextSong;
            songs.add(pointer);
        }

        return false;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}