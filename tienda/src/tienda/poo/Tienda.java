
package tienda.poo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Tienda {
    private String nombre_tienda;
    private HashMap<String,Producto> almacen;
    ////////1
    public Tienda(String nombre_tienda){
        this.nombre_tienda=nombre_tienda;
        this.almacen=new HashMap<>();
    }
    ////////2
    public String toString(){
        String res="";
        
        res+="=====================\n";
        res+="Nombre de la tienda "+this.nombre_tienda+"\n";
        if(this.almacen.values().size()>0){
            for (Producto pro : this.almacen.values()) {
                res+=pro.toString()+"\n";
            }
        }else{
            res+="NO HAY DATOS AÚN";
        }
        
        res+="=====================\n";        
        
        return res;
    }
    ////////3
    public String filtrarNombre(String nombre){
        String res="";
        
        for (Producto pro : this.almacen.values()) {
            if(pro.getProducto().toLowerCase().contains(nombre.toLowerCase())){
                res+=pro.toString();
            }
        }
        
        if(res.equals("")){
            res="NO HAY COINCIDENCIAS";
        }
        
        return res;
    }
    
    private Producto buscarProd(String nombre){
        return this.almacen.get(nombre);
    }
    ////////4
    public void añadirProd(String producto,double precio,double pr_un,int stock,char cat,String fabricante){
        Producto nuevo,busqueda;
        
        busqueda=this.buscarProd(producto);
        
        if(busqueda==null){
            nuevo=new Producto(producto,precio,stock,0,cat,fabricante,pr_un);
            this.almacen.put(producto, nuevo);
            System.out.println("Producto añadido");
        }else{
            System.out.println("Error. El producto ya existe.");
        }
    }
    ////////5
    public void borrarProdNom(String nombre){
        Producto busqueda;
        
        busqueda=this.buscarProd(nombre);
        if(busqueda!=null){
            this.almacen.remove(nombre);
            System.out.println("Producto borrado con éxito");
        }else{
            System.out.println("El producto no existe");
        }
    }
    ////////6
    public void venderProd(String nombre,int cantidad){
        Producto busqueda;
        
        busqueda=buscarProd(nombre);
        
        if(busqueda!=null){
            busqueda.vender(cantidad);
        }else{
            System.out.println("El producto no existe");
        }
    }
    //REPONER STOCK
    public void stock(){
        for (Producto prod : this.almacen.values()) {
            if(prod.getStock()>0 && prod.getStock()<prod.getUnidades()){
              int stock_actual=prod.getStock();
              int aumento=stock_actual*20/100;
              prod.reponerStock(aumento);
            }                
        }
    }
    ////////////////////////
    ////////8
    public String alfabetica(){
        String res="";
        
        ArrayList<Producto> productos=new ArrayList<>(this.almacen.values());
        
        productos.sort((a,b)->a.getProducto().compareTo(b.getProducto()));
        
        for (Producto prod : productos) {
            res+=prod.toString();
        }
        
        return res;
    }
    ////////9
    private ArrayList<Producto> beneficioTotal(){
        ArrayList<Producto> res=new ArrayList<>();
        
        for (Producto prod : this.almacen.values()) {
            res.add(prod);
        }
        
        return res;
    }

    public String menorBeneficio(){
        String res="";
        Producto menor;
        ArrayList<Producto> beneficio=this.beneficioTotal();
        
       if(beneficio.size()>0){
           beneficio.sort((a,b)->Double.compare(a.beneficio(),b.beneficio()));
           
           menor=beneficio.get(0);
           res=menor.toString();
       }else{
           res="NO HAY PRODUCTOS AÚN";
       }
        
        return res;
    }
    ////////11
    public void aumentarPrecio(String nombre,double cantidad){
        Producto busqueda;
        
        busqueda=this.buscarProd(nombre);
        
        if(busqueda!=null){
            busqueda.subirPrecio(cantidad);
        }else{
            System.out.println("El producto no existe.");
        }
    }
    ////////12
    public void rebajar(String nombre,double cantidad){
        Producto busqueda;
        
        busqueda=this.buscarProd(nombre);
        if(busqueda!=null){
            busqueda.bajarPrecio(cantidad);
        }else{
            System.out.println("El producto no existe.");
        }
    }
    ////////13
    public void borrarProd(){//PREGUNTAR, PORQUE SIEMPRE VA A HABER MÁS STOCK QUE VENTAS
        Iterator<Producto> iter=this.almacen.values().iterator();
        
        while(iter.hasNext()){
            Producto actual=iter.next();
            if(actual.getStock()>actual.getUnidades()){
                iter.remove();
            }
        }
    }
    ////////14
    private ArrayList<Producto> porCategoria(char cat){
        ArrayList<Producto> res=new ArrayList<>();
        
        for (Producto prod : this.almacen.values()) {
            if(prod.getCat()==cat){
                res.add(prod);
            }
        }
        
        return res;        
    }
    
    public String masCara(char cat){
        String res="";
        Producto mayor;
        
        ArrayList<Producto> porCat=this.porCategoria(cat);
        
        if(porCat.size()>0){
            porCat.sort((a,b)->Double.compare(b.getPrecio(),a.getPrecio()));
            mayor=porCat.get(0);
            
            res=mayor.toString();
        }else{
            res="No hay productos de esa categoría";
        }        
        
        return res;
    }
    ////////15
    private HashMap<Character,Double> totalTipos(){
        HashMap<Character,Double> suma=new HashMap<>();
        
        for (Producto prod : this.almacen.values()) {
            if(suma.containsKey(prod.getCat())){
                double actual=suma.get(prod.getCat());
                suma.put(prod.getCat(),actual+prod.beneficio());
            }else{
                suma.put(prod.getCat(),prod.beneficio());
            }
        }
        
        return suma;
    }
    
    public String beneficiosCat(){
        String res="";
        
        HashMap<Character,Double> suma=this.totalTipos();
        
        for (Entry<Character,Double> entrada:suma.entrySet()) {
            res+=Producto.getNomCat(entrada.getKey())+"->"+entrada.getValue()+"\n";
        }
        
        return res;
    }
    
    //
    ////////16
    private HashMap<String,Integer> totalDist(){
        HashMap<String,Integer> sumaDis=new HashMap<>();
        
        for (Producto prod : this.almacen.values()) {
            if(sumaDis.containsKey(prod.getFabricante())){
                int actual=sumaDis.get(prod.getFabricante());
                sumaDis.put(prod.getFabricante(),actual+prod.getStock());
            }else{
                sumaDis.put(prod.getFabricante(),prod.getStock());
            }
        }
        
        return sumaDis;
    }
    
    public String proveedorMasVendido(){
        ArrayList<Entry<String,Integer>> entradas=new ArrayList<>(this.totalDist().entrySet());
        String prov;
        
        if(this.almacen.size()>0){
            entradas.sort((a,b)->Integer.compare(b.getValue(),a.getValue()));        
            prov=entradas.get(0).getKey()+" con "+entradas.get(0).getValue();
        }else{
            prov="NO HAY PRODUCTOS ACTUALMENTE.";
        }
        
        
        return prov;
    }
    
      
    
    
    
}


