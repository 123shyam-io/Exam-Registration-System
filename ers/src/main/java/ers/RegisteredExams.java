package ers;

import static com.mongodb.client.model.Filters.eq;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.awt.Color;

public class RegisteredExams extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	String username;
	private JTable table_2;
	public RegisteredExams(String username) {
		this.username=username;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 977, 344);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase db = mongoClient.getDatabase("ExamsDB");
		MongoCollection<Document> collection=db.getCollection("userExams");
		
		
		String[][] trans= new String[11][3];
		int i=1;
		trans[0][0]="Subject";
		trans[0][1]="Date";
		trans[0][2]="Max Marks";
		
		
		MongoCursor<Document> cursor = collection.find(eq("username",username)).iterator();
        while (cursor.hasNext() && i<10) {
        	Document f=cursor.next();
            trans[i][0]=f.get("subject").toString();
            trans[i][1]=f.get("Date").toString();
            
           
            trans[i][2]=f.get("maxMarks").toString();
            

            i+=1;
            
        }
        table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
		trans,
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_2.setBounds(154, 65, 715, 216);
		contentPane.add(table_2);
		
		
		
		
		
	}
}
