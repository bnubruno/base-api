/**
 *
 */
package br.com.bnubruno.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bnubruno.mapper.EntityMapper;
import br.com.bnubruno.service.AbstractService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author bnubruno
 */
@Getter
@Slf4j
public class CrudController<S extends AbstractService<T, ID>, M extends EntityMapper<DTO, T>, T, DTO extends Serializable, ID extends Serializable>
		extends AbstractController<S, M, T, DTO, ID>
		implements CrudControllerInterface<T, DTO, AbstractService<T, ID>, ID> {

	@Autowired
	public CrudController(S service, M mapper) {
		super( service, mapper );
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")

	})
	@ApiOperation(value = "Lista todas as entidades")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista carregada com sucesso"),
			@ApiResponse(code = 204, message = "Lista vazia"),
	})
	@GetMapping
	public ResponseEntity<List<DTO>> getAll(HttpServletRequest request) {
		List<T> list = getService().findAll();
		if (list.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT );
		}

		List<DTO> dtos = list.stream().map( getMapper()::toDto ).collect( Collectors.toList() );
		return new ResponseEntity<>( dtos, HttpStatus.OK );
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")

	})
	@ApiOperation(value = "Carrega a entidade pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entidade carregada com sucesso"),
			@ApiResponse(code = 204, message = "Entidade não existe"),
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DTO> getById(@PathVariable("id") ID id) {
		log.info( "Fetching Entity with id " + id );

		Optional<T> entity = getService().findById( id );
		if (entity.isPresent()) {
			log.info( "Entity with id " + id + " not found" );
			return new ResponseEntity<>( HttpStatus.NOT_FOUND );
		}

		return new ResponseEntity<>( getMapper().toDto( entity.get() ), HttpStatus.OK );
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")

	})
	@ApiOperation(value = "Envia uma entidade")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entidade recebida com sucesso"),
	})
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DTO> create(@RequestBody T product, UriComponentsBuilder ucBuilder) {
		log.info( "Creating Entity " + product.toString() );

		T saved = getService().save( product );
		return new ResponseEntity<>( getMapper().toDto( saved ), HttpStatus.CREATED );
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")

	})
	@ApiOperation(value = "Deleta uma entidade pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entidade deletada com suceddo"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<DTO> delete(@PathVariable("id") ID id) {
		log.info( "Fetching & Deleting Entity with id " + id );

		Optional<T> entity = getService().findById( id );
		if (entity.isPresent()) {
			System.out.println( "Unable to delete. Entity with id " + id + " not founs" );
			return new ResponseEntity<>( HttpStatus.NOT_FOUND );
		}

		getService().deleteById( id );
		return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	}

}
