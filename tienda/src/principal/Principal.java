package principal;
import java.util.InputMismatchException;
import tienda.poo.Tienda;
import java.util.Scanner;
import tienda.poo.TiendaException;
public class Principal {
    
    public static void main (String[] args){
        Scanner teclado=new Scanner(System.in);
        
        Tienda n_tienda=new Tienda("La licorería de la tía Antonia");
        //PRODUCTOS INICIALES
//        n_tienda.añadirProd("Alhambra Especial", 2.5, 1.25, 300, 'C', "Cervezas Alhambra S.A");
//        n_tienda.añadirProd("Alhambra 1925", 3, 2, 150, 'C', "Cervezas Alhambra S.A");
//        n_tienda.añadirProd("Chivas 12 años", 12.7, 10, 50, 'W', "Pernod Ricard");
//        n_tienda.añadirProd("Chivas 15 años", 16.5, 12.2, 25, 'W', "Pernod Ricard");
//        n_tienda.añadirProd("Cune Crianza 2019", 48.1, 37, 15, 'V', "Bodegas Bela");
//        n_tienda.añadirProd("Tanqueray", 32.95, 27.8, 40, 'G', "Diageo España S.A");
//        n_tienda.añadirProd("Barceló", 14.30, 10.75, 50, 'R', "Ron Barceló S.R.L");
        
        int opcion=1,stock,cantidad;
        double precio,pr_un;
        String nombre,dis,fichero;
        char cat;
        
        do{
            try{
                System.out.println("0.Salir");
                System.out.println("1.Ver todos los productos");
                System.out.println("2.Filtrar productos por nombre");
                System.out.println("3.Añadir producto");
                System.out.println("4.Borrar producto por nombre");
                System.out.println("5.Vender producto");
                System.out.println("6.Reponer producto");
                System.out.println("7.Productos en orden alfabético");
                System.out.println("8.Producto con menor beneficio");
                System.out.println("9.Subir precio");
                System.out.println("10.Bajar precio");
                System.out.println("11.Borrar producto con pocas ventas");
                System.out.println("12.Más caro por categoría");
                System.out.println("13.Beneficios por categoría");
                System.out.println("14.Fabricante más vendido");
                System.out.println("15.Cargar datos desde un fichero");
                System.out.println("16.Guardar datos en un fichero");
                System.out.println("Elige una opción");
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
                    System.out.println("Producto añadido con éxito");
                    break;
                case 4:
                    System.out.println("Nombre del producto");
                    nombre=teclado.nextLine();
                    n_tienda.borrarProdNom(nombre);
                    System.out.println("Producto borrado con éxito");
                    break;
                case 5:
                    System.out.println("Nombre del producto a vender:");
                    nombre=teclado.nextLine();
                    System.out.println("Cantidad para vender:");
                    cantidad=teclado.nextInt();                    
                    n_tienda.venderProd(nombre, cantidad);
                    System.out.println("Cantidad vendida con éxito");
                    break;
                case 6:
                    n_tienda.stock();
                    System.out.println("Tienda repuesta");
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
                    System.out.println("Producto actualizado");
                    break;
                case 10:
                    System.out.println("Nombre del producto");
                    nombre=teclado.nextLine();
                    System.out.println("Precio nuevo");
                    precio=teclado.nextDouble();
                    
                    n_tienda.rebajar(nombre, precio);
                    System.out.println("Producto actualizado");
                    break;
                case 11:
                    n_tienda.borrarProd();
                    System.out.println("Productos borrados");
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
                case 15:
                    System.out.println("Nombre del fichero");
                    fichero=teclado.next();
                    teclado.nextLine();
                    n_tienda.cargarProducto(fichero);
                    System.out.println("Datos cargados con éxito.");
                    break;
                case 16:
                    System.out.println("Nombre del fichero");
                    fichero=teclado.next();
                    teclado.nextLine();
                    n_tienda.guardarProducto(fichero);
                    System.out.println("Datos guardados con éxito.");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                }        
            }catch(InputMismatchException ime){
                System.out.println("Formato de entrada inválido");
                teclado.nextLine();
            }catch(TiendaException te){
                System.out.println(te.getMessage());
            }
        }while(opcion!=0);
        
    }
}
