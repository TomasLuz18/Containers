import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BestFirst {
    protected Queue<State> abertos;
    private Map<Ilayout, State> fechados;
    private State actual;
    private Ilayout objective;

    private HashSet<Ilayout> abertosSet;

    static class State {
        private Ilayout layout;
        private State father;
        private int g;
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father!=null)
                g = father.g + l.getK();
            else g = 0;
        }
        public String toString() { return layout.toString(); }
        public int getG() {return g;}
        public int hashCode() {
            return layout.hashCode();
        }
        public boolean equals (Object o) {
            if (o==null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;
            return this.layout.equals(n.layout);
        }
    }
    final private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for(Ilayout e: children) {
            if (n.father == null || !e.equals(n.father.layout)){
                if(!abertosSet.contains(e)){
                    State nn = new State(e, n);
                    sucs.add(nn);
                }
            }
        }
        return sucs;
    }
    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        objective = goal;

        abertos = new PriorityQueue<>(10,
                (s1, s2) -> (int) Math.signum(s1.getG() - s2.getG()));
        fechados = new HashMap<>();
        abertosSet = new HashSet<>();

        abertos.add(new State(s, null));

        abertosSet.add(s);


        while (!abertos.isEmpty()) {

            actual = abertos.poll();
            abertosSet.remove(actual);


            if (actual.layout.isGoal(goal)) {
                List<State> solution = new ArrayList<>();
                State temp = actual;
                while (temp != null) {
                    solution.add(temp);
                    temp = temp.father;
                }
                Collections.reverse(solution);
                return solution.iterator();
            }

            fechados.put(actual.layout, actual);


            for (State successor : sucessores(actual)) {
                if (!fechados.containsKey(successor.layout)) {
                    abertos.add(successor);
                    abertosSet.add(successor.layout);
                }
            }
        }

        return null;
    }

}




