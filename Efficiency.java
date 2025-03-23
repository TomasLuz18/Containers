import org.junit.Test;

import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Efficiency {
    @Test(timeout = 2000)
    public void testEfficiency1() {
        Containers initialBoard = new Containers("A1B1C1D1E1F1G1H1I1");
        Containers goalBoard = new Containers("A B C D E F G H I", true);

        BestFirst solver = new BestFirst();

        Iterator<BestFirst.State> solutionPath = solver.solve(initialBoard, goalBoard);
    }

    @Test(timeout = 500)
    public void testEfficiency2() {
        Containers initialBoard = new Containers("A1B1 C1 D1 E1 F1 G1 H1");
        Containers goalBoard = new Containers("BA C D E F G H", true);

        BestFirst solver = new BestFirst();

        Iterator<BestFirst.State> solutionPath = solver.solve(initialBoard, goalBoard);
    }

    @Test(timeout = 500)
    public void testEfficiency3() {
        Containers initialBoard = new Containers("A1E1I1 B1C1D1F1");
        Containers goalBoard = new Containers("AB IDEFC", true);

        BestFirst solver = new BestFirst();

        Iterator<BestFirst.State> solutionPath = solver.solve(initialBoard, goalBoard);
    }

    @Test
    public void testHashCode() {
        Containers g = new Containers("A B C",true);
        Containers g1 = new Containers("B A C", true);
        Containers g2 = new Containers("AB C D", true);
        Containers g3 = new Containers("BA C D", true);
        Containers g4 = new Containers("C B A", true);
        Containers g5 = new Containers("AB D C", true);

        assertEquals(g.hashCode(), g1.hashCode());
        assertEquals(g1.hashCode(),g4.hashCode());
        assertEquals(g.hashCode(), g4.hashCode());
        assertNotEquals(g2.hashCode(), g3.hashCode());
        assertEquals(g2.hashCode(),g5.hashCode());
        assertNotEquals(g3.hashCode(), g5.hashCode());
    }
}
