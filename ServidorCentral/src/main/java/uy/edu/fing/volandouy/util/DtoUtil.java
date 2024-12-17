package uy.edu.fing.volandouy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DtoUtil {
	public static <T, R> List<R> convertirLista(List<T> list, Function<T, R> converter) {
		List<R> result = new ArrayList<>();
		for (T item : list) {
			result.add(converter.apply(item));
		}
		return result;
	}
}
