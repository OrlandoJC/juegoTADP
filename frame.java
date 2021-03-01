import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

class Juego {

  private int intentos;
  private int numeroAdivinar;
  private int dificultad;
  private char tipo;
  private int intervaloMax;
  private static int intentosActuales;
  private boolean gano;
  private boolean ganoPc;
  private boolean perdio;

  private int pcIntento;
  private int pcMax;
  private int pcMin;

  Juego(int dificultad, char tipo, int n) {
    this.dificultad = dificultad;
    this.tipo = tipo;
    this.intervaloMax = n;
    this.numeroAdivinar = aleatorio(1, this.intervaloMax);
    this.intentosActuales = 1;
    this.gano = false;
    this.perdio = false;
    this.ganoPc = false;
    this.pcMax = n;
    this.pcMin = 1;

    if (dificultad == 1) this.intentos = this.intervaloMax / 2; else if (
      dificultad == 2
    ) this.intentos = 2 * this.log2(this.intervaloMax);
  }

  public void setOculto(int x) {
    this.numeroAdivinar = x;
  }

  public int getOculto() {
    return this.numeroAdivinar;
  }

  public int aleatorio(int min, int max) {
    int n = (int) (Math.random() * (max - min)) + min;
    return n;
  }

  public boolean gano() {
    return this.gano || this.perdio;
  }

  public static int log2(int N) {
    int result = (int) (Math.log(N) / Math.log(2));
    return result;
  }

  public void evaluarTipoA(int n) {
    if (this.gano) {
      JOptionPane.showMessageDialog(
        null,
        "ganaste",
        "INFORMATION_MESSAGE",
        JOptionPane.INFORMATION_MESSAGE
      );
      return;
    }

    if (this.intentosActuales <= this.intentos) {
      if (n > this.numeroAdivinar) {
        JOptionPane.showMessageDialog(
          null,
          "El numero es mayor que el oculto",
          "WARNING_MESSAGE",
          JOptionPane.WARNING_MESSAGE
        );
      } else if (n < this.numeroAdivinar) {
        JOptionPane.showMessageDialog(
          null,
          "El numero es menor que el oculto",
          "WARNING_MESSAGE",
          JOptionPane.WARNING_MESSAGE
        );
      } else {
        this.gano = true;
        JOptionPane.showMessageDialog(
          null,
          "ganaste",
          "INFORMATION_MESSAGE",
          JOptionPane.INFORMATION_MESSAGE
        );
      }

      this.intentosActuales++;
    } else {
      JOptionPane.showMessageDialog(
        null,
        "Perdiste",
        "WARNING_MESSAGE",
        JOptionPane.WARNING_MESSAGE
      );
      this.perdio = true;
    }
  }

  //JuegoTipoB

  public void opcionComputadora() {
    this.pcIntento = aleatorio(this.pcMin, pcMax);

    JOptionPane.showMessageDialog(
          null,
          "la pc eligio el numero " + this.pcIntento,
          "WARNING_MESSAGE",
          JOptionPane.WARNING_MESSAGE
    );
   
  }

  public void respuestaUsuario(int opc) {
    if (this.intentosActuales == this.intentos) {
      this.perdio = true;
      System.out.println(" perdiste");
      return;
    }

    switch (opc) {
      case 1:
        System.out.println("el numero es mayor");
        this.pcMax = this.pcIntento;
        this.intentosActuales++;

        break;
      case 2:
        System.out.println("el numero es menor");
        this.pcMin = this.pcIntento;
        this.intentosActuales++;
        break;
      case 3:
        this.gano = true;
         JOptionPane.showMessageDialog(
          null,
          " gano la pc",
          "WARNING_MESSAGE",
          JOptionPane.WARNING_MESSAGE
    );
        break;
    }
  }

  public void infoJuego() {
    System.out.println("oculto " + this.numeroAdivinar);
    System.out.println("dificultad " + this.dificultad);
    System.out.println("tipo " + this.tipo);
    System.out.println("max valor " + this.intervaloMax);
    System.out.println("intentos " + this.intentos);
  }
}

public class frame {

  public static void main(String[] args) {
    JFrame frame = new JFrame("NUEVA VENTANA");
    JPanel panelDificultad = new JPanel();

    JPanel entreePanel = new JPanel();
    JPanel modo = new JPanel();

    final ButtonGroup entreeGroup = new ButtonGroup();

    final JRadioButton radioButton1;
    final JRadioButton radioButton2;

    // frame.setResizable(false);
    frame.setLocation(500, 400);

    frame.add(new JLabel("Dificultad"));
    entreePanel.add(radioButton1 = new JRadioButton("Facil"));
    radioButton1.setActionCommand("Facil");
    entreeGroup.add(radioButton1);
    entreePanel.add(radioButton2 = new JRadioButton("Dificil"));
    radioButton2.setActionCommand("Dificil");
    entreeGroup.add(radioButton2);

    frame.add(entreePanel);
    frame.add(new JLabel("Modo:"));

    JButton buttonA = new JButton("A");
    JButton buttonB = new JButton("B");

    frame.add(buttonA);
    frame.add(buttonB);

    buttonA.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int dificultad = radioButton1.isSelected()
            ? 1
            : radioButton2.isSelected() ? 2 : -1;

          String[] options = { "JUGAR" };
          JPanel panel = new JPanel();
          JLabel lbl = new JLabel(" Introduzca N ");
          JTextField txt = new JTextField(10);
          panel.add(lbl);
          panel.add(txt);
          int selectedOption = JOptionPane.showOptionDialog(
            null,
            panel,
            "The Title",
            JOptionPane.NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
          );

          if (selectedOption == 0) {
            int N = Integer.parseInt(txt.getText());

            Juego juego = new Juego(dificultad, 'A', N);

            juego.infoJuego();

            while (!juego.gano()) {
              int dato = Integer.parseInt(
                JOptionPane.showInputDialog("ingresa el numero")
              );

              juego.evaluarTipoA(dato);
            }

            System.exit(0);
          }
        }
      }
    );

    buttonB.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int dificultad = radioButton1.isSelected()
            ? 1
            : radioButton2.isSelected() ? 2 : -1;

          String[] options = { "JUGAR" };
          JPanel panel = new JPanel();
          JLabel lbl = new JLabel(" Introduzca N ");
          JTextField txt = new JTextField(10);

          JLabel lbl2 = new JLabel(" Introduzca X");
          JTextField txt2 = new JTextField(10);
          panel.add(lbl);
          panel.add(txt);

          panel.add(lbl2);
          panel.add(txt2);
          int selectedOption = JOptionPane.showOptionDialog(
            null,
            panel,
            "The Title",
            JOptionPane.NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
          );

          if (selectedOption == 0) {
            int N = Integer.parseInt(txt.getText());
            int x = Integer.parseInt(txt2.getText());
            Juego juego = new Juego(dificultad, 'B', N);
            juego.setOculto(x);
            juego.infoJuego();

            while (!juego.gano()) {
              juego.opcionComputadora(); // 1, N

              System.out.println("Elige opcion 1(mayor) 2(menor) 3(igual)");

              int opc = Integer.parseInt(
                JOptionPane.showInputDialog("Elige opcion 1(mayor) 2(menor) 3(igual)")
              );

              juego.respuestaUsuario(opc);
            }

            System.exit(0);

          }
        }
      }
    );

    frame.setLayout(new GridLayout(5, 1, 10, 10));

    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
