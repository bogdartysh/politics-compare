package org.ba.politics.compare.text;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import eu.hlavki.text.lemmagen.LemmatizerFactory;
import eu.hlavki.text.lemmagen.api.Lemmatizer;

@Slf4j
public class TextNormalization {

	public static final Pattern specialCharactersPattern = Pattern.compile("[^a-z0-9а-яіїiїґє']");
	public static final Pattern spacePattern = Pattern.compile(" ");
	private static Lemmatizer lm = null;

	public static Lemmatizer getLemmatizer() {
		if (lm != null) {
			return lm;
		}
		try {
			lm = LemmatizerFactory.getPrebuilt("mlteast-uk");
		}
		catch (Exception e) {
			log.error("failed to get dict", e);
		}

		return lm;
	}

	public static List<String> getNormalizedText(final String original) {
		val originalWithouSpecial = specialCharactersPattern.matcher(original.toLowerCase()).replaceAll(" ");
		return getLmWords(originalWithouSpecial);
	}

	private static List<String> getLmWords(final String origin) {
		val lmWordList = new LinkedList<String>();
		for (val word : spacePattern.split(origin)) {
			val lmWord = getLemmatizer().lemmatize(word).toString();
			if (StringUtils.isWordAddable(lmWord)) {
				lmWordList.add(StringUtils.getReplacableWord(lmWord));
			}
		}

		return lmWordList;
	}

}
