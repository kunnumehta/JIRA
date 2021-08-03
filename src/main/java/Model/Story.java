package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Story extends Task{
    private String storySummary;
    private List<Subtrack> subtracks;

    public Story( String title, String creator, String assignee, Status status, Type type, Date dateObject, String summary) {
        super( title,creator, assignee, status, type,dateObject,null);
        this.storySummary = summary;
        subtracks = new ArrayList<>();
    }
}
