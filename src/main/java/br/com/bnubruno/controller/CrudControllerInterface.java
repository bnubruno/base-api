/**
 *
 */
package br.com.bnubruno.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bnubruno.service.AbstractService;

/**
 * @author bnubruno
 *
 */
public interface CrudControllerInterface<T, DTO, S extends AbstractService<T, ID>, ID> {

	public ResponseEntity<List<DTO>> getAll(HttpServletRequest request);

	public ResponseEntity<DTO> getById(ID id);

	public ResponseEntity<DTO> create(T product, UriComponentsBuilder ucBuilder);

	public ResponseEntity<DTO> delete(ID id);

}
