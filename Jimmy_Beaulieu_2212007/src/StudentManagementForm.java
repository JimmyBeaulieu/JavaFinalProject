import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StudentManagementForm extends JFrame {

	private JPanel contentPane;
	Connection connection = null;
	private JTextField idTextBox;
	private JTextField studentNameTextBox;
	private JTextField feesTextBox;
	private JTextField DOBTextBox;
	private JTextField searchTextBox;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManagementForm frame = new StudentManagementForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentManagementForm() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1144, 803);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		connection = SqliteConnection.dbConnection();
		
		
		try {
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(20, 125, 186, 189);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gytim\\eclipse-workspace\\Jimmy_Beaulieu_2212007\\img\\download.png"));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("LaSalle College - Student Dashboeard");
		lblNewLabel_1.setBounds(20, 11, 932, 50);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("International School - Montreal Canada");
		lblNewLabel_2.setBounds(187, 72, 637, 37);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Student Information");
		lblNewLabel_2_1.setBounds(382, 125, 258, 37);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("ID:");
		lblNewLabel_2_1_1.setBounds(227, 195, 258, 37);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Student Name:");
		lblNewLabel_2_1_1_1.setBounds(237, 243, 258, 37);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Fees:");
		lblNewLabel_2_1_1_2.setBounds(227, 291, 258, 37);
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblNewLabel_2_1_1_2);
		
		JLabel lblNewLabel_2_1_1_3 = new JLabel("Date Of Birth:");
		lblNewLabel_2_1_1_3.setBounds(227, 339, 258, 37);
		lblNewLabel_2_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblNewLabel_2_1_1_3);
		
		idTextBox = new JTextField();
		idTextBox.setBounds(462, 195, 306, 34);
		contentPane.add(idTextBox);
		idTextBox.setColumns(10);
		
		studentNameTextBox = new JTextField();
		studentNameTextBox.setBounds(462, 243, 306, 34);
		studentNameTextBox.setColumns(10);
		contentPane.add(studentNameTextBox);
		
		feesTextBox = new JTextField();
		feesTextBox.setBounds(462, 291, 306, 34);
		feesTextBox.setColumns(10);
		contentPane.add(feesTextBox);
		
		DOBTextBox = new JTextField();
		DOBTextBox.setBounds(462, 339, 306, 34);
		DOBTextBox.setColumns(10);
		contentPane.add(DOBTextBox);
		
		JButton newButton = new JButton("New");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into StudentInfo(ID, Name, Fees, DateOfBirth) values (?, ?, ?, ?)";
					PreparedStatement pst = connection.prepareStatement(query);

					pst.setString(1, idTextBox.getText());
					pst.setString(2, studentNameTextBox.getText());
					pst.setString(3, feesTextBox.getText());
					pst.setString(4, DOBTextBox.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Student added!");
					pst.close();
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
				RefreshTable();
				RefreshFields();
				
			
			}
		});
		newButton.setBounds(959, 166, 159, 66);
		newButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		newButton.setIcon(new ImageIcon("C:\\Users\\gytim\\eclipse-workspace\\Jimmy_Beaulieu_2212007\\img\\button_violet_delete.png"));
		contentPane.add(newButton);
		
		JButton insertButton = new JButton("Insert...");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Why don't you go drink a nice glass of water?");
			}
		});
		insertButton.setBounds(959, 243, 159, 66);
		insertButton.setIcon(new ImageIcon("C:\\Users\\gytim\\eclipse-workspace\\Jimmy_Beaulieu_2212007\\img\\button_blue_add.png"));
		insertButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(insertButton);
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "update StudentInfo set Name='" + studentNameTextBox.getText() + "', fees='" + feesTextBox.getText() + "', DateOfBirth='" + DOBTextBox.getText()+"' where ID='" + idTextBox.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Student updated!");
					pst.close();
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
				RefreshTable();
				RefreshFields();
				
			
			}
		});
		updateButton.setBounds(959, 321, 159, 66);
		updateButton.setIcon(new ImageIcon("C:\\Users\\gytim\\eclipse-workspace\\Jimmy_Beaulieu_2212007\\img\\button_pink_close.png"));
		updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(updateButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int action = JOptionPane.showConfirmDialog(null, "Are you sure?", "Deleting...", JOptionPane.YES_NO_OPTION);
				if(action == 0) {
					try {
						String query = "delete from StudentInfo where ID='" + idTextBox.getText() + "'";
						PreparedStatement pst = connection.prepareStatement(query);					
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Student Removed!");
						pst.close();
					}
					catch(Exception exception) {
						exception.printStackTrace();
					}
					RefreshTable();
					RefreshFields();
				}
				else {
					RefreshFields();
				}
			
			}
		});
		btnDelete.setBounds(959, 398, 159, 66);
		btnDelete.setIcon(new ImageIcon("C:\\Users\\gytim\\eclipse-workspace\\Jimmy_Beaulieu_2212007\\img\\button_red_stop.png"));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel_2_1_1_3_1 = new JLabel("Search Student By Name:");
		lblNewLabel_2_1_1_3_1.setBounds(106, 441, 346, 37);
		lblNewLabel_2_1_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblNewLabel_2_1_1_3_1);
		
		searchTextBox = new JTextField();
		searchTextBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String query = "select * from StudentInfo where Name = ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, searchTextBox.getText());
					
					ResultSet result = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(result));
					result.close();
					pst.close();
				}
				catch(Exception exc) {
					exc.printStackTrace();
				}
			
			}
		});
		searchTextBox.setBounds(462, 441, 306, 34);
		searchTextBox.setColumns(10);
		contentPane.add(searchTextBox);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(116, 508, 836, 219);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					int row = table.getSelectedRow();
					String ID = (table.getModel().getValueAt(row, 0)).toString();
					
					//JOptionPane.showMessageDialog(null, ID);
					
					String query = "select * from StudentInfo where ID='" + ID + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet result = pst.executeQuery();
					
					while(result.next()) {
						idTextBox.setText(result.getString("ID"));
						studentNameTextBox.setText(result.getString("Name"));
						feesTextBox.setText(result.getString("Fees"));
						DOBTextBox.setText(result.getString("DateOfBirth"));
												
					}
					result.close();
					pst.close();
					
				}catch(Exception error) {
					error.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(table);
		RefreshTable();
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public void RefreshTable() {
		try {
			String query = "select * from StudentInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			
			ResultSet result = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(result));
			
			result.close();
			pst.close();
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	public void RefreshFields() {
		idTextBox.setText(null);
		studentNameTextBox.setText(null);
		feesTextBox.setText(null);
		DOBTextBox.setText(null);
	}
}
