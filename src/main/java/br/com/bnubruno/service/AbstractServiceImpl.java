package br.com.bnubruno.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import lombok.Getter;

import br.com.bnubruno.repository.AbstractCrudRespository;

@Getter
public class AbstractServiceImpl<R extends AbstractCrudRespository<T, ID>, T, ID extends Serializable>
		implements AbstractService<T, ID> {

	public R repository;

	public AbstractServiceImpl(R repository) {
		super();
		this.repository = repository;
	}

	@Override
	public <S extends T> S save(S entity) {
		return getRepository().save( entity );
	}

	@Override
	public <S extends T> List<S> saveAll(List<S> entities) {
		return getRepository().saveAll( entities );
	}

	@Override
	public boolean exists(ID id) {
		return getRepository().existsById( id );
	}

	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public List<T> findAll(List<ID> ids) {
		return getRepository().findAll();
	}

	@Override
	public long count() {
		return getRepository().count();
	}

	@Override
	public void deleteById(ID id) {
		getRepository().deleteById( id );
	}

	@Override
	public void delete(T entity) {
		getRepository().delete( entity );
	}

	@Override
	public void deleteAll(List<? extends T> entities) {
		getRepository().deleteAll( entities );
	}

	@Override
	public void deleteAll() {
		getRepository().deleteAll();
	}

	@Override
	public Optional<T> findById(ID id) {
		return getRepository().findById( id );
	}

}
