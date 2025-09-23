public class Empleado {
    int id;
    String nombre;
    String apellido;
    int salario;

    public Empleado(int id, String nombre, String apellido, int salario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCompletoNombre() {
        return nombre + " " + apellido;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int salarioAnual(){
        return salario * 12;
    }

    public int aumentoSalarial(int porcentaje){
        int nuevoSalario = salario + (salario * porcentaje / 100);
        return nuevoSalario;
    }


}


