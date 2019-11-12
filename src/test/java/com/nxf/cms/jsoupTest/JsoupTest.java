package com.nxf.cms.jsoupTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class JsoupTest {
	@Test
	public void catch_article() throws IOException {
		Document document = Jsoup.connect("https://news.163.com/").get();
		Elements elements = document.select("a");
		for (Element element : elements) {
			String href = element.attr("href");
			String title = element.text();
			if(href!=null && href.contains("news") && href.contains(".html")) {
				Document doc;
				try {
					doc = Jsoup.connect(href).get();
					if(doc!=null) {
						Element elementById = doc.getElementById("endText");
						System.out.println("title===="+title);
						System.out.println("text====="+elementById.text());
						writeFile(title,elementById.text());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void writeFile(String title, String text) throws IOException {
		title.replace(",","").replace("|", "").replace("?", "").replace("\"", "");
		File file = new File("H:\\小说\\xianNi"+title+".txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf8"));
		bw.write(text);
		bw.flush();
		bw.close();
	}
	
	
	@Test
	public void snow(){
		Document document;
		try {
			document = Jsoup.connect("http://www.uu234.cc/r625/").get();
			Elements elements = document.select("a");
			for (Element element : elements) {
				String title = element.text();
				String link = element.attr("href");
				if(link!=null&&link.contains(".html")) {
					Document doc;
					try {
						doc = Jsoup.connect(link).get();
						if(doc!=null) {
							System.out.println(title);
							String text = doc.text();
							System.out.println(doc.text());
							writeFile(title,text);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void xianni(){
		Document document;
		try {
			document = Jsoup.connect("http://www.uu234.cc/r654/").get();
			Elements elements = document.select("a");
			for (Element element : elements) {
				String title = element.text();
				String link = element.attr("href");
				if(link!=null&&link.contains(".html")) {
					Document doc;
					try {
						doc = Jsoup.connect(link).get();
						if(doc!=null) {
							System.out.println(title);
							String text = doc.text();
							System.out.println(doc.text());
							writeFile(title,text);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
