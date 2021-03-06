package application;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MoonVideo extends Contents {

	private Media me;
	private MediaPlayer mp;
	private MediaView mv;
	private JFrame VideoFrame = new JFrame();

	public MoonVideo() {
		initAndShowGUI();
	}

	private void initAndShowGUI() {
		// This method is invoked on the EDT thread
		VideoFrame.setTitle("Moon and Constellation");

		final JFXPanel fxPanel = new JFXPanel();
		fxPanel.setBounds(0, 53, 650, 330);
		VideoFrame.getContentPane().setLayout(null);
		VideoFrame.add(fxPanel);

		JButton btnNewButton = new JButton("MENU");
		btnNewButton.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mp.pause();
				MainMenu mainmenu = new MainMenu();
				mainmenu.mainMenuStart();
				VideoFrame.dispose();

			}

		});

		btnNewButton.setBounds(0, -3, 84, 37);
		VideoFrame.getContentPane().add(btnNewButton);

		JButton btnNext = new JButton("NEXT");
		btnNext.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
		btnNext.setBounds(566, 0, 84, 37);
		VideoFrame.getContentPane().add(btnNext);
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mp.pause();
				Contents summary = new Summary1();

				summary.run();
				VideoFrame.dispose();

			}

		});

		JLabel lblNewLabel_1 = new JLabel("Phases of the Moon");
		lblNewLabel_1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
		lblNewLabel_1.setForeground(java.awt.Color.WHITE);
		lblNewLabel_1.setBounds(220, 3, 200, 29);
		VideoFrame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 650, 52);
		lblNewLabel.setIcon(new ImageIcon("back.jpg"));
		VideoFrame.getContentPane().add(lblNewLabel);
		VideoFrame.getContentPane().add(fxPanel);

		VideoFrame.setSize(676, 453);

		VideoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX(fxPanel);

			}
		});
	}

	public void initFX(JFXPanel fxPanel) {
		// This method is invoked on the JavaFX thread

		Group root = new Group();
		Scene scene = new Scene(root, Color.BLACK);
		File moonVideo = new File("src/media/moon.mp4");
		me = new Media(new File(moonVideo.getAbsolutePath()).toURI().toString());
		mp = new MediaPlayer(me);
		mv = new MediaView(mp);

		int w = fxPanel.getWidth();
		int h = fxPanel.getHeight();

		mv.setFitWidth(w);
		mv.setFitWidth(h);

		MediaControl mediaControl = new MediaControl(mp);
		scene.setRoot(mediaControl);

		root.getChildren().add(mv);

		fxPanel.setScene(scene);

	}

	@Override
	public void run() {
		VideoFrame.setVisible(true);
	}

}