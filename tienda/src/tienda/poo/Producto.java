package tienda.poo;

import java.util.HashMap;

public class Producto {

    //ATRIBUTOS
    private int codigo;
    private String producto;
    private double precio;
    private int stock;
    private int unidades;
    private char cat;
    private double pr_un;
    private String fabricante;
    private static int contador = 1;

    private final static HashMap<Character, String> tipos
            = new HashMap<>() {
        {
            put('W', "Whisky");
            put('C', "Cerveza");
            put('V', "Vino");
            put('G', "Ginebra");
            put('R', "Ron");
        }
    };

    public Producto(String producto, double precio, double precio_un, int stock, int unidades, char cat, String fabricante) {
        if (producto.equals("")){
           throw new TiendaException("Error: el nombre no puede estar vacío");
        }else if(precio<0){
            throw new TiendaException("Error: el precio debe ser mayor de 0");
        }else if(stock<0){
            throw new TiendaException("Error: el stock no puede ser menor de 0");
        }else if(precio<0){
            throw new TiendaException("Error: el precio no puede ser menor de 0");
        }else if(fabricante.equals("")){
            throw new TiendaException("Error: el fabricante no puede estar vacio");
        }else if(precio_un>precio){
            throw new TiendaException("Error: el coste no puede ser mayor del precio");
        }else if(unidades<0){
            throw new TiendaException("Error: las unidades no pueden ser menor de 0");
        }else if(!tipos.containsKey(cat)){
            throw new TiendaException("Error: categoría inválida");
        }else{
            this.producto = producto;
            this.precio = precio;
            this.stock = stock;
            this.unidades = unidades;
            this.cat = cat;
            this.fabricante = fabricante;
            this.pr_un = precio_un;
            this.codigo = this.contador;
            this.contador++;
        } 

    }

    public Producto(String producto, double precio, int stock, char cat, String fabricante, double precio_un) {
        if (producto.equals("")){
           throw new TiendaException("Error: el nombre no puede estar vacío");
        }else if(precio<0){
            throw new TiendaException("Error: el precio debe ser mayor de 0");
        }else if(stock<0){
            throw new TiendaException("Error: el stock no puede ser menor de 0");
        }else if(!this.tipos.containsKey(cat)){
            throw new TiendaException("Error: categoría no válida");
        }else if(fabricante.equals("")){
            throw new TiendaException("Error: el fabricante no puede estar vacio");
        }else if(precio_un>precio){
            throw new TiendaException("Error: el coste no puede ser mayor que el precio");
        }else{
            this.producto = producto;
            this.precio = precio;
            this.stock = stock;
            this.cat = cat;
            this.fabricante = fabricante;
            this.pr_un = precio_un;
            this.unidades = 0;
            this.codigo = this.contador;
            this.contador++;
        }
    }

    public Producto(Producto c) {
        this.producto = c.producto;
        this.precio = c.precio;
        this.stock = c.stock;
        this.unidades = c.unidades;
        this.cat = c.cat;
        this.fabricante = c.fabricante;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getProducto() {
        return producto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getUnidades() {
        return unidades;
    }

    public char getCat() {
        return cat;
    }

    public static String getNomCat(char cat) {
        return tipos.get(cat);
    }

    public double getPr_un() {
        return pr_un;
    }

    public String getFabricante() {
        return fabricante;
    }

    public double beneficio() {
        double resta, p_venta, p_prod;
        p_venta = this.precio * this.unidades;
        p_prod = this.pr_un * this.unidades;
        resta = p_venta - p_prod;

        return resta;
    }

    public void subirPrecio(double precio) {
        if (this.precio < precio) {
            this.precio += precio;
        } else {
            throw new TiendaException("Error. El precio no puede ser menor que el actual");
        }
    }

    public void bajarPrecio(double precio) {
        if ((this.precio - precio) > this.pr_un) {
            this.precio -= precio;
        } else {
            throw new TiendaException("Error. El precio no puede ser menor que el precio de coste");
        }
    }

    public void vender(int cantidad) {
        if (cantidad > this.stock) {
            this.unidades += this.stock;
            int copiaStock=this.stock;
            this.stock = 0;
            throw new TiendaException("No hay suficiente stock, se han vendido "+copiaStock);            
        } else {
            this.stock -= cantidad;
            this.unidades += cantidad;

        }
    }

    public void cambiarTipo(char tipo) {
        if (this.tipos.containsKey(tipo)) {
            this.cat = tipo;
        } else {
            throw new TiendaException("Categoría errónea");
        }
    }

    public void reponerStock(int stock) {
        this.stock += stock;
    }

    public void cambiarPu(double precio) {
        if (precio > 0) {
            if (precio < this.precio) {
                this.pr_un = precio;
            } else {
                throw new TiendaException("Error. El precio no puede superar el precio de venta");
            }
        } else {
            throw new TiendaException("Error. El precio no puede ser cero.");
        }

    }

    public String toString() {
        String res = "";

        res = "=========================\n"
                + this.codigo + "\n"
                + "Nombre: " + this.producto + "\n";
        res += "Categoría:";
        res += tipos.get(this.cat) + "\n";
        res += "Precio: " + this.precio + "\n"
                + "Stock: " + this.stock + "\n"
                + "Unidades vendidas: " + this.unidades + "\n"
                + "Coste por unidad: " + this.pr_un + "\n"
                + "Fabricante: " + this.fabricante + "\n"
                + "=========================";

        return res;
    }

}
