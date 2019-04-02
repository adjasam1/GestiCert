package gestiCert.exception;

public class TechnicalErrorException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idError;

	public TechnicalErrorException()
	{
		super();
	}

	public TechnicalErrorException(String message)
	{
		super(message);
	}

	public TechnicalErrorException(Throwable cause)
	{
		super(cause);
	}

	public TechnicalErrorException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public TechnicalErrorException(Integer idError)
	{
		super(idError.toString());
		this.idError = idError;
	}

	public Integer getIdError()
	{
		return idError;
	}

	public void setIdError(Integer idError)
	{
		this.idError = idError;
	}

}
