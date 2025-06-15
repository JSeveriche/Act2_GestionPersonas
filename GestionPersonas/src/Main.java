/**
*  1) Realizar un programa de consola que permita realizar las siguientes operaciones

a) Realizar una clase que permita capturar el nombre, apellido, genero, edad, estas propiedades deberán ser definidas mediante el modificador de acceso Privado y permitir que la instancia del objeto creada pueda realizarse directamente mediante la utilización de un método constructor.
b) Realizar un método que permita Capturar y retornar el nombre y el género de 5 personas capturadas por teclado.
c) Realizar un método que permita retornar el promedio de las edades capturadas.
d) Realizar un método que permita retornar la cantidad de personas con género Masculino.
e) Realizar un método que permita retornar la cantidad de personas con género Femenino.
    */

// src/Main.java
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner; // Para poder capturar la entrada del teclado

public class Main {

    // Scanner para la entrada del teclado, se cierra al finalizar el programa.
    private static final Scanner scanner = new Scanner(System.in);
    // Lista para almacenar los objetos Persona, la movemos a nivel de clase para que sea accesible por el menú.
    private static final List<Persona> personas = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("--- GESTIÓN DE PERSONAS ---");

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcionMenu();

            switch (opcion) {
                case 1:
                    // Capturar datos de personas. Podríamos preguntar cuántas.
                    System.out.print("¿Cuántas personas desea capturar? ");
                    int numPersonas = 0;
                    boolean entradaValida = false;
                    do {
                        try {
                            numPersonas = scanner.nextInt();
                            if (numPersonas > 0) {
                                entradaValida = true;
                            } else {
                                System.out.println("Por favor, ingrese un número mayor que cero.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                            scanner.next(); // Limpiar el buffer
                        }
                    } while (!entradaValida);
                    scanner.nextLine(); // Consumir el salto de línea pendiente
                    capturarPersonas(personas, numPersonas);
                    break;
                case 2:
                    mostrarNombreYGenero(personas);
                    break;
                case 3:
                    double promedioEdades = calcularPromedioEdades(personas);
                    System.out.printf("\nPromedio de edades: %.2f\n", promedioEdades);
                    break;
                case 4:
                    int cantidadMasculino = contarGenero(personas, "Masculino");
                    System.out.println("Cantidad de personas Masculinas: " + cantidadMasculino);
                    break;
                case 5:
                    int cantidadFemenino = contarGenero(personas, "Femenino");
                    System.out.println("Cantidad de personas Femeninas: " + cantidadFemenino);
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine(); // Esperar a que el usuario presione Enter antes de mostrar el menú de nuevo
                                // Esto es crucial para pausar la ejecución después de cada operación.

        } while (opcion != 0);

        // Cerramos el scanner al finalizar el programa
        scanner.close();
    }

    /**
     * Muestra el menú de opciones al usuario.
     */
    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Capturar datos de personas");
        System.out.println("2. Mostrar nombre y género de las personas");
        System.out.println("3. Calcular promedio de edades");
        System.out.println("4. Contar personas con género Masculino");
        System.out.println("5. Contar personas con género Femenino");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Obtiene la opción seleccionada por el usuario, con manejo de errores.
     * @return La opción numérica ingresada por el usuario.
     */
    private static int obtenerOpcionMenu() {
        int opcion = -1; // Valor inicial para indicar una opción no válida
        try {
            opcion = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.next(); // Limpiar el buffer del scanner en caso de error
        }
        scanner.nextLine(); // Consumir el salto de línea pendiente después de nextInt()
        return opcion;
    }

    /**
     * Creamos el método para capturar y almacenar datos de las personas.
     * Permite capturar el nombre, apellido, genero, edad, estas propiedades deberán ser definidas mediante el
     * modificador de acceso Privado y permitir que la instancia del objeto
     * creada pueda realizarse directamente mediante la utilización de un método constructor.
     */
    private static void capturarPersonas(List<Persona> listaPersonas, int numPersonas) {
        System.out.println("\nPor favor, ingresa los datos de " + numPersonas + " personas:");
        for (int i = 0; i < numPersonas; i++) {
            System.out.println("\n--- Persona #" + (i + 1) + " ---");
            String nombre, apellido, genero;
            int edad = 0;
            boolean entradaValida;

            // Capturamos de Nombre
            System.out.print("Nombre: ");
            nombre = scanner.nextLine();

            // Capturamos de Apellido
            System.out.print("Apellido: ");
            apellido = scanner.nextLine();

            // Capturamos de Género (validación simple)
            do {
                System.out.print("Género (Masculino/Femenino): ");
                genero = scanner.nextLine();
                if (genero.equalsIgnoreCase("Masculino") || genero.equalsIgnoreCase("Femenino")) {
                    entradaValida = true;
                } else {
                    System.out.println("Género inválido. Por favor, ingresa 'Masculino' o 'Femenino'.");
                    entradaValida = false;
                }
            } while (!entradaValida);

            // Capturamos la Edad (con manejo de excepciones)
            do {
                entradaValida = true;
                try {
                    System.out.print("Edad: ");
                    edad = scanner.nextInt();
                    if (edad <= 0 || edad > 120) { // Aquí se realiza la validación de rango razonable para la edad
                        System.out.println("Edad inválida. Por favor, ingresa una edad entre 1 y 120.");
                        entradaValida = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número entero para la edad.");
                    entradaValida = false;
                    scanner.next(); // Limpiar el buffer del scanner
                }
            } while (!entradaValida);
            scanner.nextLine(); // Consumir el salto de línea pendiente después de nextInt()

            // Crear una nueva instancia de Persona usando el constructor y añadirla a la lista
            listaPersonas.add(new Persona(nombre, apellido, genero, edad));
        }
    }

    /**
     * Este método nos permite capturar y retornar el nombre y el género
     * de las personas capturadas. **/

     private static void mostrarNombreYGenero(List<Persona> personas) {
        System.out.println("\n--- Listado de Nombres y Géneros ---");
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }
        for (Persona p : personas) {
            System.out.println("Nombre: " + p.getNombre() + ", Género: " + p.getGenero());
        }
    }

    /**
     * Este método nos  permite retornar el promedio de las edades capturadas. */

     private static double calcularPromedioEdades(List<Persona> personas) {
        if (personas.isEmpty()) {
            return 0.0;
        }
        int sumaEdades = 0;
        for (Persona p : personas) {
            sumaEdades += p.getEdad();
        }
        return (double) sumaEdades / personas.size();
    }

    /**
     * Este método nos permite retornar la cantidad de personas
     * con un género específico.
     **/

     private static int contarGenero(List<Persona> personas, String generoBuscado) {
        int contador = 0;
        for (Persona p : personas) {
            if (p.getGenero().equalsIgnoreCase(generoBuscado)) {
                contador++;
            }
        }
        return contador;
    }
}