package engine;

import java.util.Comparator;

public class MoveDirectionComparator implements Comparator<MoveDirection> {
    private final MoveOrder moveOrder;

    MoveDirectionComparator(MoveOrder moveOrder) {
        this.moveOrder = moveOrder;
    }

    @Override
    public int compare(MoveDirection moveDirection1, MoveDirection moveDirection2) {
        String moveOrderString = moveOrder.toString();
        for(int i = 0; i < moveOrderString.length(); i++) {
            if(moveDirection1.toString().charAt(0) == moveOrderString.charAt(i))
                return -1;
            if(moveDirection2.toString().charAt(0) == moveOrderString.charAt(i))
                return 1;
        }
        return 0;
    }
}
