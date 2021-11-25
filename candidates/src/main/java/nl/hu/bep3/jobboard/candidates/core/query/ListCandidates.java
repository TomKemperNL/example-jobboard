package nl.hu.bep3.jobboard.candidates.core.query;

public class ListCandidates {
    private final String orderBy;
    private final String direction;

    public ListCandidates(String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "name";
        }

        if (direction == null) {
            direction = "asc";
        }

        this.orderBy = orderBy;
        this.direction = direction;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
