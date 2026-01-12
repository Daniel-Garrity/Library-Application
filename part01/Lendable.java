package part01;

public interface Lendable {
	public boolean checkout();// to request that a book be marked as 'borrowed'
	public boolean checkin();// to request that a book be marked as 'returned'
	
}
