/**
 *
 */
package br.com.base.controller;

import java.io.Serializable;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.base.service.AbstractService;
import br.com.base.mapper.EntityMapper;

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
