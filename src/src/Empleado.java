import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Empleado {

    private int id;
    private String nombre;
    private String email;
    private double salario;
    private String departamento;
    private LocalDate fechaContratacion;
    private ArrayList<String> habilidades;

    public Empleado(int id, String nombre, String email, double salario,
                    String departamento, LocalDate fechaContratacion,
                    ArrayList<String> habilidades) {
        this.id = id;
        setNombre(nombre);
        setEmail(email);
        setSalario(salario);
        setDepartamento(departamento);
        setFechaContratacion(fechaContratacion);
        this.habilidades = (habilidades != null) ? habilidades : new ArrayList<>();
    }

    public void setId(int valor) {
        this.id = valor;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        String patron = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$";
        if (email == null || !email.matches(patron)) {
            throw new IllegalArgumentException("El email no tiene un formato válido");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSalario(double salario) {
        if (salario > 0) {
            this.salario = salario;
        } else {
            throw new IllegalArgumentException("No puede ser menor a 0.0");
        }
    }

    public double getSalario() {
        return salario;
    }

    public void setDepartamento(String departamento) {
        if (departamento == null || departamento.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        if (fechaContratacion == null || fechaContratacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de contratación no puede ser nula ni futura");
        }
        this.fechaContratacion = fechaContratacion;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void agregarHabilidad(String habilidad) {
        if (habilidad == null || habilidad.isBlank()) {
            throw new IllegalArgumentException("La habilidad no puede ser nula o vacía");
        }
        if (habilidades.contains(habilidad)) {
            throw new IllegalArgumentException("La habilidad ya existe: " + habilidad);
        }
        habilidades.add(habilidad);
    }

    public List<String> getHabilidades() {
        return new ArrayList<String>(habilidades);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Departamento: %s | Salario: $%,.2f",
                id, nombre, departamento, salario);
    }

}