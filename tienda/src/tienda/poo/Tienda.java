package tienda.poo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Tienda {

    private String nombre_tienda;
    private HashMap<String, Producto> almacen;

    ////////1
    public Tienda(String nombre_tienda) {
        this.nombre_tienda = nombre_tienda;
        this.almacen = new HashMap<>();
    }

    ////////2
    public String toString() {
        String res = "";

        res += "=====================\n";
        res += "Nombre de la tienda " + this.nombre_tienda + "\n";
        if (this.almacen.values().size() > 0) {
            for (Producto pro : this.almacen.values()) {
                res += pro.toString() + "\n";
            }
        } else {
            res += "NO HAY DATOS AÚN\n";
        }

        res += "=====================\n";

        return res;
    }

    ////////3
    public String filtrarNombre(String nombre) {
        String res = "";

        for (Producto pro : this.almacen.values()) {
            if (pro.getProducto().toLowerCase().contains(nombre.toLowerCase())) {
                res += pro.toString();
            }
        }

        if (res.equals("")) {
            res = "NO HAY COINCIDENCIAS";
        }

        return res;
    }

    private Producto buscarProd(String nombre) {
        return this.almacen.get(nombre);
    }

    ////////4
    public void añadirProd(String producto, double precio, double pr_un, int stock, char cat, String fabricante) {
        Producto nuevo, busqueda;

        busqueda = this.buscarProd(producto);

        if (busqueda == null) {
            nuevo = new Producto(producto, precio, pr_un, stock, 0, cat, fabricante);
            this.almacen.put(producto, nuevo);
        } else {
           throw new TiendaException("Error. El producto ya existe.");
        }
    }

    ////////5
    public void borrarProdNom(String nombre) {
        Producto busqueda;

        busqueda = this.buscarProd(nombre);
        if (busqueda != null) {
            this.almacen.remove(nombre);
        } else {
            throw new TiendaException("El producto no existe");
        }
    }

    ////////6
    public void venderProd(String nombre, int cantidad) {
        Producto busqueda;

        busqueda = buscarProd(nombre);

        if (busqueda != null) {
            busqueda.vender(cantidad);
        } else {
            throw new TiendaException("El producto no existe");
        }
    }

    //REPONER STOCK
    public void stock() {
        for (Producto prod : this.almacen.values()) {
            if (prod.getStock() > 0 && prod.getStock() < prod.getUnidades()) {
                int stock_actual = prod.getStock();
                int aumento = stock_actual * 20 / 100;
                prod.reponerStock(aumento);
            }
        }
    }

    ////////////////////////
    ////////8
    public String alfabetica() {
        String res = "";

        ArrayList<Producto> productos = new ArrayList<>(this.almacen.values());

        productos.sort((a, b) -> a.getProducto().compareTo(b.getProducto()));
        if (this.almacen.size() > 0) {
            for (Producto prod : productos) {
                res += prod.toString();
            }
        } else {
            res = "NO HAY PRODUCTOS";
        }

        return res;
    }

    ////////9
    private ArrayList<Producto> beneficioTotal() {
        ArrayList<Producto> res = new ArrayList<>();

        for (Producto prod : this.almacen.values()) {
            res.add(prod);
        }

        return res;
    }

    public String menorBeneficio() {
        String res = "";
        Producto menor;
        ArrayList<Producto> beneficio = this.beneficioTotal();

        if (beneficio.size() > 0) {
            beneficio.sort((a, b) -> Double.compare(a.beneficio(), b.beneficio()));

            menor = beneficio.get(0);
            res = menor.toString();
        } else {
            res = "NO HAY PRODUCTOS AÚN";
        }

        return res;
    }

    ////////11
    public void aumentarPrecio(String nombre, double cantidad) {
        Producto busqueda;

        busqueda = this.buscarProd(nombre);

        if (busqueda != null) {
            busqueda.subirPrecio(cantidad);
        } else {
            throw new TiendaException("El producto no existe.");
        }
    }

    ////////12
    public void rebajar(String nombre, double cantidad) {
        Producto busqueda;

        busqueda = this.buscarProd(nombre);
        if (busqueda != null) {
            busqueda.bajarPrecio(cantidad);
        } else {
            throw new TiendaException("El producto no existe.");
        }
    }

    ////////13
    public void borrarProd() {
        Iterator<Producto> iter = this.almacen.values().iterator();

        while (iter.hasNext()) {
            Producto actual = iter.next();
            if (actual.getStock() > actual.getUnidades()) {
                iter.remove();
            }
        }
    }

    ////////14
    private ArrayList<Producto> porCategoria(char cat) {
        ArrayList<Producto> res = new ArrayList<>();

        for (Producto prod : this.almacen.values()) {
            if (prod.getCat() == cat) {
                res.add(prod);
            }
        }

        return res;
    }

    public String masCara(char cat) {
        String res = "";
        Producto mayor;

        ArrayList<Producto> porCat = this.porCategoria(cat);

        if (porCat.size() > 0) {
            porCat.sort((a, b) -> Double.compare(b.getPrecio(), a.getPrecio()));
            mayor = porCat.get(0);

            res = mayor.toString();
        } else {
            res = "No hay productos de esa categoría";
        }

        return res;
    }

    ////////15
    private HashMap<Character, Double> totalTipos() {
        HashMap<Character, Double> suma = new HashMap<>();

        for (Producto prod : this.almacen.values()) {
            if (suma.containsKey(prod.getCat())) {
                double actual = suma.get(prod.getCat());
                suma.put(prod.getCat(), actual + prod.beneficio());
            } else {
                suma.put(prod.getCat(), prod.beneficio());
            }
        }

        return suma;
    }

    public String beneficiosCat() {
        String res = "";

        HashMap<Character, Double> suma = this.totalTipos();
        if (this.almacen.size() > 0) {
            for (Entry<Character, Double> entrada : suma.entrySet()) {
                res += Producto.getNomCat(entrada.getKey()) + "->" + entrada.getValue() + "\n";
            }
        }else{
            res="NO HAY PRODUCTOS ACTUALMENTE.";
        }
        

        return res;
    }

    //
    ////////16
    private HashMap<String, Integer> totalDist() {
        HashMap<String, Integer> sumaDis = new HashMap<>();

        for (Producto prod : this.almacen.values()) {
            if (sumaDis.containsKey(prod.getFabricante())) {
                int actual = sumaDis.get(prod.getFabricante());
                sumaDis.put(prod.getFabricante(), actual + prod.getUnidades());
            } else {
                sumaDis.put(prod.getFabricante(), prod.getUnidades());
            }
        }

        return sumaDis;
    }

    public String proveedorMasVendido() {
        ArrayList<Entry<String, Integer>> entradas = new ArrayList<>(this.totalDist().entrySet());
        String prov="";

        if (this.almacen.size() > 0) {
            entradas.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
            if(entradas.get(0).getValue()==0){
                prov+="Aún no se ha vendido nada.";
            }else{
                prov += entradas.get(0).getKey()+" con "+entradas.get(0).getValue();
            }            
        } else {
            prov += "NO HAY PRODUCTOS ACTUALMENTE.";
        }

        return prov;
    }
    
    public void cargarProducto(String nombre_fichero){
        try{
            FileReader fr=new FileReader(nombre_fichero);
            BufferedReader br=new BufferedReader(fr);
            String linea,producto,fabricante;
            String[] partes,cabecera;
            double precio,precio_un;
            int stock,unidades;
            char cat;
            
            HashMap<String,String> datos=new HashMap<>();
            linea=br.readLine();
            cabecera=linea.split(":");
            
            while((linea=br.readLine())!=null){
                partes=linea.split(":");
                
                for (int i = 0; i < cabecera.length; i++) {
                    datos.put(cabecera[i],partes[i]);
                }
                
                producto=datos.get("producto");
                precio=Double.parseDouble(datos.get("precio"));
                stock=Integer.parseInt(datos.get("stock"));
                unidades=Integer.parseInt(datos.get("unidades"));
                cat=datos.get("cat").charAt(0);
                fabricante=datos.get("fabricante");
                precio_un=Double.parseDouble(datos.get("precio_un"));
                
                this.añadirProd(producto, precio, precio_un, stock, cat, fabricante);
            }
            
            br.close();
            fr.close();
        }catch(FileNotFoundException fnf){
            throw new TiendaException("Fichero no encontrado");
        }catch(IOException io){
            throw new TiendaException("Error de lectura del fichero");
        }catch(NumberFormatException nfe){
            throw new TiendaException("Error en formato numérico");
        }
    }
    
    public void guardarProducto(String nombre_fichero){
        try{
            FileWriter fw=new FileWriter(nombre_fichero);
            PrintWriter pw=new PrintWriter(fw);
            
            pw.println("producto:precio:stock:unidades:cat:fabricante:precio_un");
            
            for (Producto prod : this.almacen.values()) {
                pw.println(prod.getProducto()+":"+prod.getPrecio()+":"+prod.getStock()+":"+
                        prod.getUnidades()+":"+prod.getCat()+":"+prod.getFabricante()+":"+
                        prod.getPr_un());
            }
            
            pw.close();
            fw.close();
        }catch(IOException io){
            throw new TiendaException("Fallo de escritura del fichero");
        }
    }

}
