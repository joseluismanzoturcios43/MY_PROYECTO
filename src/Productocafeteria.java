/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */

public class Productocafeteria extends Producto {
    private String tipoProducto; // Puede ser "Bebida", "Postre", etc.

    public Productocafeteria(String id, String nombre, double precio, String tipoProducto) {
        super(id, nombre, precio);
        this.tipoProducto = tipoProducto; // Tipo específico del producto
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Producto: " + getNombre() +
                           " - Tipo: " + tipoProducto +
                           " - Precio: Q" + getPrecio());
    }
}
