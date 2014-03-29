package com.app.business.model.resp;

import java.util.List;
/**
 * 图文消息
 * @author wangx_000
 *
 */
public class NewsMessage extends BaseMessage{

	private String ArticleCount;
	private List<Article> Articles;
	
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}
