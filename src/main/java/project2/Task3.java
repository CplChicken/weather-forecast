package project2;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

@SuppressWarnings("serial")
public class Task3 extends JFrame {

	private JPanel contentPane;
	private JTextField textURL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Task3 frame = new Task3();
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
	public Task3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textURL = new JTextField();
		textURL.setBounds(57, 39, 262, 20);
		contentPane.add(textURL);
		textURL.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(28, 221, 324, 103);
		contentPane.add(textArea);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(177, 23, 29, 14);
		contentPane.add(lblUrl);
		
		final JLabel weatherIcon = new JLabel("");
		weatherIcon.setBounds(141, 128, 87, 82);
		contentPane.add(weatherIcon);
		
		JButton btnForecast = new JButton("Forecast");
		btnForecast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon originalImage = new ImageIcon(Task3.class.getResource("/not_available.png"));
        		Image copyImage = originalImage.getImage();
        		Image resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(), Image.SCALE_SMOOTH);
        		ImageIcon newImage = new ImageIcon(resizeImage);
        		
				try {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			        DocumentBuilder builder = factory.newDocumentBuilder();
			        Document doc = builder.parse(new URL(textURL.getText()).openStream());
			        
			        Element root = doc.getDocumentElement();
			        
			        String title = root.getElementsByTagName("title").item(1).getTextContent();
			        
			        Pattern hyphenPattern = Pattern.compile(":\\s(.+?),");
			        Matcher hyphenMatcher = hyphenPattern.matcher(title);
			        
			        String weather = "";
			        
			        while(hyphenMatcher.find()) {
			        	weather = hyphenMatcher.group(1);
			        }
			        
			        if (weather.equals("Light Rain")) {
						originalImage = new ImageIcon(Task3.class.getResource("/light_rain.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Clear Sky")) {
						originalImage = new ImageIcon(Task3.class.getResource("/sunny.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Sunny Intervals")) {
						originalImage = new ImageIcon(Task3.class.getResource("/sunny.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Heavy Rain")) {
						originalImage = new ImageIcon(Task3.class.getResource("/heavy_rain.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Heavy Rain Showers")) {
						originalImage = new ImageIcon(Task3.class.getResource("/heavy_rain.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Light Cloud")) {
						originalImage = new ImageIcon(Task3.class.getResource("/light_clouds.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Strong Wind")) {
						originalImage = new ImageIcon(Task3.class.getResource("/windy.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Cloudy")) {
						originalImage = new ImageIcon(Task3.class.getResource("/cloudy.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Snow")) {
						originalImage = new ImageIcon(Task3.class.getResource("/snow.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Light Snow")) {
						originalImage = new ImageIcon(Task3.class.getResource("/snow.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Lightning")) {
						originalImage = new ImageIcon(Task3.class.getResource("/thunder.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else {
						weatherIcon.setIcon(newImage);
					}
			        textArea.setText(title);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnForecast.setBounds(141, 87, 89, 23);
		contentPane.add(btnForecast);
		
		
	}
}
