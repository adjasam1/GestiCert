package gestiCert.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"gestiCert"})
public class GlobalHandlerControllerException extends ResponseEntityExceptionHandler
{
	@InitBinder
	public void dataBinding(WebDataBinder binder)
	{
		// initialiser ici toute autre donnee
	}

	// la variable "technicalError" pourra etre exploite n'importe ou dans l'application
	@ModelAttribute
	public void globalAttributes(Model model)
	{
		model.addAttribute("technicalError", "Une erreur technique est survenue");
	}
	
	@ExceptionHandler(TechnicalErrorException.class)
	public ModelAndView technicalErrorException(Exception exception)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception.getMessage());
		mav.setViewName("error");
		return mav;
	}
	
	// toutes les autres erreurs non generees sont interceptees ici
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BusinessResourceExceptionResponse> unknowError(HttpServletRequest request, Exception exception)
	{
		BusinessResourceExceptionResponse response = new BusinessResourceExceptionResponse();
		response.setErrorCode("Technical Error");
		response.setErrorMessage(exception.getMessage());
		response.setRequestURL(request.getRequestURL().toString());
		return new ResponseEntity<BusinessResourceExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessResourceException.class)
	public ResponseEntity<BusinessResourceExceptionResponse> resourceNotFound(HttpServletRequest request, BusinessResourceException exception)
	{
		BusinessResourceExceptionResponse response = new BusinessResourceExceptionResponse();
		response.setStatus(exception.getStatus());
		response.setErrorCode(exception.getErrorCode());
		response.setErrorMessage(exception.getMessage());
		response.setRequestURL(request.getRequestURL().toString());
		return new ResponseEntity<BusinessResourceExceptionResponse>(response, exception.getStatus());
	}
}
