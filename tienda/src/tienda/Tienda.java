
package tienda;

import tienda.poo.Producto;

public class Tienda {

    public static void main(String[] args) {
        
        Producto p1=new Producto("Alhambra",2.3,300,175,'C',"Alhambra S.L",1);
        Producto p2=new Producto("Chivas 12",15,200,'W',"Chivas S.A",10);
        Producto p3=new Producto(p2);
        
        //SELECTORES
//        System.out.println("Código: "+p2.getCodigo());
//        System.out.println("Producto: "+p2.getProducto());
//        System.out.println("Precio: "+p2.getPrecio());
//        System.out.println("Stock: "+p2.getStock());
//        System.out.println("Unidades vendidas: "+p2.getUnidades());
//        System.out.println("Categoría: "+p2.getCat());
//        System.out.println("Coste unitario: "+p2.getPr_un());
//        System.out.println("Fabricante: "+p2.getFabricante());
        
        //BENEFICIO
        //System.out.println(p1.beneficio());
        //SUBIR PRECIO
//        p1.subirPrecio(2.5);
//        System.out.println(p1.getPrecio());
        //BAJAR PRECIO
//        p1.bajarPrecio(1);
//        System.out.println(p1.getPrecio());
        //VENDER
//        p2.vender(20);
//        System.out.println(p2.getUnidades());
//        System.out.println(p2.beneficio());
        //CAMBIAR EL TIPO
        //p2.cambiarTipo('W');
        //System.out.println(p2.getCat());
        //REPONER STOCK
        //p1.reponerStock(200);
        //System.out.println(p1.getStock());
        //CAMBIAR EL PRECIO UNITARIO
        //p1.cambiarPu(2);
        //System.out.println(p1.getPr_un());
        
        //TO STRING
        //System.out.println(p1.toString());
        //System.out.println(p2.toString());
    }
    
}
