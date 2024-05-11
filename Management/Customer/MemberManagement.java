package Management.Customer;
import Models.Customer.Member;
import java.util.HashMap;
import java.util.Map;

public class MemberManagement {
    Map<Integer, Member> membersList = new HashMap<>();

    public Member add(Member member) {
        membersList.put(member.getCustomerId(), member); return member;
    }

    public void delete(int memberId) {
        if (membersList.containsKey(memberId)) {
            membersList.remove(memberId);
        } else {
            System.out.println("Member with ID " + memberId + " does not exist.");
        }
    }

    public Member get(int memberId) {
        return membersList.get(memberId);
    }

    public void updateAddress(Member member, String newAddress) {
        if (member != null) {
            member.setAddress(newAddress);
        } else {
            System.out.println("Given member does not exist.");
        }
    }
}
