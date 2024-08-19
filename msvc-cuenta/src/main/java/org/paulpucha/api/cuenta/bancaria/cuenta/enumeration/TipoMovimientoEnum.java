/**
 * 
 */
package org.paulpucha.api.cuenta.bancaria.cuenta.enumeration;

/**
 * 
 * <b> Enumeracion del tipo de movimiento. </b>
 * 
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *          </p>
 */
public enum TipoMovimientoEnum {

	CREDITO("CRE", "credito"), DEBITO("DEB", "debito");

	private String codigo;
	private String descripcion;

	private TipoMovimientoEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
