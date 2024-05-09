package Management.Customer;
import Models.Customer.Member;
import java.util.HashMap;
import java.util.Map;

public class MemberManagement {
    Map<Integer, Member> membersList = new HashMap<>();

    public void add(Member member) {
        membersList.put(member.getCustomerId(), member);
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

    public void updateAddress(int memberId, String newAddress) {
        Member member = membersList.get(memberId);
        if (member != null) {
            member.setAddress(newAddress);
        } else {
            System.out.println("Member with ID " + memberId + " does not exist.");
        }
    }
}
