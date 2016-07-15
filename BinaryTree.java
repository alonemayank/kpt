package kptlab3;
import java.util.*;

/**
 * 
 * @author 
 * a node in a binary search tree
 */
class BTNode{
	BTNode left, right;
	String term;
	ArrayList<Integer> docLists;
	
	
	/**
	 * Create a tree node using a term and a document list
	 * @param term the term in the node
	 * @param docList the ids of the documents that contain the term
	 */
	public BTNode(String term, ArrayList<Integer> docList)
	{
		this.term = term;
		this.docLists = docList;
	}
	
}

/**
 * 
 * @author qyuvks
 * Binary search tree structure to store the term dictionary
 */
public class BinaryTree {

	/**
	 * insert a node to a subtree 
	 * @param node root node of a subtree
	 * @param iNode the node to be inserted into the subtree
	 */
	
	public void add(BTNode node, BTNode iNode)
	{
		//TO BE COMPLETED
		if(node.term.compareTo(iNode.term)>0 && node.left!=null){
			add(node.left, iNode);
		}
		else if (node.term.compareTo(iNode.term)>0 && node.left==null){
			node.left = iNode;
		}
		else if(node.term.compareTo(iNode.term)<0 && node.right != null){
			add(node.right, iNode);
		}
		else if(node.term.compareTo(iNode.term)<0 && node.right == null){
			node.right= iNode;
		}
		else if(node.docLists.contains(iNode.docLists.get(0))!=true){
			node.docLists.add(iNode.docLists.get(0));
		}
	}
	
	/**
	 * Search a term in a subtree
	 * @param n root node of a subtree
	 * @param key a query term
	 * @return tree nodes with term that match the query term or null if no match
	 * 
	 ===================================================
	 */
	public BTNode search(BTNode n, String key)
	{
		//TO BE COMPLETED
		//Checking all conditions
		
		if(n.term.equals(key)==true){
		//If equals
			return n;
		}
		
		else if(n.term.compareTo(key)<0){
			//if less than
			return search(n.right,key);
		}
		
		else if(n.term.compareTo(key)>0){
			//If greater than
			return search(n.left,key);
		}
		else{
			return null;
		}
					
			
		}//End of Search Method
		
	
	
	//==============================================================
	/**
	 * Do a wildcard search in a subtree
	 * @param n the root node of a subtree
	 * @param key a wild card term, e.g., ho (terms like home will be returned)
	 * @return tree nodes that match the wild card
	 ================================================================
	 */
	public ArrayList<BTNode> wildCardSearch(BTNode n, String key)
	{
		//TO BE COMPLETED
		ArrayList<BTNode> BTNtemp=null;
		BTNode BTNtemp1= null;
		Trie t1 = null;
		List<String> l= t1.getWords(key);
		for(String tempL : l){
			BTNtemp1=search(n,tempL);
			BTNtemp.add(BTNtemp1);
		}
		return BTNtemp;
		
	}
	//=============================================================
	/**
	 * Print the inverted index based on the increasing order of the terms in a subtree
	 * @param node the root node of the subtree
	 */
	public void printInOrder(BTNode node)
	{
		if(node!=null){
			//TO BE COMPLETED
			//Call left node
			//System.out.println(node.term);
			printInOrder(node.left);
			System.out.println("Term: -> "+node.term+" || Found in Documents: ->"+node.docLists);
			//Call Right node
			printInOrder(node.right);
		}
		else{
			return;
		
		}
		
	}
}

