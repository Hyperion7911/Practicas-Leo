package practicas;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class SistemaVotacion {
    // Declaración de variables para almacenar información sobre candidatos, votantes registrados, contraseñas y votos.
    private List<String> candidatos;
    private Map<String, Boolean> votantesRegistrados;
    private Map<String, String> votantesContrasenas;
    private Map<String, String> votos;
 
    // Constructor de la clase SistemaVotacion
    public SistemaVotacion() {
        // Inicialización de las estructuras de datos para almacenar información
        this.candidatos = new ArrayList<>();
        this.votantesRegistrados = new HashMap<>();
        this.votantesContrasenas = new HashMap<>();
        this.votos = new HashMap<>();
    }
 
    // Método para registrar un votante con su nombre y contraseña
    public void registrarVotante(String nombre, String contrasena) {
        // Verifica si el votante ya está registrado
        if (!votantesRegistrados.containsKey(nombre)) {
            // Registra al votante con su contraseña y marca su estado como no ha votado
            votantesRegistrados.put(nombre, false);
            votantesContrasenas.put(nombre, contrasena);
            System.out.println("Votante registrado: " + nombre);
        } else {
            System.out.println("El votante ya está registrado.");
        }
    }
 
    // Método para autenticar a un votante
    public boolean autenticarVotante(String nombre, String contrasena) {
        // Verifica si el votante está registrado y si la contraseña es correcta
        return votantesContrasenas.containsKey(nombre) && votantesContrasenas.get(nombre).equals(contrasena);
    }
 
    // Método para agregar un candidato a la lista de candidatos
    public void agregarCandidato(String nombre) {
        candidatos.add(nombre);
    }
 
    // Método para registrar el voto de un votante por un candidato
    public void realizarVoto(String votante, String contrasena, String candidato) {
        // Verifica si el votante está registrado y no ha votado aún
        if (votantesRegistrados.containsKey(votante) && !votantesRegistrados.get(votante)) {
            // Verifica si el votante no ha emitido un voto previo y si la autenticación es exitosa
            if (!votos.containsKey(votante) && autenticarVotante(votante, contrasena)) {
                // Verifica si el candidato está registrado y registra el voto
                if (candidatos.contains(candidato)) {
                    votos.put(votante, candidato);
                    votantesRegistrados.put(votante, true);
                    System.out.println("Voto de " + votante + " registrado por " + candidato);
                } else {
                    System.out.println("El candidato no está registrado.");
                }
            } else if (votos.containsKey(votante)) {
                System.out.println("El votante ya ha emitido su voto.");
            } else {
                System.out.println("La contraseña es incorrecta.");
            }
        } else {
            System.out.println("El votante no está registrado.");
        }
    }
 
    // Método para contar y mostrar los votos para cada candidato
    public void contarVotos() {
        // Mapa para contar los votos por cada candidato
        Map<String, Integer> contadorVotos = new HashMap<>();
        // Inicialización del contador de votos para cada candidato en 0
        for (String candidato : candidatos) {
            contadorVotos.put(candidato, 0);
        }
 
        // Contabiliza los votos registrados para cada candidato
        for (String voto : votos.values()) {
            contadorVotos.put(voto, contadorVotos.get(voto) + 1);
        }
 
        // Muestra los resultados del conteo de votos para cada candidato
        for (Map.Entry<String, Integer> entry : contadorVotos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votos");
        }
    }
 
    // Método main para simular el proceso de votación
    public static void main(String[] args) {
        SistemaVotacion sistema = new SistemaVotacion();
 
        // Registrar votantes y candidatos
        sistema.registrarVotante("VotanteA", "contrasenaA");
        sistema.registrarVotante("VotanteB", "contrasenaB");
        sistema.registrarVotante("VotanteC", "contrasenaC");
        sistema.registrarVotante("VotanteD", "contrasenaD");
        sistema.agregarCandidato("Candidato1");
        sistema.agregarCandidato("Candidato2");
        sistema.agregarCandidato("Candidato3");
        sistema.agregarCandidato("Candidato4");
 
 
 
        // Simular el proceso de votación
        sistema.realizarVoto("VotanteA", "contrasenaA", "Candidato1");
        sistema.realizarVoto("VotanteB", "contrasenaB", "Candidato2");
        sistema.realizarVoto("VotanteC", "contrasenaC", "Candidato3");
        sistema.realizarVoto("VotanteD", "contrasenaD", "Candidato4");
        sistema.realizarVoto("Votante1", "contrasena1", "CandidatoB"); // Intentar un segundo voto
 
        // Mostrar resultados
        sistema.contarVotos();
    }
}