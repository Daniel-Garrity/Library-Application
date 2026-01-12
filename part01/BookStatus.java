package part01;
/**
 * Represents the different book statuses
 */
public enum BookStatus {
	AVAILABLE,//represents books which are available
	ON_LOAN,//represents books which are out on loan
	WITHDRAWN;//represents books which are withdrawn
	/**
	 * Displays the book statuses to the console
	 */
	public static void displayBookStatus() {
		for(int i=0;i<BookStatus.values().length;i++) {
			BookStatus status=BookStatus.values()[i];
			System.out.printf("%d. %s \n", i+1,status);
	}
	}
}
