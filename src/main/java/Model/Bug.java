package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bug extends Task {
    private Severity severity;

    public Bug( String title, String creator, String assignee, Status status, Type type, Date dueDate, Severity severity) {
        super(title,creator, assignee, status, type,dueDate, null);
        this.severity = severity;
    }
}
