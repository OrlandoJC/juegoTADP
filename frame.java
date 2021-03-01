import javax.swing.*;
import java.awt.*;  
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


 class Teclado
 {
  public static BufferedReader t = new BufferedReader(new
  InputStreamReader(System.in))  ;
  //Metodos
   public int leeInt()
   {
    int resultado = 0 ;
    try
    {
     resultado = Integer.parseInt( t.readLine() ) ;
    }
     catch(IOException e)
     {
      System.out.println("Error X: "+e);
      java.lang.System.exit(1);//termina con un gracioso error
     }
     return resultado ;
   }  //fin de int
   
  public float leeFloat()
   {
    float resultado = (float) 0.0 ;
    try
    {
     Float f = new Float( t.readLine() );
     resultado = f.floatValue() ;
    }
     catch(IOException e)
      {
      System.out.println("Error X: "+e);
      java.lang.System.exit(1);//termina con un gracioso error
      }
      return resultado ;
   }//fin de float

  public double leeDouble()
   {
    double resultado = 0.0;
    try
     {
      Double d = new Double(t.readLine() ) ;
      resultado = d.doubleValue() ;
     }
      catch(IOException e)
       {
        System.out.println("Error X: "+e);
        java.lang.System.exit(1);//termina con un gracioso error
       }
       return resultado ;
   } //fin de double

  public String leeString()
   {
    String resultado = new String() ;
    try
     {
      resultado = t.readLine() ;
     }
      catch(IOException e)
       {
        System.out.println("Error X: "+e);
        java.lang.System.exit(1);//termina con un gracioso error
       }
       return resultado ;
   } //fin de string

  public char leeChar(){
    char c = leeString().charAt(0);
    return c;
  }

 }
    

//Ventana de introduccion de numero
class Input extends JFrame
{
  JPanel panel;
  JLabel label;

  Input( String title )
  {
    super( title );          

    Font f = new Font("TimesRoman",Font.BOLD,25);
   
    setLocation(400, 300);

    setSize( 300, 200 );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   
    JLabel miLabel = new JLabel("Introduzca su numero", JLabel.CENTER);
	JTextField miText = new JTextField(10);

    JButton ok = new JButton("OK");

    add(miLabel);
    add(miText);

    add(ok);
    setLayout(new GridLayout(3, 1, 10, 10));
  }

}

//Configuracion del modo A

class MyFrameConfigB extends JFrame
{
  JPanel panel;
  JLabel label;

  MyFrameConfigB( String title )
  {
    super( title );          

    Font f = new Font("TimesRoman",Font.BOLD,35);
   

    setSize( 350, 200 );
    setLocation(400, 300);
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    JLabel miLabel = new JLabel("Introduzca N");
	JTextField miText = new JTextField(10);
    JButton miBoton = new JButton("Empezar el juego");
    
    JLabel miLabel2 = new JLabel("Introduzca X");
	JTextField miText2 = new JTextField(10);

    add(miLabel);
    add(miText);


    add(miLabel);
	add(miText);
    add(miLabel2);
	add(miText2);
    
	add(miBoton);

    setLayout(new GridLayout(5,1,10, 10));

   
  }

}

//Configuracion del modo B


class MyFrameConfigA extends JFrame
{
  JPanel panel;
  JLabel label;

