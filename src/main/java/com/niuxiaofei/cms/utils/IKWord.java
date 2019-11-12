/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: IKWord.java 
 * @Prject: chenhongjun_cms
 * @Package: com.chenhongjun.chenhongjun_cms.utils 
 * @Description: TODO
 * @author: chj   
 * @date: 2019年6月29日 上午10:33:14 
 * @version: V1.0   
 */
package com.niuxiaofei.cms.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;


/**
 * @ClassName: IKWord
 * @Description: TODO
 * @author: chj
 * @date: 2019年6月29日 上午10:33:14
 */
public class IKWord {
	
	/**
	 * 词频统计
	 *
	 * @param frequencies 词频；key：词语；value:出现次数
	 * @param content     内容
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Integer> count(Map<String, Integer> frequencies, String content) throws IOException {
		if (frequencies == null) {
			frequencies = new HashMap<>();
		}

		IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(content),false);
		Lexeme lexeme;
		while ((lexeme = ikSegmenter.next()) != null) {
			final String text = lexeme.getLexemeText();

			if (text.length() >= 1) {
				// 递增
				if (frequencies.containsKey(text)) {
					frequencies.put(text, frequencies.get(text) + 1);
				} else {// 首次出现
					frequencies.put(text, 1);
				}
			}
		}
		return frequencies;

	}

	/** 
	 * @Title: order 
	 * @Description: 对分词排序
	 * @param data
	 * @return
	 * @return: List<Map.Entry<String,Integer>>
	 */
	public static List<Map.Entry<String, Integer>> order(Map<String, Integer> data) {
		List<Map.Entry<String, Integer>> result = new ArrayList<>(data.entrySet());
		Collections.sort(result, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		return result;
	}
	
	public static void main(String[] args) throws IOException {
        String content = "三星明细GalaxyNote4N91004红军手机G手幻影白明细双卡双待 公开版+施华洛世奇水红军手机晶后壳（瑰金落日）套装手机";
        List<Map.Entry<String, Integer>> result = IKWord.order
                (IKWord.count(new HashMap<String, Integer>(), content));
        System.out.println(result);
    }
}
