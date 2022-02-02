package fhi360.covid.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userHealthData")
@Data
public class UserHealthData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String date;
    //    section one
    private String feverSymptom;
    private String headacheSymptom;
    private String sneezingSymptom;
    private String chestPainSymptom;
    private String bodyPainSymptom;
    private String nauseaOrVomitingSymptom;
    private String diarrhoeaSymptom;
    private String fluSymptom;
    private String soreThroatSymptom;
    private String fatigueSymptom;

    //    section two
    private String newOrWorseningCough;
    private String difficultyInBreathing;
    private String lossOfOrDiminishedSenseOfSmell;
    private String lossOfOrDiminishedSenseOfTaste;
    //section three
    private String contactWithFamily;

    private Long userId;

    private String firstname;
    private String phone;
    private String risk;

    public UserHealthData() {
    }

}