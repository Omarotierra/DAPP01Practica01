/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.dapp01practica01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author omar
 */
public class DAPP01Practica01 {
     private static final Scanner scanner = new Scanner(System.in);
    private static final DAOEmpleado daoEmpleado = new DAOEmpleado();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Main:");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear empleado");
            System.out.println("2. Ver empleados");
            System.out.println("3. Actualizar empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    leerEmpleados();
                    break;
                case 3:
                    actualizarEmpleado();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    System.out.println("Saliendo...");
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

        if (daoEmpleado.guardar(empleado)) {
            System.out.println("Empleado creado correctamente.");
        } else {
            System.out.println("Error al crear el empleado.");
        }
    }

    private static void leerEmpleados() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ver todos los empleados");
        System.out.println("2. Buscar empleado por ID");
        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                mostrarTodosEmpleados();
                break;
            case 2:
                buscarEmpleadoPorId();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void mostrarTodosEmpleados() {
        List<PojoEmpleado> empleados = daoEmpleado.buscarAll();
        if (empleados != null) {
            for (PojoEmpleado empleado : empleados) {
                System.out.println("ID: " + empleado.getId() + ", Nombre: " + empleado.getNombre() +
                        ", Dirección: " + empleado.getDireccion() + ", Teléfono: " + empleado.getTelefono());
            }
        } else {
            System.out.println("Error al recuperar los empleados.");
        }
    }

    private static void buscarEmpleadoPorId() {
        System.out.println("Ingrese el ID del empleado:");
        int id = Integer.parseInt(scanner.nextLine());
        PojoEmpleado empleado = daoEmpleado.buscarById(id);
        if (empleado != null) {
            System.out.println("Empleado encontrado:");
            System.out.println("ID: " + empleado.getId() + ", Nombre: " + empleado.getNombre() +
                        ", Dirección: " + empleado.getDireccion() + ", Teléfono: " + empleado.getTelefono());
        } else {
            System.out.println("No se encontró ningún empleado con el ID especificado.");
        }
    }

    private static void actualizarEmpleado() {
        System.out.println("Ingrese el ID del empleado que desea actualizar:");
        int id = Integer.parseInt(scanner.nextLine());

        PojoEmpleado empleado = daoEmpleado.buscarById(id);
        if (empleado != null) {
            System.out.println("Ingrese el nuevo nombre del empleado:");
            empleado.setNombre(scanner.nextLine());
            System.out.println("Ingrese la nueva dirección del empleado:");
            empleado.setDireccion(scanner.nextLine());
            System.out.println("Ingrese el nuevo teléfono del empleado:");
            empleado.setTelefono(scanner.nextLine());

            if (daoEmpleado.modificar(empleado, id)) {
                System.out.println("Empleado actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el empleado.");
            }
        } else {
            System.out.println("No se encontró ningún empleado con el ID especificado.");
        }
    }

    private static void eliminarEmpleado() {
        System.out.println("Ingrese el ID del empleado que desea eliminar:");
        int id = Integer.parseInt(scanner.nextLine());
        if (daoEmpleado.eliminar(id)) {
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("Error al eliminar el empleado.");
        }
    }
}