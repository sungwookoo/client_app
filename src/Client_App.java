import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays; 
import java.util.Arrays;


import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class Client_App { 
    public static JFrame frame;

    private JPanel profilePanel;
    private JPanel tablePanel;
    private JPanel homePanel;
    private JPanel updatePanel = new JPanel();
    private JPanel createPanel = new JPanel();
    private ImagePanel welcomePanel = new ImagePanel(new ImageIcon("./img/LoginBackground2.png").getImage());

    static String current_id = "";
    static String userName = "";
    private int loginResult = 2;
    private String firstCheck = "1";
    private JTextField textID = new JTextField(10);

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Client_App window = new Client_App();
                    window.frame.setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Client_App(){
        TrayIconApp trayIconApp = new TrayIconApp();
        initialize();
    }

    private void initialize(){
        //DB와 GUI 연결하기위한 Customer 객체생성
        Customer customer = new Customer();
//        customer.deleteTable();
//        customer.createTable();


        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 706);
		frame.getContentPane().setLayout(null);

        //로그인화면(첫화면) panel

        frame.getContentPane().add(welcomePanel);

        //창 닫았을때
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
            }
        });

        //************************************* profile 등록 화면 ******************************************
        profilePanel = new JPanel();
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setBounds(0,0, 958,721);
        frame.getContentPane().add(profilePanel);
        profilePanel.setLayout(null);
        profilePanel.setVisible(false);

        //메인패널 상단 label
        JLabel welcomeMain = new JLabel("Job time");
        welcomeMain.setBounds(340,30,300,40);
        welcomeMain.setFont(new Font("Vivaldi",Font.BOLD ,35 ));
        profilePanel.add(welcomeMain);

        JLabel name = new JLabel("이름");
        name.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
        name.setBounds(230,120,85,40);
        
        JTextField textName = new JTextField(10);
        textName.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        textName.setBounds(320,120,240,40);
        profilePanel.add(name);
        profilePanel.add(textName);

        JLabel phone = new JLabel("휴대폰	");
        phone.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
        phone.setBounds(230,180,85,40);
        
        JTextField textPhone = new JTextField(10);
        textPhone.setBounds(320,180,240,40);
        textPhone.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        profilePanel.add(phone);
        profilePanel.add(textPhone);

        JLabel age = new JLabel("나이");
        age.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
        age.setBounds(230,240,85,40);
        
        JTextField textAge = new JTextField(2);
        textAge.setBounds(320,240,240,40);
        textAge.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        profilePanel.add(age);
        profilePanel.add(textAge);

        JLabel birthDay = new JLabel("생일");      //달력 추가?
        birthDay.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
        birthDay.setBounds(230,300,85,40);
        
        JTextField textBirthDay = new JTextField(8);
        textBirthDay.setBounds(320,300,240,40);
        textBirthDay.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        profilePanel.add(birthDay);
        profilePanel.add(textBirthDay);

        JLabel gender = new JLabel("성별");
        gender.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
        gender.setBounds(230,360,85,40);
        
        JComboBox comboBoxGender = new JComboBox(new String[]{"남자","여자"});
        comboBoxGender.setBounds(320,360,240,40);
        comboBoxGender.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        profilePanel.add(gender);
        profilePanel.add(comboBoxGender);

        JLabel note = new JLabel("기타사항");
        note.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
        note.setBounds(230,420,85,40);
        
        JTextArea textNote = new JTextArea();
        textNote.setBounds(320,420,240,90);
        textNote.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        textNote.setBorder(BorderFactory.createLineBorder(Color.black,1));
        profilePanel.add(note);
        profilePanel.add(textNote);
        
        //************************************* profile 등록 성공시 첫 로그인 메인 화면 ******************************************

        homePanel = new JPanel();
		homePanel.setBackground(Color.WHITE);
		homePanel.setBounds(0, 0,welcomePanel.getWidth(),welcomePanel.getHeight());
		frame.getContentPane().add(homePanel);
		homePanel.setLayout(null);
		homePanel.setVisible(false);
