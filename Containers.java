import java.util.*;

public class Containers implements Ilayout, Cloneable {

    private ArrayList<Stack<Character>> stacks;
    private HashMap<Character, Integer> containerCosts;
    private int k;


    public Containers(String input) {
        stacks = new ArrayList<>();
        containerCosts = new HashMap<>();
        k = 0;

        String[] newStacks = input.trim().split("\\s+");

        for (String stack : newStacks) {
            if (stack.isEmpty()) continue;

            if (stack.length() % 2 != 0) {
                throw new IllegalArgumentException("Invalid entry: each container must be followed by a cost.");
            }

            Stack<Character> containerStack = new Stack<>();

            for (int i = 0; i < stack.length(); i += 2) {
                char container = stack.charAt(i);
                char costChar = stack.charAt(i + 1);

                if (!Character.isDigit(costChar)) {
                    throw new IllegalArgumentException("Invalid cost: " + container);
                }

                int cost = Character.getNumericValue(costChar);
                containerStack.push(container);
                containerCosts.put(container, cost);
            }
            stacks.add(containerStack);
        }

    }

    public Containers(String input, boolean isGoal) {
        stacks = new ArrayList<>();
        k = 0;

        String[] newStacks = input.trim().split("\\s+");

        for (String stack : newStacks) {
            if (stack.isEmpty()) continue;

            Stack<Character> containerStack = new Stack<>();

            for (int i = 0; i < stack.length(); i++) {
                char container = stack.charAt(i);
                containerStack.push(container);
            }
            stacks.add(containerStack);
        }

    }

    public HashMap<Character, Integer> getContainerCosts() {
        return containerCosts;
    }

    @Override
    public int getK() {
        return k;
    }

    public void updateK(int movementCost) {
        this.k = movementCost;
    }

    @Override
    public Containers clone() {
        try {
            Containers cloned = (Containers) super.clone();
            cloned.stacks = new ArrayList<>();
            cloned.containerCosts = this.containerCosts;
            cloned.k = 0;
            for (Stack<Character> stack : this.stacks) {
                Stack<Character> clonedStack = new Stack<>();
                clonedStack.addAll(stack);
                cloned.stacks.add(clonedStack);
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void moveContainer(int fromIndex, int toIndex) {
        char container = stacks.get(fromIndex).pop();
        stacks.get(toIndex).push(container);


    }

    public void moveContainerToGround(int fromIndex) {
        if (!stacks.get(fromIndex).isEmpty()) {
            char container = stacks.get(fromIndex).pop();

            Stack<Character> newStack = new Stack<>();
            newStack.push(container);
            stacks.add(newStack);

        }
    }

    public void removeEmptyStacks() {
        stacks.removeIf(Stack::isEmpty);
    }

    public List<Ilayout> children() {
        List<Ilayout> children = new ArrayList<>();

        for (int i = 0; i < stacks.size(); i++) {
            if (!stacks.get(i).isEmpty()) {
                char container = stacks.get(i).peek();

                for (int j = 0; j < stacks.size(); j++) {
                    if (i != j && !(stacks.get(j).isEmpty())) {
                        Containers newConfig = this.clone();
                        newConfig.moveContainer(i, j);
                        newConfig.updateK(containerCosts.get(container));
                        children.add(newConfig);
                    }
                }


                Containers newConfigGround = this.clone();
                newConfigGround.moveContainerToGround(i);
                newConfigGround.updateK(containerCosts.get(container));
                children.add(newConfigGround);
            }
        }
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Containers other = (Containers) o;

        removeEmptyStacks();
        other.removeEmptyStacks();
        if (stacks.size() != other.stacks.size()) return false;

        List<List<Character>> otherStacks = new ArrayList<>(other.stacks);

        List<List<Character>> thisStacks = new ArrayList<>(stacks);

        thisStacks.sort(Comparator.comparingInt((List<Character> stack) -> stack.isEmpty() ? ' ' : stack.getFirst()));
        otherStacks.sort(Comparator.comparingInt((List<Character> stack) -> stack.isEmpty() ? ' ' : stack.getFirst()));

        return thisStacks.equals(otherStacks);
    }


    @Override
    public int hashCode() {
        removeEmptyStacks();
        List<List<Character>> sortedBoard = new ArrayList<>();

        for (List<Character> stack : stacks) {
            sortedBoard.add(new ArrayList<>(stack));
        }

        sortedBoard.sort(Comparator.comparingInt((List<Character> stack) -> stack.isEmpty() ? ' ' : stack.getFirst()));

        int result = 17;
        int primeMultiplier = 37;

        for (List<Character> stack : sortedBoard) {
            result = primeMultiplier * result + stack.hashCode();
            primeMultiplier += 2;
        }

        return result;
    }
    public boolean isGoal(Ilayout l) {
        return this.equals(l);
    }


    @Override
    public String toString() {
        List<Stack<Character>> sortedStacks = new ArrayList<>(stacks);
        sortedStacks.sort((stack1, stack2) -> {
            if (stack1.isEmpty() && stack2.isEmpty()) {
                return 0;
            } else if (stack1.isEmpty()) {
                return 1;
            } else if (stack2.isEmpty()) {
                return -1;
            } else {

                return Character.compare(stack1.getFirst(), stack2.getFirst());
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int s = 0; s < sortedStacks.size(); s++) {
            Stack<Character> stack = sortedStacks.get(s);
            sb.append("[");
            for (int i = 0; i < stack.size(); i++) {
                sb.append(stack.get(i));
                if (i < stack.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");

        }
        return sb.toString();
    }

}
