package Management.Customer;
import Models.Customer.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MemberManagement {
    Map<Integer, Member> membersList = new HashMap<>();
//    private static MemberManagement instance;
//

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

    public Map<Integer, Member> getAll(){
        return membersList;
    }

    public void updateAddress(Member member, String newAddress) {
        if (member != null) {
            member.setAddress(newAddress);
        } else {
            System.out.println("Given member does not exist.");
        }
    }


    public void checkAndRenewMembership(int memberId) {
        Member member = membersList.get(memberId);
        if (member != null) {
            if (member.isMembershipExpired(member)) {
                System.out.println("Your membership is expired.");
                System.out.println("Would you like to renew your membership? (yes/no)");
                Scanner scanner = new Scanner(System.in);
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    member.renewMembership();
                } else {
                    System.out.println("Membership renewal declined.");
                }
            } else {
                System.out.println("Your membership is still valid.");
            }
        } else {
            System.out.println("Member with ID " + memberId + " does not exist.");
        }
    }

}