//		homePanel.setSize(welcomePanel.getWidth(), welcomePanel.getHeight());
		
		JLabel main = new JLabel("Job Time");
		main.setVerticalAlignment(SwingConstants.TOP);
//        main.setBounds(170,36,167,50);
        main.setBounds(170,66,167,50);
        main.setFont(new Font("Vivaldi",Font.BOLD,35));
        homePanel.add(main);
        
        RoundedButton btnNewButton = new RoundedButton("메신저");
        btnNewButton.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        btnNewButton.setBounds(160, 140, 170, 50);
        homePanel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Client();
            }
        });
        
        RoundedButton btnNewButton_1 = new RoundedButton("게시판");
        btnNewButton_1.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        btnNewButton_1.setBounds(160, 210, 170, 50);
        homePanel.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Board();
				
			}
		});

        RoundedButton btnNewButton_2 = new RoundedButton("식단표");
        btnNewButton_2.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        btnNewButton_2.setBounds(160, 280, 170, 50);
        homePanel.add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						new Carte();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
        
        RoundedButton btnNewButton_3 = new RoundedButton("채팅서버(관리자용)");
        btnNewButton_3.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        btnNewButton_3.setBounds(160, 350, 170, 50);

        homePanel.add(btnNewButton_3);
        btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Server();
            }
        });
       
        //************************************* 관리자용 TABLE 화면 ******************************************

        tablePanel = new JPanel();
        tablePanel.setBackground(Color.WHITE);

//        tablePanel.setLayout(new FlowLayout());
        tablePanel.setBounds(0,0,958,800);
        String[][] data = customer.getCustomers();
//        String[] headers = new String[]{"ID","UserName","Password","Name","Phone","Gender","Age","Note"};
        String[] headers = new String[]{"아이디","비밀번호","이름","연락처","성별","나이","기타사항"};
        JTable table = new JTable(data,headers);
//        table.setModel(new DefaultTableModel(data,headers));
//        table.setBounds(0,300,800,400);
        table.setRowHeight(30);
        table.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        table.setAlignmentX(0);

        int[] columnsWidth = {
        		70,70,70,120,70,70,150
        };
        int i=0;
        for( int width : columnsWidth) {
        	TableColumn column =table.getColumnModel().getColumn(i++);
        	column.setMinWidth(width);
        	column.setMaxWidth(width);
        	column.setPreferredWidth(width);
        }
        tablePanel.setLayout(null);
//        table.setModel(new DefaultTableModel(data, headers) {
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//		});
//
       
        table.getTableHeader().setReorderingAllowed(false);   //table 사이즈,내용 수정 불가
        table.getTableHeader().setResizingAllowed(false);
//        
        table.getTableHeader().setBackground(new Color(80, 188, 223));
        table.getTableHeader().setForeground(new Color(255,255,255));
        
