package part02;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import console.Console;
import part01.BookStatus;
import part01.BookType;
import part01.Library;
import part01.LibraryBook;
/**
 *This class represents a menu which allows a user to interact with LibaryBook Objects
 *The constructor initialises a list of books with some predefined sample books
 * @author Daniel Garrity 
 */
public class QUBLibraryUpdated {
	protected static Console con = new Console(true);//creates the console
	private static Library library= new Library();//instance of libary to manage books
	public static void main(String[] args) {
		display();//display method called to show menu
		
	}
	/**
	 * This method sets the font and colour for headings
	 */
	private static void heading() {
		con.setColour(Color.red);//sets the text colour to red
		con.setFont(new Font("Franklin Gothic Heavy",Font.BOLD,23));//sets the font,style and size
	}
	/**
	 * This method sets the font and colour for normal text
	 */
	private static void normalText() {
		con.setColour(Color.black);//sets the text colour
		con.setFont(new Font("Georgia",Font.PLAIN,18));//sets the font,style and size 
	}
	/**
	 * Displays the menu ofthe library system and handles user input to select various methods
	 */
	public static void display() {
		con.setSize(1920,1080);//set the size of the console
		con.setVisible(true);//display the console
		con.setBgColour(Color.white);//sets the background colour to white
		heading();
		con.setTitle("QUB Library Manager");//sets the title of the console
		String menuOptions[] = {//array holding menu options
				"List all Books:",
				"List Books by Status:",
				"Add a Book:",
				"Remove a Book:",
				"Borrow a Book:",
				"Return a Book:",
				"Display Ranked List:",
				"Exit:"
		};
		while(true) {
			//Display menu using menuOptions Array
			ImageIcon img = new ImageIcon("Images\\QUB-logo.png");
			con.println(img);//prints the image
			con.println("****Book Menu****");
			normalText();
			for(int i=0;i<menuOptions.length;i++) {
				con.println((i+1)+". "+menuOptions[i]);
			}
			con.println("Enter your choice from 1-8");
			int userChoice=0;
			boolean validInput=false;
			
			do {
		        try {
		            String text= con.readLn();
		            text=text.trim();
		           
		            userChoice = Integer.parseInt(text);
		            	
		            if (userChoice >= 1 && userChoice <= 8) {
		                validInput = true; // valid input, exit loop
		            } else {
		                con.println("Please enter a valid integer between 1-8");
		           
		            }

		        } catch (Exception ex) {
		            con.println("Please enter a valid integer between 1-8");
		            
		        }
		    }while (!validInput);
			switch(userChoice) {
			case 1:
				listBooks();//list all books in the library
				break;
			case 2:
				listBooksbyStatus();// list books by their current status
				break;
			case 3:
				addBook();//add a new book to the library
				break;
			case 4:
				removeBook();//remove a book from the library
				break;
			case 5:
				borrowBook();//borrow a book from the library
				break;
			case 6:
				returnBook();// return a borrowed book to the library
				break;
			case 7:
				mostPopularBook();//list the most popular books in order of loan count
				break;
			case 8:
				con.println("Exiting Menu...\nHit ENTER to confirm.");//exit the program
		        con.readLn();
		        con.setVisible(false);
				return;
			}
		}
	}
	/**
	 * This method allows the user to see a list of all Books
	 */
	public static void listBooks() {
		  heading();
		    con.setTitle("QUB Library Manager"); // sets the title of the console
		    con.println("All Current Books in the Library are listed below:\n");
		    normalText();

		    //Display the list of books for the user to choose from
		    LibraryBook[] bookArray = library.list(); 
		    con.println("ID\tTitle");
		    con.println("~~\t~~~~~");

		    // Display book IDs and Titles
		    for (int i = 0; i < bookArray.length; i++) {
		        LibraryBook book = bookArray[i];  // access each book in the array using the index
		        con.println(book.getId() + "\t" + book.getTitle());
		    }

		 // Prompt the user to either press Enter to continue or type a book ID
		    con.println("\nHit ENTER to continue or type ID for details.");

		    // Read the user input
		    String text = con.readLn();
		    text = text.trim(); 

		    
		    if (text.length() > 0) {
		        try {
		            //Parse the input to an integer (book ID)
		            int bookId = Integer.parseInt(text);

		            // Search for the book with the given ID
		            LibraryBook book = library.search(bookId); 

		            // If the book exists, show its details
		            if (book != null) {
		                con.clear(); 
		                	
		                String imagePath = "Images\\" + book.getId() + "-cover.png"; 
		                ImageIcon img = new ImageIcon(imagePath); // load the image
		                con.println(img); //print the image to the console
		                
		                
		                // Display the book's details
		                con.println("ID: " + book.getId());
		                con.println("Title: " + book.getTitle());
		                con.println("Author: " + book.getAuthor());
		                con.println("Isbn: " + book.getIsbn());
		                con.println("Type: " + book.getType());
		                con.println("Edition: " + book.getEdition());
		                con.println("Summary: " + book.getSummary());
		                con.println("Status: " + book.getStatus());
		                con.println("Loan Count: " + book.getLoanCount());

		                // Prompt the user to press ENTER to return to the menu
		                con.println("\nPress ENTER to return to the menu.");
		                con.readLn();
		            } else {
		                // If the book with the entered ID is not found
		                con.println("No book found with ID: " + bookId);
		                con.println("\nPress ENTER to return to the menu.");
		                con.readLn(); 
		            }

		        } catch (Exception e) {
		            // If the input was not a valid number, handle the error
		            con.println("Invalid input. Please enter a valid book ID (an integer).");
		            con.println("\nPress ENTER to return to the menu.");
		            con.readLn(); 
		        }
		    } else {
		        // If the user just pressed ENTER without typing anything, continue
		        con.println("No book ID entered. Continuing...");
		        con.println("\nPress ENTER to return to the menu.");
		        con.readLn(); // 
		    }
	}
	/**
	 * This method allows a user to borrow a book from the library by providing the book's ID
	 */
	private static void borrowBook() {
	    heading();  
	    con.println("Borrowing a Book:\n");
	    
	    boolean validInput = false;
	    int bookId = 0;
	    
	    normalText();  
	    
	    do {
	        con.println("Enter the Book ID to borrow:");
	        try {
	            String text = con.readLn();  // read the input from the user
	            text = text.trim();  
	            
	            // Convert the input to an integer
	            bookId = Integer.parseInt(text);
	            
	            // Check if the book exists in the library
	            LibraryBook bk = library.search(bookId); 
	            
	            if (bk != null) {  
	                // Check if the book is available for borrowing
	                if (bk.getStatus() == BookStatus.AVAILABLE) {
	                    // If available, borrow the book
	                    if (library.borrowBook(bookId)) {
	                        con.println("Book with ID " + bookId + " borrowed successfully.\n");
	                        validInput = true;  // exit the loop after successful borrow
	                    }
	                } else if (bk.getStatus() == BookStatus.ON_LOAN) {
	                    // Book is already on loan
	                    con.println("The book with ID " + bookId + " is currently on loan. You cannot borrow it right now.\n");
	                } else if (bk.getStatus() == BookStatus.WITHDRAWN) {
	                    // Book has been withdrawn
	                    con.println("The book with ID " + bookId + " has been withdrawn from the library and cannot be borrowed.\n");
	                }
	            } else {
	                // If the book was not found
	                con.println("The book with ID " + bookId + " was not found in the library.\n");
	            }
	        } catch (Exception ex) {
	            // Handle invalid integer input
	            con.println("Invalid input. Please enter a valid integer for the book ID.");
	        }
	    } while (!validInput);  // Continue asking until valid input is provided
	}

