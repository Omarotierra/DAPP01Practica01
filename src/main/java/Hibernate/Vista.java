package Hibernate;

import Hibernate.PojoEmpleado;
import Hibernate.Hibernate;
import Hibernate.EmpleadoDAOH;
import java.util.List;
import java.util.Scanner;

public class Vista {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmpleadoDAOH empleadoDAO = new EmpleadoDAOH(Hibernate.getSessionFactory());

    public static void main(String[] args) {
        while (true) {
            System.out.println("Main:");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear empleado");
            System.out.println("2. Actualizar empleado");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Buscar empleado por ID");
            System.out.println("5. Ver todos los empleados");
            System.out.println("6. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    actualizarEmpleado();
                    break;
                case 3:
                    eliminarEmpleado();
                    break;
                case 4:
                    buscarEmpleadoPorId();
                    break;
                case 5:
                    mostrarTodosEmpleados();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    Hibernate.getSessionFactory().close(); // Cerrar la sesión de Hibernate
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void crearEmpleado() {
        PojoEmpleado empleado = new PojoEmpleado();
        System.out.println("Ingrese el nombre del empleado:");
        empleado.setNombre(scanner.nextLine());
        System.out.println("Ingrese la dirección del empleado:");
        empleado.setDireccion(scanner.nextLine());
        System.out.println("Ingrese el teléfono del empleado:");
        empleado.setTelefono(scanner.nextLine());

        empleadoDAO.crearEmpleado(empleado);
        System.out.println("Empleado creado correctamente.");
    }

    private static void actualizarEmpleado() {
        System.out.println("Ingrese el ID del empleado que desea actualizar:");
        int id = Integer.parseInt(scanner.nextLine());

        PojoEmpleado empleado = empleadoDAO.buscarPorId(id);
        if (empleado != null) {
            System.out.println("Ingrese el nuevo nombre del empleado:");
            empleado.setNombre(scanner.nextLine());
            System.out.println("Ingrese la nueva dirección del empleado:");
            empleado.setDireccion(scanner.nextLine());
            System.out.println("Ingrese el nuevo teléfono del empleado:");
            empleado.setTelefono(scanner.nextLine());

            empleadoDAO.actualizarEmpleado(empleado);
            System.out.println("Empleado actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún empleado con el ID especificado.");
        }
    }

    private static void eliminarEmpleado() {
        System.out.println("Ingrese el ID del empleado que desea eliminar:");
        int id = Integer.parseInt(scanner.nextLine());

        PojoEmpleado empleado = empleadoDAO.buscarPorId(id);
        if (empleado != null) {
            empleadoDAO.eliminarEmpleado(empleado);
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún empleado con el ID especificado.");
        }
    }

    private static void buscarEmpleadoPorId() {
        System.out.println("Ingrese el ID del empleado que desea buscar:");
        int id = Integer.parseInt(scanner.nextLine());
        PojoEmpleado empleado = empleadoDAO.buscarPorId(id);
        if (empleado != null) {
            System.out.println("Empleado encontrado:");
            System.out.println("ID: " + empleado.getId() + ", Nombre: " + empleado.getNombre() +
                    ", Dirección: " + empleado.getDireccion() + ", Teléfono: " + empleado.getTelefono());
        } else {
            System.out.println("No se encontró ningún empleado con el ID especificado.");
        }
    }

    private static void mostrarTodosEmpleados() {
        List<PojoEmpleado> empleados = empleadoDAO.buscarTodos();
        if (empleados != null) {
            for (PojoEmpleado empleado : empleados) {
                System.out.println("ID: " + empleado.getId() + ", Nombre: " + empleado.getNombre() +
                        ", Dirección: " + empleado.getDireccion() + ", Teléfono: " + empleado.getTelefono());
            }
        } else {
            System.out.println("Error al recuperar los empleados.");
        }
    }
}
