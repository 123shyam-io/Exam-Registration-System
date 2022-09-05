package ers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	

	/**
	 * Create the frame.
	 */
	String username;
	public Welcome(final String username) {
		this.username=username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1127, 668);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 1113, 83);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EXAM REGISTRATION");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(372, 25, 334, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.GRAY);
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(0, 70, 212, 551);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnShowExams = new JButton("SHOW EXAMS");
		btnShowExams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showExams s= new showExams();
				s.setVisible(true);
			}
		});
		btnShowExams.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnShowExams.setForeground(new Color(255, 255, 255));
		btnShowExams.setBackground(new Color(0, 204, 204));
		btnShowExams.setBounds(10, 138, 192, 21);
		panel_1.add(btnShowExams);
		
		JButton btnExamRegistration = new JButton("EXAM REGISTRATION");
		btnExamRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				examRegistration eR=examRegistration.getInstance(username);
				eR.setVisible(true);
				
			}
		});
		btnExamRegistration.setForeground(Color.WHITE);
		btnExamRegistration.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExamRegistration.setBackground(new Color(0, 204, 204));
		btnExamRegistration.setBounds(10, 180, 192, 21);
		panel_1.add(btnExamRegistration);
		
		JButton btnRegisteredExams = new JButton("REGISTERED EXAMS");
		btnRegisteredExams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisteredExams r=new RegisteredExams(username);
				r.setVisible(true);
				
			}
		});
		btnRegisteredExams.setForeground(Color.WHITE);
		btnRegisteredExams.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegisteredExams.setBackground(new Color(0, 204, 204));
		btnRegisteredExams.setBounds(10, 221, 192, 21);
		panel_1.add(btnRegisteredExams);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBackground(new Color(0, 204, 204));
		btnLogout.setBounds(10, 530, 192, 21);
		panel_1.add(btnLogout);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(Color.YELLOW);
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Vasavi\\Desktop\\1.png"));
		lblNewLabel_1.setBounds(52, 22, 100, 100);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Vasavi\\Desktop\\3.jpg"));
		lblNewLabel_2.setBounds(222, 88, 881, 533);
		contentPane.add(lblNewLabel_2);
	}
}
