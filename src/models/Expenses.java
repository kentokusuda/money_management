package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "expenses")
@NamedQueries({
    @NamedQuery (name = "getReporCount", query = "SELECT COUNT(e) FROM Expenses AS e WHERE e.year = :year AND e.month =:month"),//カウントが0なら1-31日のデータ全部つくる
    @NamedQuery (name = "getAllData",query = "SELECT e FROM Expenses AS e WHERE e.year = :year AND e.month =:month")//表示のための取得

})

public class Expenses {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "year" , nullable = false)
    private Integer year;

    @Column(name = "month" , nullable = false)
    private Integer month;

    @Column(name = "day" , nullable = false)
    private Integer day;

    @Column(name = "expense" , nullable = false)
    private Integer expense;

    @Column(name = "remarks" , nullable = true)
    private String remarks;

    @Column(name = "update_at" , nullable = true)
    private Timestamp update_at;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }




}
