package com.assest.Exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

/**
 * Custom Exception class for validation .
 */

public class ValidationException extends Exception
{
    /**
     * serial UID for serialization.
     */
    private static final long serialVersionUID = 73166562195545142L;
    
    private BindingResult bindingResult = null;
    private Errors errors = null;
    public ValidationException()
    {
         super();
    }
    public ValidationException( String msg)
    {
         super(msg);
    }
    
    /**
     * Parameterized constructor.
     * @param msg - input message.
     * @param errorList - input error list.
     */
    public ValidationException( BindingResult bindingResult)
    {
         this.bindingResult = bindingResult;
    }
    public ValidationException( Errors errors)
    {
         this.errors = errors;
    }


    public BindingResult getBindingResult()
    {
        return bindingResult;
    }
    public Errors getErrors()
    {
        return errors;
    }
    
}
