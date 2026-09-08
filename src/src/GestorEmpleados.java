import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GestorEmpleados {
    private List<Empleado> empleados = new ArrayList<>();

    public void agregarEmpleado(Empleado e){
        if (e == null) {
            throw  new IllegalArgumentException("El empleado no puede ser nulo");
        }

        boolean idexiste = empleados.stream().anyMatch(emp -> emp.getId()== e.getId());
        if (idexiste) {
            throw new IllegalArgumentException("Ya existe un empleado con el ID: " + e.getId());
        }

        empleados.add(e);
    }

    public List<Empleado> obtenerPorDepartamento(String depto){
        List<Empleado>buscador= empleados.stream().filter(emp -> emp.getDepartamento().equals(depto)).collect(Collectors.toList());
        return buscador;
    }

    public Map<String, Double> salarioPromedioPorDepartamento() {
        Map<String, Double> saladepartamento = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.averagingDouble(Empleado::getSalario)
                ));
        return saladepartamento;
    }

    public List<String> nombresOrdenadosPorSalario(){
        List<String> nombres = empleados.stream().sorted(Comparator.comparingDouble(Empleado::getSalario).reversed()).map(Empleado::getNombre).collect(Collectors.toList());
        return nombres;
    }

    public Map<String, Long> contarPorDepartamento(){
        Map<String, Long>contar= empleados.stream().collect(Collectors.groupingBy(Empleado::getDepartamento,Collectors.counting()));

        return contar;
    }

    public Optional<Empleado> empleadoMejorPagado(){
        Optional<Empleado>pagado=empleados.stream().max(Comparator.comparingDouble(Empleado::getSalario));

        return pagado;
    }

    public Map<String, List<Empleado>> agruparPorRangoSalarial(){
        Map<String, List<Empleado>>rango=empleados.stream().collect(Collectors.groupingBy(e ->{
            if (e.getSalario()< 1500000) {
                return "Bajo";
            } else if (e.getSalario() <= 3000000) {
                return "Medio";
            } else {
                return "Alto";
            }
        }));

        return rango;
    }

    public Set<String> obtenerTodasLasHabilidades(){
        Set<String> obtener=empleados.stream().flatMap(e ->e.getHabilidades().stream()).collect(Collectors.toSet());
        return obtener;
    }

    public String generarReporte() {
        String reporte = empleados.stream()
                .map(Empleado::toString)
                .collect(Collectors.joining("\n"));
        return reporte;
    }

    public List<Empleado> buscar(Predicate<Empleado> criterio) {
        List<Empleado> resultado = empleados.stream()
                .filter(criterio)
                .collect(Collectors.toList());
        return resultado;
    }

    public List<Empleado> antiguedadMayorA(int anios) {
        LocalDate fechaLimite = LocalDate.now().minusYears(anios);
        List<Empleado> antiguedad = empleados.stream()
                .filter(e -> e.getFechaContratacion().isBefore(fechaLimite))
                .collect(Collectors.toList());
        return antiguedad;
    }

}