//        table.setSize(800,400);
        //사이즈를 정했지만 안정해지는경우도있으므로 setPreferredScrollableViewportSize 로 두번크기설정
        table.setPreferredScrollableViewportSize(new Dimension(600,400));
        //JScrollPane < 스크롤이가능한 컴포넌트로 추가한다.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(130, 25, 623, 400);
        tablePanel.add(scrollPane);
        // ************************************* create 패널 ******************************************
        RoundedButton createBtn = new RoundedButton("생성");
        createBtn.setBounds(550,443,61,27);
        createBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.setBounds(100,100,528,482);
                createPanel.setBounds(0,0,welcomePanel.getWidth(),welcomePanel.getHeight());
                createPanel.setBackground(Color.WHITE);
                createPanel.setLayout(null);


                JLabel createMain = new JLabel("Job Time");
                createMain.setBounds(200,50,300,40);
                createMain.setFont(new Font("Vivaldi",Font.BOLD,35));
                createPanel.add(createMain);	
                //관리자가 처음 만들어줄 username
                JLabel username = new JLabel("아이디");
                username.setFont(new Font("나눔바른고딕 Light",Font.BOLD,20));
                username.setBounds(100,150,110,40);

                JTextField textUserName = new JTextField(15);
                textUserName.setBounds(220,150,140,40);
                textUserName.setFont(new Font("나눔바른고딕 Light",Font.BOLD,20));
                
                
                textUserName.addFocusListener(new FocusListener() {
        			
        			@Override
        			public void focusLost(FocusEvent e) {
        				// TODO Auto-generated method stub
        				if(textUserName.getText().trim().equals(""))
        					textUserName.setText("아이디");
        				else {
        					
        				}
        						
        			}
        			
        			@Override
        			public void focusGained(FocusEvent e) {
        				// TODO Auto-generated method stub
        				if(textUserName.getText().trim().equals("아이디"))
        					textUserName.setText("");
        				else {
        					
        				}
        					
        			}
        		});

                createPanel.add(username);
                createPanel.add(textUserName);
                //관리자가 처음 만들어줄 password
                JLabel password = new JLabel("비밀번호");
                password.setFont(new Font("나눔바른고딕 Light",Font.BOLD,20));
                password.setBounds(100,250,110,40);
                
                JPasswordField textPassword = new JPasswordField(15);
                textPassword.setBounds(220,250,140,40);

                createPanel.add(password);
                createPanel.add(textPassword);
                //취소버튼
                RoundedButton cancelBtn = new RoundedButton("취소");
                cancelBtn.setFont(new Font("나눔바른고딕 Light",Font.BOLD,20));
                cancelBtn.setBounds(260,320,75,35);

                cancelBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createPanel.setVisible(false);
                        tablePanel.setVisible(true);
                        frame.setBounds(100,100,958,551);
                    }
                });

                //username 과 password 저장 버튼 생성 
                RoundedButton saveBtn = new RoundedButton("등록");
                saveBtn.setBounds(350,320,75,35);
                saveBtn.setFont(new Font("나눔바른고딕 Light",Font.BOLD,20));
                saveBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String Username = textUserName.getText();
						String password="";
						char[] secret_pw = textPassword.getPassword();
						for (char cha : secret_pw) {
								Character.toString(cha);  //cha에 저장된 값 String으로 변환
								
								//pw 에 저장하기, pw 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
								password += (password.equals("")) ? ""+cha+"" : ""+cha+"";			
						}
						Boolean flag = customer.createCustomer(Username,password);
//						Boolean flag = customer.createCustomer(Username,password,name, phone, gender, age, note);
						if(flag==true){
							
							//실행시 바로 테이블이 실시간으로 보이게끔
							table.setModel(new DefaultTableModel(customer.getCustomers(),headers));
							
			                int i=0;
			                for( int width : columnsWidth) {
			                	TableColumn column =table.getColumnModel().getColumn(i++);
			                	column.setMinWidth(width);
			                	column.setMaxWidth(width);
			                	column.setPreferredWidth(width);
			                }
			                
			                tablePanel.repaint();
			                frame.repaint();
			                frame.validate();
			                
		                    createPanel.setVisible(false);
		                    tablePanel.setVisible(true);
                            frame.setBounds(100,100,958,551);
		                }
		                else{
		                }
		            }
					
				});
                
                createPanel.add(saveBtn);
                createPanel.add(cancelBtn);
                tablePanel.setVisible(false);
                createPanel.setVisible(true);
                
                frame.getContentPane().add(createPanel);
			}
		});
        
        
        // ************************************* update 패널 ******************************************
        RoundedButton updateBtn = new RoundedButton("수정");
        updateBtn.setBounds(620,443,61,27);
        updateBtn.setFont(new Font("나눔바른고딕 Light",Font.BOLD,13));
        updateBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

//                frame.setBounds(100,100,958,721);
                frame.setBounds(100,100,900,700);
                int row1=table.getSelectedRow();
