import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class BoardUpdate extends JFrame{

	Board_list board_list = new Board_list();
	
	public BoardUpdate(Object id, String titles, String content, Object writer) {
		getContentPane().setBackground(Color.WHITE);
		
		setBounds(new Rectangle(300, 10, 600, 500));
		setTitle("게시글 수정");
		setFont(new Font("나눔바른고딕 Light", Font.BOLD, 15));
		getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("게시글 수정");
		lblNewLabel.setBounds(217,12,154,34);
		lblNewLabel.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 18));
		getContentPane().add(lblNewLabel);
		
		
		JTextField title = new JTextField(titles);
		title.setBounds(88,86,466,34);
		title.setColumns(10);
		title.setBackground(Color.white);
		title.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 13));
		getContentPane().add(title);
		
		JTextArea textArea = new JTextArea(content);
		textArea.setLineWrap(true);
        textArea.setRows(7);
        textArea.setBackground(Color.white);
        textArea.setBounds(88, 142, 466, 205);
        textArea.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 13));
        
        Border lineBorder = BorderFactory.createLineBorder(new Color(160, 160,160));  // text area 테두리생성
        Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
        textArea.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));        
        getContentPane().add(textArea);
        
        RoundedButton btnWrite = new RoundedButton("수정완료");
        btnWrite.setBounds(394,369,75,30);
        btnWrite.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 13));
        btnWrite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String titles =title.getText();
				String txtarea =textArea.getText();
				
				board_list.updateBoard(id,titles,txtarea,writer);
				setVisible(false);
				Board board= new Board();
			}
		});
        
        getContentPane().add(btnWrite);
        
        
        RoundedButton btnClose = new RoundedButton("닫기");
        btnClose.setBounds(473,369,75,30);
        btnClose.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 13));
        btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
        
        getContentPane().add(btnClose);
        
        JLabel lblNewLabel_1 = new JLabel("제목");
        lblNewLabel_1.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(24, 86, 62, 34);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("내용");
        lblNewLabel_1_1.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 15));
        lblNewLabel_1_1.setBounds(24, 139, 62, 34);
        getContentPane().add(lblNewLabel_1_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBackground(SystemColor.menu);
        separator_2.setBounds(8, 74, 546, 25);
        getContentPane().add(separator_2);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(SystemColor.menu);
        separator_1.setBounds(8, 356, 546, 25);
        getContentPane().add(separator_1);
        
        JSeparator separator = new JSeparator();
        separator.setBackground(SystemColor.menu);
        separator.setBounds(8, 130, 546, 25);
        getContentPane().add(separator);
        
        setVisible(true);
	}
}
