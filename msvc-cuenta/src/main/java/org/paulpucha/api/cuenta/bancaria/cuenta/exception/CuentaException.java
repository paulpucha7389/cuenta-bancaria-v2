package org.paulpucha.api.cuenta.bancaria.cuenta.exception;

/**
 * <b> Clase CuentaException implementada para el manejo de excepciones del msvc-cuenta. </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *     </p>
 */
public class CuentaException extends Exception {

    private static final long serialVersionUID = 3263046821289003394L;

    /**
     * Constructor.
     */
    public CuentaException() {
        super();
    }

    /**
     * Constructor with args.
     *
     * @param message The message
     * @param cause The cause
     */
    public CuentaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Contructor with args, Throwable.
     *
     * @param cause
     */
    public CuentaException(Throwable cause) {
        super(cause);
    }

    /**
     * Contructor with args, Throwable.
     *
     * @param message
     */
    public CuentaException(String message) {
        super(message);
    }
}
