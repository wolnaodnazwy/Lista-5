import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ObrazPunktow extends JFrame {
    // GUI
    private JTextField alphaField, betaField, gammaField, z0Field, dField;
    private JButton loadButton, drawButton;
    private Canvas canvas;

    // Dane
    private CiagPunktow<Punkt3D> points;
    private CiagPunktow<Punkt2D> leftPoints;
    private CiagPunktow<Punkt2D> rightPoints;

    public ObrazPunktow() {
        super("Obraz Punktow");

        // Utworzenie pol tekstowych dla katow oraz parametrow rzutowania
        alphaField = new JTextField(5);
        betaField = new JTextField(5);
        gammaField = new JTextField(5);
        z0Field = new JTextField(5);
        dField = new JTextField(5);

        // Przycisk do zaladowania punktow z pliku
        loadButton = new JButton("Zaladuj punkty");
        loadButton.addActionListener(new ZaladujListener());

        // Przycisk do rysowania
        drawButton = new JButton("Narysuj punkty");
        drawButton.addActionListener(new NarysujListener());

        // Miejsca do rysowania
        canvas = new Canvas();
        canvas.setSize(500, 500);

        // Panel z polami i przyciskami
        JPanel panel = new JPanel();
        panel.add(new JLabel("Alpha: "));
        panel.add(alphaField);
        panel.add(new JLabel("Beta: "));
        panel.add(betaField);
        panel.add(new JLabel("Gamma: "));
        panel.add(gammaField);
        panel.add(new JLabel("Z0: "));
        panel.add(z0Field);
        panel.add(new JLabel("D: "));
        panel.add(dField);
        panel.add(loadButton);
        panel.add(drawButton);

        // Dodanie panelu i miejsca do wysowania do ramki
        add(panel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        // Ustawienia ramki
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private class ZaladujListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           //Obsluga problemu z niewybraniem zadnego pliku
            boolean correctFile = false;
            while (!correctFile) {
                // Pokazuje okno wyboru
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(ObrazPunktow.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (Scanner scanner = new Scanner(file)) {
                        points = new CiagPunktow<>();
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            String[] coordinates = line.split(";");
                            double x = Double.parseDouble(coordinates[0]);
                            double y = Double.parseDouble(coordinates[1]);
                            double z = Double.parseDouble(coordinates[2]);
                            points.dodajPunkt(new Punkt3D(x, y, z));
                        }
                        correctFile = true;
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(ObrazPunktow.this, "Nie mo??na znale???? pliku", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ObrazPunktow.this, "Z??y format pliku", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            } else {
                int answer = JOptionPane.showConfirmDialog(ObrazPunktow.this, "Czy chcesz wyj???? bez wyboru pliku?", "Exit", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                    }
                }
            }
        }
    }

    private class NarysujListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Sprawdzanie czy pola tekstowe nie s?? puste
            if (alphaField.getText().isEmpty() || betaField.getText().isEmpty() || gammaField.getText().isEmpty() || z0Field.getText().isEmpty() || dField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Prosz?? wprowadzi?? wszystkie dane.");
                return;
            }
            // Pobieranie potrzebnych danych
            double alpha = Double.parseDouble(alphaField.getText());
            double beta = Double.parseDouble(betaField.getText());
            double gamma = Double.parseDouble(gammaField.getText());
            double z0 = Double.parseDouble(z0Field.getText());
            double d = Double.parseDouble(dField.getText());

            // Obrot punktow
            for (Punkt3D point : points) {
                double x = point.GetX();
                double y = point.GetY();
                double z = point.GetZ();
                point.SetX(x * Math.cos(beta) * Math.cos(gamma) + y * (-Math.cos(beta) * Math.sin(gamma)) + z * Math.sin(beta));
                point.SetY(x * Math.cos(alpha) * Math.sin(gamma) + y * Math.cos(alpha) * Math.cos(gamma) + z * (-Math.sin(alpha)));
                point.SetZ(x * Math.sin(gamma) * Math.sin(beta) + y * Math.sin(beta) * Math.cos(gamma) + z * Math.cos(beta));
            }

            // Zrzutowanie punktow i dodanie do prawych lub lewych
            leftPoints = new CiagPunktow<>();
            rightPoints = new CiagPunktow<>();
            for (Punkt3D point : points) {
                Punkt2D projectedPoint = point.Project(z0, d);
                if (projectedPoint.GetX() >= 0) {
                    rightPoints.dodajPunkt(projectedPoint); //prawe oko
                } else {
                    leftPoints.dodajPunkt(projectedPoint); //lewe oko
                }
            }
            //"Czyszczenie" planszy
            if (canvas.getWidth() > 0 && canvas.getHeight() > 0) {
                Graphics g = canvas.getGraphics();
                g.setColor(canvas.getBackground());
                g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }

            // Rysowanie punktow
            Graphics g = canvas.getGraphics();
            g.setColor(Color.RED);
            for (Punkt2D point : rightPoints) {
                g.fillOval((int) point.GetX() + 250, (int) point.GetY() + 250, 5, 5);
            }
            g.setColor(Color.CYAN);
            for (Punkt2D point : leftPoints) {
                g.fillOval((int) point.GetX() + 250, (int) point.GetY() + 250, 5, 5);
            }
        }
    }

}