import RSA.RSA;

import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class App {
    private JFrame mainFrame;

    private JLabel statusLabel;
    private JPanel controlPanel;
    private JButton encodeButton;
    private JButton decodeButton;
    private static RSA rsa;

    private static String filePath;

    public App() {
        prepareGUI();

        encodeButton.addActionListener(e -> {
            if (isValidPath(filePath) && filePath != null) {
                try {
                    rsa.encrypt(filePath);
                } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException exception) {
                    exception.printStackTrace();
                }

            }
        });

        decodeButton.addActionListener(e -> {
            if (isValidPath(filePath) && filePath != null) {
                try {
                    rsa.decrypyt(filePath);
                } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    private boolean isValidPath(String path) {
        return path != null && !path.trim().isEmpty();
    }

    public static void main(String[] args) {
        App swingControlDemo = new App();
        rsa = new RSA();
        System.out.println("PUBLIC KEY: " + RSA.publickey.toString());
        System.out.println("PRIVATE KEY: " + RSA.privateKey.toString());
        swingControlDemo.showFileChooser();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("RSA Encoding Decoding");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 2, 4, 4));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        encodeButton = new JButton("Encode");
        encodeButton.setPreferredSize(new Dimension(200, 50));

        decodeButton = new JButton("Decode");
        decodeButton.setPreferredSize(new Dimension(200, 50));

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);

        mainFrame.add(encodeButton);
        mainFrame.add(decodeButton);
        mainFrame.add(new JLabel());
        mainFrame.add(new JLabel());
        mainFrame.setVisible(true);
    }


    private void showFileChooser() {
        final JFileChooser fileDialog = new JFileChooser();
        JButton showFileDialogButton = new JButton("Open File");

        showFileDialogButton.addActionListener(e -> {
            int returnVal = fileDialog.showOpenDialog(mainFrame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                java.io.File file = fileDialog.getSelectedFile();
                filePath = file.getAbsolutePath();
                statusLabel.setText("File Selected :" + file.getName());
            } else {
                statusLabel.setText("No file selected");
            }
        });
        controlPanel.add(showFileDialogButton);
        mainFrame.setVisible(true);
    }

}