//                Object id= table.getValueAt(row1, 0);
                Object userName= table.getValueAt(row1, 0);
                Object Name =table.getValueAt(row1, 2);
                Object Phone =table.getValueAt(row1, 3);
                Object Gender =table.getValueAt(row1, 4);
                Object Age =table.getValueAt(row1, 5);
                Object Note =table.getValueAt(row1, 6);


                updatePanel.setBounds(0,0,958,751);
                updatePanel.setBackground(Color.WHITE);
                updatePanel.setLayout(null);


                JLabel updateMain = new JLabel("정보 수정");
                updateMain.setBounds(340,25,300,50);
                updateMain.setFont(new Font("나눔바른고딕 Extrabold",Font.BOLD,35));
                updatePanel.add(updateMain);

                JLabel name = new JLabel("이름");
                name.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
                name.setBounds(250,120,85,40);

                JTextField textName = new JTextField(10);
                textName.setBounds(320,120,240,40);
                textName.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
                textName.setText((String) Name);
                updatePanel.add(name);
                updatePanel.add(textName);

                JLabel phone = new JLabel("휴대전화");
                phone.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
                phone.setBounds(220,180,85,40);

                JTextField textPhone = new JTextField("휴대전화",10);
                textPhone.setBounds(320,180,240,40);
                textPhone.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
                textPhone.setText((String)Phone);
                updatePanel.add(phone);
                updatePanel.add(textPhone);

                JLabel age = new JLabel("나이");
                age.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
                age.setBounds(250,240,85,40);

                JTextField textAge = new JTextField(2);
                textAge.setBounds(320,240,240,40);
                textAge.setText((String)Age);
                textAge.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
                updatePanel.add(age);
                updatePanel.add(textAge);

                JLabel gender = new JLabel("성별");
                gender.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
                gender.setBounds(250,300,85,40);

                JComboBox comboBoxGender = new JComboBox(new String[]{"남자","여자"});
                comboBoxGender.setBounds(320,300,240,40);
                comboBoxGender.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
                updatePanel.add(gender);
                updatePanel.add(comboBoxGender);

                JLabel note = new JLabel("기타사항");
                note.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
                note.setBounds(220,360,85,40);

                JTextArea textNote = new JTextArea();
                textNote.setBounds(320,360,240,120);
                textNote.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
                textNote.setBorder(BorderFactory.createLineBorder(Color.black,1));
                textNote.setText((String)Note);
                updatePanel.add(note);
                updatePanel.add(textNote);

                //취소버튼
                RoundedButton cancelBtn = new RoundedButton("취소");
                cancelBtn.setBounds(330,520,100,35);
                cancelBtn.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));

                cancelBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updatePanel.setVisible(false);
                        tablePanel.setVisible(true);
                        frame.setBounds(100,100,958,551);
                    }
                });
                RoundedButton updateSubmitBtn = new RoundedButton("수정");
                updateSubmitBtn.setBounds(450,520,100,35);
                updateSubmitBtn.setFont(new Font("나눔바른고딕 Light",Font.BOLD,18));
                updateSubmitBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nameText = textName.getText();
                        String ageText = textAge.getText();
                        String phoneText = textPhone.getText();
                        String genderText = comboBoxGender.getSelectedItem().toString();
                        String noteText = textNote.getText();
                        Boolean flag = customer.updateCustomer(userName,nameText,phoneText,genderText,ageText,noteText,firstCheck);
                        if(flag==true){
                            table.setModel(new DefaultTableModel(customer.getCustomers(),headers));

                           
                            
                            int i=0;
			                for( int width : columnsWidth) {
			                	TableColumn column =table.getColumnModel().getColumn(i++);
			                	column.setMinWidth(width);
			                	column.setMaxWidth(width);
			                	column.setPreferredWidth(width);
			                }
			                
                            tablePanel.repaint();
                            frame.repaint();
                            frame.validate();

                            updatePanel.setVisible(false);
                            tablePanel.setVisible(true);
                            frame.setBounds(100,100,958,551);

                        }
                        else{
                        }

                    }
                });
                updatePanel.add(cancelBtn);
                updatePanel.add(updateSubmitBtn);
                tablePanel.setVisible(false);
                updatePanel.setVisible(true);

                frame.getContentPane().add(updatePanel);

            }
        });
        //*************************************************************************************************
        RoundedButton deleteBtn = new RoundedButton("삭제");
        deleteBtn.setBounds(690,443,61,27);
        deleteBtn.setFont(new Font("나눔바른고딕 Light",Font.BOLD,13));
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row1= table.getSelectedRow();
//                Object deleteId=table.getValueAt(row1,0);
                Object deleteUsername=table.getValueAt(row1,0);
                customer.deleteCustomer(deleteUsername);
                
                table.setModel(new DefaultTableModel(customer.getCustomers(),headers));


                int i=0;
                for( int width : columnsWidth) {
                	TableColumn column =table.getColumnModel().getColumn(i++);
                	column.setMinWidth(width);
                	column.setMaxWidth(width);
                	column.setPreferredWidth(width);
                }


                tablePanel.repaint();
                frame.repaint();
                frame.validate();
            }
        });
        tablePanel.add(createBtn);
        tablePanel.add(updateBtn);
        tablePanel.add(deleteBtn);
        frame.getContentPane().add(tablePanel);

        //테이블 필터만들기 JTextField search 적는순간 적은부분있는것만남게
        JTextField search = new JTextField();
        search.setFont(new Font("나눔바른고딕 Light",Font.PLAIN,17));
        search.setBounds(330,443,200,27);
        tablePanel.add(search);
        search.setColumns(10);
        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String val = search.getText();
                TableRowSorter<TableModel> trs = new TableRowSorter<>(table.getModel());
                table.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter(val)); // regular expression 을 통해 string 값이 정리가된다
            }
        });
        // 0번열 (name) 은 setPreferredWidth(100)은 100보다 사이즈가 더 커질경우 자동으로 테이블의 크기를 조절
        TableColumnModel columnModels = table.getColumnModel();
