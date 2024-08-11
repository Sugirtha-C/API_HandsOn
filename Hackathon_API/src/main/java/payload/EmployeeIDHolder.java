package payload;

/**
 * The EmployeeIDHolder class is a utility class designed to store and manage
 * the employee ID used across different test cases.
 * 
 * This class uses a static variable to hold the employee ID, allowing it to be
 * accessed and updated throughout the test execution lifecycle.
 */

public class EmployeeIDHolder {
	
	
	private static Integer nonNullEmployeeID;
    private static Integer nullEmployeeID;

    public static void setNonNullEmployeeID(Integer id) {
        nonNullEmployeeID = id;
    }

    public static Integer getNonNullEmployeeID() {
        return nonNullEmployeeID;
    }

    public static void setNullEmployeeID(Integer id) {
        nullEmployeeID = id;
    }

    public static Integer getNullEmployeeID() {
        return nullEmployeeID;
    }
}
