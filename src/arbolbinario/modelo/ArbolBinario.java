/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carloaiza
 */
public class ArbolBinario {
        int cant = 0;
         int altura;
    private Nodo raiz;

    //public void adicionarNodo()
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }

    public void adicionarNodo(int dato, Nodo ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new Nodo(dato);

        } else {
            if (dato < ubicacion.getDato()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato > ubicacion.getDato()) {
                if (ubicacion.getDerecha() == null) {
                    ubicacion.setDerecha(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }
        }
    }
    
     /**
     * Método que retorna un arreglo de enteros con los datos de recorrer el
     * árbol en postorden
     *
     * @return ArrayList
     * @throws ArbolBinarioException
     */
    public ArrayList postOrden() throws ArbolBinarioException {
        //isLleno();
        ArrayList l = new ArrayList();
        if (raiz != null) {
            postOrden(raiz, l);
        }        
        return l;
    }
    /**
     * Método recursivo que recorre todo el árbol en preorden
     * @param temp Ayudante que toma referencia en un nodo
     * @param listado Acumulador para registrar el dato del nodo visitado
     */
    private void postOrden(Nodo temp, ArrayList listado)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {           
            postOrden(temp.getIzquierda(), listado);
            postOrden(temp.getDerecha(), listado);
            listado.add(temp.getDato());
        }
    }
    

    /**
     * Método que retorna un arreglo de enteros con los datos de recorrer el
     * árbol en preorden
     *
     * @return ArrayList
     * @throws ArbolBinarioException
     */
    public ArrayList preOrden() throws ArbolBinarioException {
        //isLleno();
        ArrayList l = new ArrayList();
        if (raiz != null) {
            preOrden(raiz, l);
        }        
        return l;
    }
    /**
     * Método recursivo que recorre todo el árbol en preorden
     * @param temp Ayudante que toma referencia en un nodo
     * @param listado Acumulador para registrar el dato del nodo visitado
     */
    private void preOrden(Nodo temp, ArrayList listado)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {
            listado.add(temp.getDato());
            preOrden(temp.getIzquierda(), listado);
            preOrden(temp.getDerecha(), listado);
        }
    }
    /**
     * Metodo recursivo que acumula todos los datos a medida que hace el inorden
     * @return
     * @throws ArbolBinarioException 
     */
    public ArrayList inOrden() throws ArbolBinarioException {
        isLleno();
        ArrayList l = new ArrayList();
        inOrden(raiz, l);
        return l;
    }
/**
 * Medoto recursivo que recorre el arbol en inOrden
 * @param reco Ayudante que toma referencia en un nodo
 * @param l Acumulador para registrar el dato del nodo visitado
 */
    private void inOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(), l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(), l);
        }
    }
