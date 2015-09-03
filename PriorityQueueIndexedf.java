/*
 * Vaishakhi Kulkarni
 * Net id: vpk140230
 */

//package DAY1;

//Ver 0.1:  Sat, Feb 28.  Initial description.
//Ver 1.0:  Tue, Mar 03.  Added more comments.  Modified decreaseKey().

import java.lang.Comparable;
import java.util.Arrays;

public class PriorityQueueIndexedf<T extends Comparable<? super T> & PQIndex> {
	T[] queue;
	int size = 0;
	int value = 0;

	/** Build a priority queue with a given array q */
	
	PriorityQueueIndexedf(T[] q) {
		queue = q;
		buildHeap();

	}

	/** Create an empty priority queue of given maximum size */
	@SuppressWarnings("unchecked")
	PriorityQueueIndexedf(int n) {

		queue = (T[]) new Comparable[n + 1];

	}

	//Insert the element into the queue
	void insert(T x) {
		add(x);
	}

	//Add element into priority queue
	void add(T x) {
		if (size >= queue.length) {
			queue = this.resize();
		}
		queue[0] = x;
		int hole = size + 1;
		while (queue[hole / 2].compareTo(x) > 0) {
			queue[hole] = queue[hole / 2];
			hole = hole / 2;
		}
		queue[hole] = x;
		size++;
	}

	// To check whether the priority queue is empty
	public boolean isEmpty() {
		return size == 0;
	}

	//Remove the element from the priority queue
	T remove() {
		if (this.isEmpty()) {
			try {
				throw new Exception("Invalid Key");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return deleteMin();
	}

	//Resize the priority queue
	T[] resize() {
		return Arrays.copyOf(queue, queue.length * 2);
	}

	//To delete min element from the priority queue
	T deleteMin() {
		T rv = queue[1];
		queue[1] = queue[size];
		size--;
		percolateDown(1);
		rv.putIndex(-1);
		return rv;
	}

	//To check whether the vertex is present or not
	Boolean contains(T x) {
		if (x.getIndex() == -1)
			return false;
		else
			return true;
	}

	/** restore heap order property after the priority of x has decreased */
	void decreaseKey(T x) {
		percolateUp(x.getIndex());
	}

	// to find minimum of the priority queue
	T min() {
		return queue[1];
	}

	/**
	 * Priority of element at index i of queue has decreased. It may violate
	 * heap order. Restore heap property
	 */
	void percolateUp(int i) {

		T x = queue[i];
		queue[0] = x;
		int hole = i;
		while (queue[hole / 2].compareTo(x) > 0) {
			queue[hole] = queue[hole / 2];
			queue[hole].putIndex(hole);
			hole = hole / 2;
		}
		queue[hole] = x;
		x.putIndex(hole);
	}

	//To swap the elements
	void swap(int index1, int index2) {
		T temp = queue[index1];
		queue[index1] = queue[index2];
		queue[index2] = temp;
	}

	/**
	 * Create heap order for sub-heap rooted at node i. Precondition: Heap order
	 * may be violated at node i, but its children are roots of heaps that are
	 * fine. Restore heap property
	 */
	void percolateDown(int i) {

		if (size < (2 * i)) {
			return;
		} else if (size == (2 * i)) {
			if (queue[i].compareTo(queue[2 * i]) > 0) {
				swap(i, 2 * i);
				queue[i].putIndex(i);
				queue[2 * i].putIndex(2 * i);
			}
		} else if (size > (2 * i)) {
			int child;
			if (queue[2 * i].compareTo(queue[2 * i + 1]) > 0)
				child = 2 * i + 1;
			else
				child = 2 * i;

			if (queue[i].compareTo(queue[child]) > 0) {
				swap(i, child);
				queue[i].putIndex(i);
				queue[child].putIndex(child);
				percolateDown(child);
			}
		}
	}

	//ToString method
	public String toString() {
		return Arrays.toString(queue);
	}

	/**
	 * Create a heap. Precondition: none. Array may violate heap order in many
	 * places.
	 */
	void buildHeap() {
		for (int i = queue.length - 1 / 2; i > 0; i--)
			percolateDown(i);
		size = queue.length - 1;
	}

}
