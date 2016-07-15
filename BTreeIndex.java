package kptlab3;


import java.util.*;

public class BTreeIndex {
	String[] myDocs;
	BinaryTree termList;
	BTNode root;
	static Trie t1= new Trie();
	
	
	//To print all nodes in tree
	public void BTreeObjPrint(BTNode btr, BinaryTree tl){
		tl.printInOrder(btr);
	}
	/**
	 * Construct binary search tree to store the term dictionary 
	 * @param docs List of input strings
	 * 
	 */
	public BTreeIndex(String[] docs)
	{
		//TO BE COMPLETED
		
		//Same as INvertedINdex 
		myDocs=docs;
		termList= new BinaryTree();
		ArrayList<Integer> docList;
		for(int i=0;i<myDocs.length;i++){
			//RegEx for splitting tokens
			String[] tokens = myDocs[i].split("[-.,!?:; -&%$#/+_()]");
			//Iterate through the loop for each term
			for(String tokenSingle:tokens){
				docList=new ArrayList<Integer>();
				docList.add(i);
				
				//Add word to trie Data Structure
				t1.addWord(tokenSingle);
				//Add tokens into Tree with a temporary Node
				BTNode NodeT = new BTNode(tokenSingle.toLowerCase(), docList);
				//Check if root node is empty 
				if(root != null){
					//Add Child
					System.out.println("Root: "+root.term);
					System.out.println(NodeT.docLists);
					System.out.println(NodeT.term);
					termList.add(root, NodeT);
					
				}
				else{
					//Make a new root
					root = NodeT;					
				}
			}
			
		}
	}
	
	
	/**
	 * Single keyword search
	 * @param query the query string
	 * @return doclists that contain the term
	 */
	public ArrayList<Integer> search(String query)
	{
			BTNode node = termList.search(root, query);
			if(node==null)
				return null;
			return node.docLists;
	}
	
	/**
	 * conjunctive query search
	 * @param query the set of query terms
	 * @return doclists that contain all the query terms
	 */
	public ArrayList<Integer> search(String[] query)
	{
		ArrayList<Integer> result = search(query[0].toLowerCase());
		int termId = 1;
		while(termId<query.length)
		{
			ArrayList<Integer> result1 = search(query[termId]);
			result = merge(result,result1);
			termId++;
		}		
		return result;
	}
	
	/**
	 * 
	 * @param wildcard the wildcard query, e.g., ho (so that home can be located)
	 * @return a list of ids of documents that contain terms matching the wild card
	 ===================================================
	 */
	
	public ArrayList<Integer> wildCardSearch(String wildcard)
	{
		//TO BE COMPLETED
		ArrayList<Integer> i1=new ArrayList<Integer>();
		
	List<String> l= t1.getWords(wildcard.toLowerCase());
		for(String temp : l ){
		System.out.println("Term Found in : "+temp);
		i1.addAll(search(temp));
		System.out.println("Documents containing term : "+search(temp));
		}
		
		return i1;
		
	/*	ArrayList<Integer> i1=null;
		ArrayList<BTNode> btTemp= termList.wildCardSearch(root, wildcard);
		for(BTNode temp2 : btTemp){
			i1.addAll(temp2.docLists);
		}
		return i1;*/
	}
	
	
	
	private ArrayList<Integer> merge(ArrayList<Integer> l1, ArrayList<Integer> l2)
	{
		ArrayList<Integer> mergedList = new ArrayList<Integer>();
		int id1 = 0, id2=0;
		while(id1<l1.size()&&id2<l2.size()){
			if(l1.get(id1).intValue()==l2.get(id2).intValue()){
				mergedList.add(l1.get(id1));
				id1++;
				id2++;
			}
			else if(l1.get(id1)<l2.get(id2))
				id1++;
			else
				id2++;
		}
		return mergedList;
	}
	
	
	/**
	 * Test cases
	 * @param args commandline input
	
	
	 */
	
	public static void main(String[] args)
	{
		String[] docs = {"new home sales top forecasts",
						 "home sales rise in july",
						 "increase in home sales in july",
						 "july new home sales rise",
						 "House",
						 "horse"
						};
		//TO BE COMPLETED with testcases
		
		//Initialize BTreeIndex Object
		
		BTreeIndex b1=new BTreeIndex(docs);
		
		//Print the whole tree
		System.out.println("********************");
		//System.out.println(b1.root.left);
		//b1.termList.printInOrder(b1.root);
		b1.BTreeObjPrint(b1.root, b1.termList);
		System.out.println("********************");
		
		//Query for single string
		String s1[]={"sales"};
		System.out.println("******** Query for single String ************");
		System.out.println(s1[0]);
		System.out.println(b1.search(s1));
		System.out.println("********************");
		
		//Query for multiple string
		String s2[]={"sales","july"};
		System.out.println("******** Query for single String ************");
		System.out.println(s2[0]+" AND "+s2[1]);
		System.out.println(b1.search(s2));
		System.out.println("********************");
		
		
		//Print all words in tree Data Structure
		
		List<String> l= t1.getWords("");
		for(String temp : l )
		System.out.println(temp);
				
		/*ArrayList<Integer> tempI= b1.wildCardSearch("s");
		for(Integer i2: tempI){
			System.out.println(i2);
			
		}*/
		
		System.out.println("**********************Wild Card Query*********************");
		System.out.println("All document containing term start with ho "+b1.wildCardSearch("ho"));
		System.out.println("******************************************************************");
				
	}
}