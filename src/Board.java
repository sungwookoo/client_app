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

		setBounds(300,100,705,566);
		setTitle("게시판");
		setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
		
		boardPane = new JPanel();
		boardPane.setBackground(Color.WHITE);

		setContentPane(boardPane);

		String[][] data = Board_list.getBoards();
		String[] colNames = new String[] {"번호","제목","작성자","조회수","작성일"};
		JTable boardTable = new JTable(data,colNames);
		
		boardTable.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 17));
        boardTable.setAlignmentX(0);
        boardTable.setSize(400,200);
        
        
        boardTable.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"2", "\uD14C\uC2A4\uD2B81", "A", "35", "2020-05-08 01:14:15"},
        		{"3", "\uAE00", "A", "15", "2020-05-08 03:36:32"},
        		{"5", "\uC544\uD504\uB2E4", "A", "10", "2020-05-25 21:39:28"},
        		{"6", "\uC548\uB155", "A", "3", "2020-05-26 04:17:37"},
        		{"7", "\uAE00\uC81C\uBAA9\uC785\uB2C8\uB2E4.", "A", "3", "2020-05-26 04:19:15"},
        		{"13", "\uC81C\uBAA9\uC744 \uC785\uB825\uD574 \uC8FC\uC138\uC694.", "A", "1", "2020-05-28 23:32:57"},
        		{"14", "\uC548\uB155\uD558\uC138\uC694", "a", "5", "2020-05-29 00:43:35"},
        		{"17", "\uD14C\uC2A4\uD2B835", "a", "1", "2020-05-29 12:39:21"},
        		{"18", "\uD14C\uC2A4\uD2B8 455", "a", "2", "2020-05-29 12:39:47"},
        		{"19", "\uC548\uB155\uD558\uC138\uC694", "mnb", "2", "2020-05-29 12:48:45"},
        		{"20", "\uC548\uB155\uD558\uC138\uC694 \uAE40\uC7A5\uBE44\uC785\uB2C8\uB2E4.", "Ace", "5", "2020-05-29 13:31:29"},
        		{"21", "\u314E\u3147\uC694", "Ace", "2", "2020-05-29 13:45:52"},
        		{"22", "", "A", "0", "2020-06-15 19:48:25"},
        	},
        	new String[] {
        		"\uBC88\uD638", "\uC81C\uBAA9", "\uC791\uC131\uC790", "\uC870\uD68C\uC218", "\uC791\uC131\uC77C"
        	}
        ));
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
        scrollPane.setBounds(40, 131, 600, 330);
        //사이즈를 정했지만 안정해지는경우도있으므로 setPreferredScrollableViewportSize 로 두번크기설정
        scrollPane.setPreferredSize(new Dimension(600,330));
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
		comboBox.setFont(new Font("나눔바른고딕 Light", Font.PLAIN, 15));
		comboBox.setBounds(159, 473, 75, 24);
		boardPane.add(comboBox);
		
		RoundedButton searchButton = new RoundedButton("검색");
		searchButton.setFont(new Font("나눔바른고딕 Light", Font.PLAIN, 13));
		searchButton.setBounds(436, 471, 85, 27);
		boardPane.add(searchButton);
		
		this.setVisible(true);
	}
}
