package com.nxf.cms.jsoupTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import com.niuxiaofei.cms.utils.IKWord;

public class IKTest {
	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> frequencies = new HashMap<String,Integer>();
		IKWord.count(frequencies, "八串亩维教育中华人民共和国串亩");
		//获取Entry的set集合
				/*Set<Entry<String, Integer>> set = frequencies.entrySet();
				//获取Entry的set集合的迭代器
				Iterator<Entry<String, Integer>> iterator = set.iterator();
				//遍历迭代器
				while(iterator.hasNext()) {
					//获取Entry实体对象
					Entry<String, Integer> entry = iterator.next();
					System.out.println(entry.getValue()+"@@@"+entry.getKey());
					
				}*/
				
		
		List<Entry<String, Integer>> list = IKWord.order(IKWord.count(frequencies,"八串亩维教育中华人民共和国串亩"));
		list.forEach(System.out::println);
	}
	
}

