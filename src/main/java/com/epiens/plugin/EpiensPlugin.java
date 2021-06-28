package com.epiens.plugin;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import com.epiens.chosung.ChosungTokenFilterFactory;
import com.epiens.eng2kor.Eng2KorConvertFilterFactory;
import com.epiens.jamo.JamoTokenFilterFactory;
import com.epiens.kor2eng.Kor2EngConvertFilterFactory;
import com.epiens.spell.SpellFilterFactory;

public class EpiensPlugin extends Plugin implements AnalysisPlugin {
	@Override
	public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
		Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();

		// (1) 한글 자모 분석 필터
        extra.put("jamo_filter", JamoTokenFilterFactory::new);
        
		// (2)  한글 초성 분석 필터
		extra.put("chosung_filter", ChosungTokenFilterFactory::new);
		
		 // (3) 영한 오타 변환 필터
        extra.put("eng2kor_filter", Eng2KorConvertFilterFactory::new);
        
        // (4) 한영 오타 변환 필터
        extra.put("kor2eng_filter", Kor2EngConvertFilterFactory::new);

        // (5) 한글 스펠링 체크 필터
        extra.put("spell_filter", SpellFilterFactory::new);
		
		return extra;
	}
}
