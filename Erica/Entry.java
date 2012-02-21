package Erica;

/* NOTES:
-Find a way to uniquely identify an entry
**************/

import javax.swing.*;

public class Entry{
	private String title;
	private String artist;
	private String album;
	private String filePath;
        private String artFilePath;
	private Date dateAdded;
	private Rating rating=new Rating();
	private boolean selected;
	private JButton buttonPlus;
	private boolean tagged;

	public Entry(){
		title="";
		artist="";
		album="";
		filePath="";
                artFilePath="";
		dateAdded=new Date("unknown");
		rating=new Rating();
		selected=false;
		buttonPlus=new JButton("+");
	}//end constructor

	public Entry(String title, String artist, String album, String added, String rating, String art, String location){
		setTitle(title);
		setArtist(artist);
		setAlbum(album);
                setArtPath(art);
		setFilePath(location);
		dateAdded=new Date(added);
		setRating(Integer.parseInt(rating));
		selected=false;
		buttonPlus=new JButton("+");
	}//end constructor

	/******************************************************************************/
	public String getString(int k){
		String result=title;
		switch(k){
			default:
			case 0: result = title;
			break;
			case 1: result = artist;
			break;
			case 2: result = album;
			break;
			case 3: result=dateAdded.toString();
			break;
			case 4: result=rating.toString();
			break;
		}

		return result;
	}//

        public String getArtPath() {
            return artFilePath;
        }

	public String getTitle(){
		return title;
	}//end getTitle

	public void setTitle(String givenTitle){
		title=givenTitle;
	}//end setTitle

	public String getArtist(){
		return artist;
	}//end getArtist

	public void setArtist(String givenArtist){
		artist=givenArtist;
	}//end setArtist

	public String getAlbum(){
		return album;
	}//end getAlbum

	public void setAlbum(String givenAlbum){
		album=givenAlbum;
	}//end setAlbum

	public String getFilePath(){
		return filePath;
	}//end getFilePath

	public void setFilePath(String givenFilePath){
		filePath=givenFilePath;
	}//end setFilePath

	public Date getDate(){
		return dateAdded;
	}//end getDate

	public void setDate(String givenDate){
		dateAdded=new Date(givenDate);
	}//end setDate

	public int getRating(){
		return rating.getRating();
	}//end getRating

        public void setArtPath(String art) {
            artFilePath = art;
        }

	public void setRating(int givenRating){
		if (givenRating==-1||(givenRating>0&&givenRating<=5)){
			rating.setRating(givenRating);
		}
		else System.err.println("Incorrect rating found.");
	}//end setRating

	public boolean getSelected(){
		return selected;
	}//end getSelected

	public void setSelected(boolean given){
		selected=given;
	}//end setSelected

	public JButton getButton(){
		return buttonPlus;
	}//end getButton

	public String toString() {
		    return "Title: " + this.title + "\n" +
            "Artist: " + this.artist + "\n" +
            "Album: " + this.album + "\n" +
            "Date Added: " + this.dateAdded + "\n" +
            "Rating: " + this.rating + "\n" +
            "FilePath: " + this.filePath + "\n";
	}

	/******************************************************************************/

	private boolean equals(Entry otherEntry){
		return this.getFilePath().equalsIgnoreCase(otherEntry.getFilePath());
	}//end equals

	public boolean contains(String search){
		search=search.toLowerCase();
		if (title.toLowerCase().contains(search)){
			return true;
		}
		if (artist.toLowerCase().contains(search)){
			return true;
		}
		if (dateAdded.toString().toLowerCase().contains(search)){
			return true;
		}
		if ((""+dateAdded.getYear()).equals(search)){ //searching for songs by the four-digit year value
			return true;
		}
		return false;
	}//end contains



public class Rating{
	private int numberRating=-1;

	private Rating(){
	}

	private int getRating(){
		return numberRating;
	}

	private void setRating(int givenRating){
		numberRating=givenRating;
	}

	public String toString(){
		String star="\u2605"; 	//unicode number for the star char
		String result="";
		if (numberRating==-1){
			result="Not rated.";
		}
		for (int i=0; i<numberRating; i++){
			result+=star;
		}
		return result;
	}//end toString
}//end Rating
}//end Entry