//        columnModels.getColumn(0).setPreferredWidth(10);
//        columnModels.getColumn(1).setPreferredWidth(100);
//        columnModels.getColumn(3).setPreferredWidth(50);
//        columnModels.getColumn(4).setPreferredWidth(10);
        tablePanel.setVisible(false);
//        ===============================================================================================================
        
        //submit(제출 ) 버튼 생성 및 action      
        RoundedButton submitBtn = new RoundedButton("등록");
        submitBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
        submitBtn.setBounds(340,540,195,45);
        submitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nameText = textName.getText();
                String ageText = textAge.getText();
                String phoneText = textPhone.getText();
                String genderText = comboBoxGender.getSelectedItem().toString();
                String noteText = textNote.getText();
                String userName = textID.getText();
                Boolean flag = customer.updateCustomer(userName, nameText, phoneText,genderText,ageText,  noteText,firstCheck);
				if(flag==true) {
					profilePanel.setVisible(false);
					homePanel.setVisible(true);
                    frame.setBounds(100,100,528,482);
					 table.setModel(new DefaultTableModel(customer.getCustomers(),headers));

		                TableColumnModel columnModels = table.getColumnModel();
//		                columnModels.getColumn(0).setPreferredWidth(10);
//		                columnModels.getColumn(1).setPreferredWidth(100);
//		                columnModels.getColumn(3).setPreferredWidth(50);
//		                columnModels.getColumn(4).setPreferredWidth(10);

		                tablePanel.repaint();
		                frame.repaint();
		                frame.validate();
				}else {
					
				}
			}
		});
        profilePanel.add(submitBtn);
        frame.getContentPane().add(profilePanel);


      
        //************************************* LOGIN 로그인 화면 ******************************************

        //로그인화면 ID label
        //로그인화면 PW label
        //로그인화면 ID textField
       
        JLabel id = new JLabel("아이디");
        id.setFont(new Font("나눔바른고딕 Light",Font.PLAIN,15));
        id.setBounds(93,190,85,40);

        textID.setFont(new Font("나눔바른고딕 Light", Font.PLAIN, 18));
