package com.epiens.common.parser;

import com.epiens.common.util.JamoUtil;

/**
 * 한글 자모 Parser
 *
 * @author yslee
 *
 */
public class KoreanJamoParser extends AbstractKoreanParser {

    @Override
    protected void processForKoreanChar(StringBuilder sb, char chosung, char jungsung, char jongsung) {
        sb.append(chosung).append(jungsung);

        if(jongsung != JamoUtil.UNICODE_JONG_SUNG_EMPTY) {
            sb.append(jongsung);
        }
    }

    @Override
    protected void processForOther(StringBuilder sb, char eachToken) {
        sb.append(eachToken);
    }

}


