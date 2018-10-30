/**
 *
 */
package br.com.bnubruno.controller;

import java.io.Serializable;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bnubruno.mapper.EntityMapper;
import br.com.bnubruno.service.AbstractService;

/**
 * @author brnubruno
 */
@Getter
public class AbstractController<S extends AbstractService<T, ID>, M extends EntityMapper<DTO, T>, T, DTO extends Serializable, ID extends Serializable> {

	@Autowired
	private S service;

	@Autowired
	private M mapper;

	public AbstractController(S service, M mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}

}
