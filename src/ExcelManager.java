import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	 private static ExcelManager excelMng;
	   
	    public ExcelManager() {
	        // TODO Auto-generated constructor stub
	    }
	   
	    public static ExcelManager getInstance() {
	        if (excelMng == null)
	            excelMng = new ExcelManager();
	        return excelMng;
	    }
	   
	 
	    public List<HashMap<String, String>> getListExcel() throws Exception {
	        List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
	        FileInputStream file = new FileInputStream("./excel.xlsx"); // 식단표 경로 입력
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        //엑셀파일의 시트 존재 유무 확인
	        if (workbook.getNumberOfSheets() < 1) return null;
	       
	        //첫번째 시트를 읽음
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            list.add(readCellData(sheet.getRow(i)));
	        }
	        return list;
	    }	        
	   
	   
	    private HashMap<String, String> readCellData(XSSFRow row) {
	        HashMap<String, String> hMap = new HashMap<String, String>();
	        int maxNum = row.getLastCellNum();
	        for(int i = 0; i < maxNum; i++){
	            hMap.put("attr"+i,getStringCellData(row.getCell(i)));
	        }	       
	        return hMap;
	    }   

	    private String getStringCellData (XSSFCell cell) {
	        DecimalFormat df = new DecimalFormat();
	        FormulaEvaluator evaluator =
	        		new XSSFWorkbook().getCreationHelper().createFormulaEvaluator();
	        if (cell != null) {
	            String data = null;
	            switch (cell.getCellType()) {
	                case BOOLEAN:
	                    boolean bdata = cell.getBooleanCellValue();
	                    data = String.valueOf(bdata);
	                    break;
	                case NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	                        data = formatter.format(cell.getDateCellValue());
	                    } else {
	                        double ddata = cell.getNumericCellValue();
	                        data = df.format(ddata);
	                    }
	                    break;
	                case STRING:
	                    data = cell.toString();
	                    break;
	                case BLANK:
	                case ERROR:
	                case FORMULA:
	                    if (!(cell.toString() == "")) {
	                        if (evaluator.evaluateFormulaCell(cell) ==CellType.NUMERIC) {

	                            double fddata = cell.getNumericCellValue();
	                            data = df.format(fddata);
	                        } else if (evaluator.evaluateFormulaCell(cell) ==

	                                                                             CellType.STRING) {
	                            data = cell.getStringCellValue();
	                        } else if (evaluator.evaluateFormulaCell(cell) ==

	                                                                             CellType.BOOLEAN) {
	                            boolean fbdata = cell.getBooleanCellValue();
	                            data = String.valueOf(fbdata);
	                        }
	                        break;
	                    }
	                default:
	                    data = cell.toString();
	            }
	            return data;
	        } else {
	            return null;
	        }
	    }
	}

