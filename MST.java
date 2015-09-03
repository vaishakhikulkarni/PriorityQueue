/*
 * Vaishakhi Kulkarni
 * Net id: vpk140230
 */
package DAY1;

import DAY1.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import DAY1.PriorityQueueIndexedf;

public class MST {
	static final int Infinity = Integer.MAX_VALUE;

	static int PrimMST(Graph g) {
		// Initialize the weight
		int wmst = 0;
		Graph.Vertex src = g.V[1];
		src.weight = 0;
		// Create priority queue
		PriorityQueueIndexedf<Graph.Vertex> pq = new PriorityQueueIndexedf<Graph.Vertex>(
				g.V);

		while (!pq.isEmpty()) {
			Graph.Vertex u = pq.deleteMin();
			u.seen = true;
			wmst = wmst + u.weight;
			// Check all the edges going from vertex u
			for (Graph.Edge e : u.Adj) {
				// All the adjacent vertices of vertex u
				Graph.Vertex v = e.otherEnd(u);
				// Check whether the other vertex is present in the priority
				// queue and check the weight
				if (v.seen == false && v.weight > e.Weight) {
					v.parent = u;
					v.weight = e.Weight;
					// Decrement the key when minimum value is updated
					pq.decreaseKey(v);
				}
			}

		}
		return wmst;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(args[0]));
		// Read the graph
		Graph g = Graph.readGraph(in);
		// Initialize the vertex
		g.initialize();
		long startTime = System.currentTimeMillis();
		// Minimum spanning tree value calculation
		System.out.println("Minumum spanning tree weight is :"+PrimMST(g));
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		// Total time taken
		System.out.println("Time is:" + elapsedTime);
	}
}
