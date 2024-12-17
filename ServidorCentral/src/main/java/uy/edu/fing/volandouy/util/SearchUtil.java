package uy.edu.fing.volandouy.util;

import java.util.List;
import java.util.function.Predicate;

public class SearchUtil {
	public static <T> T buscarPorCondicion(List<T> list, Predicate<T> predicate) {
		for (T item : list) {
			if (predicate.test(item)) {
				return item;
			}
		}
		return null;
	}
}
