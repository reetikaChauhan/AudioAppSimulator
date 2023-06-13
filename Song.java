/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent implements Comparable<Song>// implement the Comparable interface
{
	public static final String TYPENAME = "SONG";

	public static enum Genre {
		POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL
	};

	private String artist; // Can be multiple names separated by commas
	private String composer; // Can be multiple names separated by commas
	private Genre genre;
	private String lyrics;

	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics) {
		// Make use of the constructor in the super class AudioContent.
		super(title, year, id, type, audioFile, length);
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;

		// Initialize additional Song instance variables.
	}

	public String getType() {
		return TYPENAME;
	}

	// Print information about the song. First print the basic information of the
	// AudioContent
	// by making use of the printInfo() method in superclass AudioContent and then
	// print artist, composer, genre
	public void printInfo() {
		// AudioContent audioFile;
		// super.getAudioFile();
		// String s = super.getAudioFile();
		// for (int i = 0; i < s.length(); i++) {

		// System.out.println("Title: " + super.getTitle() + " Id: " + super.getId()+ "
		// Year: " + super.getYear() + " Type: " + super.type + " Length: " +
		// super.getLength());

		super.printInfo();
		System.out.println("Artist:" + this.artist + " Composer: " + this.composer + " Genre: " + this.genre + "\n");

	}

	// Play the song by setting the audioFile to the lyrics string and then calling
	// the play() method of the superclass
	public void play() {
		super.setAudioFile(this.lyrics);
		super.play();

	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	// Two songs are equal if their AudioContent information is equal and both the
	// composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other) {
		Song otherA = (Song) other;

		if (super.equals(otherA) && this.composer.equals(otherA.composer) && this.artist.equals(otherA.artist)) {

			return true;

		}
		return false;

	}

	// Implement the Comparable interface
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other) {
		return super.getTitle().compareTo(other.getTitle());
	}
}