/**
 * metodo que nos permite guardando los datos para conformar el arbol
 * @param datos acumulador que permite registrar el nuevo dato para el arbol
 * @throws ArbolBinarioException 
 */
    public void llenarArbol(String datos) throws ArbolBinarioException {
        String[] arrayDatos = datos.split(",");
        for (String cadena : arrayDatos) {
            adicionarNodo(Integer.parseInt(cadena), raiz);
        }

    }
    
    
    /**
     * Método que retorna la cantidad de nodos del arbol
     * @return int cantidad de nodos
     */
    public String contarNodos(){
        cant =0 ;
       contarNodos (raiz);
       return " " +cant;
    }
    /**
     * metodo que nos permite recorrer el arbol e indicando cuantos nodos hay 
     * en el arbol
     * @param reco ayudante que permite llevar el conteo de los nodos
     */
    private void contarNodos(Nodo reco)
    {
      if (reco != null) {
            cant++;
            contarNodos(reco.getIzquierda());
            contarNodos(reco.getDerecha());
        }
       
    }
    
    /**
     * metodo nos permite llevar el conteo de las hojas que contiene el arbol
     * @return 
     */
    public String cantidadNodosHoja() {
        cant = 0;
        cantidadNodosHoja(raiz);
        return""+cant;
    }
    /**
     * Metodo que recorre el arbol e identifica las hojas que tiene el arbol
     * @param reco ayudante que permite llevar el conteo de las hojas de cada 
     * diferente rama
     */
    private void cantidadNodosHoja(Nodo reco) {
      if (reco != null) {
            if (reco.getIzquierda() == null && reco.getDerecha() == null) {
                cant++;
            }
            cantidadNodosHoja(reco.getIzquierda());
            cantidadNodosHoja(reco.getDerecha());
        }
    }
    /**
     * Metodo que identifica el menor valor del arbol
     * @return 
     */
   public String menorValor() {
        
         Nodo reco = raiz;
         
        if (raiz == null){
            return "0";
        }
        if (raiz != null) {
            
            while (reco.getIzquierda() != null) {
                reco = reco.getIzquierda();
            }
        }
       return (" "  + reco.getDato());
    }
   
   /**
    * Metodo que identifica el mayor valor del arbol
    * @return 
    */
   public String mayorValor() {
        Nodo reco = raiz;
         if (raiz == null){
            return "0";
        }
        if (raiz != null) {
            while (reco.getDerecha() != null) {
                reco = reco.getDerecha();
            }
        }
        return ("" + reco.getDato());
    }
   
    int subizq = 0;
    int subder = 0;
    
    /**
     * Metodo que  permite visualizar el balance que hay en el arbol
     * @return 
     */
    
    public String imprimirBalance() {
         subizq = 0;
         subder = 0;

        Balance(this.raiz, true, 0);
        
        if (subizq - subder == 0) {
            return ("El balance es: 0 ");
        } else if (subizq - subder == -1) {
            return("El balance es -1, derecha");
        } else if (subizq - subder == 1) {
            return("El balance 1, izquierda");

        } else {
            return("No es balanceado.."
                    + "porque es mas grande el lado "
                    + ((subizq > subder) ? "Izquierdo" : "Derecho"));
        }

    }
    /**
     * Metodo que permite identicar como esta el nivel del arbol
     * @param reco ayudante que permite recorrer el arbol
     * @param lado ayudante que permite identificar el lado del arbol
     * @param i     ayudante que ayuda a contar los nodos del arbol
     */
    public void Balance(Nodo reco, boolean lado, int i) {

        if (reco != null) {

            if (reco.getDerecha()== null && reco.getIzquierda() == null) {
                if (lado) {
                    subder = (i > subder) ? i : subder;
                } else {
                    subizq = (i > subizq) ? i : subizq;
                }
            }

            Balance(reco.getDerecha(), lado, i + 1);
            if (i == 0) {
                lado = false;
            }
            Balance(reco.getIzquierda(), lado, i + 1);
        }

    }
    /**
     * Metodo que nos permite borrar elnumero menor del arbol 
    */
    public String borrarMenor() {

        Nodo reco=raiz.getIzquierda();
        
        if (raiz != null) {
            if (raiz.getIzquierda() == null) {
                raiz = raiz.getDerecha();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getIzquierda();
                while (reco.getIzquierda() != null) {
                    anterior = reco;
                    reco = reco.getIzquierda();
                }
                
                anterior.setIzquierda(reco.getDerecha());
            }
        }
         return ("Valor eliminado: " + reco.getDato());
    }
    /**
     * Metodo que permite borrar el numero mayor del arbol
     * @return 
     */
    public String borrarMayor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getDerecha() == null) {
                raiz = raiz.getIzquierda();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getDerecha();
                while (reco.getDerecha() != null) {
                    anterior = reco;
                    reco = reco.getDerecha();
                }
                
                anterior.setDerecha(reco.getIzquierda());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
    
   int numeroRamas = 0;
    public ArrayList<String>ObtenerRamamayor(){
    obtenernumeroRamas(this.raiz, 0);
    return ObtenerRamamayor(this.raiz, 0, "", new ArrayList<String>());
    }
    /**
     * metodo que nos permite identificar cual es la rama mas grande 
     * @param pivote ayudante que permite recorre el arbol identifcar cual es la rama
     * mayor
     * @param contador lleva el conteo de las cantidades de nodos que hay en cada rama
     */
    public void obtenernumeroRamas(Nodo pivote, int contador) {
        if (pivote != null) {
            contador++;
            obtenernumeroRamas(pivote.getIzquierda(), contador);
            obtenernumeroRamas(pivote.getDerecha(), contador);
        }
        if (contador > this.numeroRamas) {
            this.numeroRamas = contador;
        }
    }
public ArrayList<String> ObtenerRamamayor(Nodo pivote, int contador, String dato, ArrayList lista){
        if (pivote != null ) {
            dato+=pivote.getDato()+",";
            contador ++;
            lista=ObtenerRamamayor(pivote.getIzquierda(), contador, dato, lista);
            lista=ObtenerRamamayor(pivote.getDerecha(), contador, dato, lista);
            
            if (contador == this.numeroRamas) {
                lista.add(dato);
            }
        }
        return lista;
    }
public ArrayList getHojas() {
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }
/**
 * @param getHojas: Este metodo nos permite identicar los nodos que se encuentran en el ultimo nivel
 * que son conocido como las hojas del arbol.
 */
    private void getHojas(Nodo r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzquierda(), l);
            getHojas(r.getDerecha(), l);
        }

    }
    protected boolean esHoja(Nodo x) {
        return (x != null && x.getIzquierda() == null && x.getDerecha() == null);
    }
public void podar() {
        podar(this.raiz);
    }

    private void podar(Nodo x) {
        if (x == null) {
            return;
        }
        if (this.esHoja(x.getIzquierda())) {
            x.setIzquierda(null);
        }
        if (this.esHoja(x.getDerecha())) {
            x.setDerecha(null);
        }
        podar(x.getIzquierda());
        podar(x.getDerecha());
    }
    
    public boolean cambiar() {
            cambiar(raiz, 1);    
            return true;
    }

    private void cambiar(Nodo reco, int nivel) {
        if (reco != null) {
            reco.setDato(reco.getDato() * 3);
            cambiar(reco.getIzquierda(), nivel + 1);
            cambiar(reco.getDerecha(), nivel + 1);
        }
    }

  
}
