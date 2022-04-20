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
        
    }
}