//        textID.setBounds(87,280,300,47);
        textID.setBounds(88,225,315,47);
        
        textID.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(textID.getText().trim().equals(""))
					textID.setText("ID");
				else {
					
				}
						
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(textID.getText().trim().equals("ID"))
					textID.setText("");
				else {
					
				}
					
			}
		});
        
        //로그인화면 PW textField

        JLabel pw = new JLabel("비밀번호");
        pw.setFont(new Font("나눔바른고딕 Light",Font.PLAIN,15));
        pw.setBounds(93,285,85,40);
        
        JPasswordField textPW = new JPasswordField(10);
        textPW.setFont(new Font("나눔바른고딕 Light", Font.PLAIN, 18));
//        textPW.setBounds(140,300,220,35);
        textPW.setBounds(88,320,315,47);


        
        char passwordChar = textPW.getEchoChar();
        textPW.setEchoChar((char)0);
        textPW.setText("PW");
        textPW.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(textPW.getText().equals("")) {
					textPW.setText("PW");
					textPW.setEchoChar(passwordChar);
				} else {
					
				}
			}
			
			
//			@Override
//			public void focusGained(FocusEvent e) {
//				// TODO Auto-generated method stub
//				textPW.setText("");
//				textPW.setEchoChar(passwordChar);
//			}
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(textPW.getText().equals("PW")) {
					textPW.setText("");
					textPW.setEchoChar(passwordChar);
				} else {
					
				}
			}
		});
        


        //로그인화면 Login Button

        RoundedButton logBtn = new RoundedButton("LogIn");
        logBtn.setFont(new Font("나눔바른고딕 Light",Font.BOLD,15));
        logBtn.setBounds(153, 400, 185, 50);

        logBtn.addActionListener(new ActionListener() {

        	@Override
        	public void actionPerformed(ActionEvent e) {

        		//유저 ID PW로 로그인 방법
        		String userName = textID.getText();
        		current_id=userName;
        		String password="";
        		
        		char [] pwd = textPW.getPassword();
        		for (char cha : pwd) {
					Character.toString(cha);  //cha에 저장된 값 String으로 변환
					
					//pw 에 저장하기, pw 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
					password += (password.equals("")) ? ""+cha+"" : ""+cha+"";			
        			}
        			loginResult= customer.LoginCustomer(userName, password,loginResult);
        		if(loginResult ==0) { // 프로필 미등록자 로그인
        			welcomePanel.setVisible(false);
        			profilePanel.setVisible(true);
        			frame.setBounds(100,100,900,700);
//        			textID.setText("");
        			textPW.setText("");
        		}else if(loginResult ==1) { // 프로필 등록자 로그인
        			welcomePanel.setVisible(false);
        			homePanel.setVisible(true);
//                    textID.setText("");
                    textPW.setText("");
        		}else if(textID.getText().equals("admin")&&Arrays.equals(textPW.getPassword(),"admin".toCharArray())){ //관리자 로그인
                    current_id = "admin";
                    System.out.println("administrator");
                    welcomePanel.setVisible(false);
                    tablePanel.setVisible(true);
//                    textID.setText("");
                    textPW.setText("");
                    frame.setBounds(100,100,958,551);
//                    frame.pack();

                }else{  //로그인 실패 
        			JOptionPane.showMessageDialog(null, "로그인 실패");
        		}
        	}

        });

        welcomePanel.add(id);
        welcomePanel.add(textID);
        welcomePanel.add(pw);
        welcomePanel.add(textPW);
        welcomePanel.add(logBtn);
   //****************************************************************************************
        frame.setJMenuBar(menuBar(welcomePanel));
        frame.setSize(welcomePanel.getWidth(),welcomePanel.getHeight());
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //************************************* 상단 메뉴바 ******************************************
    	public JMenuBar menuBar(ImagePanel panel) {
    	
    	
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("나눔바른고딕 Light",Font.BOLD,13));
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.setFont(new Font("나눔바른고딕 Light",Font.BOLD,13));
        
        bar.add(fileMenu);
        bar.add(aboutMenu);

        JMenuItem openFile = new JMenuItem("Open");
        openFile.setFont(new Font("나눔바른고딕 Light",Font.BOLD,13));
        JMenuItem exit = new JMenuItem("Exit"); 
        exit.setFont(new Font("나눔바른고딕 Light",Font.BOLD,13));
        JMenuItem logout = new JMenuItem("logout");
        logout.setFont(new Font("나눔바른고딕 Light",Font.BOLD,13));
        
        fileMenu.add(openFile);
        fileMenu.add(logout);
        fileMenu.addSeparator();
        fileMenu.add(exit);
         

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
                
        logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				profilePanel.setVisible(false);
				homePanel.setVisible(false);
				tablePanel.setVisible(false);
                updatePanel.setVisible(false);
                createPanel.setVisible(false);
				panel.setVisible(true);
				frame.setSize(new Dimension(welcomePanel.getWidth(),welcomePanel.getHeight()));
				frame.setPreferredSize(new Dimension(welcomePanel.getWidth(),welcomePanel.getHeight()));
				frame.getContentPane().setLayout(null);
