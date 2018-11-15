package notifire;

import java.io.Serializable;
import java.time.LocalDate;

public class Task extends Announce implements Serializable {

    private LocalDate deadline;

    public Task(String name, String message, LocalDate deadline) {
        super(name, message);
        this.deadline = deadline;
    }

    @Override
    public LocalDate getAnnoucedDate() {
        return deadline;
    }
}
