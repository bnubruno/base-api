/**
 *
 */
package br.com.base.service;

import java.util.List;
import java.util.Optional;

/**
 * @author bnubruno
 *
 */
public interface AbstractService<T, ID> {

	<S extends T> S save(S entity);

	<S extends T> List<S> saveAll(List<S> entities);

	boolean exists(ID id);

	List<T> findAll();

	List<T> findAll(List<ID> ids);

	long count();

	void deleteById(ID id);

	void delete(T entity);

	void deleteAll(List<? extends T> entities);

	void deleteAll();

	Optional<T> findById(ID id);

}
