package part01;
/**
 * This class represents a LibraryBook object, extending the Book class and implementing the Lendable interface
 * @author Daniel Garrity
 */
public class LibraryBook extends Book implements Lendable {
	private int id;//a unique identifier for a LibraryBook instance
	private static int nextId=1;//next usable identifier, to be used when assigning id above
	private BookStatus status;//the status of a book (available to borrow, on loan, withdrawn)
	private String image; // file path or name of the cover image for the book
	private int loanCount;//number of times the book has been checked out
	/**
     * Constructor to initialise the LibraryBook object with its details.
     * It also assigns a unique ID to the book and sets the initial status to AVAILABLE.
     * @param title the title of the book
	 * @param author the author of the book
	 * @param isbn the books ISBN number
	 * @param type the type of book, accessed using an enumerator
	 * @param edition the books edition
	 * @param summary a summary of what the books about
	 * @param price price of the book
     * @param coverImage the filename of the book's cover image
     */
	public LibraryBook(String title, String author, String isbn, BookType type, int edition, String summary,
			double price, String coverImage) {
		super(title, author, isbn, type, edition, summary, price);
		this.id=nextId;
		nextId++;
		this.status=BookStatus.AVAILABLE;
		this.image=coverImage;
		this.loanCount=0;
		
	}
	/**
	 * Gets the books ID
	 * @return the books ID
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * Gets the books status
	 * @return the books status
	 */
	public BookStatus getStatus() {
		return this.status;
	}
	/**
	 * Gets the cover image of the book
	 * @return the books cover image
	 */
	public String getCoverImage() {
		return this.image;
	}
	/**
	 * Gets the loancount associated with the book
	 * @return the books loancount
	 */
	public int getLoanCount() {
		return this.loanCount;
	}
	/**
	 * Sets the status of the book, ensuring that the status is not null
	 * @param status
	 */
	public void setStatus(BookStatus status) {
		if(status==null) {//checks if the status is null
			throw new IllegalArgumentException("Invalid Status, must not be null.");//throws exception if status does not meet validation
		}else {
			this.status=status;
		}
	}
	/**
	 * Sets the loan count for the book, ensuring that the count is greater than 0
	 * @param loanCount
	 */
	public void setLoanCount(int loanCount) {
		if(loanCount<0) {//checks if the loancount is less than 0
			throw new IllegalArgumentException("Invalid Loan Count, must be greater than 0.");//throws exception if loancount does not meet validatio
		}else {
			this.loanCount=loanCount;
		}
	}
	//Overrides the toString method
	@Override
	public String toString() {
		String data="ID: "+this.id+" ,";
			   data+=super.toString();
			   data+=" Status: "+this.status+" ,"; 
			   data+=" Loan Count: "+this.loanCount+" ,";
			   data+=" Image: "+this.image+"\n";
			   return data;
	}
    /**
     * Implements the checkout method from Lendable interface
     * Checks out the book if it is available. Updates the status to ON_LOAN.
     * @return true if the book was successfully checked out, false if it is unavailable
     */
	@Override
	public boolean checkout() {
		if(this.status==BookStatus.AVAILABLE) {//checks if status is available
			this.status= BookStatus.ON_LOAN;
			System.out.println("Book Sucessfully checked out.");
			return true;//return true, once status is set to on loan
		}else {
			System.out.println("Book is unavailable.");
		return false;//return false if book cannot be checked out
		}
	}
	 
    /**
     * Implements the checkin method from Lendable interface
     * Checks in the book if it is currently on loan. Updates the status to AVAILABLE.
     * @return true if the book was successfully checked in, false if it was not checked out
     */
	@Override
	public boolean checkin() {
		if(this.status==BookStatus.ON_LOAN) {//checks if status is on loan
			this.status= BookStatus.AVAILABLE;
			System.out.println("Book Sucessfully Returned.");
			return true;//return true, once status is set to on available
		}else {
			System.out.println("Book was not checked out, unable to check book in.");
		return false;//return false if book cannot be checked in
		}
	}
}
