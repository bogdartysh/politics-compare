package org.ba.politics.compare.text;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import junitparams.JUnitParamsRunner;
import lombok.val;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.Gson;

@RunWith(JUnitParamsRunner.class)
public class TestTextNormalization {
	static Gson gson = new Gson();
	static final String origin = "негайна емісія військових облігацій чи 3-5% річних для відновлення української армії;";
	static final List<String> expected = Arrays.asList(gson.fromJson(
			"[негайний, емісія, військовий, облігація, i, 3, 5, річний, для, відновлення, український, армія]", String[].class));

	@Test
	public void testNormalize() {

		val actual = TextNormalization.getNormalizedText(origin);

		val expSet = new HashSet<String>();
		expSet.addAll(expected);
		val actSet = new HashSet<String>();
		actSet.addAll(actual);

		assertEquals(expSet, actSet);
		assertEquals(expected.size(), actual.size());
	}

}
