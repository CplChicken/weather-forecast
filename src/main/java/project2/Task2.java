package project2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Task2 extends JFrame {

	private JPanel contentPane;
	private JTextField textURL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Task2 frame = new Task2();
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
	public Task2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textURL = new JTextField();
		textURL.setBounds(57, 39, 262, 20);
		contentPane.add(textURL);
		textURL.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(28, 136, 324, 103);
		contentPane.add(textArea);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(177, 23, 29, 14);
		contentPane.add(lblUrl);
		
		JButton btnForecast = new JButton("Forecast");
		btnForecast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			        DocumentBuilder builder = factory.newDocumentBuilder();
			        Document doc = builder.parse(new URL(textURL.getText()).openStream());
			        
			        Element root = doc.getDocumentElement();
			        
			        textArea.setText(root.getElementsByTagName("title").item(1).getTextContent());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnForecast.setBounds(141, 87, 89, 23);
		contentPane.add(btnForecast);
	}
}
