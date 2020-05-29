import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardDetail extends JFrame {
	
	
	Board_list board_list = new Board_list();
	
	public BoardDetail(Object id, String titles, Object writer) {
		
		String content = Board_list.getContent(id,titles,writer); //content 찾기
		setBounds(new Rectangle(300,10,450,350));
		setTitle("글상세 내용");
		
		getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("제목");
		lblNewLabel.setBounds(50,10,57,15);
		lblNewLabel.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 13));
		getContentPane().add(lblNewLabel);
		
		JTextField title = new JTextField(titles);
		title.setBounds(90,10,200,20);
		title.setColumns(10);
		title.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 13));
		title.setBackground(Color.white);
		title.setEditable(false);
		getContentPane().add(title);
		
		JLabel lblNewLabel_1 = new JLabel("내용");
		lblNewLabel_1.setBounds(50,50,57,15);
		getContentPane().add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea(content);
		textArea.setLineWrap(true);
        textArea.setRows(7);
        textArea.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 13));
        textArea.setEditable(false);
        textArea.setBackground(Color.white);
        textArea.setBounds(90, 50, 300, 150);
        getContentPane().add(textArea);
        
        
        RoundedButton btnUpdate = new RoundedButton("수정");
        btnUpdate.setBounds(160,230,75,23);
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
        btnDelete.setBounds(240,230,75,23);
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
        btnClose.setBounds(320,230,75,23);
        btnClose.setFont(new Font("나눔바른고딕 Light", Font.BOLD, 13));
        btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Board board=new Board();
			}
		});
        getContentPane().add(btnClose);
        
        setVisible(true);
	}

}
