/**
 *
 */
package br.com.bnubruno.mapper;

import java.util.List;

/**
 * @author bnubruno
 *
 */
public interface EntityMapper<D, E> {

	public D toDto(E entity);

	public E toEntity(D dto);

	public List<E> toEntity(List<D> dtoList);

	public List<D> toDto(List<E> entityList);
}
