import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Board extends JFrame {
	//Board Main 화면
	private static JPanel boardPane;
	
	private JTextField searchText;
	private JButton searchBoardBtn;
	private RoundedButton createBoardBtn;
	
	public Board() {
		initialize();
	}
	
	    
	private void initialize() {
		Board_list board_list = new Board_list();
//		board_list.deleteBoardTable();
//		board_list.createBoardTable();
		setBounds(300,100,705,566);
		setTitle("게시판");
		setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
		
		boardPane = new JPanel();
		boardPane.setBackground(Color.WHITE);

		setContentPane(boardPane);

		String[][] data = Board_list.getBoards();
		String[] colNames = new String[] {"번호","제목","작성자","조회수","작성일"};
		JTable boardTable = new JTable(data,colNames);
		
		boardTable.setFont(new Font("나눔스퀘어라운드 Regular", Font.BOLD, 17));
        boardTable.setAlignmentX(0);
        boardTable.setSize(400,200);        
        
        boardTable.setModel(new DefaultTableModel(data,colNames) {
        	public boolean isCekkEditable(int row, int column) {
        		return false;
        	}
        });
        
        boardTable.getColumnModel().getColumn(0).setPreferredWidth(56);
        boardTable.getColumnModel().getColumn(1).setPreferredWidth(131);
        boardTable.getColumnModel().getColumn(2).setPreferredWidth(61);
        boardTable.getColumnModel().getColumn(3).setPreferredWidth(42);
        boardTable.getColumnModel().getColumn(4).setPreferredWidth(122);
        
        boardTable.getTableHeader().setReorderingAllowed(false);
        boardTable.getTableHeader().setResizingAllowed(false);
        
        boardTable.getTableHeader().setBackground(new Color(80, 188, 223));
        boardTable.getTableHeader().setForeground(new Color(255,255,255));
       
        boardTable.setRowHeight(35);
        
        
        boardTable.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		int rowNum = boardTable.getSelectedRow();
        		Object num =boardTable.getValueAt(rowNum, 0);
        		String title =(String) boardTable.getValueAt(rowNum, 1);
//        		String content =(String) boardTable.getValueAt(rowNum, 2);
        		Object writer =boardTable.getValueAt(rowNum, 2);
        		
        		//상세조회 클릭시 조회수 오르게끔 구현하기! 
        		Board_list.hitsBoard(num,writer);
        		//Board 상세 정보 
        		new BoardDetail(num,title,writer);
        		setVisible(false);
        		boardTable.setModel(new DefaultTableModel(board_list.getBoards(),colNames));

                TableColumnModel columnModels = boardTable.getColumnModel();
                columnModels.getColumn(0).setPreferredWidth(10);
                columnModels.getColumn(1).setPreferredWidth(50);
                columnModels.getColumn(2).setPreferredWidth(100);
                columnModels.getColumn(3).setPreferredWidth(20);
                columnModels.getColumn(4).setPreferredWidth(10);

                boardPane.repaint();
                repaint();
                validate();
        	}
		});
        JScrollPane scrollPane = new JScrollPane(boardTable);
        scrollPane.setBounds(40, 120, 600, 340);
        //사이즈를 정했지만 안정해지는경우도있으므로 setPreferredScrollableViewportSize 로 두번크기설정
        scrollPane.setPreferredSize(new Dimension(600,350));
        boardPane.setLayout(null);
        
        searchText=new JTextField();
        searchText.setFont(new Font("나눔바른고딕 Light", Font.PLAIN, 15));
		searchText.setBounds(248,473,174,24);
		boardPane.add(searchText);
		searchText.setColumns(10);
		
		searchText.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				String val = searchText.getText();
                TableRowSorter<TableModel> trs = new TableRowSorter<>(boardTable.getModel());
                boardTable.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter(val)); // regular expression 을 통해 string 값이 정리가된다
			}
		});
		

		createBoardBtn =new RoundedButton("글작성");

		RoundedButton createBoardBtn =new RoundedButton("글작성");

		createBoardBtn.setBounds(565,87,75,27);
		createBoardBtn.setFont(new Font("나눔바른고딕 Light",Font.BOLD,13));
		
		boardPane.add(createBoardBtn);
		
		createBoardBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new BoardInsert();
			}
		});
		
		boardPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./img/logo.png"));
		lblNewLabel.setBounds(-2, 3, 97, 91);
		boardPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"제목","작성자"}));
		comboBox.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 15));
		comboBox.setBounds(159, 473, 75, 24);
		boardPane.add(comboBox);
		
		RoundedButton searchButton = new RoundedButton("검색");
		searchButton.setFont(new Font("나눔바른고딕 Light", Font.PLAIN, 13));
		searchButton.setBounds(436, 471, 85, 27);
		boardPane.add(searchButton);
		
		this.setVisible(true);
	}
}