//                frame.setBounds(100, 100, 1000, 706);
			}
		});

        return bar;
    }

}

// 패널에 Image를 쉽게 넣기위해 생성한 ImagePanel
class ImagePanel extends JPanel{
    private Image img;
    public ImagePanel(Image img){
        this.img=img;
        setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
        setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
        setLayout(null);
    }
    public int getWidth(){ //이미지의 가로넓이 리턴
        return img.getWidth(null);
    }
    public int getHeight(){
        return img.getHeight(null);
    }
    public void paintComponent(Graphics g){
        g.drawImage(img,0,0,null);
    }
}

// logBtn 버튼 클래스

class RoundedButton extends JButton { 
	
	public RoundedButton() { 
		super(); 
		decorate(); 
	} 
	
	public RoundedButton(String text) { 
		super(text); 
		decorate(); 
	} 
	
	public RoundedButton(Action action) { 
		super(action); 
		decorate(); 
	} 
	
	public RoundedButton(Icon icon) { 
		super(icon); 
		decorate(); 
	} 
	
	public RoundedButton(String text, Icon icon) { 
		super(text, icon); 
		decorate(); 
	} 
	protected void decorate() { 
		setBorderPainted(false); 
		setOpaque(false); 
	} 
	@Override 
	protected void paintComponent(Graphics g) { 
		int width = getWidth(); 
		int height = getHeight(); 
		Graphics2D graphics = (Graphics2D) g; 
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		
		if (getModel().isArmed()) { //눌렀을때
			graphics.setColor(getBackground().WHITE);
		} 
		else if (getModel().isRollover()) { //커서 댔을때
//			graphics.setColor(getBackground().BLUE);
			graphics.setColor(new Color(80, 188, 223));
		} 
		else { //평상시
			graphics.setColor(getBackground().WHITE);
		} 
		
		graphics.fillRoundRect(0, 0, width, height, 10, 10); 
		FontMetrics fontMetrics = graphics.getFontMetrics(); 
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
		int textX = (width - stringBounds.width) / 2; 
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
		graphics.setColor(getForeground()); 
		
		if(getModel().isArmed()) {
//			graphics.setColor(getForeground().BLUE);
			graphics.setColor(new Color(80, 188, 223));
		}
		else if(getModel().isRollover()) {
			graphics.setColor(getForeground().WHITE);
		}
		else {
//			graphics.setColor(getForeground().BLUE);
			graphics.setColor(new Color(80, 188, 223));
			graphics.drawRoundRect(0, 0, width-1, height-1, 10, 10);
		}
		
		graphics.setFont(getFont()); 
		graphics.drawString(getText(), textX, textY); 
		graphics.dispose(); 
		super.paintComponent(g); 
	}
}
