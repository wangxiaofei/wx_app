package com.app.business.model.resp;

import java.util.List;
/**
 * 图文消息
 * @author wangx_000
 *
 */
public class NewsMessageResp extends BaseMessageResp{

	private String ArticleCount;
	private List<ArticleResp> ArticleResps;
	
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	public List<ArticleResp> getArticles() {
		return ArticleResps;
	}
	public void setArticles(List<ArticleResp> articleResps) {
		ArticleResps = articleResps;
	}
}
