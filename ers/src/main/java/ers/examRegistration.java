package ers;

import static com.mongodb.client.model.Filters.eq;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class examRegistration extends JFrame {

	private JPanel contentPane;
	private static examRegistration obj;

	String username;
	private JTextField subjectTextField;
	private JTextField examDateTextField;
	private JTextField maxMarksTextField;
//	private JTextArea dateTextArea;
	private examRegistration(final String username) {
		this.username=username;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 835, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter subject");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(83, 137, 136, 13);
		panel.add(lblNewLabel);
		
		subjectTextField = new JTextField();
		subjectTextField.setBounds(198, 133, 261, 25);
		panel.add(subjectTextField);
		subjectTextField.setColumns(10);
		
		JLabel lblExamDate = new JLabel("Exam date");
		lblExamDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblExamDate.setBounds(83, 204, 136, 13);
		panel.add(lblExamDate);
		
		examDateTextField = new JTextField();
		examDateTextField.setColumns(10);
		examDateTextField.setBounds(198, 200, 261, 25);
		panel.add(examDateTextField);
		examDateTextField.setEditable(false);
		
		JLabel lblMaxMarks = new JLabel("Max Marks");
		lblMaxMarks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaxMarks.setBounds(83, 263, 136, 13);
		panel.add(lblMaxMarks);
		
		maxMarksTextField = new JTextField();
		maxMarksTextField.setColumns(10);
		maxMarksTextField.setBounds(198, 262, 261, 25);
		panel.add(maxMarksTextField);
		maxMarksTextField.setEditable(false);
//		dateTextArea = new JTextArea();
//		dateTextArea.setBounds(263, 345, 196, 36);
//		panel.add(dateTextArea);
		
		
		
		
		JButton checkBtn = new JButton("Check");
		checkBtn.setForeground(Color.WHITE);
		checkBtn.setBackground(Color.BLUE);
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("resource")
				MongoClient mongoClient = new MongoClient("localhost",27017);
				MongoDatabase db = mongoClient.getDatabase("ExamsDB");
				MongoCollection<Document> collection=db.getCollection("exams");
				String subject=subjectTextField.getText();
				Document d=collection.find(eq("subject",subject)).first();
				
				
				
				if (d==null) {
					JOptionPane.showMessageDialog(new JFrame(), "Enter valid subject","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					examDateTextField.setText(d.get("time").toString());
					maxMarksTextField.setText(d.getString("maxMarks"));
					//dateTextArea.setText(d.get("time").toString());
					
					  
					
				}
							
				
			}
		});
		checkBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBtn.setBounds(514, 135, 85, 21);
		panel.add(checkBtn);
		JButton enrollBtn = new JButton("Enroll");
		enrollBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		enrollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String su=subjectTextField.getText();
			    String date=examDateTextField.getText();
				String maxMarks=maxMarksTextField.getText();
				
				@SuppressWarnings("resource")
				MongoClient mongoClient1 = new MongoClient("localhost",27017);
				MongoDatabase db1 = mongoClient1.getDatabase("ExamsDB");
				MongoCollection<Document> collection=db1.getCollection("exams");
				Document d=collection.find(eq("subject",su)).first();
								
				if(d!=null) {
					String date1 = d.get("time").toString();
					String maxMarks1 = d.get("maxMarks").toString();
					
					@SuppressWarnings("resource")
					MongoClient mongoClient2 = new MongoClient("localhost",27017);
					MongoDatabase db2 = mongoClient2.getDatabase("ExamsDB");
					MongoCollection<Document> registeredExams=db2.getCollection("userExams");
					Document d2 = registeredExams.find(eq("subject",su)).first();
					if(d2 == null) {
						if(date1.contentEquals(date) == false) {
							JOptionPane.showMessageDialog(new JFrame(), "Enter valid date","Error",JOptionPane.ERROR_MESSAGE);
//							System.out.println(date1);
//							System.out.println(date);
						}
						else if(maxMarks1.equals(maxMarks) == false) {
							JOptionPane.showMessageDialog(new JFrame(), "Enter valid maximum marks","Error",JOptionPane.ERROR_MESSAGE);
						}
						else {
							@SuppressWarnings("resource")
							MongoClient mongoClient = new MongoClient("localhost",27017);
							MongoDatabase db = mongoClient.getDatabase("ExamsDB");
							MongoCollection<Document> examCollection=db.getCollection("userExams");
							
							Document doc =new Document("username",username);
							doc.append("subject",su);
							doc.append("Date",date);
							doc.append("maxMarks",maxMarks);
							
							examCollection.insertOne(doc);
							JOptionPane.showMessageDialog(new JFrame(), "Registartion successful","exam added Successfully",JOptionPane.OK_OPTION);
						}
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "already registered for this exam","Error",JOptionPane.ERROR_MESSAGE);
					}
					
					
					
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Enter valid subject details","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		enrollBtn.setBounds(113, 357, 85, 21);
		panel.add(enrollBtn);
		
	}
	public static examRegistration getInstance(final String username) {
		if(obj==null) {
			obj = new examRegistration(username);
			return obj;
		}
		return obj;
	}
}
