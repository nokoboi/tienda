package principal;
import tienda.poo.Tienda;
import java.util.Scanner;
public class Principal {
    
    public static void main (String[] args){
        Scanner teclado=new Scanner(System.in);
        
        Tienda n_tienda=new Tienda("La licorería de la tía Antonia");
        //PRODUCTOS INICIALES
        n_tienda.añadirProd("Alhambra Especial", 2.5, 1.25, 300, 'C', "Cervezas Alhambra S.A");
        n_tienda.añadirProd("Alhambra 1925", 3, 2, 150, 'C', "Cervezas Alhambra S.A");
        n_tienda.añadirProd("Chivas 12 años", 12.7, 10, 50, 'W', "Pernod Ricard");
        n_tienda.añadirProd("Chivas 15 años", 16.5, 12.2, 25, 'W', "Pernod Ricard");
        n_tienda.añadirProd("Cune Crianza 2019", 48.1, 37, 15, 'V', "Bodegas Bela");
        n_tienda.añadirProd("Tanqueray", 32.95, 27.8, 40, 'G', "Diageo España S.A");
        n_tienda.añadirProd("Barceló", 14.30, 10.75, 50, 'R', "Ron Barceló S.R.L");
        
        int opcion,stock,cantidad;
        double precio,pr_un;
        String nombre,dis;
        char cat;
        
        do{
            System.out.println("0.Salir");
            System.out.println("1.Ver todos los productos");//BIEN
            System.out.println("2.Filtrar productos por nombre");//BIEN
            System.out.println("3.Añadir producto");//BIEN
            System.out.println("4.Borrar producto por nombre");//BIEN
            System.out.println("5.Vender producto");//BIEN
            System.out.println("6.Reponer producto");//BIEN
            System.out.println("7.Productos en orden alfabético");//BIEN
            System.out.println("8.Producto con menor beneficio");//BIEN
            System.out.println("9.Subir precio");//BIEN
            System.out.println("10.Bajar precio");//BIEN
            System.out.println("11.Borrar producto con pocas ventas");//BIEN
            System.out.println("12.Más caro por categoría");//BIEN
            System.out.println("13.Beneficios por categoría");//BIEN
            System.out.println("14.Fabricante más vendido");//BIEN
            System.out.println("Elige una opción");//BIEN
            opcion=teclado.nextInt();
            teclado.nextLine();
            switch(opcion){
                case 0:
                    System.out.println("Cerrando aplicación");
                    break;
                case 1:
                    System.out.println(n_tienda.toString());
                    break;
                case 2:
                    System.out.println("Nombre del producto:");
                    nombre=teclado.nextLine();
                    System.out.println(n_tienda.filtrarNombre(nombre));
                    break;
                case 3:
                    System.out.println("Nombre del producto");
                    nombre=teclado.nextLine();
                    System.out.println("Precio del producto");
                    precio=teclado.nextDouble();
                    System.out.println("Coste de producción");
                    pr_un=teclado.nextDouble();
                    System.out.println("Existencias del producto");
                    stock=teclado.nextInt();
                    System.out.println("Categoría del producto");
                    cat=teclado.next().charAt(0);
                    teclado.nextLine();
                    System.out.println("Fabricante del producto");
                    dis=teclado.nextLine();
                    
                    n_tienda.añadirProd(nombre, precio, pr_un, stock, cat, dis);
                    break;
                case 4:
                    System.out.println("Nombre del producto");
                    nombre=teclado.nextLine();
                    n_tienda.borrarProdNom(nombre);
                    break;
                case 5:
                    System.out.println("Nombre del producto a vender:");
                    nombre=teclado.nextLine();
                    System.out.println("Cantidad para vender:");
                    cantidad=teclado.nextInt();
                    
                    n_tienda.venderProd(nombre, cantidad);
                    break;
                case 6:
                    n_tienda.stock();
                    break;
                case 7:
                    System.out.println(n_tienda.alfabetica());
                    break;
                case 8:
                    System.out.println(n_tienda.menorBeneficio());
                    break;
                case 9:
                    System.out.println("Nombre del producto");
                    nombre=teclado.nextLine();
                    System.out.println("Precio nuevo");
                    precio=teclado.nextDouble();
                    
                    n_tienda.aumentarPrecio(nombre, precio);
                    break;
                case 10:
                    System.out.println("Nombre del producto");
                    nombre=teclado.nextLine();
                    System.out.println("Precio nuevo");
                    precio=teclado.nextDouble();
                    
                    n_tienda.rebajar(nombre, precio);
                    break;
                case 11:
                    n_tienda.borrarProd();
                    break;
                case 12:
                    System.out.println("Categoria del producto");
                    cat=teclado.next().charAt(0);
                    teclado.nextLine();
                    System.out.println("La más cara de "+cat+" es:\n"+n_tienda.masCara(cat));
                    break;
                case 13:
                    System.out.println(n_tienda.beneficiosCat());
                    break;
                case 14:
                    System.out.println(n_tienda.proveedorMasVendido());
                    break;        
                }                    
        }while(opcion!=0);
        
    }
}
