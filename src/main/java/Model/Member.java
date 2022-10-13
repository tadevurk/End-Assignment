package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member {
    int memberId;
    String memberName;
    String memberSurname;
    LocalDate memberBirthDate;

    // List of the item that received by member
    List<Item> receivedItemsByMember;

    // List of items that lent by member
    List<Item> lentItemsByMember;

    public List<Item> getReceivedItemsByMember() {
        return receivedItemsByMember;
    }

    public void setReceivedItemsByMember(List<Item> receivedItemsByMember) {
        this.receivedItemsByMember = receivedItemsByMember;
    }

    public List<Item> getLentItemsByMember() {
        return lentItemsByMember;
    }

    public void setLentItemsByMember(List<Item> lentItemsByMember) {
        this.lentItemsByMember = lentItemsByMember;
    }

    public Member(int memberId, String memberName, String memberSurname, LocalDate memberBirthDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberSurname = memberSurname;
        this.memberBirthDate = memberBirthDate;
        receivedItemsByMember = new ArrayList<>();
        lentItemsByMember = new ArrayList<>();
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

    public LocalDate getMemberBirthDate() {
        return memberBirthDate;
    }

    public void setMemberBirthDate(LocalDate memberBirthDate) {
        this.memberBirthDate = memberBirthDate;
    }
}
