package com.andrii;

import com.andrii.RC5.RC5;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {
    private JFrame mainFrame;

    private JLabel statusLabel;
    private JPanel controlPanel;
    private JButton encodeButton;
    private JButton decodeButton;

    private static RC5 cryptoRC5;

    private static String filePath;

    public App() {
        cryptoRC5 = new RC5();

        prepareGUI();

        encodeButton.addActionListener(e -> {
            if (isValidPath(filePath)) {
                String result = (String) JOptionPane.showInputDialog(
                        mainFrame,
                        "Input keyword",
                        "Keyword for encoding",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                if (result != null) {
                    byte[] resultBytes = cryptoRC5.encrypt(new Reader(filePath).readLines().getBytes(), result);
                    new Writer("result_encode.txt").write(resultBytes);
                }
            }
        });

        decodeButton.addActionListener(e -> {
            if (isValidPath(filePath)) {
                String result = (String) JOptionPane.showInputDialog(
                        mainFrame,
                        "Input keyword",
                        "Keyword for decoding",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                if (result != null) {
                    byte[] byteArray = toByteArray(new Reader(filePath).readLines());
                    byte[] resultBytes = cryptoRC5.decrypt(byteArray, result);
                    new Writer("result_decode.txt").write(new String(resultBytes));
                    System.out.println(new String(resultBytes));
                }
            }
        });
    }

    private byte[] toByteArray(String rawFileString) {
        String[] arr = rawFileString.split(", ");
        // change first and last symbol of read data
        arr[0] = arr[0].split("\\[")[1];
        arr[arr.length - 1] = arr[arr.length - 1].split("\\]")[0];
        byte[] byteArray = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            byteArray[i] = Byte.parseByte(arr[i]);
        }
        return byteArray;
    }

    private boolean isValidPath(String path) {
        return path != null && !path.trim().isEmpty();
    }

    public static void main(String[] args) {
        App swingControlDemo = new App();
        swingControlDemo.showFileChooser();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("RC5 Encoding Decoding");
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