	/**
	 * This method allows a user to return a borrowed book to the library by providing the book's ID
	 */
	private static void returnBook() {
		heading();  
	    con.println("Returning a Book:\n");
		boolean validInput=false;
		normalText();
		do{con.println("Enter the Book ID to return:");
		try {
			String text= con.readLn();
            text=text.trim();
           
            int bookId = Integer.parseInt(text);
            // accept the Book ID as input
		if(library.returnBook(bookId)) {
			con.println("Book with ID "+bookId+" returned sucessfully \n");
			validInput=true;
		}else {
			con.println("Book with ID: "+bookId+" could not be returned \n");
			validInput=true;
		}
		} catch (Exception ex) {
			con.println("Invalid input. Please enter a valid integer for the book ID.");
		}
		}while(!validInput);
	}
	/**
	 * This method prompts the user for details to add a new book to the library
	 */
	private static void addBook() {
		heading();
		con.println("Adding a Book:\n");
		normalText();
		
		con.println("Enter Book title:");
		String title= con.readLn();
        title=title.trim();
        
		con.println("Enter Book author:");
		String author= con.readLn();
        author=author.trim();
		
		con.println("Enter Book isbn:");
		String isbn= con.readLn();
        isbn=isbn.trim();
        
        
		con.println("Select Book type: (Enter 1, 2 or 3)");
		for(int i=0;i<BookType.values().length;i++) {
			BookType type=BookType.values()[i];
		con.println(i + 1 + ". " + type);
		}
		
		int userChoice=0;
		boolean validInput=false;
		while (!validInput) {
	        try {
	            con.println("Enter the number corresponding to the Book type");
	            String text= con.readLn();
	            text=text.trim();
	           
	            userChoice = Integer.parseInt(text);
	            
	            // check if the input is within the valid range (1, 2, or 3)
	            if (userChoice >= 1 && userChoice <= 3) {
	                validInput = true;  // exit the loop if the input is valid
	            } else {
	                con.println("Invalid choice. Please enter a number between 1 and 3.");
	            }
	        } catch (Exception ex) {
	            // catch invalid inputs and prompt the user again
	            con.println("Invalid input. Please enter a valid integer between 1 and 3.");
	        }
	    }
		BookType selectedType =BookType.values()[userChoice-1];
		int edition =-1;
		while(edition<=0) {
		try {
			con.println("Enter Book edition");
			String text= con.readLn();
            text=text.trim();
           
            edition = Integer.parseInt(text); 
			if(edition<1) {
				con.println("Edition must be a positive number");
			}
		} catch (Exception ex) {
			// catch invalid inputs and prompt the user again
			con.println("Invalid Input. Please enter a valid integer for the books edition");
		}
		}
		con.println("Enter Book summary:");
		String summary= con.readLn();
        summary=summary.trim();
		
		double price =-1;
		while(price<=0)
			try {
	            con.println("Enter Book price:");
	            String text= con.readLn();
	            text=text.trim();
	           
	            price = Double.parseDouble(text); 
	            if (price <= 0) {
	                con.println("Price must be a positive number.");
	            }
	        } catch (Exception ex) {
	            con.println("Invalid input. Please enter a valid positive number for the price.");
	        }
		con.println("Enter the Books cover image file name:");
		String coverImage= con.readLn();
        coverImage=coverImage.trim();
		try {
			LibraryBook newBook = new LibraryBook(title, author, isbn, selectedType, edition, summary, price, coverImage);//attempt to add the book to the library
			if(library.add(newBook)) {
				con.println("Book added sucessfully.");
			}else {
				con.println("Failed to add a new Book.");
			}
			
		}catch(Exception ex) {
			con.println("Error: "+ex.getMessage());
		}
	}
	/**
	 * This method removes a book from the library based on its ID
	 */
	private static void removeBook() {
		boolean validInput=false;
		do{
			heading();
			con.println("Removing a Book:\n");
			normalText();
			con.println("Enter the Book ID of the Book you wish to remove:");
		
		try {
		    int bookId = 0; //
		    String text= con.readLn();
            text=text.trim();
            bookId = Integer.parseInt(text);
            LibraryBook book = library.search(bookId);
            if (book == null) {
                con.println("Book with ID " + bookId + " not found.");
                continue;//continue if book isnt found
            }
		    con.println("Are you sure you want to remove this book with ID: "+bookId+" (yes/no).");
		    String userAns=con.readLn();
		    if(userAns.equals("no")) {
		    	con.println("Removal Cancelled, returning to Main Menu.");
		    	validInput=true;
		    }else if (userAns.equals("yes")) {
                if (book.getStatus() == BookStatus.ON_LOAN) {
                    con.println("Unable to remove book as it is currently on loan.");
                    break;  // exit the loop if the book is on loan
                }

                if (book.getStatus() == BookStatus.WITHDRAWN) {
                    con.println("The book with ID " + bookId + " has already been removed.");
                    break;  // exit the loop if the book is withdrawn
                }

                //Remove the book if the status is okay
                if (library.remove(bookId)) {
                    con.println("Book with ID " + bookId + " has been removed.");
                    validInput = true;
                } else {
                    con.println("Failed to remove the book. Please try again.");
                }
            } else {
                con.println("Invalid response. Please enter 'yes' or 'no'.");
            }
		} catch (Exception ex) {
		    con.println("Invalid input. Please enter a valid Book ID.");
		
		}
	}while(!validInput);
	}
	/**
	 * This method displays a list of books based on their current status (e.g available, on loan)
	 */
	private static void listBooksbyStatus() {
		heading();
		con.setTitle("QUB Library Manager");//sets the title of the console
		con.println("List Books by Status: \n");
		normalText();
		con.println("Select Book Status: (Enter 1, 2 or 3)");
			for(int i=0;i<BookStatus.values().length;i++) {
				BookStatus status=BookStatus.values()[i];
				 con.println(i + 1 + ". " + status);
			}
			
		int userChoice=0;
		boolean validInput=false;
		while (!validInput) {
	        try {
	            con.println("Enter the number corresponding to the Book Status");
	            String text= con.readLn();
	            text=text.trim();
	           
	            userChoice = Integer.parseInt(text);
	            // check if the input is within the valid range (1, 2, or 3)
	            if (userChoice >= 1 && userChoice <= 3) {
	                validInput = true;  // exit the loop if the input is valid
	            } else {
	                con.println("Invalid choice. Please enter a number between 1 and 3.");
	                
	            }
	        } catch (Exception ex) {
	            // Catch invalid inputs (non-integer) and prompt the user again
	            con.println("Invalid input. Please enter a valid integer between 1 and 3.");
	        }
	    }

	    //Retrieve the selected status and list books
	    BookStatus selectedStatus = BookStatus.values()[userChoice - 1];  // get the selected status
	    LibraryBook[] booksByStatus = library.list(selectedStatus);  

	    // Print the filtered books
	    if (booksByStatus.length > 0) {
	    	heading();
	        con.println("Books with status " + selectedStatus + ":");
	        normalText();
	        for (int i = 0; i < booksByStatus.length; i++) {
	            LibraryBook book = booksByStatus[i];
	            con.println(book);  
	        }
	    } else {
	        con.println("No books found with status " + selectedStatus + ".");
	    }
	}
	/**
	 * This method displays a ranked list of the most popular books, based on the number of times they have been loaned
	 */
	private static void mostPopularBook() {
		con.println("Books ranked in popularity order");
		normalText();
		LibraryBook[]popularBooks = library.mostPopular();

	    // display the ranked books
	    if (popularBooks.length > 0) {
	    	for (int i = 0; i < popularBooks.length; i++) {
	    	    LibraryBook book = popularBooks[i];
	    	    con.println("ID: " + book.getId() + "  Title: " + book.getTitle() + "  Author: " + book.getAuthor() + "  Borrowed: " + book.getLoanCount() + "  times\n");
	    	}
	    } else {
	        con.println("No books available.");
	    }
	}
}


