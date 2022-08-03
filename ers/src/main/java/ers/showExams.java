package ers;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class showExams extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public showExams() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1034, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase db = mongoClient.getDatabase("ExamsDB");
		MongoCollection<Document> collection=db.getCollection("exams");
		
		
		String[][] trans= new String[11][4];
		int i=1;
		trans[0][0]="Exam id";
		trans[0][1]="Subject";
		trans[0][2]="Time";
		trans[0][3]="Max Marks";
		
		MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext() && i<10) {
        	Document f=cursor.next();
            trans[i][0]=f.getObjectId("_id").toString();
            trans[i][1]=f.get("subject").toString();
            
           
            trans[i][2]=f.get("time").toString();
            trans[i][3]=f.get("maxMarks").toString();

            i+=1;
            
        }
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			trans,
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table_2.setBounds(190, 65, 456, 207);
		contentPane.add(table_2);
		
		
		
		
		
		
    	
		
	}
}
