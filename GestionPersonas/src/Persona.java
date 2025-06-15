public class Persona {
    // Aquí declaramos las ropiedades privadas para nombre, apellido, genero, edad
    private String nombre;
    private String apellido;
    private String genero; // "Masculino" o "Femenino"
    private int edad;

    /**
     * Este método constructor lo usamos inicializar las propiedades de la Persona.
     * Permite la creación de la instancia del objeto directamente con los valores.
     */
    
     public Persona(String nombre, String apellido, String genero, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad = edad;
    }

    // Usamos métodos "getter" para acceder a las propiedades privadas
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
        
    }

    public String getGenero() {
        return genero;
    }

    public int getEdad() {
        return edad;
    }

    // Usamos el  método toString, de forma opcional, para facilitar la impresión de objetos Persona
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido + ", Género: " + genero + ", Edad: " + edad;
    }
}