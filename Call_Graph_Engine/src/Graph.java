import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javafx.util.Pair;


public class Graph {
	private Node dummy; // root of all the classes and methods
	private Map<String, Node> map; // map of classes, methods, and clusters
	private Map<String, Node> clusterMap; // cluster map
	
	public Graph() {
		dummy = new Node(Type.DUMMY, "dummy");
		map = new HashMap<String, Node>();
		map.put("dummy", dummy);
		clusterMap = new HashMap<String, Node>();
	}
	
	// add class to cluster, create cluster if it does not already exist
	public void addClassToCluster(String cluster, String cla) throws Exception {
		Node nCluster;
		if (!clusterMap.containsKey(cluster)) {
			nCluster = new Node(Type.CLUSTER, cluster);
			clusterMap.put(cluster, nCluster);
			map.put(cluster, nCluster);
		}
		else {
			nCluster = clusterMap.get(cluster);
		}
		if (map.containsKey(cla)) {
			Node nClass = map.get(cla);
			nCluster.addNeighbor(InvocationType.MEMBER , nClass);
		}
		else {
			throw new Exception("unrecognized class");
		}
	}
	
	// add class to graph
	public void addClass(String s) {
		if (!map.containsKey(s)) {
			Node n = new Node(Type.CLASS, s);
			dummy.addNeighbor(InvocationType.CLASS, n);
			map.put(s, n);
		}
	}
	
	// add method to graph, caller can be either class or method
	public void addMethod(String caller, String callee, InvocationType i) throws Exception {
		if (map.containsKey(caller)) {
			Node nCaller = map.get(caller);
			Node nCallee;
			if (!map.containsKey(callee)) {
				nCallee = new Node(Type.METHOD, callee);
				map.put(callee, nCallee);
			}
			else {
				nCallee = map.get(callee);
			} 
			nCaller.addNeighbor(i, nCallee);
		}
		else {
			throw new Exception("unrecognized caller");
		}
	}
	
	public void bfs (String s) {
		if (!map.containsKey(s)) return;
		Node root = map.get(s);
		Queue<Node> q = new LinkedList<Node>();
		Map<Node, Integer> depth = new HashMap<Node, Integer>();
		Map<Integer, Integer> counter = new HashMap<Integer, Integer>();
		q.add(root);
		depth.put(root, 0);
		while (!q.isEmpty()) {
			Node n = q.peek();
			q.remove();
			int d = depth.get(n);
			System.out.println("at depth " + d + " found: " + n.getName());
			if (!counter.containsKey(d)) 
				counter.put(d, 0);
			counter.put(d, counter.get(d)+1);
			Iterator<Pair<InvocationType, Node>> it = n.getNeighbors();
			while (it.hasNext()) {
				Node next = it.next().getValue();
				if (!depth.containsKey(next)) {
					q.add(next);
					depth.put(next, d+1);
				}
			}
		}
		System.out.println("Counts: ");
		for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
			System.out.println("depth " + entry.getKey() + ": " + entry.getValue());
		}
	}
	
	public void overlap(String s1, String s2) {
		if (!map.containsKey(s1) || !map.containsKey(s2)) return;
		Set<Node> set1 = new HashSet<Node>();
		Set<Node> set2 = new HashSet<Node>();
		Map<Node, Integer> probed1 = new HashMap<Node, Integer>();
		Map<Node, Integer> probed2 = new HashMap<Node, Integer>();
		Node n1 = map.get(s1);
		Node n2 = map.get(s2);
		set1.add(n1);
		set2.add(n2);
		probed1.put(n1, 0);
		probed2.put(n2, 0);
		int depth = 1;
		while(!set1.isEmpty() && !set2.isEmpty()) {
			Set<Node> set11 = new HashSet<Node>();
			Set<Node> set22 = new HashSet<Node>();
			for (Node n : set1) {
				Iterator<Pair<InvocationType, Node>> it = n.getNeighbors();
				while (it.hasNext()) {
					Node next = it.next().getValue();
					if (!probed1.containsKey(next)) {
						set11.add(next);
						probed1.put(next, depth);
						if (probed2.containsKey(next)) {
							System.out.println(next.getName() + ": " + probed1.get(next) + " " + probed2.get(next));
							// we do not go any deeper from this node
							set11.remove(next);
						}
	
					}
				}
			}
			for (Node n : set2) {
				Iterator<Pair<InvocationType, Node>> it = n.getNeighbors();
				while (it.hasNext()) {
					Node next = it.next().getValue();
					if (!probed2.containsKey(next)) {
						set22.add(next);
						probed2.put(next, depth);
						if (probed1.containsKey(next)) {
							System.out.println(next.getName() + ": " + probed1.get(next) + " " + probed2.get(next));
							set11.remove(next);
							set22.remove(next);
						}
	
					}
				}
			}
			set1 = set11;
			set2 = set22;
			depth += 1;
		}
		
	}
}
