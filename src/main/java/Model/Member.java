package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {
    private int memberId;
    private String memberName;
    private String memberSurname;
    private LocalDate memberBirthDate;

    public Member(int memberId, String memberName, String memberSurname, LocalDate memberBirthDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberSurname = memberSurname;
        this.memberBirthDate = memberBirthDate;
    }


    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSurname() {
        return memberSurname;
    }

    public void setMemberSurname(String memberSurname) {
        this.memberSurname = memberSurname;
    }

    public LocalDate  getMemberBirthDate() {
        return memberBirthDate;
    }

    public void setMemberBirthDate(LocalDate memberBirthDate) {
        this.memberBirthDate = memberBirthDate;
    }
}
