package com.app.excel;


public class ParserToBean {
//    private static Logger logger = Logger.getLogger(ParserToBean.class);
//    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    private HssExcel xssExcel;
//    public ParserToBean (InputStream is) throws IOException{
//        xssExcel = HssExcel.load(is);
//    }
//    
//    public List<DataRange> getDataRange(){
//        logger.info("Start parse data range in sheet0.");
//        List<DataRange> dataRange = new ArrayList<>();
//        XSSFSheet sheet = xssExcel.getSheet(0);
//        for(int i=1;i<=sheet.getLastRowNum();i++){
//            DataRange dr = new DataRange();
//            XSSFRow xssRow = sheet.getRow(i);
//            XSSFCell cell = xssRow.getCell(0);//index
//            if(cell == null)
//                break;
//            Double obj = (Double)getValue(cell);
//            if(obj==null || "".equals(obj))
//                break;
//            dr.setIdx(obj.intValue());
//            //campaignid
//            cell = xssRow.getCell(1);
//            Double campaignId = (Double)getValue(cell);
//            dr.setCampaignId(campaignId.intValue());
//            //spotids
//            cell = xssRow.getCell(2);
//            String spots = (String)getValue(cell);
//            dr.setSpotIds(spots);
//            //starttime
//            cell = xssRow.getCell(3);
//            String startTime = format.format(cell.getDateCellValue());
//            dr.setStartTime(startTime);
//            //endtime
//            cell = xssRow.getCell(4);
//            String endTime = format.format(cell.getDateCellValue());
//            dr.setEndTime(endTime);
//            
//            dataRange.add(dr);
//            
//            logger.info("data range at row "+i+" is OK.");
//        }
//        return dataRange;
//    }
//    
//    public List<SubTask> getSubTasks(){
//        logger.info("Start parse subtasks in sheet1.");
//        List<SubTask> subTasks = new ArrayList<>();
//        XSSFSheet sheet = xssExcel.getSheet(1);
//        XSSFSheet sheet1 = xssExcel.getSheet(2);
//        Map<Integer,List<SubTaskContent>> contents = getSubTaskContent(sheet1);
//        for(int i=1;i<=sheet.getLastRowNum();i++){
//            SubTask st = new SubTask();
//            XSSFRow xssRow = sheet.getRow(i);
//            XSSFCell cell = xssRow.getCell(0);
//            if(cell == null)
//                break;
//            Double obj = (Double)getValue(cell);
//            if(obj==null || "".equals(obj))
//                break;
//            Integer num =  obj.intValue();
//            st.setSubTaskContent(contents.get(num));
//            
//            cell = xssRow.getCell(1);
//            String name = (String)getValue(cell);
//            st.setSubTaskName(name);
//            
//            cell = xssRow.getCell(2);
//            String byday = (String)getValue(cell);
//            if("是".equals(byday))
//                st.setCalculateByday(1);
//            else
//                st.setCalculateByday(0);
//            
//            cell = xssRow.getCell(3);
//            String accumulative = (String)getValue(cell);
//            if("是".equals(accumulative))
//                st.setAccumulative(1);
//            else
//                st.setAccumulative(0);
//            
//            cell = xssRow.getCell(4);
//            String region = (String)getValue(cell);
//            st.setRegionNames(region);
//            
//            cell = xssRow.getCell(5);
//            String asMarket = (String)getValue(cell);
//            if("是".equals(asMarket))
//                st.setAsMarket(1);
//            else
//                st.setAsMarket(0);
//            
//            subTasks.add(st);
//            
//            logger.info("subtask at row "+i+" is OK.");
//        }
//        return subTasks;
//    }
//    
//    private Map<Integer,List<SubTaskContent>> getSubTaskContent(XSSFSheet sheet){
//        logger.info("Start parse subtask data unin.");
//        Map<Integer,List<SubTaskContent>> map = new HashMap<>();
//        for(int i=1;i<=sheet.getLastRowNum();i++){
//            SubTaskContent stc = new SubTaskContent();
//            XSSFRow xssRow = sheet.getRow(i);
//            XSSFCell cell = xssRow.getCell(0);
//            if(cell==null || ("").equals(cell)){
//            	break;
//            }
//            Object obj = getValue(cell);
//            Double dObj = null;
//            if(obj==null || "".equals(obj)){
//                break;
//            }else{
//            	dObj = (Double)getValue(cell);	
//            }
//            Integer num =  dObj.intValue();
//            
//            cell = xssRow.getCell(1);
//            String campaignIds = null;
//            Object c = getValue(cell);
//            if(c instanceof Double){
//                Double d = (Double)getValue(cell);
//                campaignIds = d.intValue()+"";
//            }else
//                campaignIds = (String)getValue(cell);
//            stc.setDataUnin(campaignIds);
//            
//            if(map.containsKey(num)){
//                map.get(num).add(stc);
//            } else {
//                List<SubTaskContent> stContent = new ArrayList<>();
//                stContent.add(stc);
//                map.put(num, stContent);
//            }
//            logger.info("data unin at row "+i+" is OK.");
//        }
//        
//        return map;
//    }
//    
//    private Object getValue(XSSFCell xssfCell) {
//        if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
//            // 返回布尔类型的值
//            return xssfCell.getBooleanCellValue();
//        } else if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
//            // 返回数值类型的值
//            return xssfCell.getNumericCellValue();
//        }else {
//        
//            // 返回字符串类型的值
//            return xssfCell.getStringCellValue();
//        }
//    }
}
