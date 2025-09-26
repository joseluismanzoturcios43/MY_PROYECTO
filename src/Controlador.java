/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */

import java.util.*;

public class Controlador {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("===== CAFETERÍA RUFUS DEN =====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar producto");
            System.out.println("3. Mostrar datos");
            System.out.println("4. Actualizar registro");
            System.out.println("5. Eliminar registro");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    registrarProducto();
                    break;
                case 3:
                    listarDatos();
                    break;
                case 4:
                    actualizarRegistro();
                    break;
                case 5:
                    eliminarRegistro();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (opcion != 6);
    }

    private void registrarCliente() {
        System.out.print("ID Cliente: ");
        String id = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Teléfono: ");
        String tel = sc.nextLine();

        Cliente c = new Cliente(id, nombre, tel);
        ArchivoUtil.guardar("CLIENTE," + id + "," + nombre + "," + tel);
        System.out.println(" Cliente registrado.");
    }

    private void registrarProducto() {
        System.out.print("ID Producto: ");
        String id = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        sc.nextLine();
        System.out.print("Tipo de producto (Bebida/Postre): ");
        String tipo = sc.nextLine();

        Productocafeteria p = new Productocafeteria(id, nombre, precio, tipo);
        ArchivoUtil.guardar("PRODUCTO," + id + "," + nombre + "," + precio + "," + tipo);
        System.out.println(" Producto registrado.");
    }

    private void actualizarRegistro() {
        System.out.print("Ingrese ID a actualizar: ");
        String id = sc.nextLine();
        List<String> registros = ArchivoUtil.leer();
        boolean encontrado = false;
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).contains("," + id + ",")) {
                encontrado = true;

                System.out.println("Registro encontrado: " + registros.get(i));
                System.out.println("¿Es CLIENTE o PRODUCTO?");
                String tipo = sc.nextLine().toUpperCase();

                switch (tipo) {
                    case "CLIENTE":
                        {
                            System.out.print("Nuevo nombre: ");
                            String nombre = sc.nextLine();
                            System.out.print("Nuevo teléfono: ");
                            String tel = sc.nextLine();
                            registros.set(i, "CLIENTE," + id + "," + nombre + "," + tel);
                            break;
                        }
                    case "PRODUCTO":
                        {
                            System.out.print("Nuevo nombre: ");
                            String nombre = sc.nextLine();
                            System.out.print("Nuevo precio: ");
                            double precio = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Nuevo tipo de producto: ");
                            String tipoProducto = sc.nextLine();
                            registros.set(i, "PRODUCTO," + id + "," + nombre + "," + precio + "," + tipoProducto);
                            break;
                        }
                    default:
                        System.out.println(" Tipo no válido.");
                        break;
                }
                break;
            }
        }

        if (encontrado) {
            ArchivoUtil.sobrescribir(registros);
            System.out.println(" Registro modificado.");
        } else {
            System.out.println(" No se encontró el ID.");
        }
    }

    private void listarDatos() {
        System.out.println("\nDatos en archivo:");
        ArchivoUtil.leer().forEach((linea) -> {
            System.out.println(linea);
        });
    }

    private void eliminarRegistro() {
        System.out.print("Ingrese ID a eliminar: ");
        String id = sc.nextLine();

        List<String> registros = ArchivoUtil.leer();
        registros.removeIf(linea -> linea.contains("," + id + ","));

        ArchivoUtil.sobrescribir(registros);
        System.out.println(" Registro eliminado (si existía).");
    }
}
