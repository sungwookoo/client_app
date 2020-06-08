import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class Carte extends JFrame {
	
	private static JPanel cartePane;
	public JTable table;
	
	String[] colNames = {"","월","화","수","목","금"};
    public Object data[][];
    
	public Carte() throws Exception {
		setBounds(300,100,600,330);
		setTitle("식단표");
		cartePane = new JPanel();
		setContentPane(cartePane);
		java.util.List<HashMap<String,String>> list =ExcelManager.getInstance().getListExcel();
		data= new String[list.size()][6];
		//row 번호 설정 
		for(int i =0;i<list.size();i++) {
			data[i][0]=Integer.toString(i+1);
			
		}
		
		for(int i =0 ; i<list.size();i++) {
			int sizeMap = list.get(i).size();
			for(int j=0; j<sizeMap;j++) {
				data[i][j+1] =list.get(i).get("attr"+j);
			}
		}
				table =new JTable(data,colNames);
		table.setRowHeight(30);
		table.getColumn("").setPreferredWidth(10);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(550,232));  //크기 조절 ! 
		cartePane.add(scrollPane);
//		cartePane.add(new JScrollPane(table),BorderLayout.CENTER);
		
		try {

			FileInputStream file = new FileInputStream("./excel.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(file);	//파일
			
			XSSFSheet sh = wb.getSheetAt(0);	//SHEET
			
			//행의 수 
			int rows = sh.getPhysicalNumberOfRows();
			
			for(int i =0 ; i < rows ; i++ ) {
				ArrayList<String> arrLine = new ArrayList<String>();
				
			//행을 읽음
			XSSFRow row =sh.getRow(i);
			
			if(row ==null) continue;
			
			//셀의 수 
			int cells =row.getLastCellNum(); // 비어있지않은 가장 끝의 셀 위치 
			
			for(int k =0; k<=cells; k++) {
				//셀 값을 읽음
				XSSFCell cell =sh.getRow(i).getCell(k);
				
				if(cell ==null) {
					
					continue;
				}
				
				//타입별로 내용 읽기
				String value ="";
				
				switch(cell.getCellType()) {
				case FORMULA :
					value =cell.getCellFormula();
					break;
				case NUMERIC : //데이터타입이 숫자
					value =cell.getNumericCellValue()+"";
					break;
				case STRING :
					value = cell.getStringCellValue()+"";
					break;
				case BLANK : 
					value = cell.getBooleanCellValue()+"";
					break;
				case ERROR : 
					value = cell.getErrorCellValue()+"";
					break;
				default : 
					break; 
					
				}
				
				arrLine.add(value);
			}

			System.out.println(arrLine);
			
			
		

	

			
			
			
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}  
		
		
		
		this.setVisible(true);
	}

}
