package com.pingan.debug.net.downdemo;

import android.util.Xml;


import com.pasc.lib.net.download.DownloadInfo;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;

public class ApkUrlParser {
	public static ArrayList<DownloadInfo> parse(InputStream is) throws Exception {
		ArrayList<DownloadInfo> urls = null;
		DownloadInfo url = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				urls = new ArrayList<DownloadInfo>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("apk")) {
					url = new DownloadInfo();
				} else if (parser.getName().equals("id")) {
					eventType = parser.next();
					url.id(Integer.parseInt(parser.getText()));
				} else if (parser.getName().equals("name")) {
					eventType = parser.next();
					url.fileName(parser.getText());
				} else if (parser.getName().equals("icon")) {
					eventType = parser.next();
					url.icon(parser.getText());
				} else if (parser.getName().equals("path")) {
					eventType = parser.next();
					url.downloadUrl(parser.getText());
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("apk")) {
					urls.add(url);
					url = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return urls;

	}
}
