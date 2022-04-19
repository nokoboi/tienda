
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
    private static int contador=1;
    
     private final static HashMap<Character,String> tipos=
            new HashMap<>(){
                {
                    put('W',"Whisky");
                    put('C',"Cerveza");
                    put('V',"Vino");
                    put('G',"Ginebra");
                }
            };

    public Producto(String producto, double precio, int stock, int unidades, char cat, String fabricante,double precio_un) {
        this.producto = producto;
        this.precio = precio;
        this.stock = stock;
        this.unidades = unidades;
        this.cat = cat;
        this.fabricante = fabricante;
        this.pr_un=precio_un;
        this.codigo=this.contador;
        this.contador++;
    }

    public Producto(String producto, double precio, int stock, char cat, String fabricante,double precio_un) {
        this.producto = producto;
        this.precio = precio;
        this.stock = stock;
        this.cat = cat;
        this.fabricante = fabricante;
        this.pr_un=precio_un;
        this.unidades=0;
        this.codigo=this.contador;
        this.contador++;
    }
    
    public Producto(Producto c){
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

    public double getPr_un() {
        return pr_un;
    }

    public String getFabricante() {
        return fabricante;
    }
    
    public double beneficio(){
        double resta, p_venta, p_prod;
        p_venta=this.precio*this.unidades;
        p_prod=this.pr_un*this.unidades;
        resta=p_venta-p_prod;
        
        return resta;
    }
    
    public void subirPrecio(double precio){
        if(this.precio<precio){
            this.precio+=precio;
            System.out.println("Actualizado con éxito");
        }else{
            System.out.println("Error. El precio no puede ser menor que el actual");
        }
    }
    
    public void bajarPrecio(double precio){
        if((this.precio-precio)>this.pr_un){
            this.precio-=precio;
            System.out.println("Actualizado con éxito");
        }else{
            System.out.println("Error. El precio no puede ser menor que el precio de coste");
        }
    }
    
    public void vender(int cantidad){
        if(cantidad>this.stock){
            System.out.println("No hay suficiente stock");
        }else{
            this.stock-=cantidad;
            this.unidades+=cantidad;
            System.out.println("Compra realizada con éxito");
        }
    }
    
    public void cambiarTipo(char tipo){
        if(tipo=='W' || tipo=='C' || tipo=='V' || tipo=='R' ||
                tipo=='G'){
            this.cat=tipo;
        }else{
            System.out.println("Error. Categoría errónea");
        }
    }
    
    public void reponerStock(int stock){
        this.stock+=stock;
        System.out.println("Stock repuesto con éxito");
    }
    
    public void cambiarPu(double precio){
        if(precio>0){
            if(precio<this.precio){
                this.pr_un=precio;
                System.out.println("Precio unitario cambiado exitosamente");
            }else{
                System.out.println("Error. El precio no puede superar el precio de venta");
            }
        }else{
            System.out.println("Error. El precio no puede ser cero.");
        }
        
    }
    
    public String toString(){
       String res="";
       
       res="=========================\n"+
                this.codigo+"\n"+
                "Nombre: "+this.producto+"\n";
                res+="Categoría:";
                res+=this.tipos.get(this.cat);
                res+="Precio: "+this.precio+"\n"+
                "Stock: "+this.stock+"\n"+
                "Unidades vendidas: "+this.unidades+"\n"+
                "Coste por unidad: "+this.pr_un+"\n"+
                "Fabricante: "+this.fabricante+"\n"+
                "=========================";              
                
       return res;
    }
    
    
    
    
    
    
}
