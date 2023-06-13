import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library {
	private ArrayList<Song> songs;
	private ArrayList<AudioBook> audiobooks;
	private ArrayList<Playlist> playlists;

	// private ArrayList<Podcast> podcasts;

	// Public methods in this class set errorMesg string
	// Error Messages can be retrieved from main in class MyAudioUI by calling
	// getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";

	public String getErrorMessage() {
		return errorMsg;
	}

	public Library() {
		songs = new ArrayList<Song>();
		audiobooks = new ArrayList<AudioBook>();
		;
		playlists = new ArrayList<Playlist>();
		// podcasts = new ArrayList<Podcast>();
		;
	}

	/*
	 * Download audio content from the store. Since we have decided (design
	 * decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list)
	 * then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or
	 * AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already
	 * there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false.
	 * Otherwise add it to the list and return true
	 * See the video
	 */
	public boolean download(AudioContent content) {
		if ((content.getType().equals(Song.TYPENAME))) {
			if (!(songs.contains((Song) content))) {
				songs.add((Song) content);
				return true;
			} else {
				String errorMsg = "Content already in list";
				return false;
			}
		} else if ((content.getType().equals(AudioBook.TYPENAME))) {
			if (!(audiobooks.contains((AudioBook) content))) {
				audiobooks.add((AudioBook) content);
				return true;
			} else {
				String errorMsg = "Content already in list";
				return false;
			}
		} else {
			return false;
		}

	}

	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs() {
		for (int i = 0; i < songs.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();
		}
	}

	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks() {
		for (int i = 0; i < audiobooks.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();
		}

	}

	// Print Information (printInfo()) about all podcasts in the array list
	/*
	 * public void listAllPodcasts() {
	 * for (int i = 0; i < podcasts.size(); i++) {
	 * int index = i + 1;
	 * System.out.print("" + index + ". ");
	 * podcasts.get(i).printInfo();
	 * System.out.println();
	 * }
	 * 
	 * }
	 */

	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists() {
		for (int i = 0; i < playlists.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			System.out.println(playlists.get(i));
		}

	}

	// Print the name of all artists.
	public void listAllArtists() {
		// First create a new (empty) array list of string
		// Go through the songs array list and add the artist name to the new arraylist
		// only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists
		// names
		ArrayList<String> newList = new ArrayList<String>();
		Song ob;
		for (int i = 0; i < songs.size(); i++) {
			ob = songs.get(i);
			boolean ans = newList.contains(ob.getArtist());
			if (!ans) {
				newList.add(ob.getArtist());
			}
		}
		int counter = 1;
		for (int i = 0; i < newList.size(); i++) {

			System.out.println(counter + newList.get(i));
			counter++;
		}

	}

	// Delete a song from the library (i.e. the songs list) -
	// also go through all playlists and remove it from any playlist as well if it
	// is part of the playlist
	public boolean deleteSong(int index) {
		songs.remove(index - 1);
		for (int i = 0; i < playlists.size(); i++) {

			if (playlists.get(i).equals(playlists.get(index - 1))) {
				playlists.remove(i);
			}
		}
		return false;
	}

	// Sort songs in library by year

	public void sortSongsByYear() {
		SongYearComparator sb = new SongYearComparator();
		Collections.sort(songs, sb);

		// Use Collections.sort()

	}

	// Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			return Integer.compare(s1.getYear(), s2.getYear());
		}
	}

	// Sort songs by length
	public void sortSongsByLength() {
		SongLengthComparator s2 = new SongLengthComparator();
		Collections.sort(songs, s2);
		// Use Collections.sort()
	}

	// Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			return Integer.compare(s1.getLength(), s2.getLength());
		}

	}

	/* */
	// Sort songs by title
	public void sortSongsByName() {
		// Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}

	/*
	 * Play Content
	 */

	// Play song from songs list
	public boolean playSong(int index) {
		if (index < 1 || index > songs.size()) {
			errorMsg = "Song Not Found";
			return false;
		}
		songs.get(index - 1).play();
		return true;
	}

	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode) {
		return false;
	}

	// Print the episode titles of a specified season
	// Bonus
	public boolean printPodcastEpisodes(int index, int season) {
		return false;
	}

	// Play a chapter of an audio book from list of audiobooks
	public boolean playAudioBook(int index, int chapter) {
		if (index < 1 || index > songs.size()) {
			return false;
		} else {
			audiobooks.get(index - 1).selectChapter(chapter);
			audiobooks.get(index - 1).play();
			return true;
		}

	}

	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public boolean printAudioBookTOC(int index) {
		if (index < 1 || index > songs.size()) {
			return false;
		} else {
			audiobooks.get(index - 1).printTOC();
			return true;
		}
	}

	/*
	 * Playlist Related Methods
	 */

	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public boolean makePlaylist(String title) {
		for (Playlist p : this.playlists) {
			if (p.getTitle().equals(title)) {
				this.errorMsg = "Playlist With The Same Title Already Exists";
				return false;
			}
		}
		playlists.add(new Playlist(title));
		return true;
	}

	// Print list of content information (songs, audiobooks etc) in playlist named
	// title from list of playlists
	public boolean printPlaylist(String title) {
		for (Playlist p : this.playlists) {
			if (p.getTitle().equals(title)) {
				p.printContents();
				return true;
			}
		}
		this.errorMsg = "Playlist Not Found";
		return false;
	}

	// Play all content in a playlist
	public boolean playPlaylist(String playlistTitle) {
		for (Playlist p : this.playlists) {
			if (p.getTitle().equals(playlistTitle)) {
				for (AudioContent sa : p.getContent()) {
					sa.play();
				}
				return true;
			}
		}
		this.errorMsg = "Playlist Not Found";
		return false;
	}

	// Play a specific song/audiobook in a playlist
	public boolean playPlaylist(String playlistTitle, int indexInPL) {
		indexInPL--;
		for (Playlist p : this.playlists) {
			if (p.getTitle().equals(playlistTitle) && p.getContent().size() > indexInPL) {
				p.getContent().get(indexInPL).play();
				return true;
			}
		}
		this.errorMsg = "Playlist or Content Not Found";
		return false;
	}

	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public boolean addContentToPlaylist(String type, int index, String playlistTitle) {
		index--;
		if (Song.TYPENAME.equalsIgnoreCase(type) && index >= 0 && index < this.songs.size()) {
			for (Playlist p : this.playlists) {
				if (p.getTitle().equals(playlistTitle)) {
					p.addContent(this.songs.get(index));
					return true;
				}
			}
		} else if (AudioBook.TYPENAME.equalsIgnoreCase(type) && index >= 0 && index < this.audiobooks.size()) {
			for (Playlist p : this.playlists) {
				if (p.getTitle().equals(playlistTitle)) {
					p.addContent(this.audiobooks.get(index));
					return true;
				}
			}
		}
		this.errorMsg = "Playlist or Content Not Found";
		return false;
	}

	// Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is
	// valid
	public boolean delContentFromPlaylist(int index, String title) {
		index--;
		for (Playlist p : this.playlists) {
			if (p.getTitle().equals(title)) {
				p.deleteContent(index);
				return true;
			}
		}
		this.errorMsg = "Playlist or Content Not Found";
		return false;
	}

}
