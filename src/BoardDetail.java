import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class BoardDetail extends JFrame {
	
	
	Board_list board_list = new Board_list();
	
	public BoardDetail(Object id, String titles, Object writer) {
		setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 15));
		getContentPane().setBackground(Color.WHITE);
		
		String content = Board_list.getContent(id,titles,writer); //content 찾기
		setBounds(new Rectangle(300, 10, 600, 500));
		setTitle("글상세 내용");
		
		getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("제목");
		lblNewLabel.setBounds(24,86,62,34);
		lblNewLabel.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 15));
		getContentPane().add(lblNewLabel);
		
		JTextField title = new JTextField(titles);
		title.setBounds(88,86,466,34);
		title.setColumns(10);
		title.setFont(new Font("나눔바른고딕 Light", Font.PLAIN, 13));
		title.setBackground(Color.white);
		title.setEditable(false);		
		getContentPane().add(title);
		
		JLabel lblNewLabel_1 = new JLabel("내용");
		lblNewLabel_1.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(24,139,62,34);
		getContentPane().add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea(content);
		textArea.setLineWrap(true);
        textArea.setRows(7);
        textArea.setFont(new Font("나눔바른고딕 Light", Font.PLAIN, 13));
        textArea.setEditable(false);      
        textArea.setBackground(Color.white);
        textArea.setBounds(88, 142, 466, 205);
        
        Border lineBorder = BorderFactory.createLineBorder(new Color(160, 160,160));  // text area 테두리생성
        Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
        textArea.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(textArea);
        
        
        RoundedButton btnUpdate = new RoundedButton("수정");
        btnUpdate.setBounds(318,373,75,23);
        btnUpdate.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 13));
        btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Client_App.current_id.equals(writer)) {
					//BoardUpdate 패널
					new BoardUpdate(id,titles,content,writer);
					
					
				}else {
					JOptionPane.showMessageDialog(null,"수정기능은 작성자만 할수있습니다!!");
					
				}
				
				
				setVisible(false);
				
			}
		});
        
        getContentPane().add(btnUpdate);
        
        
        RoundedButton btnDelete = new RoundedButton("삭제");
        btnDelete.setBounds(397,373,75,23);
        btnDelete.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 13));
        btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Client_App.current_id.equals(writer)) {
					board_list.deleteBoard(id,writer);
					Board board= new Board();
				}else {
					JOptionPane.showMessageDialog(null,"삭제기능은 작성자만 할수있습니다!!");
					
				}
				
				
				setVisible(false);
			}
		});
        
        getContentPane().add(btnDelete);
        
        RoundedButton btnClose = new RoundedButton("닫기");
        btnClose.setBounds(479,373,75,23);
        btnClose.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 13));
        btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Board board=new Board();
			}
		});
        getContentPane().add(btnClose);
        
        JSeparator separator = new JSeparator();
        separator.setBackground(SystemColor.menu);
        separator.setBounds(8, 130, 546, 25);
        getContentPane().add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(SystemColor.menu);
        separator_1.setBounds(8, 356, 546, 25);
        getContentPane().add(separator_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBackground(SystemColor.menu);
        separator_2.setBounds(8, 74, 546, 25);
        getContentPane().add(separator_2);
        
        JLabel lblNewLabel_2 = new JLabel("게시글 내용 보기\r\n\r\n");
        lblNewLabel_2.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 18));
        lblNewLabel_2.setBounds(201, 12, 154, 34);
        getContentPane().add(lblNewLabel_2);
        
        setVisible(true);
	}
}
