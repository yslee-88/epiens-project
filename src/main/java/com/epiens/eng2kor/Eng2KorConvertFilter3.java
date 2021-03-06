package com.epiens.eng2kor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import com.epiens.common.converter.EngToKorConverter;

/**
 * 영한 변환 필터
 *
 * @author yslee
 *
 */
public final class Eng2KorConvertFilter3 extends TokenFilter {

	private EngToKorConverter converter;
	private CharTermAttribute termAtt;

	private PositionIncrementAttribute positionIncrementAttribute;
	private Queue<char[]> simpleQueue;

	public Eng2KorConvertFilter3(TokenStream stream) {
		super(stream);
		this.converter = new EngToKorConverter();
		this.termAtt = addAttribute(CharTermAttribute.class);

		this.positionIncrementAttribute = addAttribute(PositionIncrementAttribute.class);

		this.simpleQueue = new LinkedList<char[]>();
	}

	@Override
	public boolean incrementToken() throws IOException {

		if (!simpleQueue.isEmpty()) {
			char[] buffer = simpleQueue.poll();
			termAtt.setEmpty();
			termAtt.copyBuffer(buffer, 0, buffer.length);

			positionIncrementAttribute.setPositionIncrement(0);

			return true;
		}

		if (input.incrementToken()) {
			String result = converter.convert(termAtt.toString());
			simpleQueue.add(result.toCharArray());

			return true;
		}

		return false;
	}

}