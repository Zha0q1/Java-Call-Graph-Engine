import java.util.ArrayList;
import java.util.Iterator;
import javafx.util.Pair;

enum Type { 
    CLASS, METHOD, CLUSTER, DUMMY; 
} 

enum InvocationType {
	M, I, O, S, D, MEMBER, CLUSTER, CLASS, UNKOWN;
}

public class Node {
	private Type type;
	private String name;
	private ArrayList<Pair<InvocationType, Node>> neighbors;
	
	public Node(Type t, String s) {
		type = t;
		name = s;
		neighbors = new ArrayList<Pair<InvocationType, Node>>();
	}
	
	public void addNeighbor(InvocationType i, Node n) {
		neighbors.add(new Pair<InvocationType, Node>(i,n));
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public Iterator<Pair<InvocationType, Node>> getNeighbors() {
		return neighbors.iterator();
	}

}
