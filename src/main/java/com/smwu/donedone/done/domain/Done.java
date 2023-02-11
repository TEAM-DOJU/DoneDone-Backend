package com.smwu.donedone.done.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Done {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime date;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Status status = Status.NOT_DONE;

    public Done(String title, LocalDateTime date, Category category) {
        this.title = title;
        this.date = date;
        this.category = category;
    }

    public void changeStatus() {
        if (this.status.equals(Status.DONE)) {
            this.status = Status.NOT_DONE;
            return;
        }
        this.status = Status.DONE;
    }
}
