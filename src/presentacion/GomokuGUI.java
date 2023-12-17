/**
 * Clase que representa la interfaz gráfica del juego Gomoku.
 * Contiene el método principal (main) y todos los componentes que se muestran en la interfaz.
 * Además, implementa la lógica del juego y maneja eventos de acción.
 * 
 * @author Chicuazuque-Sierra
 * @version 1.0 25/11/2023
 */
package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import dominio.Board;
import dominio.Gomoku;
import dominio.HumanoPlayer;
import dominio.ModoLimiteTiempo;

public class GomokuGUI extends JFrame implements ActionListener {
	private static GomokuGUI gomokuGUI;

	private static JPanel menuPrincipal;
	private static JButton normal;
	private static JButton timeLtiempo;
	private static JButton limiteFichas;
	private static JButton exitButton;

	private static JPanel menu;
	private static TitleComponent title;
	private static JButton play2PlayerButton;
	private static JButton playWithComputerButton;
	private static JButton exitButton2;

	private static JPanel panel;
	private static BoardVisual BoardVisual;
	private static InfoComponent infoComponent;
	private static ButtonRestart buttonComponent;
	private static TimerComponent timerComponent;
	private static TimerDownComponent timerDownComponent;
	private static String firstName;
	private static String secondName;
	private static TypeOfRock typeRock;

	private static JPanel modoMaquinas;
	private static JButton maquinaMiedosa;
	private static JButton maquinaExperta;
	private static JButton maquinaAgresiva;
	private static JButton exitButton3;

	public static Gomoku gomoku;

