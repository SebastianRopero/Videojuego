import java.util.Scanner;

//Creamos la clase Personajes la cual va servir de herencia para crear las clases de heroes y villanos
class Personajes {
    // Agregamos los atributos
    String nombre;
    int vida;
    int vidaMaxima;
    int fuerza;
    int defensa;
    int habilidadEspecial;
    boolean tieneVida;
    int energia;
    int curacion;
    int agilidad;
    boolean esquivo;

    // Creamos el constructor para iniciar los atributos de la clase Personajes
    // Este metodo lo vamos a llamar cuando se vaya a crear un personaje
    public Personajes(String nombre, int vida, int fuerza, int defensa, int habilidadEspecial,
            int vidaMaxima, int energia, int curacion, int agilidad) {

        // Usamos this para referirnos a los atributos de la clase
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.habilidadEspecial = habilidadEspecial;
        this.tieneVida = true;
        this.energia = energia;
        this.curacion = 40;
        this.agilidad = 25;
        this.esquivo = false;

    }

    // Aca crearemos el metodo para los ataques de los heroes
    public void ataque(Personajes objetivo) {

        // Definimos constantes para el metodo de ataque
        final int ENERGIA_ATAQUE_BASICO = 30;
        // creamos un if para condicionar que debemos tener almenos 30 de energia para
        // realizar un ataque
        if (this.energia >= ENERGIA_ATAQUE_BASICO) {
            int dañoReal = this.fuerza - objetivo.defensa;
            // si el daño real queda debajo de 0 entonces este seria 0
            if (dañoReal < 0) {
                dañoReal = 0;
            }
            objetivo.vida -= dañoReal;
            System.out.println(this.nombre + " realiza un ataque basico a " + objetivo.nombre + " y le causa "
                    + dañoReal + " de daño");
            System.out.println(" ");
            // Realizamos otro if para verificar si el contrincante quedo muerto o vivo
            // despues del ataque
            if (objetivo.vida <= 0) {
                // si el objetivo baja su vida de 0 colocamos que esta es igual a 0 para no
                // mostrar vida negativa
                objetivo.vida = 0;
                objetivo.tieneVida = false;
                return; // Si el objetivo es derrotado salimos del metodo
            }

            this.energia -= ENERGIA_ATAQUE_BASICO;
            // Si no tenemos energia para atacar el juego muestra el mensaje y sale del
            // metodo
        } else {
            System.out.println("No tiene suficiente energia para atacar");
            return;
        }
    }

    // Creamos el metodo para la curacion
    public void curarse() {
        // Declaramos constantes para el metodo de curacion
        final int ENERGIA_CURAR = 20;
        // Verificamos que el personaje al que vamos a curar tenga vida

        // Ponemos que al curarse gaste un porcentaje de energia
        if (this.energia >= ENERGIA_CURAR) {
            this.vida += this.curacion;

            // Un if para que la vida no sobrepase la vida maxima
            if (this.vida > this.vidaMaxima) {
                this.vida = this.vidaMaxima;
            }
            System.out.println("Has sumado " + this.curacion + " puntos de vida");
            System.out.println(" ");

            this.energia -= ENERGIA_CURAR;

            // Si no tenemos energia para curarte el juego muestra el mensaje y sale del
            // metodo
        } else {
            System.out.println("No tiene suficiente energia para curarte");
            return;
        }

    }
 
    //Aca crearemos el metodo con el cual podremos esquivar los ataques del contrincante
    public void esquivar(Personajes objetivo) {
        //Definimos constantes para realizar el metodo
        final int RANGO_AZAR = 101;
        //Definimos un valor al azar para manejar la probabilidad que habra de esquivar o no un ataque
        int azar = (int) (Math.random() * RANGO_AZAR);
        //Usamos un if para poner la condicion, en este caso si el numero es menor o igual al nivel de agilidad del personaje
        //Este podra realizar un esquive
        if (azar <= this.agilidad) {
            esquivo = true;
            System.out.println(" Felicidad, podras esquivar el proximo ataque");

            final int ENERGIA_RECUPERADA = 10;
            this.energia = this.energia + ENERGIA_RECUPERADA;

        } else {
            esquivo = false;
            System.out.println("No lograste esquivar");
        }

    }

}

// Creamos la clase heroe
class Heroe extends Personajes {

