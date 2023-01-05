package com.parking.ronald;

import javax.persistence.*;

@Entity
@Table(name = "tuition")
public class Tuition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double fee;

    // Que columna en la tabla Tuition tiene la FK
    @JoinColumn(name = "student_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Student studentRoa;

    public Tuition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Student getStudentRoa() {
        return studentRoa;
    }

    public void setStudentRoa(Student studentRoa) {
        this.studentRoa = studentRoa;
    }
}