	/**
	 * Constructor de la clase GomokuGUI. Configura la apariencia inicial de la
	 * interfaz.
	 */
	public GomokuGUI() {
		setTitle("Gomoku");
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setBackground(Color.BLACK);
		constructMainDisplay();
		add(menuPrincipal, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		gomoku = new Gomoku("player1", "HumanoPlayer", "player2", "HumanoPlayer", "Normal");
		setVisible(true);
	}

	/**
	 * Configura y construye los componentes principales de la interfaz.
	 */
	public void constructMainDisplay() {
		Dimension size = new Dimension(800, 750);

		menuPrincipal = new JPanel();
		menuPrincipal.setPreferredSize(size);
		menuPrincipal.setLayout(null);
		menuPrincipal.setBackground(Color.BLACK);

		title = new TitleComponent();
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new GridLayout(5, 1));
		contenedor.setBackground(Color.BLACK);
		menuPrincipal.add(contenedor);
		contenedor.setPreferredSize(size);

		normal = new JButton("Normal");
		normal.setForeground(Color.WHITE);
		normal.setFont(new Font("SansSerif", Font.PLAIN, 30));
		normal.setOpaque(false);
		normal.setContentAreaFilled(false);
		normal.addActionListener(this);
		normal.setFocusPainted(false);

		timeLtiempo = new JButton("Tiempo");
		timeLtiempo.setForeground(Color.WHITE);
		timeLtiempo.setFont(new Font("SansSerif", Font.PLAIN, 30));
		timeLtiempo.setOpaque(false);
		timeLtiempo.setContentAreaFilled(false);
		timeLtiempo.addActionListener(this);
		timeLtiempo.setFocusPainted(false);

		limiteFichas = new JButton("Limite de fichas");
		limiteFichas.setForeground(Color.WHITE);
		limiteFichas.setFont(new Font("SansSerif", Font.PLAIN, 30));
		limiteFichas.setOpaque(false);
		limiteFichas.setContentAreaFilled(false);
		limiteFichas.addActionListener(this);
		limiteFichas.setFocusPainted(false);

		exitButton = new JButton("Salir");
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.addActionListener(this);
		exitButton.setFocusPainted(false);

		contenedor.add(title);
		contenedor.add(normal);
		contenedor.add(timeLtiempo);
		contenedor.add(limiteFichas);
		contenedor.add(exitButton);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		contenedor.setBounds((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2, size.width,
				size.height);

		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setBackground(Color.BLACK);
		JLabel about1 = new JLabel("Creado por Diego Chicuazuque y Yeltzyn Sierra");
		about1.setForeground(Color.WHITE);
		about1.setFont(new Font("SansSerif", Font.PLAIN, 10));
		panel.add(about1);
		JLabel about2 = new JLabel("Proyecto de POOB 2023-2");
		about2.setForeground(Color.WHITE);
		about2.setFont(new Font("SansSerif", Font.PLAIN, 10));
		panel.add(about2);
	}

	public void constructModePlayersDisplay() {
		Dimension size = new Dimension(800, 600);
		menu = new JPanel();
		menu.setPreferredSize(size);
		menu.setLayout(null);
		menu.setBackground(Color.BLACK);

		JPanel container = new JPanel();
		container.setLayout(new GridLayout(5, 1));
		container.setBackground(Color.BLACK);
		menu.add(container);
		container.setPreferredSize(size);

		play2PlayerButton = new JButton("Jugador vs Jugador");
		play2PlayerButton.setForeground(Color.WHITE);
		play2PlayerButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
		play2PlayerButton.setOpaque(false);
		play2PlayerButton.setContentAreaFilled(false);
		play2PlayerButton.addActionListener(this);
		play2PlayerButton.setFocusPainted(false);

		playWithComputerButton = new JButton("Jugador vs Computador");
		playWithComputerButton.setForeground(Color.WHITE);
		playWithComputerButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
		playWithComputerButton.setOpaque(false);
		playWithComputerButton.setContentAreaFilled(false);
		playWithComputerButton.addActionListener(this);
		playWithComputerButton.setFocusPainted(false);

		exitButton2 = new JButton("Salir");
		exitButton2.setForeground(Color.WHITE);
		exitButton2.setFont(new Font("SansSerif", Font.PLAIN, 30));
		exitButton2.setOpaque(false);
		exitButton2.setContentAreaFilled(false);
		exitButton2.addActionListener(this);
		exitButton2.setFocusPainted(false);

		container.add(title);
		container.add(play2PlayerButton);
		container.add(playWithComputerButton);
		container.add(exitButton2);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		container.setBounds((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2, size.width,
				size.height);

		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setBackground(Color.BLACK);
		JLabel about1 = new JLabel("Creado por Diego Chicuazuque y Yeltzyn Sierra");
		about1.setForeground(Color.WHITE);
		about1.setFont(new Font("SansSerif", Font.PLAIN, 10));
		panel.add(about1);
		JLabel about2 = new JLabel("Proyecto de POOB 2023-2");
		about2.setForeground(Color.WHITE);
		about2.setFont(new Font("SansSerif", Font.PLAIN, 10));
		panel.add(about2);
	}

	public void constructModeMachine() {
		Dimension size = new Dimension(800, 600);
		modoMaquinas = new JPanel();
		modoMaquinas.setPreferredSize(size);
		modoMaquinas.setLayout(null);
		modoMaquinas.setBackground(Color.BLACK);

		JPanel container2 = new JPanel();
		container2.setLayout(new GridLayout(5, 1));
		container2.setBackground(Color.BLACK);
		modoMaquinas.add(container2);
		container2.setPreferredSize(size);

		maquinaAgresiva = new JButton("Maquina Agresiva");
		maquinaAgresiva.setForeground(Color.WHITE);
		maquinaAgresiva.setFont(new Font("SansSerif", Font.PLAIN, 30));
		maquinaAgresiva.setOpaque(false);
		maquinaAgresiva.setContentAreaFilled(false);
		maquinaAgresiva.addActionListener(this);
		maquinaAgresiva.setFocusPainted(false);

		maquinaExperta = new JButton("Maquina Experta");
		maquinaExperta.setForeground(Color.WHITE);
		maquinaExperta.setFont(new Font("SansSerif", Font.PLAIN, 30));
		maquinaExperta.setOpaque(false);
		maquinaExperta.setContentAreaFilled(false);
		maquinaExperta.addActionListener(this);
		maquinaExperta.setFocusPainted(false);

		maquinaMiedosa = new JButton("Maquina Miedosa");
		maquinaMiedosa.setForeground(Color.WHITE);
		maquinaMiedosa.setFont(new Font("SansSerif", Font.PLAIN, 30));
		maquinaMiedosa.setOpaque(false);
		maquinaMiedosa.setContentAreaFilled(false);
		maquinaMiedosa.addActionListener(this);
		maquinaMiedosa.setFocusPainted(false);

		exitButton3 = new JButton("Salir");
		exitButton3.setForeground(Color.WHITE);
		exitButton3.setFont(new Font("SansSerif", Font.PLAIN, 30));
		exitButton3.setOpaque(false);
		exitButton3.setContentAreaFilled(false);
		exitButton3.addActionListener(this);
		exitButton3.setFocusPainted(false);

		container2.add(title);
		container2.add(maquinaAgresiva);
		container2.add(maquinaExperta);
		container2.add(maquinaMiedosa);
		container2.add(exitButton3);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		container2.setBounds((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2, size.width,
				size.height);

		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setBackground(Color.BLACK);
		JLabel about1 = new JLabel("Creado por Diego Chicuazuque y Yeltzyn Sierra");
		about1.setForeground(Color.WHITE);
		about1.setFont(new Font("SansSerif", Font.PLAIN, 10));
		panel.add(about1);
		JLabel about2 = new JLabel("Proyecto de POOB 2023-2");
		about2.setForeground(Color.WHITE);
		about2.setFont(new Font("SansSerif", Font.PLAIN, 10));
		panel.add(about2);
	}

	/**
	 * Maneja eventos de acción para botones y realiza acciones correspondientes.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton || e.getSource() == exitButton2) {
			int optionValue = JOptionPane.showConfirmDialog(null, "Estas seguro de salir del juego?", "Confirm Exit",
					JOptionPane.OK_CANCEL_OPTION);
			if (optionValue == 0) {
				System.exit(0);
			}
		} else if (e.getSource() == normal || e.getSource() == timeLtiempo || e.getSource() == limiteFichas) {
			remove(menuPrincipal);
			remove(panel);

			constructModePlayersDisplay();

			add(menu, BorderLayout.CENTER);
			add(panel, BorderLayout.SOUTH);

			revalidate();

			if (e.getSource() == normal) {
				Gomoku.getInstance().setModoDeJuego("Normal");
			} else if (e.getSource() == timeLtiempo) {
				Gomoku.getInstance().setModoDeJuego("Tiempo");
			} else if (e.getSource() == limiteFichas) {
				Gomoku.getInstance().setModoDeJuego("Limite de fichas");
			}
			remove(menuPrincipal);
			remove(panel);
			constructMainDisplay();
		} else if (e.getSource() == play2PlayerButton || e.getSource() == playWithComputerButton) {
			remove(menu);
			remove(panel);

			if (e.getSource() == play2PlayerButton) {
				Gomoku.setPlayer1("HumanoPlayer", "Jugador 1");
				Gomoku.setPlayer2("HumanoPlayer", "Jugador 2");
				Dimension boardSize = new Dimension(600, 600);

				panel = new JPanel();
				add(panel, BorderLayout.CENTER);
				panel.setPreferredSize(boardSize);
				panel.setBackground(Color.BLACK);
				panel.setLayout(null);

				BoardVisual = new BoardVisual();
				panel.add(BoardVisual);
				BoardVisual.setPreferredSize(boardSize);

				infoComponent = new InfoComponent();
				timerDownComponent = new TimerDownComponent();
				timerComponent = new TimerComponent();
				buttonComponent = new ButtonRestart();
				if (Gomoku.getInstance().getModoDeJuego() instanceof ModoLimiteTiempo) {

					add(timerDownComponent, BorderLayout.WEST);
				} else {

					add(timerComponent, BorderLayout.WEST);
				}
				typeRock = new TypeOfRock();
				add(infoComponent, BorderLayout.NORTH);
				add(buttonComponent, BorderLayout.SOUTH);

				add(typeRock, BorderLayout.EAST);

				revalidate();
				BoardVisual.setBounds((panel.getWidth() - boardSize.width) / 2,
						(panel.getHeight() - boardSize.height) / 2, boardSize.width, boardSize.height);

				revalidate();
				welcomeScreen();
			} else if (e.getSource() == playWithComputerButton) {
				Gomoku.setPlayer1("HumanoPlayer", "Jugador 1");
				Gomoku.setPlayer2("MaquinaExpertaPlayer", "Computador");
				constructModeMachine();
				add(modoMaquinas, BorderLayout.CENTER);
				add(panel, BorderLayout.SOUTH);
				revalidate();
			}

		} else if (e.getSource() == maquinaAgresiva || e.getSource() == maquinaExperta
				|| e.getSource() == maquinaMiedosa) {
			remove(modoMaquinas);
			remove(panel);
			Dimension boardSize = new Dimension(600, 600);

			panel = new JPanel();
			add(panel, BorderLayout.CENTER);
			panel.setPreferredSize(boardSize);
			panel.setBackground(Color.BLACK);
			panel.setLayout(null);

			BoardVisual = new BoardVisual();
			panel.add(BoardVisual);
			BoardVisual.setPreferredSize(boardSize);

			infoComponent = new InfoComponent();

			buttonComponent = new ButtonRestart();
			timerComponent = new TimerComponent();
			typeRock = new TypeOfRock();
			add(infoComponent, BorderLayout.NORTH);
			add(buttonComponent, BorderLayout.SOUTH);
			add(timerComponent, BorderLayout.WEST);
			add(typeRock, BorderLayout.EAST);
			revalidate();
			BoardVisual.setBounds((panel.getWidth() - boardSize.width) / 2,
					(panel.getHeight() - boardSize.height) / 2, boardSize.width, boardSize.height);

			revalidate();
			welcomeScreen();
			if (e.getSource() == maquinaAgresiva) {
				Gomoku.setPlayer2("MaquinaAgresivaPlayer", "Computador");
			} else if (e.getSource() == maquinaExperta) {
				Gomoku.setPlayer2("MaquinaExpertaPlayer", "Computador");
			} else if (e.getSource() == maquinaMiedosa) {
				Gomoku.setPlayer2("MaquinaMiedosaPlayer", "Computador");
			}

		}

	}

	/**
	 * Obtiene el componente del tablero.
	 * 
	 * @return El componente del tablero.
	 */
	public static BoardVisual getBoardVisual() {
		return BoardVisual;
	}

	/**
	 * Obtiene el componente de información.
	 * 
	 * @return El componente de información.
	 */
	public static InfoComponent getInfoComponent() {
		return infoComponent;
	}

	/**
	 * Obtiene el nombre del primer jugador.
	 * 
	 * @return El nombre del primer jugador.
	 */
	public static String getFirstName() {
		return firstName;
	}

	/**
	 * Obtiene el nombre del segundo jugador.
	 * 
	 * @return El nombre del segundo jugador.
	 */
	public static String getSecondName() {
		return secondName;
	}

	/**
	 * Método llamado antes del inicio del juego para que los jugadores ingresen sus
	 * nombres.
	 * Además, inicia el temporizador del juego según la configuración.
	 */
	public static void welcomeScreen() {
		// Obtener el nombre del jugador 1
		String firstName = JOptionPane.showInputDialog(null,
				"Bienvenido a GOMOKU!", "Ingresa el nombre del jugador uno");
		firstName = (firstName == null || firstName.trim().isEmpty()) ? "Primer jugador" : firstName;

		// Obtener el color de la ficha del jugador 1
		Color colorJugador1 = elegirColorFicha("Elige el color de tu ficha, " + firstName);

		// Configurar el jugador 1 con el nombre y el color de la ficha
		Gomoku.getInstance().getPlayer1().setName(firstName);
		Gomoku.getInstance().getPlayer1().setColorFicha(colorJugador1);

		// Configurar el temporizador si el segundo jugador es la computadora
		if (!(Gomoku.getInstance().getPlayer2() instanceof HumanoPlayer)) {
			configurarTemporizador();
			Gomoku.getInstance().getPlayer2().setName("Computador");
		} else {
			// Obtener el nombre del jugador 2
			String secondName = JOptionPane.showInputDialog(null,
					"Bienvenido a GOMOKU!", "Ingresa el nombre del jugador dos");
			secondName = (secondName == null || secondName.trim().isEmpty()) ? "Segundo jugador" : secondName;

			// Obtener el color de la ficha del jugador 2
			Color colorJugador2 = elegirColorFicha("Elige el color de tu ficha, " + secondName);

			// Configurar el jugador 2 con el nombre y el color de la ficha
			Gomoku.getInstance().getPlayer2().setName(secondName);
			Gomoku.getInstance().getPlayer2().setColorFicha(colorJugador2);

			// Configurar el temporizador
			configurarTemporizador();
		}
	}

	private static void configurarTemporizador() {
		if (Gomoku.getInstance().getModoDeJuego() instanceof ModoLimiteTiempo) {
			TimerDownComponent.getTimer().start();
		} else {
			TimerComponent.getTimer().start();
		}
	}

	private static Color elegirColorFicha(String mensaje) {
		Object[] options = { "Negro", "Blanco" };
		int choice = JOptionPane.showOptionDialog(null, mensaje, "Elige el color de tu ficha",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		// Devolver el color elegido
		return (choice == JOptionPane.YES_OPTION) ? Color.BLACK : Color.WHITE;
	}

	public static void guardarEstadoJuego() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter(null, "Archivos de datos (*.dat)", "dat"));
			int seleccion = fileChooser.showSaveDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File archivo = fileChooser.getSelectedFile();

				// Obtener la puntuación actual de los jugadores
				String nombreJugador1 = Gomoku.getInstance().getPlayer1().getName();
				String nombreJugador2 = Gomoku.getInstance().getPlayer2().getName();
				String colorJugador1 = Gomoku.getInstance().getPlayer1().getColor();
				String colorJugador2 = Gomoku.getInstance().getPlayer2().getColor();
				int puntuacionJugador1 = Gomoku.getInstance().getPlayer1().getScore();
				int puntuacionJugador2 = Gomoku.getInstance().getPlayer2().getScore();
				int[][] posiciones = obtenerPosiciones();

				// Crear un BufferedWriter para escribir en el archivo
				BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));

				// Escribir la información en el archivo
				writer.write("Nombre Jugador 1: " + nombreJugador1 + "\n");
				writer.write("Nombre Jugador 2: " + nombreJugador2 + "\n");
				writer.write("Color Jugador 1: " + colorJugador1 + "\n");
				writer.write("Color Jugador 2: " + colorJugador2 + "\n");
				writer.write("Puntuación Jugador 1: " + puntuacionJugador1 + "\n");
				writer.write("Puntuación Jugador 2: " + puntuacionJugador2 + "\n");
				writer.write("Posiciones:\n");
				for (int[] posicion : posiciones) {
					writer.write(posicion[0] + "," + posicion[1] + "\n");
				}

				writer.write("Mensaje: " + Gomoku.getInstance().getMensaje() + "\n");

				// Cerrar el BufferedWriter
				writer.close();

				// Mensaje de confirmación
				JOptionPane.showMessageDialog(null,
						"El estado del juego se ha guardado exitosamente en " + archivo,
						"Guardar juego", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			// Manejar cualquier error al escribir el archivo
			JOptionPane.showMessageDialog(null,
					"Error al guardar el estado del juego: " + e.getMessage(),
					"Guardar juego", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void cargarEstadoJuego() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de datos (*.txt)", "txt"));
			int seleccion = fileChooser.showOpenDialog(null);

			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File archivo = fileChooser.getSelectedFile();

				BufferedReader reader = new BufferedReader(new FileReader(archivo));
				String linea;
				StringBuilder contenido = new StringBuilder();
				boolean leyendoPosiciones = false;

				while ((linea = reader.readLine()) != null) {
					contenido.append(linea).append("\n");
					if (linea.startsWith("Posiciones:")) {
						leyendoPosiciones = true;
						continue;
					}

					if (leyendoPosiciones) {
						// Procesar las posiciones
						String[] partes = linea.split(":");
						if (partes.length == 2) {
							String[] coordenadas = partes[1].trim().split(",");
							// Aquí podrías hacer algo con las coordenadas, por ejemplo, imprimir
							for (String coordenada : coordenadas) {
								System.out.println(coordenada.trim());
							}
						}
					}
				}
				reader.close();
				System.out.println("Contenido del archivo cargado:");
				System.out.println(contenido.toString());

				String[] lineas = contenido.toString().split("\n");
				int puntuacionJugador1 = Integer.parseInt(lineas[0].split(":")[1].trim());
				int puntuacionJugador2 = Integer.parseInt(lineas[1].split(":")[1].trim());

				// Actualizar las puntuaciones en el juego
				Gomoku.getInstance().getPlayer1().setScore(puntuacionJugador1);
				Gomoku.getInstance().getPlayer2().setScore(puntuacionJugador2);

				// Mensaje de éxito
				JOptionPane.showMessageDialog(null, "Estado del juego cargado exitosamente.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar el estado del juego: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Método para obtener las posiciones de las celdas
	private static int[][] obtenerPosiciones() {
		int[][] posiciones = new int[Board.WIDTH * Board.HEIGHT][2];
		int index = 0;

		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				if (BoardVisual.getCells()[i][j].getColor() != Cell.EMPTY) {
					posiciones[index][0] = i;
					posiciones[index][1] = j;
					index++;
				}
			}
		}

		return Arrays.copyOfRange(posiciones, 0, index);
	}

	/**
	 * Método llamado cuando un jugador está en la arena de juego y desea volver al
	 * menú principal.
	 * Restaura la interfaz al estado del menú principal y reinicia el juego.
	 */
	public void mainMenu() {
		gomokuGUI.remove(panel);
		gomokuGUI.remove(infoComponent);
		gomokuGUI.remove(buttonComponent);
		if (Gomoku.getInstance().getModoDeJuego() instanceof ModoLimiteTiempo) {
			gomokuGUI.remove(timerDownComponent);
		} else {
			gomokuGUI.remove(timerComponent);
		}
		gomokuGUI.remove(typeRock);
		Gomoku.getInstance().setSeEncontroGanador(false);

		constructMainDisplay();

		gomokuGUI.add(menuPrincipal, BorderLayout.CENTER);
		gomokuGUI.add(panel, BorderLayout.SOUTH);

		gomokuGUI.revalidate();
		GameController.restartGame();
	}

	/**
	 * Obtiene la instancia de la clase GomokuGUI.
	 * 
	 * @return La instancia de GomokuGUI.
	 */
	public static GomokuGUI getGomokuGUI() {
		return gomokuGUI;
	}

	/**
	 * Método principal (main) que inicia la aplicación.
	 * z
	 * 
	 * @param args Argumentos de la línea de comandos (no se utilizan).
	 */
	public static void main(String[] args) {
		gomokuGUI = new GomokuGUI();
	}
}
