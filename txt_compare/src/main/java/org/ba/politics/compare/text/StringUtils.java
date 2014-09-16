package org.ba.politics.compare.text;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class StringUtils {
	private static Gson gson = new Gson();

	private static final Map<String, String> replacableWords = gson.fromJson("{чи:i,та:i,ї:i,або:i,проте:але,хоча:але}", LinkedTreeMap.class);

	public static String getReplacableWord(final String word) {
		if (!replacableWords.containsKey(word)) {
			return word;
		}

		return replacableWords.get(word);
	}

	public static boolean isWordAddable(final String word) {
		return word != null && word.length() > 0;
	}
}
