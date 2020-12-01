package project2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

@SuppressWarnings("serial")
public class Task6 extends JFrame {
	
	boolean run = true;
	StringWriter stringWriter;
	XMLOutputFactory xmlOutput;
	XMLStreamWriter xmlWriter;
	String Id;
	boolean found = false;
	File temp;
	
	private JPanel contentPane;
	private JTextField textLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Task6 frame = new Task6();
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
	public Task6() {
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
				ImageIcon originalImage = new ImageIcon(Task6.class.getResource("/not_available.png"));
        		Image copyImage = originalImage.getImage();
        		Image resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(), Image.SCALE_SMOOTH);
        		ImageIcon newImage = new ImageIcon(resizeImage);
        		
				try {
					String searchURL = "http://api.geonames.org/search?q=" + textLocation.getText() + "&maxRows=1&lang=en&username=eesa03";
					
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			        DocumentBuilder builder = factory.newDocumentBuilder();
			        Document doc = builder.parse(new URL(searchURL).openStream());
			        
			        Element root = doc.getDocumentElement();
			        
			        found = false;
			        if (root.getElementsByTagName("geonameId").getLength() != 0) {
			        	found = true;
			        	
						Id = root.getElementsByTagName("geonameId").item(0).getTextContent();
						String weatherURL = "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/" + Id;
						doc = builder.parse(new URL(weatherURL).openStream());
						root = doc.getDocumentElement();
						String title = root.getElementsByTagName("title").item(1).getTextContent();
						
						Pattern hyphenPattern = Pattern.compile(":\\s(.+?),");
						Matcher hyphenMatcher = hyphenPattern.matcher(title);
						
						String weather = "";
						while (hyphenMatcher.find()) {
							weather = hyphenMatcher.group(1);
						}
						
						if (weather.equals("Light Rain")) {
							originalImage = new ImageIcon(Task6.class.getResource("/light_rain.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Clear Sky")) {
							originalImage = new ImageIcon(Task6.class.getResource("/sunny.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Sunny Intervals")) {
							originalImage = new ImageIcon(Task6.class.getResource("/sunny.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Heavy Rain")) {
							originalImage = new ImageIcon(Task6.class.getResource("/heavy_rain.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Heavy Rain Showers")) {
							originalImage = new ImageIcon(Task6.class.getResource("/heavy_rain.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Light Cloud")) {
							originalImage = new ImageIcon(Task6.class.getResource("/light_clouds.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Strong Wind")) {
							originalImage = new ImageIcon(Task6.class.getResource("/windy.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Cloudy")) {
							originalImage = new ImageIcon(Task6.class.getResource("/cloudy.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Snow")) {
							originalImage = new ImageIcon(Task6.class.getResource("/snow.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Light Snow")) {
							originalImage = new ImageIcon(Task6.class.getResource("/snow.png"));
							copyImage = originalImage.getImage();
							resizeImage = copyImage.getScaledInstance(weatherIcon.getWidth(), weatherIcon.getHeight(),
									Image.SCALE_SMOOTH);
							newImage = new ImageIcon(resizeImage);
							weatherIcon.setIcon(newImage);
						}
						else if (weather.equals("Lightning")) {
							originalImage = new ImageIcon(Task6.class.getResource("/thunder.png"));
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
						
						String[] descriptionArray = { "Temperature", "Wind Direction", "Wind Speed", "Humidity", "Pressure", "Visibility" };
						
						int i = 0;
						while (hyphenMatcher.find()) {
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
					}
			        
					if(run == true) {
						String tempDir = System.getProperty("java.io.tmpdir");
						temp = new File(tempDir + "searches.xml");
						
						xmlOutput = XMLOutputFactory.newInstance();
						xmlWriter = xmlOutput.createXMLStreamWriter(new FileWriter(temp));
						
						xmlWriter.writeStartDocument();
						xmlWriter.writeStartElement("searches");
						
						run = false;
			        }
			        
					xmlWriter.writeStartElement("search");
			        
			        xmlWriter.writeStartElement("term");
			        xmlWriter.writeCharacters(textLocation.getText());
			        xmlWriter.writeEndElement();
			        
			        if(found) {
			        	xmlWriter.writeStartElement("found");
				        xmlWriter.writeCharacters("True");
				        xmlWriter.writeEndElement();
			        }
			        else {
			        	xmlWriter.writeStartElement("found");
				        xmlWriter.writeCharacters("False");
				        xmlWriter.writeEndElement();
			        }
			        
			        if(found) {
			        	xmlWriter.writeStartElement("geoNameID");
			        	xmlWriter.writeCharacters(Id);
			        	xmlWriter.writeEndElement();
			        }
			        else {
			        	xmlWriter.writeStartElement("geoNameID");
			        	xmlWriter.writeCharacters("Not found");
			        	xmlWriter.writeEndElement();
			        }
			        
			        xmlWriter.writeEndElement();
			        
			        if(found == false) {
			        	weatherIcon.setIcon(null);
			        	textArea.setText("");
			        	
			        	lblTemp.setText("Temperature: ");
			        	lblWindDir.setText("Wind Direction: ");
			        	lblWindSpeed.setText("Wind Speed: ");
			        	lblHumidity.setText("Humidity: ");
			        	lblPressure.setText("Pressure: ");
			        	lblVisibility.setText("Visibility: ");
			        }
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
		
		JLabel lblTempDir = new JLabel("Temporary XML Directory: " + System.getProperty("java.io.tmpdir"));
		lblTempDir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTempDir.setBounds(57, 304, 658, 20);
		contentPane.add(lblTempDir);
		
		JButton btnExit = new JButton("Save & Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					xmlWriter.writeEndElement();
					xmlWriter.writeEndDocument();
					xmlWriter.flush();
					xmlWriter.close();
					
					DocumentBuilderFactory formatFactory = DocumentBuilderFactory.newInstance();
			        formatFactory.setValidating(false);
			        DocumentBuilder formatBuilder = formatFactory.newDocumentBuilder();
			        
			        Document doc = formatBuilder.parse(new FileInputStream(temp));
			        Transformer tf = TransformerFactory.newInstance().newTransformer();
			        tf.setOutputProperty(OutputKeys.INDENT, "yes");
			        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			        tf.transform(new DOMSource(doc), new StreamResult(temp));
					
					Runtime.getRuntime().exec("explorer.exe /select," + (System.getProperty("java.io.tmpdir")  + "searches.xml"));
					System.exit(0);
				} catch (XMLStreamException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExit.setBounds(57, 271, 145, 23);
		contentPane.add(btnExit);
		
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit_1.setBounds(208, 271, 111, 23);
		contentPane.add(btnExit_1);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("explorer.exe /select," + (System.getProperty("java.io.tmpdir")  + "searches.xml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(10, 303, 39, 23);
		contentPane.add(button);
		
		
	}
}
