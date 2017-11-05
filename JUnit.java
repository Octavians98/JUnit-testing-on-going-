import static org.junit.Assert.*;

import org.junit.Test;

public class DAGSortTest {

	@Test(expected = InvalidNodeException.class)
	public void invalidNode() throws InvalidNodeException, CycleDetectedException {
		int[][] edges = { { 1 }, { 2, 5 }, { 5 }, { 0, 4 }, {}, {}, { 3, 7 } };
		DAGSort.sortDAG(edges);
	}

	@Test(expected = InvalidNodeException.class)
	public void invalidNode2() throws InvalidNodeException, CycleDetectedException {
		int[][] edges = { { 1 } };
		DAGSort.sortDAG(edges);
	}

	@Test(expected = CycleDetectedException.class)
	public void CycleDetected() throws InvalidNodeException, CycleDetectedException {
		int[][] edges = { { 1 }, { 0 } };
		DAGSort.sortDAG(edges);
	}

	@Test(expected = CycleDetectedException.class)
	public void CycleDetected2() throws InvalidNodeException, CycleDetectedException {
		int[][] edges = { { 1 }, { 2, 5 }, { 5 }, { 0, 4 }, {}, { 1 }, { 3, 4 } };
		DAGSort.sortDAG(edges);
	}

	@Test(expected = CycleDetectedException.class)
	public void CycleDetected3() throws InvalidNodeException, CycleDetectedException {
		int[][] edges = { { 0 } };
		DAGSort.sortDAG(edges);
	}

	@Test
	public void ruleTest() throws CycleDetectedException, InvalidNodeException {
		int[][] edges = { { 1, 4 }, { 2, 4 }, {}, { 0 }, {}, { 3, 6 }, { 7 }, { 4 } };
		int[] sorted = DAGSort.sortDAG(edges);
		int[] order = { 0, 0, 0, 0, 0, 0, 0, 0 };
		int index = 0;
		boolean ok = true;
		for (int i = 0; i < 8; i++) {
			order[sorted[i]] = index;
			index++;
		}
		for (int i = 0; i < 8; i++) {
			for (int j : edges[i]) {

				if (order[i] > order[j]) {
					ok = false;
				}
			}
		}
		assertEquals(true, ok);

	}

	@Test
	public void ruleTest2() throws CycleDetectedException, InvalidNodeException {
		int[][] edges = { { 1 }, {} };
		int[] sorted = DAGSort.sortDAG(edges);
		int[] order = { 0, 0 };
		int index = 0;
		boolean ok = true;
		for (int i = 0; i < 2; i++) {
			order[sorted[i]] = index;
			index++;
		}
		for (int i = 0; i < 2; i++) {
			for (int j : edges[i]) {
				if (order[i] > order[j]) {
					ok = false;
				}
			}
		}
		assertEquals(true, ok);

	}

	@Test
	public void ruleTest3() throws CycleDetectedException, InvalidNodeException {
		int[][] edges = { {} };
		int[] sorted = DAGSort.sortDAG(edges);
		int[] order = { 0 };
		int index = 0;
		boolean ok = true;
		for (int i = 0; i < 1; i++) {
			order[sorted[i]] = index;
			index++;
		}
		for (int i = 0; i < 1; i++) {
			for (int j : edges[i]) {
				if (order[i] > order[j]) {
					ok = false;
				}
			}
		}
		assertEquals(true, ok);

	}

	@Test
	public void ruleTest4() throws CycleDetectedException, InvalidNodeException {
		int[][] edges = {};
		int[] sort = DAGSort.sortDAG(edges);
		int[] order = { 0 };
		int index = 0;
		boolean ok = true;
		for (int i = 0; i < 0; i++) {
			order[sort[i]] = index;
			index++;
		}
		for (int i = 0; i < 0; i++) {
			for (int j : edges[i]) {
				if (order[i] > order[j]) {
					ok = false;
				}
			}
		}
		assertEquals(true, ok);
	}

	@Test
	public void sizeTest1() throws CycleDetectedException, InvalidNodeException {
		int[][] edges = { {}, {}, {}, {}, };
		int output = DAGSort.sortDAG(edges).length;
		assertEquals(4, output);
	}

	
}