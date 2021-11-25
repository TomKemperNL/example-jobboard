package nl.hu.bep3.jobboard.candidates.core.query;

public class FindCandidatesByKeyword {
    private final String keyword;
    private final String orderBy;
    private final String direction;

    public FindCandidatesByKeyword(String keyword, String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "name";
        }

        if (direction == null) {
            direction = "asc";
        }

        this.keyword = keyword;
        this.orderBy = orderBy;
        this.direction = direction;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
