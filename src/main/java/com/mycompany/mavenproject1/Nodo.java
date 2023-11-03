/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.util.ArrayList;
import java.io.PrintWriter;
/**
 *
 * @author Alexia Iracheta
 */
public class Nodo {
    //atrubutos de los Nodos
    public char nodoValor;
    public Nodo padre;
    public ArrayList<Nodo> hijos;
    public int miIndice = -1;
    
    public Nodo(Nodo padreInput, char nodoValorInput){
        //Constructor
        this.padre = padreInput;
        this.nodoValor = nodoValorInput;
        hijos = new ArrayList<>();
    }
    
    public void AgregarHijo(char nodoValorInput){
    //Agregara un hijo al nodo actual.
       Nodo hijo = new Nodo(this, nodoValorInput);
       hijo.miIndice = hijos.size();
       hijos.add(hijo);
    }
    
    public void Remover(PrintWriter out){
    //Eliminara al nodo actual y sus hijos se enlazaran con el padre.
        int cantidad = hijos.size();
        Nodo aux;
        
        //Se agregan los hijos del nodo a su padre enlazandolos entre si
        for (int i = 0; i < cantidad; i++){
            aux = hijos.get(i);
            padre.AgregarHijo(aux.nodoValor);
        }
        //Se elimina el nodo en base al indice que se encuentra, y al finalizar recorre el indice para que el nodo de la derecha tome su lugar y asi sucesivamente dependiendo de la cantidad de hijos del padre.
        cantidad= padre.hijos.size();
        for (int i = miIndice +1 ; i < cantidad; i++){
            aux = padre.hijos.get(i);
            aux.miIndice--; 
        }
        out.println("Se removera el valor en el indice: "+miIndice+ " del arbol" + "<br>");
        padre.hijos.remove(miIndice);
        
        
    }

    public void Visualizar(PrintWriter out){
    //Imprimira el arbol actual.
        int cantidad = hijos.size();
        //String respuesta = "";
        Nodo aux;
        
        //Iniciamos con el padre, y vamos imprimiendo los hijos dependiendo de la cantidad de hijos del padre
        out.print(nodoValor + " - Hijos: ");
        //respuesta = respuesta + nodoValor + "- Hijos: ";
        for (int i = 0; i < cantidad; i++){
            aux = hijos.get(i);
            out.print(aux.nodoValor+",");
        }
        //Se imprimen los hijos de los hijos \n
        out.println("<br>");
        for (int i = 0; i < cantidad; i++){
            aux = hijos.get(i);
            aux.Visualizar(out);
        }
        //Si no tiene hijos enter
        if(cantidad == 0)out.println("<br>");
        
    }
    
    public static void principal(PrintWriter out) {
        Nodo Arbol = new Nodo(null,'1');
        Arbol.AgregarHijo('3');
        Arbol.AgregarHijo('5');
        Arbol.AgregarHijo('7');
        Arbol.hijos.get(0).AgregarHijo('6');
        Arbol.hijos.get(0).hijos.get(0).AgregarHijo('4');
        Arbol.hijos.get(1).AgregarHijo('8');
        Arbol.Visualizar(out);
        Arbol.hijos.get(1).Remover(out);
        Arbol.Visualizar(out);
        Arbol.hijos.get(1).Remover(out);
        Arbol.Visualizar(out);
    }
}



