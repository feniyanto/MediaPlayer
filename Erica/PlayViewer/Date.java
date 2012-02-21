package Erica.PlayViewer;

/**************
Erica Olavarria
January 2012
***************
Date
Class 4 of 11
**************/

/**
  * Date is an ADT that represents the purchase date of a plant. The 
  * constructor for Date accepts a String in the format YYYY, MM/YYYY,
  * or MM/DD/YYYY.  The constructor parses this String and adds each 
  * component to the String[]date.  It then stores each String as an 
  * integer in its respective int variable: day, month, or year.
  * Date contains a get method for each of these int variables. The 
  * compareTo method is utilized during sorting and determines if a given 
  * Date is before, after, or equal to the current Date. The toString method 
  * formats the Date into YYYY, MM/YYYY, or MM/DD/YYYY.
  *
  * Date also contains a boolean unknown, which is true if the purchaseDate
  * for a plant is unknown. Unknown dates are always sorted at the end of
  * the list. Additionally, unknown dates display as "" and they save as
  * "Unknown Purchase Date".
  */
public class Date{
	private String[] date={"0","0","0"};
	private int day=0;
	private int month=0;
	private int year=0;
	private boolean unknown=false;

	public Date(String givenDate){
		if (givenDate.length()==0||givenDate.toLowerCase().contains("unknown")){
			unknown=true;
		}
		else{
		int count=0;
		int start=0;
		givenDate+="/";					//add a slash on the end to make parsing easier
		for (int i=0; i<givenDate.length(); i++){
			if (givenDate.charAt(i)=='/'){
				date[count]=givenDate.substring(start,i);
				start=i+1;
				count++;
			}
		}
		if (count==1){					//YYYY
			year=Integer.parseInt(date[0]);
		}
		if (count==2){					//MM/YYYY
			month=Integer.parseInt(date[0]);
			year=Integer.parseInt(date[1]);
		}
		if (count==3){					//MM/DD/YYYY
			month=Integer.parseInt(date[0]);
			day=Integer.parseInt(date[1]);
			year=Integer.parseInt(date[2]);
		}}

	}//end constructor

	public int getDay(){
		return day;
	}//end getDay

	public int getMonth(){
		return month;
	}//end getMonth

	public int getYear(){
		return year;
	}//end getYear
	
	public boolean getUnknown(){
		return unknown;
	}//end getUnknown

	/**
	  * returns -1 if otherDate comes after, or 1
	  * if otherDate comes before, or 0 if equal
	  */
	public int compareTo(Date otherDate){
		if (unknown&&otherDate.getUnknown()){
			return 0;			//two unknown dates=equal
		}
		if (unknown){
			return 1;			//unknown dates sorted to end
		}
		if (otherDate.getUnknown()){
			return -1;			//unknown dates sorted to end
		}
		
		if (year<otherDate.getYear()){
			return -1;
		}
		if (year>otherDate.getYear()){
			return 1;
		}					//years must be equal
		if (month<otherDate.getMonth()){	//so we check months
			return -1;
		}
		if (month>otherDate.getMonth()){
			return 1;
		}					//months must be equal
		if (day<otherDate.getDay()){		//so we check days
			return -1;
		}
		if (day>otherDate.getDay()){
			return 1;
		}					//days must be equal

		return 0;				//so the Dates are equal
	}//end compareTo

	public String toString(){
		return toString(false);	//this is the toString for displaying
	}//end overloaded toString

	public String toString(boolean input){
		if (unknown&&input){
			return "unknown";	//for saving
		}
		if (unknown){
			return "";						//for display
		}
		if (month==0&&day==0){
			return ""+year;
		}
		if (day==0){
			return month+"/"+year;
		}
		return month+"/"+day+"/"+year;
	}//end toString

}//end Date