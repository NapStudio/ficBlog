package asi.ficblog.model.util.exceptions;



public class InputValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputValidationException(String error) {
        super(error);
    }
}
