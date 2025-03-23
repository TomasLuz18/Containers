import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String initialConfig = sc.nextLine();
        String goalConfig = sc.nextLine();

        try {
            Containers initialContainers = new Containers(initialConfig);

            Containers goalContainers = new Containers(goalConfig, true);

            BestFirst solver = new BestFirst();
            Iterator<BestFirst.State> it = solver.solve(initialContainers, goalContainers);

            if (it == null) {
                System.out.println("No solution found");
            } else {
                while (it.hasNext()) {
                    BestFirst.State state = it.next();
                    System.out.println(state);
                    


                    if (!it.hasNext()) {
                        System.out.println( state.getG());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
