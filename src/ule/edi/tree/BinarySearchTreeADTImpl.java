package ule.edi.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import ule.edi.EmptyCollectionException;

/**
 * Árbol binario de búsqueda (binary search tree, BST).
 * 
 * @author profesor
 * 
 * @param <T>
 *            tipo de la información en cada nodo, comparable.
 */
public class BinarySearchTreeADTImpl<T extends Comparable<? super T>> extends
		AbstractBinaryTreeADT<T> {

	/**
	 * Devuelve el árbol binario de búsqueda izquierdo.
	 * 
	 * @return
	 */
	protected BinarySearchTreeADTImpl<T> getLeftBST() {
		return (BinarySearchTreeADTImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeADTImpl<T> left) {
		this.leftSubtree = left;
	}
	
	/**
	 * Devuelve el árbol binario de búsqueda derecho.
	 * 
	 * @return
	 */
	protected BinarySearchTreeADTImpl<T> getRightBST() {
		return (BinarySearchTreeADTImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeADTImpl<T> right) {
		this.rightSubtree = right;
	}
	
	/**
	 * Árbol BST vacío
	 */
	public BinarySearchTreeADTImpl() {
		
		setContent(null);
		
		setLeftBST(null);
		setRightBST(null);
	}

	private BinarySearchTreeADTImpl<T> emptyBST() {
		return new BinarySearchTreeADTImpl<T>();
	}
	
	/**
	 * Inserta todos los elementos de un array en el árbol.
	 * 
	 * @param elements elementos a insertar.
	 */
	@SuppressWarnings("unchecked")
	public void insert(T ... elements) {
		
		//	O todos o ninguno; si alguno es 'null', ni siquiera se comienza a insertar
		for (T i : elements) {
			if (i == null) {
				throw new IllegalArgumentException("No se aceptan elementos nulos");
			}
		}
		
		for (T j : elements) {
			insert(j);
		}
	}
	
	/**
	 * Inserta (como hoja) un nuevo elemento en el árbol de búsqueda.
	 * Si el elemento ya existe en el árbol NO lo inserta.
	 * 
	 * @param element
	 *            valor a insertar.
	 */
	public void insert(T element) {
		if (isEmpty()) {
			
			// Ya no es vacío, ahora es hoja
			setContent(element);
			
			//	Sus hijos son vacíos
			setLeftBST(emptyBST());
			setRightBST(emptyBST());
		} else {
			
			// Compara con el valor de la raíz
			int diff = element.compareTo(content);

			if (diff == 0) {
				// Ya está en el árbol
				return;
			}

			if (diff < 0) {
				// Es menor, irá en el sub-árbol izquierdo
				getLeftBST().insert(element);
			} else {
				// Es mayor, irá al sub-árbol derecho
				getRightBST().insert(element);
			}
		}
	}
	
	/*private void insert(BinarySearchTreeADTImpl<T> leaf, T element) {
		if (leaf.getLeftBST().height() <= leaf.getRightBST().height()) {
			leaf = leaf.getLeftBST();
			if(leaf.isEmpty()) {
				setContentAndLeafs(leaf, element);
			} else {
				insert(leaf, element);
			}
		} else {
			leaf = leaf.getRightBST();
			if(leaf.isEmpty()) {
				setContentAndLeafs(leaf, element);
			} else {
				insert(leaf, element);
			}
			
		}
	}
	
	private void setContentAndLeafs(BinarySearchTreeADTImpl<T> leaf, T element) {
		leaf.setContent(element);
		leaf.setLeftBST(new BinarySearchTreeADTImpl<T>());
		leaf.setRightBST(new BinarySearchTreeADTImpl<T>());
	}*/

	/**
	 * Elimina los valores en un array del árbol.
	 * 
	 * @param elements valores a eliminar.
	 */
	@SuppressWarnings("unchecked")
	public void withdraw(T ... elements) {
		for (T e : elements) {
			withdraw(e);
		}
	}
	
	private void p(String s) {
		System.out.println(s);
	}

	/*private ArrayList<BinarySearchTreeADTImpl<T>> findLeafInformation(T element) {
		ArrayList<BinarySearchTreeADTImpl<T>> result = new ArrayList<BinarySearchTreeADTImpl<T>>();
		Collections.addAll(result, null, null, null, null);
		if (! isEmpty()) {
			if (getContent().equals(element)) {
				return result;
			} else {
				if (leftSubtree != null && leftSubtree.getContent() != null && leftSubtree.getContent().equals(element)) {
					result.set(0, this);
					result.set(1, (BinarySearchTreeADTImpl<T>) leftSubtree);
					result.set(2, (BinarySearchTreeADTImpl<T>) leftSubtree.getSubtree(0));
					result.set(3, (BinarySearchTreeADTImpl<T>) leftSubtree.getSubtree(1));
					return result;
				} else if (rightSubtree != null && rightSubtree.getContent() != null && rightSubtree.getContent().equals(element)) {
					result.set(0, this);
					result.set(1, (BinarySearchTreeADTImpl<T>) rightSubtree);
					result.set(2, (BinarySearchTreeADTImpl<T>) rightSubtree.getSubtree(0));
					result.set(3, (BinarySearchTreeADTImpl<T>) rightSubtree.getSubtree(1));
					return result;
				} else {
					result = leftSubtree != null ? ((BinarySearchTreeADTImpl<T>) leftSubtree).findLeafInformation(element) : null;
					if (result.get(0) != null) {
						return result;
					} else {
						return rightSubtree != null ? ((BinarySearchTreeADTImpl<T>) rightSubtree).findLeafInformation(element) : null;
					}
				}
			}
		} else {
			return result;
		}
	}*/
	
	/**
	 * Elimina un elemento del árbol de búsqueda.
	 * 
	 * @param element
	 *            elemento a eliminar.
	 * @throws NoSuchElementException si el elemento a eliminar no está en el árbol           
	 */
	public void withdraw(T element) {
	
		if (element == null) {
			//	Eliminado (no estaba)
			return;
		}
		
		if (!isEmpty()) {
			// Compara con la información en la raíz
			int diff = element.compareTo(getContent());

			if (diff == 0) {
				
				// Eliminar este nodo

				// Si es una hoja
				if (isLeaf()) {
					setContent(null);
					setLeftBST(null);
					setRightBST(null);
				}
				//  Si tiene sub-árbol izquierdo pero no derecho
				else if (!getLeftBST().isEmpty() && getRightBST().isEmpty() ){
					setContent(getLeftBST().getContent());
					
					setRightBST(getLeftBST().getRightBST());
					setLeftBST(getLeftBST().getLeftBST());
				  	}
				//  Si tiene sub-árbol derecho pero no izquierdo
				else if (getLeftBST().isEmpty() /*&& !getRightBST().isEmpty() */){
					setContent(getRightBST().getContent());
					setLeftBST(getRightBST().getLeftBST());
					setRightBST(getRightBST().getRightBST());
					
				}
				// 	//  Si tiene sub-árbol izquierdo y derecho
				else {
					T searchMax=getLeftBST().maximo(getLeftBST().getContent()); // busca el mayor de los menores
					//sustituye el elemento que quiere eliminar por el mayor de los menores 
					content=searchMax;
					//elimina el mayor de los menores que no tendrá hijo derecho y por tanto termina sin recursividad
					getLeftBST().withdraw(searchMax);
					
					}
					
				}
			// Si no lo encuentra sigue buscando por la rama adecuada
			else {
				   if (diff>0) getRightBST().withdraw(element);
			
					else getLeftBST().withdraw(element);
			}
		}
		// si el elemento no está en el árbol
		else throw new NoSuchElementException();
	}
	
	public T maximo(T element) {
		if(!isEmpty()) {
			//p("isleaf");
			//p("--" + getContent());
		}if (getLeftBST().getContent() != null && getRightBST().getContent() != null){
			//p("!=null && !=null");
			if(getLeftBST().getContent().compareTo(getRightBST().getContent()) > 0) {
				if(element.compareTo(getLeftBST().getContent()) < 0) {
					element = getLeftBST().getContent();
					return getLeftBST().maximo(element);
				}else {
					return getLeftBST().maximo(element);
				}
			}else {
				if(element.compareTo(getRightBST().getContent()) < 0) {
					element = getRightBST().getContent();
					return getRightBST().maximo(element);
				}else {
					return getRightBST().maximo(element);
				}
			}
		}else if(getLeftBST().getContent() == null && getRightBST().getContent() != null) {
			if(element.compareTo(getRightBST().getContent()) < 0) {
				element = getRightBST().getContent();
				return getRightBST().maximo(element);
			}else {
				return getRightBST().maximo(element);
			}
			
			
		}else if(getLeftBST().getContent() != null && getRightBST().getContent() == null) {
			if(element.compareTo(getLeftBST().getContent()) < 0) {
				element = getLeftBST().getContent();
				return getLeftBST().maximo(element);
			}else {
				return getLeftBST().maximo(element);
			}

		}
	//p("**" + element);
	return element;
		
	}

	public T minimo(T element) {
		//p("-" + getContent());
		if(!isEmpty()) {
			
				//p("isleaf");
				//p("--" + getContent());
				
			}if (getLeftBST().getContent() != null && getRightBST().getContent() != null){
				//p("!=null && !=null");
				if(getLeftBST().getContent().compareTo(getRightBST().getContent()) > 0) {
					if(element.compareTo(getLeftBST().getContent()) > 0) {
						element = getLeftBST().getContent();
						return getLeftBST().minimo(element);
					}else {
						return getLeftBST().minimo(element);
					}
				}else {
					if(element.compareTo(getRightBST().getContent()) > 0) {
						element = getRightBST().getContent();
						return getRightBST().minimo(element);
					}else {
						return getRightBST().minimo(element);
					}
				}
			}else if(getLeftBST().getContent() == null && getRightBST().getContent() != null) {
				if(element.compareTo(getRightBST().getContent()) > 0) {
					element = getRightBST().getContent();
					return getRightBST().minimo(element);
				}else {
					return getRightBST().minimo(element);
				}
			}else if(getLeftBST().getContent() != null && getRightBST().getContent() == null) {
				if(element.compareTo(getLeftBST().getContent()) > 0) {
					element = getLeftBST().getContent();
					return getLeftBST().minimo(element);
				}else {
					return getLeftBST().minimo(element);
				}
			}
		//p("**" + element);
		return element;
	}

	/**
	 * Elimina de forma eficiente el elemento máximo del árbol.
	 * 
	 * @return elemento a eliminar (max)
	 * 
	 * @throws EmptyCollectionException si el árbol está vacío           
   	 *  
	 */
	public T removeMax() throws EmptyCollectionException{
		T element;
		//	TODO Implementar operación
		if (isEmpty()) {
			throw new EmptyCollectionException("El árbol no puede ser vacío");
		}
		element=maximo(getContent());
		withdraw(element);
		return element;
	}
	
	/**
	 * Elimina de forma eficiente el elemento mínimo del árbol.
	 * 
	 * @return elemento a eliminar (min) o null si el árbol está vacío.
	 *
	 * @throws EmptyCollectionException si el árbol está vacío           
   	 *  
	 */
	public T removeMin() throws EmptyCollectionException{
		T element = null;
		//	TODO Implementar operación
		if (isEmpty()) {
			throw new EmptyCollectionException("El árbol no puede ser vacío");
		}
		
		element = minimo(getContent());
		//p("---" + element);
		withdraw(element);
		return element;
	}
    
    /**
	 * Calcula y devuelve el número de aristas del árbol (sin contar las aristas a nodos vacíos)
	 *  Si el árbol es vacío devolverá 0
	 * 
     * @return número de aristas del árbol
	 */
    public int nOfEdges() {
	    if(isEmpty()){
			return 0;
		}else{
			return numAristas();
		}
	}

	private int numAristas(){
		if(getLeftBST().getContent()==null && getRightBST().getContent()==null){
			return 0;
		}else if(getLeftBST().isEmpty() && !getRightBST().isEmpty()){
			return 1+getRightBST().numAristas();
		}else if(getRightBST().isEmpty() && !getLeftBST().isEmpty()){
			return 1+getLeftBST().numAristas();
		}else{
			return 2+getLeftBST().numAristas()+getRightBST().numAristas();
		}
	}
    
    /**
	 * Acumula en la lista dada como parámetro todos los caminos
	 * y el contenido de los nodos alcanzados desde la raíz.
	 * 
	 * Si se codifica "bajar por la izquierda" como "0" y
	 * "bajar por la derecha" como "1", el camino desde un 
	 * nodo N hasta un nodo M (en uno de sus sub-árboles) será la
	 * cadena de 0s y 1s que indica cómo llegar desde N hasta M.
     *
     * Se define también el camino vacío desde un nodo N hasta
     * él mismo, como cadena vacía.
     * 
	 * Por ejemplo, sea un árbol {10, {5, ∅, ∅}, {20, ∅, {30, ∅, ∅}}}:
	 * 
	 * 10
	 * |  5
	 * |  |  ∅
	 * |  |  ∅
	 * |  20
	 * |  |  ∅
	 * |  |  30
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * 
	 * El camino desde 10 a 30 en el árbol anterior sería "11", porque
	 * desde 10 se baja al sub-árbol derecho, y a continuación se baja
	 * al sub-árbol derecho del nodo 20. En el resultado final se
	 * insertaría la cadena "11::>30".
	 * 
	 * En este ejemplo el método devuelve una lista con los caminos
	 * 
	 * ["::>10", "0::>5", "1::>20", "11::>30"]
	 * 
	 * @param paths
	 */
    
	public void findAllPaths(List<String> paths) {
		// TODO Implementar este método
	}
	
	private void findAllPaths(BinarySearchTreeADTImpl<T>)
	/**
	 * Encuentra y devuelve el elemento que ocupa la posición pasada como parámetro 
	 *  en el recorrido descendente (de mayor a menor) del árbol de búsqueda.
	 *  
	 *  Se debe realizar de forma eficiente, es decir, recorriendo solamente la parte 
	 *    del árbol estrictamente necesaria. OJO! NO SE PUEDE PASAR TODO EL CONTENIDO DEL 
	 *    ARBOL A UNA LISTA Y DESPUÉS DEVOLVER EL ELEMENTO N-ESIMO DE DICHA LISTA.
	 *    
	 * 
	 * @param position
	 *            posición del elemento a encontrar 
	 *            
	 * @return T
	 * 			  elemento a encontrar 
	 *            Devolverá null si es el árbol vacío o no tiene al menos position elementos.
	 *                      
	 */
	
	public T findInDescendingOrder(int position){

		int numPosition;
		numPosition=position;
		if(isEmpty()){
			return null;
		}else{
			return descendingOrder(numPosition);
		}
	}
	
	public T descendingOrder(int numPosition){
		T aux=null;
		
		if(getRightBST().getContent()!=null){
			aux= getRightBST().descendingOrder(numPosition);
		}
		
		if(aux!=null) {
			return aux;
		}
			
		
		if(numPosition==1) {
			return getContent();
		}
			
		numPosition--;
		
		if(getLeftBST().getContent()!=null){
			aux= getLeftBST().descendingOrder(numPosition);
		}				
			//si acaba el proceso sin devolver nada es que es una posicion muy grande
		return aux;
	}
	
	/**
	 * Encuentra y devuelve el elemento que ocupa la posición pasada como parámetro 
	 *  en el recorrido en anchura (por niveles) del árbol de búsqueda.
	 *      
	 * 
	 * @param position
	 *            posición del elemento a encontrar 
	 *            
	 * @return T
	 * 			  elemento a encontrar 
	 *            Devolverá null si es el árbol vacío o no tiene al menos position elementos.
	 *                      
	 */
	public T findInbreadthOrder(int position){
		int numPosition;
		numPosition=position;
		if(isEmpty()){
			return null;
		}else if(position==1){
			return getContent();
		}else{

			return anchuraOrder(numPosition);
		}

	}
	
	public T anchuraOrder(int numPosition){
		numPosition--;
		if(numPosition==1){
			return getLeftBST().getContent();
		}
		numPosition--;
		if(numPosition==1){
			return getRightBST().getContent();
		}else{
			return null;
		}
		
		
	}
	
	/*public static void main(String[] args) throws EmptyCollectionException {
		BinarySearchTreeADTImpl<Integer> arbol = new BinarySearchTreeADTImpl<Integer>();
		arbol.insert(1, 2, 3, 4, 5, 6, 7, 8 ,9 ,10, 11 ,12 ,13, 14, 15, 16, 17, 18, 19, 20);
		//arbol.insert(20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1);
		BinarySearchTreeADTImpl<Integer>  TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(5, 2, 8, 1, 3, 7, 9);
		
		BinarySearchTreeADTImpl<Integer> T1234 = new BinarySearchTreeADTImpl<Integer>();
		T1234.insert(1,2,3,4);
		//System.out.println(arbol.nOfEdges());
		System.out.println(TC3.toString());
		TC3.withdraw(5);
	
	
		
		//System.out.println(arbol.toString());
		//arbol.withdraw(5);
	}*/
}
	
