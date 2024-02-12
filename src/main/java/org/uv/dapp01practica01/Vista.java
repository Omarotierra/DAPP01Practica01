/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp01practica01;
import java.util.List;
import java.util.Scanner;

public class Vista {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logica logica = new Logica();

    public static void main(String[] args) {
        while (true) {
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
        Empleado empleado = new Empleado();
        System.out.println("Ingrese el nombre del empleado:");
        empleado.setNombre(scanner.nextLine());
        System.out.println("Ingrese la dirección del empleado:");
        empleado.setDireccion(scanner.nextLine());
        System.out.println("Ingrese el teléfono del empleado:");
        empleado.setTelefono(scanner.nextLine());

        logica.crearEmpleado(empleado);
    }

    private static void leerEmpleados() {
        List<Empleado> empleados = logica.leerEmpleados();
        for (Empleado empleado : empleados) {
            System.out.println("Clave: " + empleado.getClave() + ", Nombre: " + empleado.getNombre() +
                    ", Dirección: " + empleado.getDireccion() + ", Teléfono: " + empleado.getTelefono());
        }
    }

    private static void actualizarEmpleado() {
        System.out.println("Ingrese la clave del empleado que desea actualizar:");
        int clave = Integer.parseInt(scanner.nextLine());

        Empleado empleado = new Empleado();
        empleado.setClave(clave);
        System.out.println("Ingrese el nuevo nombre del empleado:");
        empleado.setNombre(scanner.nextLine());
        System.out.println("Ingrese la nueva dirección del empleado:");
        empleado.setDireccion(scanner.nextLine());
        System.out.println("Ingrese el nuevo teléfono del empleado:");
        empleado.setTelefono(scanner.nextLine());

        logica.actualizarEmpleado(empleado);
    }

    private static void eliminarEmpleado() {
        System.out.println("Ingrese la clave del empleado que desea eliminar:");
        int clave = Integer.parseInt(scanner.nextLine());
        logica.eliminarEmpleado(clave);
    }
}

