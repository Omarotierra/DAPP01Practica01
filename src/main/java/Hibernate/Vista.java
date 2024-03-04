package Hibernate;

import java.util.List;
import java.util.Scanner;

public class Vista {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DAODetalleVenta detalleVentaDAO = new DAODetalleVenta(HibernateUtil.getSessionFactory());
    private static final DAOVenta ventaDAO = new DAOVenta(HibernateUtil.getSessionFactory());

    public static void main(String[] args) {
        while (true) {
            System.out.println("Main:");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear detalle de venta");
            System.out.println("2. Crear venta");
            System.out.println("3. Modificar detalle de venta");
            System.out.println("4. Modificar venta");
            System.out.println("5. Eliminar detalle de venta");
            System.out.println("6. Eliminar venta");
            System.out.println("7. Buscar detalle de venta por ID");
            System.out.println("8. Buscar venta por ID");
            System.out.println("9. Mostrar todos los detalles de venta");
            System.out.println("10. Mostrar todas las ventas");
            System.out.println("11. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearDetalleVenta();
                    break;
                case 2:
                    crearVenta();
                    break;
                case 3:
                    modificarDetalleVenta();
                    break;
                case 4:
                    modificarVenta();
                    break;
                case 5:
                    eliminarDetalleVenta();
                    break;
                case 6:
                    eliminarVenta();
                    break;
                case 7:
                    buscarDetalleVentaPorId();
                    break;
                case 8:
                    buscarVentaPorId();
                    break;
                case 9:
                    mostrarTodosDetallesVenta();
                    break;
                case 10:
                    mostrarTodasVentas();
                    break;
                case 11:
                    System.out.println("Saliendo...");
                    HibernateUtil.getSessionFactory().close(); // Cierra la sesión de Hibernate
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void crearDetalleVenta() {
        DetalleVenta detalleVenta = new DetalleVenta();
        System.out.println("Ingrese la cantidad:");
        detalleVenta.setCantidad(Double.parseDouble(scanner.nextLine()));
        System.out.println("Ingrese el precio:");
        detalleVenta.setPrecio(Double.parseDouble(scanner.nextLine()));
        System.out.println("Ingrese el producto:");
        detalleVenta.setProducto(scanner.nextLine());
        System.out.println("Ingrese el ID de la venta:");
        detalleVenta.setIdventa(Long.parseLong(scanner.nextLine()));

        detalleVentaDAO.crearDetalleVenta(detalleVenta);
        System.out.println("Detalle de venta creado correctamente.");
    }

    private static void crearVenta() {
        Venta venta = new Venta();
        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        venta.setFecha(java.sql.Date.valueOf(scanner.nextLine()));
        System.out.println("Ingrese el cliente:");
        venta.setCliente(scanner.nextLine());
        System.out.println("Ingrese el total:");
        venta.setTotal(Double.parseDouble(scanner.nextLine()));

        ventaDAO.crearVenta(venta);
        System.out.println("Venta creada correctamente.");
    }

    private static void modificarDetalleVenta() {
        System.out.println("Ingrese el ID del detalle de venta que desea modificar:");
        long id = Long.parseLong(scanner.nextLine());

        DetalleVenta detalleVenta = detalleVentaDAO.buscarPorId(id);
        if (detalleVenta != null) {
            System.out.println("Ingrese la nueva cantidad:");
            detalleVenta.setCantidad(Double.parseDouble(scanner.nextLine()));
            System.out.println("Ingrese el nuevo precio:");
            detalleVenta.setPrecio(Double.parseDouble(scanner.nextLine()));
            System.out.println("Ingrese el nuevo producto:");
            detalleVenta.setProducto(scanner.nextLine());
            System.out.println("Ingrese el nuevo ID de la venta:");
            detalleVenta.setIdventa(Long.parseLong(scanner.nextLine()));

            detalleVentaDAO.actualizarDetalleVenta(detalleVenta);
            System.out.println("Detalle de venta actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún detalle de venta con el ID especificado.");
        }
    }

    private static void modificarVenta() {
        System.out.println("Ingrese el ID de la venta que desea modificar:");
        long id = Long.parseLong(scanner.nextLine());

        Venta venta = ventaDAO.buscarPorId(id);
        if (venta != null) {
            System.out.println("Ingrese la nueva fecha (YYYY-MM-DD):");
            venta.setFecha(java.sql.Date.valueOf(scanner.nextLine()));
            System.out.println("Ingrese el nuevo cliente:");
            venta.setCliente(scanner.nextLine());
            System.out.println("Ingrese el nuevo total:");
            venta.setTotal(Double.parseDouble(scanner.nextLine()));

            ventaDAO.actualizarVenta(venta);
            System.out.println("Venta actualizada correctamente.");
        } else {
            System.out.println("No se encontró ninguna venta con el ID especificado.");
        }
    }

    private static void eliminarDetalleVenta() {
        System.out.println("Ingrese el ID del detalle de venta que desea eliminar:");
        long id = Long.parseLong(scanner.nextLine());

        DetalleVenta detalleVenta = detalleVentaDAO.buscarPorId(id);
        if (detalleVenta != null) {
            detalleVentaDAO.eliminarDetalleVenta(detalleVenta);
            System.out.println("Detalle de venta eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún detalle de venta con el ID especificado.");
        }
    }

    private static void eliminarVenta() {
        System.out.println("Ingrese el ID de la venta que desea eliminar:");
        long id = Long.parseLong(scanner.nextLine());

        Venta venta = ventaDAO.buscarPorId(id);
        if (venta != null) {
            ventaDAO.eliminarVenta(venta);
            System.out.println("Venta eliminada correctamente.");
        } else {
            System.out.println("No se encontró ninguna venta con el ID especificado.");
        }
    }

    private static void buscarDetalleVentaPorId() {
        System.out.println("Ingrese el ID del detalle de venta que desea buscar:");
        long id = Long.parseLong(scanner.nextLine());
        DetalleVenta detalleVenta = detalleVentaDAO.buscarPorId(id);
        if (detalleVenta != null) {
            System.out.println("Detalle de venta encontrado:");
            System.out.println("ID: " + detalleVenta.getId() + ", Cantidad: " + detalleVenta.getCantidad() +
                    ", Precio: " + detalleVenta.getPrecio() + ", Producto: " + detalleVenta.getProducto() +
                    ", ID de Venta: " + detalleVenta.getIdventa());
        } else {
            System.out.println("No se encontró ningún detalle de venta con el ID especificado.");
        }
    }

    private static void buscarVentaPorId() {
        System.out.println("Ingrese el ID de la venta que desea buscar:");
        long id = Long.parseLong(scanner.nextLine());
        Venta venta = ventaDAO.buscarPorId(id);
        if (venta != null) {
            System.out.println("Venta encontrada:");
            System.out.println("ID: " + venta.getId() + ", Fecha: " + venta.getFecha() +
                    ", Cliente: " + venta.getCliente() + ", Total: " + venta.getTotal());
        } else {
            System.out.println("No se encontró ninguna venta con el ID especificado.");
        }
    }

    private static void mostrarTodosDetallesVenta() {
        List<DetalleVenta> detallesVenta = detalleVentaDAO.buscarTodos();
        if (detallesVenta != null) {
            for (DetalleVenta detalleVenta : detallesVenta) {
                System.out.println("ID: " + detalleVenta.getId() + ", Cantidad: " + detalleVenta.getCantidad() +
                        ", Precio: " + detalleVenta.getPrecio() + ", Producto: " + detalleVenta.getProducto() +
                        ", ID de Venta: " + detalleVenta.getIdventa());
            }
        } else {
            System.out.println("Error al recuperar los detalles de venta.");
        }
    }

    private static void mostrarTodasVentas() {
        List<Venta> ventas = ventaDAO.buscarTodos();
        if (ventas != null) {
            for (Venta venta : ventas) {
                System.out.println("ID: " + venta.getId() + ", Fecha: " + venta.getFecha() +
                        ", Cliente: " + venta.getCliente() + ", Total: " + venta.getTotal());
            }
        } else {
            System.out.println("Error al recuperar las ventas.");
        }
    }
}