    // Definimos los atributos
    String poderEspecial;
    String descripcionPoderEspecial;

    // Creamos el constructor de los atributos de los heroes
    public Heroe(String nombre, String descripcionPoderEspecial, int vida, int fuerza,
            int defensa, int habilidadEspecial, int vidaMaxima, String poderEspecial, int energia,
            int curacion, int agilidad) {

        // Llamamos al constructor de la clase personajes para usar tambien sus
        // atributos
        super(nombre, vida, fuerza, defensa, habilidadEspecial, vidaMaxima, energia, curacion, agilidad);
        this.poderEspecial = poderEspecial;
        this.descripcionPoderEspecial = descripcionPoderEspecial;
    }

    // Creamos el metodo para el ataque especial
    public void ataqueEspecial(Personajes villano) {

        //Definimos la constante de energia que va a gastar
        final int ENERGIA_ESPECIAL = 50;
        //Hacemos la operacion para sacar el daño de la habilidad especial
        int dañoEspecial = this.fuerza + this.habilidadEspecial;
        //El if para hacer la condicion de la energia que va a requerir
        if (this.energia >= ENERGIA_ESPECIAL) {
            this.energia -= ENERGIA_ESPECIAL;
            //Calculamos el daño real que va a recibir el oponente
            int dañoReal = dañoEspecial - villano.defensa;
            if (dañoReal <= 0) {
                dañoReal = 0;
            }
            System.out.println(this.nombre + " Usa su ataque especial " + this.poderEspecial + " en el cual "
            + this.descripcionPoderEspecial+" y le causa "+dañoReal+" de daño");
            System.out.println(" ");
            villano.vida -= dañoReal;
            //Un if por si la vida baja de 0 que esta no quede como un numero negativo
            if (villano.vida <= 0) {
                villano.vida = 0;
                villano.tieneVida = false;
            } else {
                System.out.println(villano.nombre + " tiene " + villano.vida + " puntos de vida");
                System.out.println(" ");
            }
        } else {
            System.out.println("No tiene suficiente energia para realizar esta opcion");
            System.out.println(" ");
            return;
        }

    }
}

// Creamos la clase para los villanos
class Villano extends Personajes {

    // definimos los atributos
    String poderEspecial;
    String descripcionPoderEspecial;

    // Creamos el constructor para inicializar los atributos de los villanos
    public Villano(String nombre, String descripcionPoderEspecial, int vida, int fuerza,
            int defensa, int habilidadEspecial, int vidaMaxima, String poderEspecial, int energia,
            int curacion, int agilidad) {
        // Llamamos al constructor de la clase personaje
        super(nombre, vida, fuerza, defensa, habilidadEspecial, vidaMaxima, energia, curacion, agilidad);
        this.poderEspecial = poderEspecial;
        this.descripcionPoderEspecial = descripcionPoderEspecial;
        this.curacion = curacion;
    }

