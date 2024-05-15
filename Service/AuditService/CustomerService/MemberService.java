package Service.AuditService.CustomerService;

import Models.Customer.Member;
import Management.Customer.MemberManagement;
import Service.AuditService.WriteService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MemberService {
    private MemberManagement memberManagement;
    private static MemberService instance;

    private MemberService() {
        this.memberManagement = new MemberManagement();
    }

    public static MemberService getInstance() {
        if (instance == null) {
            synchronized (MemberService.class) {
                if (instance == null) {
                    instance = new MemberService();
                }
            }
        }
        return instance;
    }

    public Member addMember() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();

        System.out.println("Enter the first name of the member: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter the last name of the member: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter the birthday of the member (YYYY-MM-DD): ");
        String birthdayStr = scanner.nextLine();
        Date birthday = parseDate(birthdayStr);

        System.out.println("Enter the address of the member: ");
        String address = scanner.nextLine();
        System.out.println("Enter the member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the subscription date of the member (YYYY-MM-DD): ");
        String subscriptionDateStr = scanner.nextLine();
        Date subscriptionDate = parseDate(subscriptionDateStr);

        Member member = new Member(firstName, lastName, birthday, address, memberId, subscriptionDate);
        Member result = memberManagement.add(member);
        writeService.writeAction("added member");
        return result;
    }

    public Member getMember(int memberId) {
        return memberManagement.get(memberId);
    }

    public Map<Integer, Member> getAllMembers(){
        return memberManagement.getAll();
    }

    public void updateMemberAddress(int memberId, String newAddress) {
        WriteService writeService = new WriteService();

        Member member = memberManagement.get(memberId);
        if (member != null) {
            memberManagement.updateAddress(member, newAddress);
            writeService.writeAction("Member address updated");
        } else {
            System.out.println("Member with ID " + memberId + " does not exist.");
        }
    }

    public void deleteMember(int memberId) {
        WriteService writeService = new WriteService();

        memberManagement.delete(memberId);
        writeService.writeAction("Member deleted");
    }

    public void checkAndRenewMembership(int memberId) {
        WriteService writeService = new WriteService();
        Member member = memberManagement.get(memberId);
        if (member != null) {
            memberManagement.checkAndRenewMembership(memberId);
        } else {
            System.out.println("Member with ID " + memberId + " does not exist.");
        }
        writeService.writeAction("Member renewed");
    }



    private Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            return null;
        }
    }

}
