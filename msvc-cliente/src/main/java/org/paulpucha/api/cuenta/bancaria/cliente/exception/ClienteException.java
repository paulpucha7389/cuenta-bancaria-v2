package org.paulpucha.api.cuenta.bancaria.cliente.exception;

/**
 * <b> Clase ClienteException implementada para el manejo de excepciones del msvc-cliente. </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *     </p>
 */
public class ClienteException extends Exception {

    private static final long serialVersionUID = 3263046821289003394L;

    /**
     * Constructor.
     */
    public ClienteException() {
        super();
    }

    /**
     * Constructor with args.
     *
     * @param message The message
     * @param cause The cause
     */
    public ClienteException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Contructor with args, Throwable.
     *
     * @param cause
     */
    public ClienteException(Throwable cause) {
        super(cause);
    }

    /**
     * Contructor with args, Throwable.
     *
     * @param message
     */
    public ClienteException(String message) {
        super(message);
    }
}
