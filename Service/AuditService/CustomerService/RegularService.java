package Service.AuditService.CustomerService;

import Management.Customer.RegularManagement;
import Models.Customer.Regular;
import Service.AuditService.WriteService;
public class RegularService {
    private RegularManagement regularManagement;

    public RegularService() {
        this.regularManagement = new RegularManagement();
    }

}
