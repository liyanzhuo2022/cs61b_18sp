package byog.Core;
import java.util.Comparator;

class RoomComparator implements Comparator<Room> {
    @Override
    public int compare(Room r1, Room r2) {
        return Integer.compare(r1.getPx(), r2.getPx());
    }
}
