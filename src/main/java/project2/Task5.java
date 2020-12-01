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
import java.awt.Font;

@SuppressWarnings("serial")
public class Task5 extends JFrame {

	private JPanel contentPane;
	private JTextField textLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Task5 frame = new Task5();
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
	public Task5() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textLocation = new JTextField();
		textLocation.setBounds(57, 39, 262, 20);
		contentPane.add(textLocation);
		textLocation.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(28, 221, 324, 28);
		contentPane.add(textArea);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(160, 23, 50, 14);
		contentPane.add(lblLocation);
		
		final JLabel weatherIcon = new JLabel("");
		weatherIcon.setBounds(141, 128, 87, 82);
		contentPane.add(weatherIcon);
		
		final JLabel lblTemp = new JLabel("Tempearture: ");
		lblTemp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTemp.setBounds(401, 58, 284, 20);
		contentPane.add(lblTemp);
		
		final JLabel lblWindDir = new JLabel("Wind Direction: ");
		lblWindDir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWindDir.setBounds(401, 102, 284, 20);
		contentPane.add(lblWindDir);
		
		final JLabel lblWindSpeed = new JLabel("Wind Speed: ");
		lblWindSpeed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWindSpeed.setBounds(401, 148, 284, 20);
		contentPane.add(lblWindSpeed);
		
		final JLabel lblHumidity = new JLabel("Humidity: ");
		lblHumidity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHumidity.setBounds(401, 196, 284, 20);
		contentPane.add(lblHumidity);
		
		final JLabel lblPressure = new JLabel("Pressure: ");
		lblPressure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPressure.setBounds(401, 242, 284, 20);
		contentPane.add(lblPressure);
		
		final JLabel lblVisibility = new JLabel("Visibility: ");
		lblVisibility.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVisibility.setBounds(401, 287, 284, 20);
		contentPane.add(lblVisibility);
		
		JButton btnForecast = new JButton("Forecast");
		btnForecast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon originalImage = new ImageIcon(Task5.class.getResource("/not_available.png"));
        		Image copyImage = originalImage.getImage();
        		Image resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(), Image.SCALE_SMOOTH);
        		ImageIcon newImage = new ImageIcon(resizeImage);
        		
				try {
					String searchURL = "http://api.geonames.org/search?q=" + textLocation.getText() + "&maxRows=1&lang=en&username=eesa03";
					
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			        DocumentBuilder builder = factory.newDocumentBuilder();
			        Document doc = builder.parse(new URL(searchURL).openStream());
			        
			        Element root = doc.getDocumentElement();
			        
			        String Id = root.getElementsByTagName("geonameId").item(0).getTextContent();
			        
			        String weatherURL = "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/" + Id;
			        
			        doc = builder.parse(new URL(weatherURL).openStream());
			        
			        root = doc.getDocumentElement();
			        
			        String title = root.getElementsByTagName("title").item(1).getTextContent();
			        
			        Pattern hyphenPattern = Pattern.compile(":\\s(.+?),");
			        Matcher hyphenMatcher = hyphenPattern.matcher(title);
			        
			        String weather = "";
			        
			        while(hyphenMatcher.find()) {
			        	weather = hyphenMatcher.group(1);
			        }
			        
			        if (weather.equals("Light Rain")) {
						originalImage = new ImageIcon(Task5.class.getResource("/light_rain.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Clear Sky")) {
						originalImage = new ImageIcon(Task5.class.getResource("/sunny.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Sunny Intervals")) {
						originalImage = new ImageIcon(Task5.class.getResource("/sunny.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Heavy Rain")) {
						originalImage = new ImageIcon(Task5.class.getResource("/heavy_rain.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Heavy Rain Showers")) {
						originalImage = new ImageIcon(Task5.class.getResource("/heavy_rain.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Light Cloud")) {
						originalImage = new ImageIcon(Task5.class.getResource("/light_clouds.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Strong Wind")) {
						originalImage = new ImageIcon(Task5.class.getResource("/windy.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Cloudy")) {
						originalImage = new ImageIcon(Task5.class.getResource("/cloudy.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Snow")) {
						originalImage = new ImageIcon(Task5.class.getResource("/snow.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Light Snow")) {
						originalImage = new ImageIcon(Task5.class.getResource("/snow.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else if (weather.equals("Lightning")) {
						originalImage = new ImageIcon(Task5.class.getResource("/thunder.png"));
						copyImage = originalImage.getImage();
						resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
								Image.SCALE_SMOOTH);
						newImage = new ImageIcon(resizeImage);
						weatherIcon.setIcon(newImage);
					}
					else {
						weatherIcon.setIcon(newImage);
					}
			        
			        String description = root.getElementsByTagName("description").item(1).getTextContent();
			        
			        hyphenPattern = Pattern.compile(":\\s(.+?)(?:,|$)");
			        hyphenMatcher = hyphenPattern.matcher(description);
			        
			        String[] descriptionArray = {"Temperature", "Wind Direction", "Wind Speed", "Humidity", "Pressure", "Visibility"};
			        int i = 0;
			        
			        while(hyphenMatcher.find()) {
			        	descriptionArray[i] = hyphenMatcher.group(1);
			        	i++;
			        }
			        
			        lblTemp.setText("Temperature: " + descriptionArray[0]);
			        lblWindDir.setText("Wind Direction: " + descriptionArray[1]);
			        lblWindSpeed.setText("Wind Speed: " + descriptionArray[2]);
			        lblHumidity.setText("Humidity: " + descriptionArray[3]);
			        lblPressure.setText("Pressure: " + descriptionArray[4]);
			        lblVisibility.setText("Visibility: " + descriptionArray[5]);
			        
			        textArea.setText(title);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnForecast.setBounds(141, 87, 89, 23);
		contentPane.add(btnForecast);
		
		JLabel lblTitle = new JLabel("Additional Information");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(461, 18, 177, 20);
		contentPane.add(lblTitle);
		
		
		
		
	}
}
