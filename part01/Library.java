package part01;

import java.util.ArrayList;


/**
 *This class represents a Library which holds a collection of LibraryBook objects
 *The constructor initialises a list of books with some predefined sample books
 * @author Daniel Garrity 
 */
public class Library{
	private ArrayList<LibraryBook> books = new ArrayList<>();//arraylist which stores books available in the library
	/**
	 *Constructor that initialises the library with some predefined books and attributes
	 */
	public Library() {
		
		//Adding predefined books to the library
		this.books = new ArrayList<>();
		books.add(new LibraryBook("Harry Potter","JK Rowling","0123456789",BookType.FICTION,1,
				"A story about an aspiring wizard.",14.99,"1-cover.png"));

        books.add(new LibraryBook("The Catcher in the Rye", "J.D. Salinger", "9780316769", BookType.FICTION, 1, 
            "A story of a young man’s journey of self-discovery and disillusionment with the adult world.", 10.99, "2-cover.png"));

        books.add(new LibraryBook("To Kill a Mockingbird", "Harper Lee", "0060935467", BookType.FICTION, 1, 
            "A novel about racial injustice, moral growth, and the complexities of human nature.", 12.99, "3-cover.png"));

        books.add(new LibraryBook("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "9780062316", BookType.NON_FICTION, 2, 
            "An exploration of the history of human beings from the Stone Age to the present day.", 18.99, "4-cover.png"));

        books.add(new LibraryBook("The Silent Patient", "Alex Michaelides", "9781250301", BookType.FICTION, 1, 
            "A psychological thriller about a woman who shoots her husband and then refuses to speak a word.", 14.99, "5-cover.png"));
    
	}
/**
 * Attempts to borrow a book from the library by changing its status to "ON_LOAN".
 * This method checks the book's availability, ensuring that it is not already on loan or withdrawn
 * @param id a unique identifier for a LibraryBook instance
 * @return true if the book is successfully borrowed, false otherwise
 */
	public boolean borrowBook(int id) {
	    LibraryBook bk = search(id);  // search for the book by ID
	    if (bk != null) {
	        if (bk.getStatus() == BookStatus.AVAILABLE) {
	            //If the  book is available, borrow it
	            bk.setStatus(BookStatus.ON_LOAN); 
	            bk.setLoanCount(bk.getLoanCount() + 1);  // increment the loan count
	            return true;  // successfully borrowed
	        } else if (bk.getStatus() == BookStatus.ON_LOAN) {
	            // Book is already on loan cant borrow book
	            return false;  
	        } else if (bk.getStatus() == BookStatus.WITHDRAWN) {
	            // Book has been withdrawn cant borrow book
	            return false;  
	        }
	    }
	    return false;  // book not found 
	}



	/**
	 * Attempts to return a book to the library by changing its status to "AVAILABLE".
     * This method checks the book's availability, ensuring that it is on loan  
	 * @param id 
	 * @return true if the book is successfully returned, false otherwise
	 */
	public boolean returnBook(int id) {
		LibraryBook bk =search(id);
		if(bk!=null &&bk.getStatus()==BookStatus.ON_LOAN) {//checks if the book is on loan
			bk.setStatus(BookStatus.AVAILABLE);//changes status to available
			return true; 
		}
		return false;
	}
	/**
	 * This method lists all books currently in the library by returning an array
	 * @return an array of LibraryBook objects representing all the books in the library
	 */
	public LibraryBook[] list() {
		LibraryBook[] bookArray = new LibraryBook[books.size()];//create a new array to hold all the books in the library
		//loop through array and print out each books details
		for(int i=0;i<books.size();i++) {
			LibraryBook book = books.get(i);
            bookArray[i] = book;
	}
		return bookArray;//return the array of books
	}
	/**
	 * This method lists the books currently in the library by their BookStatus e.g ON_L0AN
	 * @return an array of LibraryBook objects representing all the books in the library
	 */
	public LibraryBook[] list(BookStatus stat) {
	LibraryBook[] matchingBooks = new LibraryBook[books.size()];
	int count=0;
	for(int i=0; i<books.size(); i++) {
		if (books.get(i).getStatus() == stat) {
            // add the matching book to the array
            matchingBooks[count] = books.get(i);
            count++;  // increment the count for matched books
        }
    }

    // Create a new array to hold only the books that match the status
    LibraryBook[] result = new LibraryBook[count];

    // Copy the matching books into the result array
    for (int i = 0; i < count; i++) {
        result[i] = matchingBooks[i];
    }

    // return the result array
    return result;
	}
	/**
	 * This method returns an array of books sorted by their popularity
	 * The books are sorted in descending order (most borrowed books first)
	 * @return an array of LibraryBook objects sorted by their loan count in descending order
	 */
	public LibraryBook[] mostPopular() {
LibraryBook[] bookArray = books.toArray(new LibraryBook[0]);
//Sorting the books array using a bubble sort based on loan count
        for (int i = 0; i < bookArray.length - 1; i++) {
            for (int j = 0; j < bookArray.length - i - 1; j++) {
                if (bookArray[j].getLoanCount() < bookArray[j + 1].getLoanCount()) {
                    LibraryBook temp = bookArray[j];
                    bookArray[j] = bookArray[j + 1];
                    bookArray[j + 1] = temp;
                }
            }
        }
        return bookArray;//return the sorted array of books
	}
	/**
	 * This method searches for a book in the library using the a books ID
	 * @param id 
	 * @return the LibraryBook that matches the ID given or null if it wasn't
	 */
	public LibraryBook search(int id) {
		LibraryBook book=null;
		//loop to find books with matching ID
		for (int i = 0; i < books.size(); i++) {
	        LibraryBook bk = books.get(i);
	        if (bk.getId() == id) { // if the book matches the ID assign it to the new book variable
	            book = bk;
	            break;
	        }
	    }
	    return book; // returns null if not found
	}
	/**
	 * This method adds a new LibraryBook to the library
	 * @param bk 
	 * @return true if the book was successfully added, false if the book is null
	 */
	public boolean add(LibraryBook bk) {
		if(bk==null) {//check if the book is null
		return false;
		}else {
			books.add(bk);
			return true;
		}
		
	}
	/**
	 * This method is used to remove a book from the library using the books ID
	 * @param id 
	 * @return true if the book was successfully removed, false if the book is on loan, withdrawn, or not found
	 */
	public boolean remove(int id) {
		LibraryBook bk = search(id);//search for the book using its ID
		if(bk!=null&&bk.getStatus()==BookStatus.AVAILABLE) {
			bk.setStatus(BookStatus.WITHDRAWN);//if the book is available, mark it as withdrawn
			return true;	
		}
		System.out.println("Book with ID "+id+" not found.");
		return false;//if book is not found,return false
	}
}
