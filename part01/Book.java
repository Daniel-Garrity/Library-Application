package part01;
/**
 * This class represents a Book object, with attributes and methods that define and manage a book's details
 * @author Daniel Garrity
 */
public class Book {
	protected String title;//the books title represented by a string
	protected String author;//the books author represented by a string
	protected String isbn;//the books isbn represented by a string
	protected BookType type;//the books type represented by an enumerator
	protected int edition;//the books edition represented by an integer
	protected String summary;//the books summary represented by a string
	protected double price;//the books price represented by a double
	/**
	 * Constructor which uses setters for validation
	 * @param title the title of the book
	 * @param author the author of the book
	 * @param isbn the books ISBN number
	 * @param type the type of book, accessed using an enumerator
	 * @param edition the books edition
	 * @param summary a summary of what the books about
	 * @param price price of the book
	 */
	public Book(String title,String author,String isbn,BookType type,int edition,String summary,double price) {
		//Initialises a new book object by using setters to ensure that validation is present
		setTitle(title);
		setAuthor(author);
		setIsbn(isbn);
		setType(type);
		setEdition(edition);
		setSummary(summary);
		setPrice(price);
	}
	/**
	 * Gets the title of the book
	 * @return the books title
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * Gets the author of the book
	 * @return the books author
	 */
	public String getAuthor() {
		return this.author;
	}
	/**
	 * Gets the ISBN of the book
	 * @return the books ISBN
	 */
	public String getIsbn() {
		return this.isbn;
	}
	/**
	 * Gets the type of the book
	 * @return the books type
	 */
	public BookType getType() {
		return this.type;
	}
	/**
	 * Gets the edition of the book
	 * @return the books edition
	 */
	public int getEdition() {
		return this.edition;
	}
	/**
	 * Gets the summary of the book
	 * @return the books summary
	 */
	public String getSummary() {
		return this.summary;
	}
	/**
	 * Gets the price of the book
	 * @return the books price
	 */
	public double getPrice() {
		return this.price;
	}
	/**
	 *Sets the book's title, ensuring it meets the validation criteria
	 *The title must be between 5 and 40 characters in length
	 * @param title
	 */
	public void setTitle(String title) {
		if(title==null||title.length()<5||title.length()>40) {//checks if the title is null or its length is not between 5 and 40 characters
			throw new IllegalArgumentException("Title must be between 5 and 40 characters long.");//throws exception if title does not meet validation
		}else {
			this.title=title;
		}
		
	}
	/**
	 *Sets the book's author, ensuring it meets the validation criteria
	 *The author must be between 5 and 40 characters in length 
	 * @param author
	 */
	public void setAuthor(String author) {
		if(author==null||author.length()<5||author.length()>40) {//checks if the title is null or its length is not between 5 and 40 characters
			throw new IllegalArgumentException("Author must be between 5 and 40 characters long.");//throws exception if author does not meet validation
		}else {
			this.author=author;
		}
		
	}
	/**
	 *Sets the book's ISBN, ensuring it meets the validation criteria
	 *The ISBN must be 10 digits long
	 * @param isbn
	 */
	public void setIsbn(String isbn) {
		if(isbn==null||isbn.length()!=10) {//checks if the isbn is null or is not exactly 10 characters long
			throw new IllegalArgumentException("ISBN must be exactly 10 digits long.");//throws exception if isbn does not meet validation
		}
		//for loop iterates throw isbn checking if each character is a digit
			for(int i=0; i<isbn.length();i++) {
				char curchar=isbn.charAt(i);
				boolean digit=Character.isDigit(curchar);
			if(!digit) {
				throw new IllegalArgumentException("ISBN must only contain digits.");//throws exception if a character is not a digit
			}
			
	else {
			this.isbn=isbn;
		}
			}
	
	}
	/**
	 *Sets the book's type, ensuring it meets the validation criteria
	 *The BookType must not be null 
	 * @param author
	 */
	public void setType(BookType type) {
		if(type==null) {//checks if the type is null
			throw new IllegalArgumentException("Book Type must not be null.");//throws exception if type does not meet validation
		}else {
			this.type=type;
		}
		
	}
	/**
	 *Sets the book's edition, ensuring it meets the validation criteria
	 *The edition must be greater than 0 
	 * @param edition
	 */
	public void setEdition(int edition) {
		if(edition<0) {//checks if the edition is less than 0
			throw new IllegalArgumentException("Edition must be greater than 0.");//throws exception if edition does not meet validation
		}else {
			this.edition=edition;
		}
		
	}
	/**
	 *Sets the book's summary, ensuring it meets the validation criteria
	 *The summary length must be between 20 and 150 characters 
	 * @param summary
	 */
	public void setSummary(String summary) {
		if(summary==null||summary.length()<20||summary.length()>150) {//checks if the summary is null or its length is not between 20 and 150 characters
			throw new IllegalArgumentException("The book Summary must be between 20 and 150 characters long.");//throws exception if summary does not meet validation
		}else {
			this.summary=summary;
		}
		
	}
	/**
	 *Sets the book's price, ensuring it meets the validation criteria
	 *The price must be greater than 0 
	 * @param price
	 */
	public void setPrice(double price) {
		if(price<0) {//checks if the price is less than 0
			throw new IllegalArgumentException("The price of the book must be above 0.");//throws exception if price does not meet validation
		}else {
			this.price=price;
		}
		
	}
	/**
	 * The string includes the title, author, ISBN, type, edition, summary, and price
	 * The price is formatted to two decimal places and the sterling character is added
	 * @return a string representation of the book
	 */
	public String toString() {
		String data=" Title: "+this.title+" ,";
		data+=" Author: "+this.author+" ,";
		data+=" ISBN: "+this.isbn+" ,";
		data+=" Type: "+this.type+" ,";
		data+=" Edition: "+this.edition+" ,";
		data+=" Summary: "+this.summary+" ,";
		data+=String.format(" Price: £%.2f ",this.price);
		return data;
	}
}
