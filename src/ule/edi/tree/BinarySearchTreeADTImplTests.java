package ule.edi.tree;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeADTImplTests {

	private BinarySearchTreeADTImpl<Integer> T1234 = null;
	private BinarySearchTreeADTImpl<Integer> T4321 = null;
	
	//	Completo con tres niveles
	private BinarySearchTreeADTImpl<Integer> TC3 = null;
	
	@Before
	public void setupBSTs() {
		
		new BinarySearchTreeADTImpl<Integer>();
		
		T1234 = new BinarySearchTreeADTImpl<Integer>();
		T1234.insert(1,2,3,4);
		
		T4321 = new BinarySearchTreeADTImpl<Integer>();
		T4321.insert(4, 3, 2, 1);
		
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(5, 2, 8, 1, 3, 7, 9);
	}
	



	@Test(expected = IllegalArgumentException.class)
	public void testInsertNull() {
		T4321.insert(new Integer[] { null });
	}

	@Test
	public void testWithdrawNull() {
		T4321.withdraw(new Integer[] { null });
		Assert.assertEquals("{4, {3, {2, {1, ∅, ∅}, ∅}, ∅}, ∅}", T4321.toString());
	}
	
	@Test
	public void testWithdrawSeveralElements() {
		T4321.withdraw(1, 3);
		Assert.assertEquals("{4, {2, ∅, ∅}, ∅}", T4321.toString());
	}

	@Test
	//@HintPrefix("Elimina el dato más a la izquierda en un árbol BST completamente 'torcido' hacia ese lado:")
	public void testWithdrawLeftUBST() {
		T4321.withdraw(1);
		Assert.assertEquals("{4, {3, {2, ∅, ∅}, ∅}, ∅}", T4321.toString());
	}

	@Test
	//@HintPrefix("Elimina el dato raíz en un árbol BST completamente 'torcido' a la izquierda:")
	public void testWithdrawLeftUBSTRoot() {
		T4321.withdraw(4);
		Assert.assertEquals("{3, {2, {1, ∅, ∅}, ∅}, ∅}", T4321.toString());
	}

	@Test
	//@HintPrefix("Elimina un dato interno en un árbol BST completamente 'torcido' a la izquierda:")
	public void testWithdrawLeftUBSTInsider() {
		T4321.withdraw(3);
		Assert.assertEquals("{4, {2, {1, ∅, ∅}, ∅}, ∅}", T4321.toString());
	}
	
	@Test
	//@HintPrefix("Elimina el dato más a la derecha en un árbol BST completamente 'torcido' hacia ese lado:")
	public void testWithdrawRightUBST() {
		T1234.withdraw(4);
		Assert.assertEquals("{1, ∅, {2, ∅, {3, ∅, ∅}}}", T1234.toString());
	}

	@Test
	//@HintPrefix("Elimina el dato raíz en un árbol BST completamente 'torcido' a la derecha:")
	public void testWithdrawRightUBSTRoot() {
		T1234.withdraw(1);
		Assert.assertEquals("{2, ∅, {3, ∅, {4, ∅, ∅}}}", T1234.toString());
	}

	@Test
	//@HintPrefix("Elimina un dato interno en un árbol BST completamente 'torcido' a la derecha:")
	public void testWithdrawRightUBSTInsider() {
		T1234.withdraw(2);
		Assert.assertEquals("{1, ∅, {3, ∅, {4, ∅, ∅}}}", T1234.toString());
	}
	
	@Test
	//@HintPrefix("Elimina la raíz de un árbol BST completo con tres niveles:")
	public void testWithdrawC3D0() {
		TC3.withdraw(5);
		Assert.assertEquals("{3, {2, {1, ∅, ∅}, ∅}, {8, {7, ∅, ∅}, {9, ∅, ∅}}}", TC3.toString());
	}

	@Test
	//@HintPrefix("Elimina un nodo que no tiene sub-árbol derecho en un árbol BST:")
	public void testWithdrawC3DNR() {
		TC3.withdraw(5);
		Assert.assertEquals("{3, {2, {1, ∅, ∅}, ∅}, {8, {7, ∅, ∅}, {9, ∅, ∅}}}", TC3.toString());
		TC3.withdraw(2);
		Assert.assertEquals("{3, {1, ∅, ∅}, {8, {7, ∅, ∅}, {9, ∅, ∅}}}", TC3.toString());
	}

	@Test
	//@HintPrefix("Elimina un nodo que no tiene sub-árbol izquierdo en un árbol BST:")
	public void testWithdrawC3DNL() {
		TC3.withdraw(7);
		Assert.assertEquals("{5, {2, {1, ∅, ∅}, {3, ∅, ∅}}, {8, ∅, {9, ∅, ∅}}}", TC3.toString());
		TC3.withdraw(8);
		Assert.assertEquals("{5, {2, {1, ∅, ∅}, {3, ∅, ∅}}, {9, ∅, ∅}}", TC3.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	//@HintPrefix("Elimina un nodo que no está en el árbol BST: ")
	public void testWithdrawNotThere() {
		TC3.withdraw(13);
	}
	
}
