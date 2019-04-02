package gestiCert.exception;

import org.springframework.http.HttpStatus;

public class BusinessResourceException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idResource;
	private String errorCode;
	private HttpStatus status;
	
	public BusinessResourceException(String message)
	{
		super(message);
	}

	public BusinessResourceException(Integer idResource, String message)
	{
		super(message);
		this.idResource = idResource;
	}

	public BusinessResourceException(Integer idResource, String errorCode, String message)
	{
		super(message);
		this.idResource = idResource;
		this.errorCode = errorCode;
	}

	public BusinessResourceException(String errorCode, String message)
	{
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessResourceException(String errorCode, HttpStatus status, String message)
	{
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}

	public Integer getIdResource()
	{
		return idResource;
	}

	public void setIdResource(Integer idResource)
	{
		this.idResource = idResource;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public HttpStatus getStatus()
	{
		return status;
	}

	public void setStatus(HttpStatus status)
	{
		this.status = status;
	}

}
