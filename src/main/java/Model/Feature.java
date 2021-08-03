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
public class Feature extends Task{

    private String featureSummary;
    private Impact impact;

    public Feature( String title, String creator, String assignee, Status status, Type type, Date dueDate, String summary, Impact impact) {
        super( title,creator, assignee, status, type,dueDate, null);
        this.featureSummary = summary;
        this.impact = impact;
    }
}
