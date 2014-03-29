package com.app.business.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.app.utils.PageUtils;
import com.app.utils.Utils;

public class PageVO {
    // 总记录数
    private Integer totalCount;
    // 当前页码
    private Integer currentPage = 1;
    // 每页的行数
    private Integer lines = 20;
    // 总页数
    private Integer totalPage = 1;
    //排序列
    private String orderby = null;
    //排序方式
    private String order = null;
    // 被填充到页面table的数据
    private List<?> rows;
    //当使用查询top xx后剩余的行
    private List<?> otherRows;
    public PageVO(){
        
    }
    public PageVO(Map<String,Object> params){
        this.calcPageParams(params);
    }
    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLines() {
        return lines;
    }

    public void setLines(Integer lines) {
        this.lines = lines;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Map<String, Object> getOtherData() {
        return otherData;
    }

    public void setOtherData(Map<String, Object> otherData) {
        this.otherData = otherData;
    }
public List<?> getOtherRows() {
        return otherRows;
    }
    public void setOtherRows(List<?> otherRows) {
        this.otherRows = otherRows;
    }
/**
 * 对结果只取前几行,返回还剩余的行数
 */
    public int setLimitRows(Integer limitRows) {
        int over = 0;
        if(limitRows !=null && limitRows<this.rows.size()){
            over = this.rows.size()-limitRows;
            int toIndex = limitRows>0?limitRows:0;
            List<Object> others = new ArrayList<Object>();
            for(Object obj : this.rows.subList(toIndex, this.rows.size())){
                others.add(obj);
            }
            this.otherRows =others;
            this.rows = this.rows.subList(0, toIndex);
        }
        return over;
    }
    // 其它额外的数据获取
    private Map<String, Object> otherData;

    /**
     * 
     * @param map
     */
    public void fillPaged(Map<String, Object> map) {
        Object cp = map.get(PageUtils.CURRENTPAGE);
        if (!Utils.isEmpty(cp)) {
            currentPage = Integer.valueOf(cp.toString());
        }
        Object line = map.get(PageUtils.LINES);
        if (!Utils.isEmpty(line)) {
            lines = Integer.valueOf(line.toString());
        }
        Object tp = map.get(PageUtils.TOTALPAGE);
        if (!Utils.isEmpty(tp)) {
            totalPage = Integer.valueOf(tp.toString());
        }
        if (totalCount != null && lines != null) {
            if (totalCount > lines) {
                totalPage = (totalCount % lines == 0) ? (totalCount / lines) : (totalCount / lines + 1);
            } else {
                totalPage = 1;
            }
        }
    }
    public  void calcPageParams(Map<String, Object> params) {
        Object currentPage = params.get(PageUtils.CURRENTPAGE);
        Object lines = params.get(PageUtils.LINES);
        String keyword = (String)params.get(PageUtils.KEYWORD);
        if(keyword==""){
            params.put(PageUtils.KEYWORD, null);
        }else{
            params.put(PageUtils.KEYWORD, "%"+keyword+"%");
        }
        if (currentPage != null) {
            this.currentPage = Integer.parseInt(currentPage.toString());
        }
        if( lines != null){
            this.lines = Integer.parseInt(lines.toString());
            Integer firstResult = (this.currentPage - 1) * this.lines;
            Integer maxResults =this.lines;
            params.put(PageUtils.FIRSTRESULT, firstResult);
            params.put(PageUtils.MAXRESULT, maxResults);
        }
        Object orderField = params.get(PageUtils.ORDER_FIELD);
        Object orderType = params.get(PageUtils.ORDER_TYPE);
        if(orderField !=null && orderType !=null){
           params.put(PageUtils.ORDER, orderField +" "+orderType);
        }
    }
    
    /**
     * 处理 包含 ‘其它’ 的列表 分页
     * 
     * @param x
     * @param params
     * 
     * @return isNeed Add other option
     */
    public static Boolean initParamContainsOtherTopX(Integer tops, Map<String, Object> params, Integer count) {
        
        boolean result = false;
        if(tops ==null){
            return result;
        }
        Integer cp = Integer.valueOf(params.get(PageUtils.CURRENTPAGE).toString());
        Integer lns = Integer.valueOf(params.get(PageUtils.LINES).toString());
        if (cp > tops / lns) {
            params.put(PageUtils.MAXRESULT, tops % lns);
            if (null != count && count > tops) {
                result = true;
            }
        } else {
            params.put(PageUtils.MAXRESULT, lns);
        }
        return result;
    }
}