    // Creamos el metodo para el ataque especial
    public void ataqueEspecial(Personajes heroe) {

        //Definimos la constante de energia que va a gastar
        final int ENERGIA_ESPECIAL = 50;
        //Hacemos la operacion para sacar el daño de la habilidad especial
        int dañoEspecial = this.fuerza + this.habilidadEspecial;
        //El if para hacer la condicion de la energia que va a requerir
        if (this.energia >= ENERGIA_ESPECIAL) {
            this.energia -= ENERGIA_ESPECIAL;          
           //Calculamos el daño real que va a recibir el oponente
            int dañoReal = dañoEspecial - heroe.defensa;
            if (dañoReal <= 0) {
                dañoReal = 0;
            }
            System.out.println(this.nombre + " Usa su ataque especial " + this.poderEspecial + " en el cual "
            + this.descripcionPoderEspecial+" y le causa "+dañoReal+" de daño");
            System.out.println(" ");
            heroe.vida -= dañoReal;
            //Un if por si la vida baja de 0 que esta no quede como un numero negativo
            if (heroe.vida <= 0) {
                heroe.vida = 0;
                heroe.tieneVida = false;
            } else {
                System.out.println(heroe.nombre + " tiene " + heroe.vida + " puntos de vida");
                System.out.println(" ");
            }

        } else {
            System.out.println("No tiene suficiente energia para realizar el ataque especial");
            return;
        }

    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // Aca creamos a los heroes y a los villanos almacenados en un arrays para poder hacer luego que se pueda escoger que personajes usar
        Heroe[] heroes = new Heroe[5];
        Villano[] villanos = new Villano[5];

        //Creamos tanto los heroes como los villanos
        heroes[0] = new Heroe("Naruto Uzumaki",
                "Naruto reune una gran cantidad de chakra en su mano y le lanza un rasengan al contrincante",
                100, 40, 25, 35, 100, "Rasengan", 150, 15, 25);
        heroes[1] = new Heroe("Sakura Haruno", "Sakura realiza un golpe de palma sabia a su oponente",
                100, 40, 20, 30, 100, "Golpe de palma sabia", 150, 50, 20);
        heroes[2] = new Heroe("Kakashi Hatake",
                "Kakashi se levanta su bandana ninja y le realiza un raikiri a su rival",
                100, 45, 30, 35, 100, "Raikiri", 150, 10, 30);
        heroes[3] = new Heroe("Sasuke Uchicha",
                "Sasuke reune chakra electrico en la palma de su mano de esta forma desata un chidori contra su oponente ",
                100, 40, 25, 30, 100, "Chidori Senbon", 150, 10, 30);
        heroes[4] = new Heroe("Hinata Hyuga",
                "Hinata activa su Byakugan y desata una lluvia de puños suaves en el enemigo",
                100, 40, 25, 25, 100, "Puño Suave", 150, 20, 50);
        villanos[0] = new Villano("Madara Uchicha",
                "Madara usa su Tengai Shinsei para atraer enormes meteoros que impactan en su contrincante",
                100, 50, 35, 30, 100, "Tengai Shinsei", 150, 5, 35);
        villanos[1] = new Villano("Obito Uchicha",
                "Obito invoca la estatua demoniaca para impactar un golpe a su oponente",
                100, 45, 25, 40, 100, "Gedo Mazo", 150, 5, 20);
        villanos[2] = new Villano("Itachi Uchicha", "Itachi lograr capturar a su enemigo en su genjutsu",
                100, 45, 25, 45, 100, "Tsukuyomi", 150, 10, 30);
        villanos[3] = new Villano("Orochimaru",
                "Orochimaru se convierte en una serpiente blanca de 8 cabezas y ahoga a su oponente",
                100, 45, 30, 35, 100, "Serpiente de Ocho Cabezas", 150, 60, 1);
        villanos[4] = new Villano("Pain Nagato ",
                "Pain se alza en vuelo y utiliza el Shinra Tensei para hacer explotar al enemigo",
                100, 45, 25, 25, 100, "Shinra Tensei", 150, 20, 35);

                //Creamos el menu de seleccion de personajes de los heroes
        System.out.println("|------------------------------|");
        System.out.println("|     Selecciona tu heroe:     |");
        System.out.println("|1: " + heroes[0].nombre + "             |");
        System.out.println("|2: " + heroes[1].nombre + "              |");
        System.out.println("|3: " + heroes[2].nombre + "             |");
        System.out.println("|4: " + heroes[3].nombre + "             |");
        System.out.println("|5: " + heroes[4].nombre + "               |");
        System.out.println("|------------------------------|");
        //definimos una variable con la cual escogeremos el heroe -1 para que los indices del array se puedan escoger de 1 a 5
        int seleccionarHeroe = scanner.nextInt() - 1;
        Heroe heroeSeleccionado = heroes[seleccionarHeroe];

            //Creamos el menu de seleccion de personajes de los villanos
        System.out.println("Selecciona tu villano:");
        System.out.println("|------------------------------|");
        System.out.println("|     Selecciona tu heroe:     |");
        System.out.println("|1: " + villanos[0].nombre + "             |");
        System.out.println("|2: " + villanos[1].nombre + "              |");
        System.out.println("|3: " + villanos[2].nombre + "             |");
        System.out.println("|4: " + villanos[3].nombre + "                 |");
        System.out.println("|5: " + villanos[4].nombre + "               |");
        System.out.println("|------------------------------|");
        //definimos una variable con la cual escogeremos el villano y usamos -1 para que los indices del array se puedan escoger de 1 a 5
        int seleccionarVillano = scanner.nextInt() - 1;
        Villano villanoSeleccionado = villanos[seleccionarVillano];

        //Creamos el while donde se ejecutara el juego con la condicion de que ambos personajes deben estar vivos para continuar jugando
        while (heroeSeleccionado.tieneVida && villanoSeleccionado.tieneVida) {

            //Menu de turnos para el jugador 1
            System.out.println("Turno de " + heroeSeleccionado.nombre);
            System.out.println("|-------------------------|---------------------------|");
            System.out.println("|1: Atacar al contrincante|        stats              |");
            System.out.println("|2: Curarte               |Vida:    " + heroeSeleccionado.vida + "               |");
            System.out.println("|3: Habilidad especial    |Chakra: " + heroeSeleccionado.energia + "                |");
            System.out.println("|4: Esquivar              |                           |");
            System.out.println("|-------------------------|---------------------------|");
            //Una variable para escoger la opcion 
            int opcion = scanner.nextInt();

            //Swicht para manejar las acciones que se tienen en el juego
            //En este caso atacar, curarse, habilidad especial y esquivar
            switch (opcion) {
                case 1:
                    //Creamos un if para manejar la opcion del esquivo cuando el enemigo esquiva
                    if (!villanoSeleccionado.esquivo) {
                        //Si el enemigo no tiene ningun esquivo el personaje le ataca
                        heroeSeleccionado.ataque(villanoSeleccionado);
                    } else {
                        //Si tiene esquivo el enemigo no recibe daño
                        System.out.println(villanoSeleccionado.nombre + " esquivó el ataque.");
                        //Aca se cambia denuevo el estado de esquivo a false para que no esquive infinitamente
                        villanoSeleccionado.esquivo = false;
                    }
                    break;
                case 2:
                    //Llamamos al metodo curar
                    heroeSeleccionado.curarse();

                    break;

                case 3:
                    //Llamamos al metodo del ataque especial
                    heroeSeleccionado.ataqueEspecial(villanoSeleccionado);
                    break;
                case 4:
                    //Llamamos al metodo de esquivar
                    heroeSeleccionado.esquivar(villanoSeleccionado);
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
            //Si el enemigo muere termina el juego
            if (villanoSeleccionado.tieneVida == false) {
                System.out.println("Has derrotado a " + villanoSeleccionado.nombre);
                break;
            }

            //Menu de turnos para el jugador 2
            System.out.println("Turno de " + villanoSeleccionado.nombre);
            System.out.println("|-------------------------|---------------------------|");
            System.out.println("|1: Atacar al contrincante|        stats              |");
            System.out.println("|2: Curarte               |Vida:    " + villanoSeleccionado.vida + "               |");
            System.out
                    .println("|3: Habilidad especial    |Chakra: " + villanoSeleccionado.energia + "               |");
            System.out.println("|4: Esquivar              |                           |");
            System.out.println("|-------------------------|---------------------------|");
            //Una variable para escoger la opcion
            int opcionVillano = scanner.nextInt();

             //Swicht para manejar las acciones que se tienen en el juego
            //En este caso atacar, curarse, habilidad especial y esquivar
            switch (opcionVillano) {
                case 1:
                    //Creamos un if para manejar la opcion del esquivo cuando el enemigo esquiva
                    if (!heroeSeleccionado.esquivo) {
                        //Si el enemigo no tiene ningun esquivo el personaje le ataca
                        villanoSeleccionado.ataque(heroeSeleccionado);
                    } else {
                        //Si tiene esquivo el enemigo no recibe daño
                        System.out.println(heroeSeleccionado.nombre + " esquivó el ataque.");
                        //Aca se cambia denuevo el estado de esquivo a false para que no esquive infinitamente
                        heroeSeleccionado.esquivo = false;

                    }
                    break;
                case 2:
                    //Llamamos al metodo de curar
                    villanoSeleccionado.curarse();

                    break;

                case 3:
                    //Llamamos el metodo del ataque especial
                    villanoSeleccionado.ataqueEspecial(heroeSeleccionado);
                    break;
                case 4:
                    //Llamamos al metodo de esquivar
                    villanoSeleccionado.esquivar(heroeSeleccionado);
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

            //Si derrota al heroe el juego termina
            if (heroeSeleccionado.tieneVida == false) {
                System.out.println("Has derrotado a " + heroeSeleccionado.nombre);
                break;
            }
        }
        scanner.close();
    }
}