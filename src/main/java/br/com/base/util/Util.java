package br.com.base.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Util {

	public static <T> List<T> toList(Iterable<T> it) {
		return StreamSupport.stream( it.spliterator(), false ).collect( Collectors.toList() );
	}

}