  MyFrameConfigA( String title)
  {
    super( title );          

    Font f = new Font("TimesRoman",Font.BOLD,35);
   

    setSize( 350, 200 );
    setLocation(400, 300);
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    JLabel miLabel = new JLabel("Introduzca N");
	JTextField miText = new JTextField(10);
    JButton miBoton = new JButton("Empezar el juego");
    
    miBoton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Input in = new Input("Ventana nueva");
            in.setVisible( true );
        }

    });
    add(miLabel);
	add(miText);
	add(miBoton);
    setLayout(new GridLayout(3,1,10, 10 ));
  }
}

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

     Juego(int dificultad, char tipo, int n){
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
      

        if (dificultad == 1)
            this.intentos = this.intervaloMax / 2;
        else if (dificultad == 2)
            this.intentos = 2*this.log2(this.intervaloMax);
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

    public static int log2(int N)  { 
        int result = (int)(Math.log(N) / Math.log(2)); 
        return result; 
    } 

    public void evaluarTipoA(int n) {

        if(this.gano){
            System.out.println("ganaste"); return;
        }
       
        if(this.intentosActuales <= this.intentos ) {
            if(n > this.numeroAdivinar) 
                System.out.println("El numero es mayor que el oculto");
            else if( n < this.numeroAdivinar) 
                System.out.println("El numero es menor que el oculto");
            else 
                this.gano = true;
            this.intentosActuales++;
        } else {
            System.out.println(" perdiste");
            this.perdio = true;
        }
        
    }    
    //JuegoTipoB

    public void opcionComputadora() {
        this.pcIntento = aleatorio(this.pcMin, pcMax);

        System.out.println("la pc eligio el numero " + this.pcIntento);
    }


    public void respuestaUsuario(int opc) {

         if(this.intentosActuales == this.intentos ) { 
            this.perdio = true;
            System.out.println(" perdiste");
            return;
        }


        switch(opc) {
            case 1 :
                System.out.println("el numero es mayor");
                this.pcMax = this.pcIntento;
                this.intentosActuales++;
                
            break;
            case 2  :
                System.out.println("el numero es menor");
                this.pcMin = this.pcIntento;
                this.intentosActuales++;
            break;
            case 3 :
                   this.gano = true;   
                   System.out.println("ganaste");  
                break;
        }
    }

    public void infoJuego() {
      System.out.println("oculto "+ this.numeroAdivinar);
      System.out.println("dificultad "+ this.dificultad);
      System.out.println("tipo " + this.tipo);
      System.out.println("max valor " + this.intervaloMax);
      System.out.println("intentos " + this.intentos);
    }
  
}

public class frame {
    public static void main(String[] args){



    //    Teclado teclado = new Teclado();

    // //    int dificultad = teclado.leeInt();
    // //    char modo = teclado.leeChar();
    // //    int rangoMax  = teclado.leeInt();


    // //     Juego juego = new Juego(1, 'A', 10);
    // //     juego.infoJuego();



    // // //tipoA
    // //     while(!juego.gano()) {
    // //         int numero = teclado.leeInt();

    // //         juego.evaluar(numero);

    // //     }



    //     //tipoB
    //     int oculto = teclado.leeInt();
    //     Juego juego = new Juego(2, 'B', 1000);
    //     juego.setOculto(oculto);
    //     juego.infoJuego();
        
    // //tipoA
    //     while(!juego.gano()) {

    //         juego.opcionComputadora(); // 1, N

    //         System.out.println("Elige opcion 1(mayor) 2(menor) 3(igual)");
    //         int opc = teclado.leeInt();

    //         juego.respuestaUsuario(opc);

    //     }

      
        JFrame frame = new JFrame("NUEVA VENTANA");
        JPanel panelDificultad = new JPanel();

        JPanel entreePanel = new JPanel();
        JPanel modo = new JPanel();



        final ButtonGroup entreeGroup = new ButtonGroup();
        
        final JRadioButton radioButton1;
        final JRadioButton radioButton2;

    
        // frame.setResizable(false);
        frame.setLocation(500,400);

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
        JButton buttonB= new JButton("B");

        frame.add(buttonA);
        frame.add(buttonB);
        
        buttonA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyFrameConfigA frame = new MyFrameConfigA("Ventana nueva");
                int dificultad = radioButton1.isSelected() ? 1 : radioButton2.isSelected() ? 2 : -1 ;
             
                frame.setVisible( true );
            }

        });

        buttonB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyFrameConfigB frame = new MyFrameConfigB("Ventana nueva");
                
                frame.setVisible( true );
            }

        });

        frame.setLayout(new GridLayout(5,1,10,10));
          
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      
    }
}
