package model;

import java.time.LocalDateTime;

public class Actions {

    private int memberId;
    private int bookId;
    private String actionType;
    private LocalDateTime actionTime;

    public Actions(int memberId, int bookId, String actionType, LocalDateTime actionTime) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.actionType = actionType;
        this.actionTime = actionTime;
    }

    public int getMemberId() { return memberId; }
    public int getBookId() { return bookId; }
    public String getActionType() { return actionType; }
    public LocalDateTime getActionTime() { return actionTime; }
}
